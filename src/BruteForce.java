public class BruteForce {
    private final char[] punctuation = ".,:!?".toCharArray();
    public boolean autoKey(String text){
        text = text.substring(0 , text.indexOf("\r\n"));
        return rule1(text) & rule2(text);
    }


    private boolean rule1(String text){
        boolean rule = true;
        for (int i = 0; i < text.length() - 1; i++)
            for (char mark : punctuation)
                if (text.charAt(i) == mark)
                    if (text.charAt(i + 1) != ' ')
                        return false;
        return rule;
    }

    private boolean rule2(String text){
        return text.endsWith(".") || text.endsWith("!") || text.endsWith("?");
    }
}
