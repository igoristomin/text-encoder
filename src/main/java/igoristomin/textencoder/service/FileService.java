package igoristomin.textencoder.service;

import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class FileService {

    static String getFileValue(String filePath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try(InputStream inputStream = FileService.class.getClassLoader().getResourceAsStream(filePath)) {
            doIfFileNotExist(inputStream, filePath);
            try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            }
        }
        return stringBuilder.toString();
    }

    private static void doIfFileNotExist(InputStream inputStream, String filePath) throws FileNotFoundException {
        if(inputStream == null) throw new FileNotFoundException("The " + filePath + " file does not exist.");
    }

}
