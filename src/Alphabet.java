public class Alphabet {
    public static final char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz.,\":!? ".toCharArray();

    private static int getPosition(char symbol) {
        for (int i = 0; i < ALPHABET.length; i++)
            if (ALPHABET[i] == Character.toLowerCase(symbol))
                return i;
        return -1;
    }

    public static char symbolShift(char symbol, int shift){
        if (Character.isDigit(symbol)){
            int number = Integer.parseInt(String.valueOf(symbol)) + shift;
            return (char) (((10 + number % 10) % 10) + '0');
        }
        if(getPosition(symbol) != -1) {
            char symbolShift = ALPHABET[(ALPHABET.length + getPosition(symbol) + shift) % ALPHABET.length];
            return Character.isUpperCase(symbol) ? Character.toUpperCase(symbolShift) : symbolShift;
        }
        return symbol;
    }

}
