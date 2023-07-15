package igoristomin.textencoder.service;

import org.springframework.stereotype.Service;
import static igoristomin.textencoder.repository.CmdRepository.CMD_BACK;
import static igoristomin.textencoder.repository.CmdRepository.CMD_CLOSE;
import static igoristomin.textencoder.repository.HeadRepository.HEAD_BACK;
import static igoristomin.textencoder.repository.HeadRepository.HEAD_CLOSE;

@Service
class PrintService {

    private static final String arrowSeparator = " -> ";
    public static final String NOTE_CLOSE = CMD_CLOSE + arrowSeparator + HEAD_CLOSE;

    public static void printHead(String text) {
        System.out.println("\n======== " + text + " ========");
    }

    public static void printDoneCase(String cmd) {
        System.out.println("Done " + cmd);
        printBorder("fat");
    }

    public static void printBackClose() {
        System.out.print(CMD_BACK + arrowSeparator + HEAD_BACK + " | " + NOTE_CLOSE + "\n");
        printBorder("thin");
    }

    public static boolean doIfBack(String cmd) {
        if(cmd.equals(CMD_BACK)) {
            printDoneCase(cmd);
            return true;
        }
        return false;
    }

    public static void doIfClose(String cmd) {
        if(cmd.equals(CMD_CLOSE)) {
            printDoneCase(cmd);
            System.exit(0);
        }
    }

    public static void printBorder(String type) {
        switch(type) {
            case "thin" -> System.out.println("--------------------------------");
            case "fat" -> System.out.println("================================");
        }
    }

    public static void printResult(char[] textResultArr, String message) {
        String textResult = String.join("", String.valueOf(textResultArr));
        System.out.println(message + textResult);
        printBorder("fat");
    }

    public static void printCmdDescription(String cmd, String header) {
        System.out.println(cmd + arrowSeparator + header);
    }

}
