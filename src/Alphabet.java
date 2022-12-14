public class Alphabet {
    final private static char[] alphabet = "abcdefghijklmnopqrstuvwxyz.,\":!? ".toCharArray();

    private int getPosition(char symbol) {
        for (int i = 0; i < alphabet.length; i++)
            if (alphabet[i] == Character.toLowerCase(symbol))
                return i;
        return -1;
    }

    public char symbolShift(char symbol, int shift){
        if (Character.isDigit(symbol)){
            int number = Integer.parseInt(String.valueOf(symbol)) + shift;
            return (char) (((10 + number % 10) % 10) + '0');
        }
        if(getPosition(symbol) != -1) {
            char symbolShift = alphabet[(alphabet.length + getPosition(symbol) + shift) % alphabet.length];
            return Character.isUpperCase(symbol) ? Character.toUpperCase(symbolShift) : symbolShift;
        }
        return symbol;
    }

}
