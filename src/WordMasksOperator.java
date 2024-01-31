import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class WordMasksOperator {
    private String word;
    private String[] mask;
    private final Set<String> usedLetters = new HashSet<>(); //хранит уже использованные буквы
    private final Set<String> wordUniqueLetters = new HashSet<>(); //хранит уникальные буквы загаданного слова
    private int numberOfGuessedLetters = 0;

    public void setWord(String word) {
        this.word = word;
        this.mask = new String[word.length()];
        Arrays.fill(mask, "*");
        Collections.addAll(wordUniqueLetters, word.split(""));
    }

    public void printMask() {
        System.out.println(String.join("", mask));
    }

    public void updateMask(String letter) {
        for (int i = 0; i < word.length(); i++) {
            if (Character.toString(word.charAt(i)).equals(letter)) {
                mask[i] = letter;
            }
        }

        numberOfGuessedLetters++;
    }

    public boolean containsLetter(String letter) {
        return wordUniqueLetters.contains(letter);
    }

    public void useUserInputLetter(String letter) {
        usedLetters.add(letter);
    }
    public boolean userWon() {
        return numberOfGuessedLetters == wordUniqueLetters.size();
    }

    public boolean isLetterAlreadyUsed(String letter) {
        return usedLetters.contains(letter);
    }

    public void clearBuffer() {
        usedLetters.clear();
        wordUniqueLetters.clear();
        numberOfGuessedLetters = 0;
    }
}
