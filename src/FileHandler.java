import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.charset.StandardCharsets;

public class FileHandler {

    public static String readFile(String filePath) throws IOException {
        StringBuilder text = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                text.append(line);
            }
        }
        return text.toString();
    }

    public static void writeFile(String text,String newFilePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(newFilePath))) {
            writer.write(text);
        }
    }
}