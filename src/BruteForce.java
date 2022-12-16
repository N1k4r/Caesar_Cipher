import java.util.Arrays;
import java.util.List;

public class BruteForce {
    private static final List<String> WORDS = Arrays.asList("the", "for", "were", "was", "that", "will");
    public static int getKey(String textFile){
        for (int i = 1; i < 33; i++) {
            StringBuilder decryptText = new StringBuilder();

            for (int j = 0; j < textFile.length(); j++)
                decryptText.append(Alphabet.symbolShift(textFile.charAt(j), -i));

            String[] words = decryptText.toString().split("\\W");
            for (String word : words)
                if (WORDS.contains(word.toLowerCase()))
                    return -i;
        } return 0;
    }
}
