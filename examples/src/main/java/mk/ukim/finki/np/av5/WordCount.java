package mk.ukim.finki.np.av5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Argument: examples/data/quotes.txt
 */
public class WordCount {

    public static void main(String[] args) throws IOException {
        StringBuilder result = new StringBuilder();
        if (args.length == 0) {
            System.out.println("Provide filename as argument");
            return;
        }
        for (String fileName : args) {
            try {
                long start = System.nanoTime();
                //String wordCount = processFile(fileName);
                //String wordCount = processFile(fileName);
                FileStat wordCount = pureFunctional(fileName);
                long end = System.nanoTime();
                System.out.printf("Took: %.3fms\n", (end - start) / 1000000.);
                result.append(String.format("%s -> %s\n", fileName, wordCount));
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println(result.toString());
    }

    /**
     * Solution using {@link BufferedReader} reading line by line
     */
    private static String processFile(String fileName) throws IOException {
        int linesCount = 0;
        int wordsCount = 0;
        int charactersCount = 0;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                linesCount++;
                String[] words = line.split("\\s+");
                wordsCount += words.length;
                charactersCount += line.length() + 1;
            }
        }
        return String.format("%d %d %d", linesCount, wordsCount, charactersCount);
    }

    /**
     * Solution using {@link Consumer< FileCounts >} function
     */
    private static String processWithConsumer(String fileName) throws IOException {
        FileCounts fileCounts = new FileCounts();
        Files.lines(Paths.get(fileName))
                .forEach(fileCounts);
        return fileCounts.toString();
    }

    /**
     * Solution using map-reduce and long[] array as data holder
     */
    private static String processWithMapReduce(String fileName) throws IOException {
        Pattern word = Pattern.compile("\\s+");
        long[] result = Files.lines(Paths.get(fileName))
                .map(line -> {
                    long words = word.split(line).length;
                    return new long[]{1, words, line.length() + 1};
                })
                .reduce(new long[]{0, 0, 0},
                        (left, right) -> {
                            long[] sum = new long[3];
                            Arrays.setAll(sum, i -> left[i] + right[i]);
                            return sum;
                        });
        return Arrays.stream(result)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));

    }

    private static FileStat pureFunctional(String fileName) throws IOException {
        return Files.lines(Paths.get(fileName))
                .map(new WordCounter())
                .reduce(FileStat.identity(), FileStat::add);
    }

    static class FileCounts implements Consumer<String> {
        private static final Pattern WORD = Pattern.compile("\\s+");
        long lines;
        long chars;
        long words;

        public FileCounts() {
            this.lines = 0;
            this.chars = 0;
            this.words = 0;
        }

        @Override
        public void accept(String line) {
            ++lines;
            this.chars += line.length() + 1; // + the \n character
            this.words += WORD.split(line).length;
        }

        @Override
        public String toString() {
            return String.format("%d %d %d", lines, words, chars);
        }
    }

    static class FileStat {
        private final long lines;
        private final long chars;
        private final long words;

        FileStat(long lines, long chars, long words) {
            this.lines = lines;
            this.chars = chars;
            this.words = words;
        }

        static FileStat identity() {
            return new FileStat(0, 0, 0);
        }

        FileStat add(FileStat stat) {
            return new FileStat(this.lines + stat.lines, this.chars + stat.chars, this.words + stat.words);
        }

        @Override
        public String toString() {
            return String.format("%d %d %d", lines, words, chars);
        }
    }

    static class WordCounter implements Function<String, FileStat> {
        private static final Pattern WORD = Pattern.compile("\\s+");

        @Override
        public FileStat apply(String line) {
            return new FileStat(1, line.length() + 1, WORD.split(line).length);
        }
    }

}
