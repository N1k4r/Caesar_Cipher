import java.util.HashMap;

public class Alphabet {
    static char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    //TwoWayMap
    final private HashMap<Integer, Character> keyNumber = new HashMap<>();
    final private HashMap<Character, Integer> keyLetter = new HashMap<>();

    public Alphabet(){
        for (int i = 0; i < 26; i++)
            setValue(i, alphabet[i]);
    }

    public char getSymbol(int position) {
        return keyNumber.get(position);
    }

    public void setValue(int x, char y) {
        this.keyNumber.put(x, y);
        this.keyLetter.put(y, x);
    }

    public int getPosition(char symbol) {
        return Character.toLowerCase(keyLetter.get(symbol));
    }

    public char symbolShift(char symbol, int shift){
        if (Character.isDigit(symbol)){
            int number = Integer.parseInt(String.valueOf(symbol)) + shift;
            return (char) (((10 + number % 10) % 10) + '0');
        }
        if(Character.isUpperCase(symbol))
            return Character.toUpperCase((getSymbol((26 + (getPosition(Character.toLowerCase(symbol)) + shift)) % 26)));
        else if (Character.isLowerCase(symbol))
            return (getSymbol((26 + (getPosition(Character.toLowerCase(symbol)) + shift)) % 26));

        else return symbol;
    }

}
