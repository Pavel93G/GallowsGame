import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomWordGenerator {
    private List<String> word = new ArrayList<>();

    public RandomWordGenerator() {
        populateWordsListFromFile();
    }

    public String getRandomlyGeneratorWord() {
        Random random = new Random();
        return word.get(random.nextInt(word.size()));
    }

    private void populateWordsListFromFile() {
        StringBuilder stringBuilder = new StringBuilder();

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("resources/Dictionary.txt"))) {
            bufferedReader.lines().forEach(stringBuilder::append);
        } catch (FileNotFoundException e) {
            System.out.println("File not found!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String wordsSeparatedByCommaAndSpace = stringBuilder.toString();
        word.addAll(Arrays.asList(wordsSeparatedByCommaAndSpace.split(", ")));
    }
}
