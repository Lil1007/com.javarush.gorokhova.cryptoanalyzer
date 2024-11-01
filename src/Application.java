import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Application {
    private static final char[] ALPHABET = {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    public static void main(String[] args) throws IOException {
        while (true) {
            System.out.println("ШИФРОВАНИЕ МЕТОДОМ ЦЕЗАРЯ");
            System.out.println("Выберите пункт меню:");
            System.out.println("1. Шифрование");
            System.out.println("2. Расшифровка с ключом");
            System.out.println("3. Brute force");
            System.out.println("0. Выход");

            Scanner console = new Scanner(System.in);
            int value = console.nextInt();

            Cipher cipher = new Cipher(ALPHABET);
            int key = 0;
            String path = "";

            if (value == 1 || value == 2) {
                System.out.println("Введите ключ: ");
                key = console.nextInt();

                console.nextLine(); // Очищаем буфер Scanner

                if (!Validator.isValidKey(key, cipher.getAlphabet())) {
                    System.out.println("Значение ключа слишком большое! Максимальное значение - " + (cipher.getAlphabet().length - 1));
                    continue;
                }
            }

            if (value != 0){
                System.out.println("Введите путь к файлу: ");
                path = console.nextLine();
                if (!Validator.isFileExists(path)) {
                    System.out.println("Такого файла не существует!");
                    continue;
                }
            }


            switch (value) {
                case 0:
                    System.out.println("Программа завершена.");
                    return;
                case 1:
                    try {
                        String fileText = FileHandler.readFile(path);

                        fileText = cipher.encrypt(fileText, key);

                        FileHandler.writeFile(fileText, "new_file_encrypt.txt");

                        System.out.println("Файл успешно зашифрован!");
                    } catch (IOException e) {
                        System.err.println("Ошибка при обработке файлов: " + e.getMessage());
                    }

                    continue;

                case 2:
                    try {
                        String fileText = FileHandler.readFile(path);

                        fileText = cipher.decrypt(fileText, key);

                        FileHandler.writeFile(fileText, "new_file_decrypt.txt");

                        System.out.println("Файл успешно расшифрован!");
                    } catch (IOException e) {
                        System.err.println("Ошибка при обработке файлов: " + e.getMessage());
                    }

                    continue;
                case 3:
                    try {
                        String fileText = FileHandler.readFile(path);

                        fileText = cipher.bruteForce(fileText);

                        FileHandler.writeFile(fileText, "new_file_brute_force.txt");

                        System.out.println("Файл успешно расшифрован!");
                    } catch (IOException e) {
                        System.err.println("Ошибка при обработке файлов: " + e.getMessage());
                    }

                    continue;
            }
        }
    }
}
