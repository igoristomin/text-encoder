package igoristomin.textencoder.service;

import org.springframework.stereotype.Service;
import java.util.Scanner;

@Service
public class InputService {

    public static String inputValue() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
