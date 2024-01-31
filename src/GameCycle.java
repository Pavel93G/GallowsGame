import java.util.Scanner;

public class GameCycle {
    private final HangmanDrawer hangmanDrawer = new HangmanDrawer();
    private final RandomWordGenerator randomWordGenerator = new RandomWordGenerator();
    private final WordMasksOperator wordMasksOperator = new WordMasksOperator();

    public void start() {
        Scanner scanner = new Scanner(System.in);
        String option;
        int mistakesCount;

        while (true) {
            System.out.println("Меню: [Н]овая игра / [В]ыход из игры");
            option = scanner.nextLine();

            if (option.equalsIgnoreCase("Н")) {
                mistakesCount = 0;
                wordMasksOperator.clearBuffer();
                hangmanDrawer.clearDrawing();
                String letter;
                String guessedWord = randomWordGenerator.getRandomlyGeneratorWord();
                wordMasksOperator.setWord(guessedWord);
                System.out.println("Случайное слово загадано!");
                wordMasksOperator.printMask();

                while (!wordMasksOperator.userWon()) {
                    System.out.println("Введите букву: ");
                    letter = scanner.nextLine();

                    if (wordMasksOperator.isLetterAlreadyUsed(letter)) {
                        System.out.printf("Вы уже используете букву '%s'!\n", letter);
                    } else {
                        wordMasksOperator.useUserInputLetter(letter);
                        if (wordMasksOperator.containsLetter(letter)) {
                            System.out.println("Вы угадали !");
                            System.out.print("Слово: ");
                            wordMasksOperator.updateMask(letter);
                            wordMasksOperator.printMask();
                        } else {
                            System.out.println("Вы не угадали !");
                            mistakesCount++;
                            System.out.printf("Ошибок допущено: %s/5\n", mistakesCount);
                            hangmanDrawer.updateHangmanDrawingMatrix(mistakesCount);
                            hangmanDrawer.printHangman();
                        }
                    }

                    if (mistakesCount == 5) {
                        System.out.println("Вы проиграли !");
                        System.out.printf("Слово которое вы не угадали: %s \n", guessedWord);
                        break;
                    } else if (wordMasksOperator.userWon()) {
                        System.out.println("Вы победили! Поздравляем!");
                    }
                }
            } else if (option.equalsIgnoreCase("В")) {
                System.out.println("Пока !");
                System.exit(0);
            } else {
                System.out.println("Вы ввели неправильную команду, попробуйте еще раз !");
            }
        }
    }
}
