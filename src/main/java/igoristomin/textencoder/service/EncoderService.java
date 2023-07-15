package igoristomin.textencoder.service;

import org.springframework.stereotype.Service;
import java.io.IOException;
import static igoristomin.textencoder.repository.HeadRepository.HEAD_DECODE;
import static igoristomin.textencoder.repository.HeadRepository.HEAD_ENCODE;
import static igoristomin.textencoder.service.FileService.getFileValue;
import static igoristomin.textencoder.service.InputService.inputValue;
import static igoristomin.textencoder.service.PrintService.*;

@Service
class EncoderService {

    private static int alphabetLength;
    private static int keyLength;
    private static char[] alphabetArr;
    private static char[] keyArr;
    private static final String entryMessage = "Enter the text: ";

    static {
        try {
            // Get values from files
            getFileValues();
            // Execute if the key length does not match the alphabet length
            doIfLengthKeyNotMatchAlphabet(keyLength, alphabetLength);
        } catch(IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static void getFileValues() throws IOException {
        // Get values from files
        String alphabet = getFileValue("alphabet.txt");
        String key = getFileValue("key.txt");

        // Get lengths of values
        alphabetLength = alphabet.length();
        keyLength = key.length();

        // Get arrays of symbols from values
        alphabetArr = alphabet.toCharArray();
        keyArr = key.toCharArray();
    }

    public static void encode() {
        while(true) {
            // Print the menu
            printHead(HEAD_ENCODE);
            printBackClose();

            // Print a message and get the entered value into the text
            System.out.print(entryMessage);
            String text = inputValue();

            // Execute if the text is equal to the CMD_BACK or CMD_CLOSE
            if(doIfBack(text)) break;
            doIfClose(text);

            // Get the length of the text and convert the text to an array of symbols
            int textLength = text.length();
            char[] textArr = text.toCharArray();

            // Encode text
            char[] textResultArr = new char[textLength];
            for(int iText = 0; iText < textLength; iText++) {
                // Replace the text symbol with the key symbol according to the alphabet
                boolean symbolFound = false;
                for(int iAlphabet = 0; iAlphabet < alphabetLength; iAlphabet++) {
                    if(textArr[iText] == alphabetArr[iAlphabet]) {
                        textResultArr[iText] = keyArr[iAlphabet];
                        symbolFound = true;
                    }
                }
                // Execute if the symbol is not found
                doIfSymbolNotFound(symbolFound, textResultArr, textArr, iText);
            }

            // Print the result
            printResult(textResultArr, "Encoded text: ");
        }
    }

    public static void decode() {
        while(true) {
            // Print the menu
            printHead(HEAD_DECODE);
            printBackClose();

            // Print a message and get the entered value into the text
            System.out.print(entryMessage);
            String text = inputValue();

            // Get the length of the text and convert the text to an array of symbols
            int textLength = text.length();
            char[] textArr = text.toCharArray();

            // Execute if the text is equal to the CMD_BACK or CMD_CLOSE
            if(doIfBack(text)) break;
            doIfClose(text);

            // Decode text
            char[] textResultArr = new char[textLength];
            for(int iText = 0; iText < textLength; iText++) {
                // Replace the text symbol with the alphabet symbol according to the key
                boolean symbolFound = false;
                for(int iAlphabet = 0; iAlphabet < alphabetLength; iAlphabet++) {
                    if(textArr[iText] == keyArr[iAlphabet]) {
                        textResultArr[iText] = alphabetArr[iAlphabet];
                        symbolFound = true;
                    }
                }
                // Execute if the symbol is not found
                doIfSymbolNotFound(symbolFound, textResultArr, textArr, iText);
            }

            // Print the result
            printResult(textResultArr, "Decoded text: ");
        }
    }

    private static void doIfLengthKeyNotMatchAlphabet(int keyLength, int alphabetLength) throws IOException {
        if(keyLength != alphabetLength) {
            throw new IOException("The length of the key does not match the length of the alphabet.");
        }
    }

    private static void doIfSymbolNotFound(boolean symbolFound, char[] textResultArr, char[] textArr, int iText) {
        if(!symbolFound) textResultArr[iText] = textArr[iText];
    }

}
