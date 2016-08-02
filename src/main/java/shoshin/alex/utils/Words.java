package shoshin.alex.utils;

public class Words {
    private static final String punctuationAtTheBeginning = "^[^а-яёА-ЯЁ]*";
    private static final String punctuationAtTheEnd = "[^а-яёА-ЯЁ]*$";
    private static final String russianWord = "[а-яёА-ЯЁ]+-?[а-яёА-ЯЁ]+";
    
    public static String removePunctuationMarks(String word) {
        return word.replaceAll(punctuationAtTheEnd, "").replaceAll(punctuationAtTheBeginning, "");
    }
    public static boolean isRussianWord(String word) {
        return word.matches(punctuationAtTheBeginning + russianWord + punctuationAtTheEnd);
    }
}