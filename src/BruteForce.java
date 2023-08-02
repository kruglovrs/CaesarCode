import java.io.*;
import java.util.*;

public class BruteForce {


    public static void loadText(String inputFilePath, ArrayList<Character> alphabet) {

        List<String> commonUkrainianWords = new ArrayList<>(Arrays.asList(
                "як", "не", "на", "та", "за", "то", "що", "по", "від", "це",
                "зі", "до", "ти", "він", "вона", "ми", "ви", "вони", "із", "його",
                "її", "їм", "мене", "тебе", "йому", "їй", "нам", "вам", "їм", "мені",
                "тобі", "йому", "їй", "нам", "вам", "їм", "собі", "собі", "сам", "сама",
                "саме", "цей", "ця", "це", "ці", "той", "та", "те", "ті", "кожен",
                "кожна", "кожне", "кожні", "багато", "мало", "менше", "більше", "ніхто", "нічого",
                "ніщо", "чогось", "хтось", "щось", "кому", "кого", "чому", "що", "якісь", "інший",
                "весь", "вся", "все", "всі", "ніби", "так", "там", "тут", "ось", "але",
                "чи", "щоб", "про", "з", "перед", "після", "замість", "через", "для", "по",
                "під", "вгорі", "внизу", "навколо", "поряд", "разом", "навпроти", "попереду", "позаду", "між", "серпень",
                "концерт", "чемодан", "цвіркун", "сад"
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

                for (int allText = chars.length; allText > 0; allText--) {

                    for (int i = 0; i < chars.length; i++) {
                        char currentChar = chars[i];
                        int index = alphabet.indexOf(currentChar);

                        if (index != -1) {
                            int newIndex = (index - k + alphabet.size()) % alphabet.size();
                            chars[i] = alphabet.get(newIndex);
                        }
                    }

                String done = new String(chars);
                    int matchedWords = 0;
                    String[] words = done.split("\\s+");
                    for (String word : words) {
                        if (commonUkrainianWords.contains(word.toLowerCase())) {
                            matchedWords++;
                        }
                    }

                    if (matchedWords >= 2) {
                        System.out.println("");
                        System.out.println("Текст успішно розшифровано з ключем " + k);
                        System.out.println(done);
                        return;
                    }
                }
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
        Scanner scanner = new Scanner(System.in);

        System.out.println(alphabet);
        System.out.println(alphabet.size());

        System.out.print("Введіть шлях до вихідного файлу: ");

        String inputFilePath;

        do {
            inputFilePath = scanner.nextLine();

            File file = new File(inputFilePath);
            if (!file.exists()) {
                System.out.println("Шлях не є дійсним. Повторіть введення.");
            }
        } while (!new File(inputFilePath).exists());

        System.out.println("Спроба зламу шифру Цезаря...");
        System.out.println("Вихідний текст: ");

        loadText(inputFilePath, alphabet);
    }
}