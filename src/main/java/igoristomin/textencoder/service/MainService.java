package igoristomin.textencoder.service;

import org.springframework.stereotype.Service;
import static igoristomin.textencoder.repository.CmdRepository.*;
import static igoristomin.textencoder.repository.HeadRepository.HEAD_DECODE;
import static igoristomin.textencoder.repository.HeadRepository.HEAD_ENCODE;
import static igoristomin.textencoder.service.EncoderService.decode;
import static igoristomin.textencoder.service.EncoderService.encode;
import static igoristomin.textencoder.service.InputService.inputValue;
import static igoristomin.textencoder.service.PrintService.*;

@Service
public class MainService {

    public static void run() {
        String cmd;
        boolean execute = true;

        while(execute) {
            // Print the menu
            printHead("Text Encoder");
            printCmdDescription(CMD_ENCODE, HEAD_ENCODE);
            printCmdDescription(CMD_DECODE, HEAD_DECODE);
            printBorder("thin");
            System.out.println(NOTE_CLOSE);
            printBorder("thin");

            // Print a message and get the entered value into the cmd
            System.out.print("Enter the command: ");
            cmd = inputValue();

            // Check the cmd and perform the following actions
            switch(cmd) {
                case CMD_ENCODE -> {
                    printDoneCase(CMD_ENCODE);
                    encode();
                }

                case CMD_DECODE -> {
                    printDoneCase(CMD_DECODE);
                    decode();
                }

                case CMD_CLOSE -> execute = false;

                default -> {
                    System.out.println("The command is invalid!");
                    printBorder("fat");
                }
            }

            // Execute if the cmd is equal to the CMD_CLOSE
            doIfClose(cmd);
        }
    }

}
