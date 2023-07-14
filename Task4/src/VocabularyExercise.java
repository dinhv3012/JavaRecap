import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * A simple vocabulary exercise program that quizzes the user on English words.
 * Program displays a Swedish word and prompts the user to enter the
 * corresponding English word.
 * The user can quit the program by typing 'Q'.
 * After 10 tries, the program automatically finishes and shows statistics on
 * the correct answers.
 */
public class VocabularyExercise {
    /**
     * The main method of the program. Used to start the vocabulary exercise.
     *
     * @param args the command line arguments (not used)
     */
    public static void main(String[] args) {
        VocabularyExercise exercise = new VocabularyExercise();
        exercise.start();
    }

    /**
     * Starts the vocabulary exercise program.
     */
    public void start() {
        VocabularyLoader loader = new VocabularyLoader();
        LinkedHashMap<String, Word> vocabulary = loader.loadVocabulary("Vocabulary.txt");
        VocabularyGame game = new VocabularyGame(vocabulary);

        System.out.println("\t*** Vocabulary Exercise - ENGLISH ***\n");
        System.out.println("Write the English word. Quit the program by typing 'Q'.\n");

        Scanner scanner = new Scanner(System.in);
        int attemptCount = 0;
        int correctCount = 0;

        while (game.hasMoreWords() && attemptCount < 10) {
            Word word = game.getNextWord();
            attemptCount++;

            System.out.print(word.getSwedishWord() + " : ");
            String userInput = scanner.nextLine().trim();

            if (userInput.equalsIgnoreCase("q")) {
                break;
            }

            String result = game.checkAnswer(userInput, word);
            if (result.equals("CORRECT")) {
                correctCount++;
                printStats(correctCount, attemptCount);
            } else {
                System.out.println(getResultMessage(result, word.getEnglishWord()));
            }
        }

        scanner.close();
        printFinalStats(correctCount, attemptCount);
    }

    /**
     * Prints the current statistics of correct attempts out of total attempts.
     *
     * @param correctCount the number of correct attempts
     * @param attemptCount the total number of attempts
     */
    private void printStats(int correctCount, int attemptCount) {
        System.out.println("Correct! " + correctCount + " correct out of " + attemptCount + " word.");
    }

    /**
     * Prints the final statistics of the exercise.
     *
     * @param correctCount the number of correct attempts
     * @param attemptCount the total number of attempts
     */
    private void printFinalStats(int correctCount, int attemptCount) {
        System.out.println("You answered a total of " + attemptCount + " words and got " + correctCount +
                " correct. Goodbye and welcome back!");
    }

    /**
     * Retrieves the message corresponding to the given result.
     *
     * @param result      the result of checking an answer
     * @param englishWord the correct English word
     * @return the message associated with the result
     */
    private String getResultMessage(String result, String englishWord) {
        if (result.equals("ALMOST_CORRECT")) {
            return "Almost correct! The correct answer is: " + englishWord;
        } else {
            return "Wrong! The correct answer is: " + englishWord;
        }
    }
}

/**
 * Class responsible for loading word pairs from a file and creating a
 * vocabulary.
 */
class VocabularyLoader {
    /**
     * Loads the vocabulary from a file and creates a map of word pairs.
     *
     * @param filePath the path to the file containing the word pairs
     * @return the vocabulary map
     */
    public LinkedHashMap<String, Word> loadVocabulary(String filePath) {
        LinkedHashMap<String, Word> vocabulary = new LinkedHashMap<>();

        try {
            Scanner scanner = new Scanner(new File(filePath));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(" : ");
                if (parts.length == 2) {
                    String swedishWord = parts[0].trim();
                    String englishWord = parts[1].trim();
                    Word word = new Word(swedishWord, englishWord);
                    vocabulary.put(swedishWord.toLowerCase(), word);
                } else {
                    System.out.println("Invalid line format: " + line);
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("The file could not be found.");
        }

        return vocabulary;
    }
}

/**
 * Class representing a word pair consisting of a Swedish word and its
 * corresponding English word.
 */
class Word {
    private final String swedishWord;
    private final String englishWord;

    /**
     * Creates a new Word instance.
     *
     * @param swedishWord the Swedish word
     * @param englishWord the English word
     */
    public Word(String swedishWord, String englishWord) {
        this.swedishWord = swedishWord;
        this.englishWord = englishWord;

    }

    /**
     * Gets the Swedish word.
     *
     * @return the Swedish word
     */
    public String getSwedishWord() {
        return swedishWord;
    }

    /**
     * Gets the English word.
     *
     * @return the English word
     */
    public String getEnglishWord() {
        return englishWord;
    }
}

/**
 * Class responsible for managing the vocabulary quiz.
 */
class VocabularyGame {
    private final Iterator<Map.Entry<String, Word>> wordIterator;
    private int totalAttempts;
    private int correctAttempts;

    /**
     * Creates a new VocabularyGame instance.
     *
     * @param vocabulary the vocabulary map
     */
    public VocabularyGame(LinkedHashMap<String, Word> vocabulary) {
        this.wordIterator = vocabulary.entrySet().iterator();
        this.totalAttempts = 0;
        this.correctAttempts = 0;
    }

    /**
     * Checks if there are more words in the quiz.
     *
     * @return true if there are more words, false otherwise
     */
    public boolean hasMoreWords() {
        return wordIterator.hasNext();
    }

    /**
     * Gets the next word from the quiz.
     *
     * @return the next word
     */
    public Word getNextWord() {
        return wordIterator.next().getValue();
    }

    /**
     * Checks the user's answer against the correct word and returns the result.
     *
     * @param userInput the user's input
     * @param word      the word containing the correct answer
     * @return the result of the answer check
     */
    public String checkAnswer(String userInput, Word word) {
        totalAttempts++;

        if (userInput.equalsIgnoreCase(word.getEnglishWord())) {
            return "CORRECT";
        } else if (isAlmostCorrect(userInput, word.getEnglishWord())) {
            return "ALMOST_CORRECT";
        } else {
            return "INCORRECT";
        }
    }

    /**
     * Checks if the user's input is almost correct compared to the correct English
     * word.
     * A word should be judged as almost correct if a majority of the letters are
     * correct.
     *
     * @param userInput   the user's input
     * @param englishWord the correct English word
     * @return true if the user's input is almost correct, false otherwise
     */
    private boolean isAlmostCorrect(String userInput, String englishWord) {
        int minLength = Math.min(userInput.length(), englishWord.length());
        int correctCount = 0;

        for (int i = 0; i < minLength; i++) {
            if (Character.toLowerCase(userInput.charAt(i)) == Character.toLowerCase(englishWord.charAt(i))) {
                correctCount++;
            }
        }

        double accuracy = (double) correctCount / englishWord.length();
        return accuracy > 0.5;
    }

    /**
     * Gets the total number of attempts made.
     *
     * @return the total number of attempts
     */
    public int getTotalAttempts() {
        return totalAttempts;
    }

    /**
     * Gets the number of correct attempts made.
     *
     * @return the number of correct attempts
     */
    public int getCorrectAttempts() {
        return correctAttempts;
    }
}
