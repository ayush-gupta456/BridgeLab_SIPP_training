package searchproblems;

public class FindSentenceWithWord {
    public static String findSentenceWithWord(String[] sentences, String word) {
        for (String sentence : sentences) {
            if (sentence.contains(word)) return sentence;
        }
        return "Not Found";
    }
}