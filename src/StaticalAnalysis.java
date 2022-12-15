public class StaticalAnalysis {
    public static int getKey(String file){
        int maxValue = 0;
        int[] numberOfLetters = new int[Alphabet.ALPHABET.length];
        for (int i = 0; i < file.length(); i++) {
            for (int j = 0; j < Alphabet.ALPHABET.length; j++) {
                if (file.charAt(i) == Alphabet.ALPHABET[j])
                    if (++numberOfLetters[j] > numberOfLetters[maxValue])
                        maxValue = j;
            }
        }
        return -(maxValue + 1);
    }
}
