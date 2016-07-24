package shoshin.alex.utils;

public class Words {
    private static final String punctuationAtTheBegining = "^[^а-яёА-ЯЁ]*";
    private static final String punctuationAtTheEnd = "[^а-яёА-ЯЁ]*$";
    
    public static String removePunctuationMarks(String word) {
        return word.replaceAll(punctuationAtTheEnd, "").replaceAll(punctuationAtTheBegining, "");
    }
    public static boolean isRussianWord(String word) {
        return word.matches("[а-яёА-ЯЁ]+-?[а-яёА-ЯЁ]+");
    }
}