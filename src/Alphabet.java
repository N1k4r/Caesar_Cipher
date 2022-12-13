public class Alphabet {
    final private static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    final private static char[] punctuation = ".,\":!? ".toCharArray();

    public int getPosition(char symbol, char[] array) {
        for (int i = 0; i < array.length; i++)
            if (array[i] == Character.toLowerCase(symbol))
                return i;
        return -1;
    }

    public char symbolShift(char symbol, int shift){
        if (Character.isDigit(symbol)){
            int number = Integer.parseInt(String.valueOf(symbol)) + shift;
            return (char) (((10 + number % 10) % 10) + '0');
        }
        if(Character.isLetter(symbol)) {
            char symbolShift = alphabet[(26 + getPosition(symbol, alphabet) + shift) % 26];
            return Character.isUpperCase(symbol) ? Character.toUpperCase(symbolShift) : symbolShift;
        }
        if (getPosition(symbol, punctuation) != -1)
            return punctuation[(7 + getPosition(symbol, punctuation) + shift % 7) % 7];

        return symbol;
    }

}
