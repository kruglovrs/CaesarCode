import java.io.*;
import java.util.*;

public class BruteForce {


    public static void loadText(String inputFilePath, ArrayList<Character> alphabet) {

        List<String> commonUkrainianWords = new ArrayList<>(Arrays.asList(
                "як", "не", "на", "та", "за", "то", "що", "по", "його", "це",
                "він", "так", "від", "якщо", "з", "але", "для", "мій", "ви", "в",
                "і", "вона", "ще", "я", "ми", "ти", "вони", "тут", "вам", "ми",
                "був", "щоб", "бути", "їх", "той", "або", "та", "щоб", "ні", "де", "вона", "де", "осінь", "твої",
                "серп", "привіт", "як", "справи"
        ));

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {

            String decryptedText = "";
            int charCode;
            while ((charCode = reader.read()) != -1) {
                char originalChar = (char) charCode;
                decryptedText += originalChar;
            }

            char[] chars = decryptedText.toCharArray();
            System.out.println(chars);

            for (int k = 1; k < alphabet.size(); k++) {

                for (int i = 0; i < chars.length; i++) {
                    char currentChar = chars[i];
                    int index = alphabet.indexOf(currentChar);

                    if (index != -1) {
                        int newIndex = (index - k + alphabet.size()) % alphabet.size();
                        chars[i] = alphabet.get(newIndex);
                    }
                    String done = new String(chars);
                    int matchedWords = 0;
                    String[] words = done.split("\\s+");
                    for (String word : words) {
                        if (commonUkrainianWords.contains(word.toLowerCase())) {
                            matchedWords++;
                        }
                    }

                    if (matchedWords >= 1) {
                        System.out.println("Текст успешно расшифрован с ключом " + k);
                        System.out.println(done);
                        return;
                    }
                }
//                System.out.println(chars);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {

        char[] ukralphabet = {
                ' ', '.', ',', '?', '!', '"', ':', '-', 'А', 'Б', 'В', 'Г', 'Ґ', 'Д', 'Е', 'Є', 'Ж', 'З', 'И', 'І', 'Ї',
                'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ь',
                'Ю', 'Я', 'а', 'б', 'в', 'г', 'ґ', 'д', 'е', 'є', 'ж', 'з', 'и', 'і', 'ї', 'й', 'к', 'л',
                'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ь', 'ю', 'я'
        };

        ArrayList<Character> alphabet = new ArrayList<>();
        for (char ch : ukralphabet) {
            alphabet.add(ch);
        }

        System.out.println(alphabet);
        System.out.println(alphabet.size());

        System.out.print("Введіть шлях до вихідного файлу: ");

        Scanner scanner = new Scanner(System.in);
        String inputFilePath = scanner.nextLine();

        System.out.println("Попытка взлома шифра Цезаря...");

        loadText(inputFilePath, alphabet);
    }
}