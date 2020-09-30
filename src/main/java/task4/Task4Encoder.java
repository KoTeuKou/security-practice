package task4;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Task4Encoder extends JFrame {

    private HashMap<Character, Character> rusToEng;

    public Task4Encoder(HashMap<Character, Character> rusToEng) {
        this.rusToEng = rusToEng;
    }

    public void encode(String path, String phrase) throws IOException {
        Path path1 = Paths.get(path);
        List<String> lines = Files.readAllLines(path1);
        String dirPath = path1.getParent().toString();

        List<String> resultLines = new ArrayList<>();
        BitSet codePhraseBits = BitSet.valueOf(phrase.getBytes());

        int numOfChangableSymbol = 0;
        for (String line : lines) {
            List<Character> newChars = new ArrayList<>();
            char[] chars = line.toCharArray();
            for (char aChar : chars) {
                if (rusToEng.get(aChar) != null) {
                    if (codePhraseBits.get(numOfChangableSymbol)) {
                        newChars.add(rusToEng.get(aChar));
                    } else {
                        newChars.add(aChar);
                    }
                    numOfChangableSymbol++;
                } else {
                    newChars.add(aChar);
                }
            }
            resultLines.add(newChars.stream().map(Object::toString).collect(Collectors.joining()));
        }

        File file = new File(dirPath + "/encoded4TaskFile.txt");
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
