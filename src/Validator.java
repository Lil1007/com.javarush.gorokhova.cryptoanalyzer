import java.nio.file.Files;
import java.nio.file.Path;

public class Validator {

    public static boolean isValidKey(int key, char[] alphabet) {
        if (key < alphabet.length - 1) {
            return true;
        }

        return false;
    }

    public static boolean isFileExists(String filePath) {
        return Files.exists(Path.of(filePath));
    }
}
