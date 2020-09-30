package task3;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.stream.Collectors;

public class Task3Encoder extends JFrame {

    public Task3Encoder() {
    }

    public void encode(String path, String phrase) throws IOException {
        Path path1 = Paths.get(path);
        List<String> lines = Files.readAllLines(path1);
        String dirPath = path1.getParent().toString();

        List<String> resultLines = new ArrayList<>();
        BitSet codePhraseBits = BitSet.valueOf(phrase.getBytes());
        int numOfWhitespace = 0;
        for (String line : lines) {
            List<Character> newChars = new ArrayList<>();
            char[] chars = line.toCharArray();
            for (char aChar : chars) {
                newChars.add(aChar);
                if (aChar == ' ') {
                    if (codePhraseBits.get(numOfWhitespace)) {
                        newChars.add(' ');
                    }
                    numOfWhitespace++;
                }
            }
            resultLines.add(newChars.stream().map(Object::toString).collect(Collectors.joining()));
        }

        File file = new File(dirPath + "/encoded3TaskFile.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (PrintWriter out = new PrintWriter(file.getAbsoluteFile())) {
                resultLines.forEach(out::println);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
