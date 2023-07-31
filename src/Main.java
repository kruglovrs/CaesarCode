import java.io.*;
import java.util.Scanner;


public class Main {
    public static final char[] charArray = {
            ' ', '.', ',', '?', '!', '"', ':', '-', 'А', 'Б', 'В', 'Г', 'Ґ', 'Д', 'Е', 'Є', 'Ж', 'З', 'И', 'І', 'Ї',
            'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь',
            'Ю', 'Я', 'а', 'б', 'в', 'г', 'ґ', 'д', 'е', 'є', 'ж', 'з', 'и', 'і', 'ї', 'й', 'к', 'л',
            'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ь', 'ю', 'я'
    };

    public static char encryptChar(char ch, int shift) {
        int index = -1;
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ch) {
                index = i;
                break;
            }
        }

        if (shift > 0) {
            if (index != -1) {

                int newIndex = (index + shift) % charArray.length;
                return charArray[newIndex];
            } else {
                return ch;
            }
        } else if (shift < 0) {
            if (index != -1) {

                int newIndex = (index + shift + charArray.length) % charArray.length;
                return charArray[newIndex];
            } else {
            }
        }
        return ch;
    }

    public static char decryptChar(char ch, int shift) {
        int index = -1;
        for (int i = 0; i < charArray.length; i++) {
            if (charArray[i] == ch) {
                index = i;
                break;
            }
        }

        if (shift < 0) {
            if (index != -1) {

                int newIndex = (index - shift) % charArray.length;
                return charArray[newIndex];
            } else {
                return ch;
            }
        } else if (shift > 0) {
            if (index != -1) {

                int newIndex = (index - shift + charArray.length) % charArray.length;
                return charArray[newIndex];
            } else {
            }
        }
        return ch;
    }

    public static void encryptFile(String inputFilePath, String outputFilePath, int shift) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            int charCode;
            while ((charCode = reader.read()) != -1) {
                char originalChar = (char) charCode;
                char encryptedChar = encryptChar(originalChar, shift);
                writer.write(encryptedChar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void decryptFile(String inputFilePath, String outputFilePath, int shift) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {

            int charCode;
            while ((charCode = reader.read()) != -1) {
                char originalChar = (char) charCode;
                char decryptedChar = decryptChar(originalChar, shift);
                writer.write(decryptedChar);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Якщо ви хочете зашифрувати повідомлень введіть 1, якщо розшифрувати введіть 0.");
        String choice = scanner.nextLine();
        if (choice.equals("1")) {

            System.out.print("Введіть шлях до вихідного файлу: ");
            String inputFilePath;

            do {
                inputFilePath = scanner.nextLine();

                File file = new File(inputFilePath);
                if (!file.exists()) {
                    System.out.println("Шлях не є дійсним. Повторіть введення.");
                }
            } while (!new File(inputFilePath).exists());

            System.out.print("Введіть шлях до вихідного текстового файлу: ");
            String outputFilePath = scanner.nextLine();



            int shift;
            do {
                System.out.print("Введіть ключ (ціле число, додатне або від'ємне):");
                while (!scanner.hasNextInt()) {
                    System.out.println("Помилка введення! Будь ласка, введіть ціле число.");
                    scanner.next(); // Считываем некорректный ввод, чтобы очистить буфер
                }
                shift = scanner.nextInt();
            } while (shift == 0);

            encryptFile(inputFilePath, outputFilePath, shift);
            System.out.println("Файл успішно зашифрований і збережений у " + outputFilePath);
        }

        else {
            System.out.print("Введіть шлях до вихідного текстового файлу: ");
            String inputFilePath;

            do {
                inputFilePath = scanner.nextLine();

                File file = new File(inputFilePath);
                if (!file.exists()) {
                    System.out.println("Шлях не є дійсним. Повторіть введення.");
                }
            } while (!new File(inputFilePath).exists());


            System.out.print("Введіть шлях для збереження дешифрованого файлу: ");
            String outputFilePath = scanner.nextLine();

            System.out.print("Введіть ключ для шифру Цезаря (ціле число): ");
            int shift;
            do {
                System.out.print("Введіть ключ (ціле число, додатне або від'ємне):");
                while (!scanner.hasNextInt()) {
                    System.out.println("Помилка введення! Будь ласка, введіть ціле число.");
                    scanner.next(); // Считываем некорректный ввод, чтобы очистить буфер
                }
                shift = scanner.nextInt();
            } while (shift == 0);


            decryptFile(inputFilePath, outputFilePath, shift);
            System.out.println("Файл успішно разшифрований і збережений у " + outputFilePath);

        }

    }
}