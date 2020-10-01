package task4;

import util.FileUtils;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
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

        int numOfChangeableSymbol = 0;
        for (String line : lines) {
            List<Character> newChars = new ArrayList<>();
            char[] chars = line.toCharArray();
            for (char aChar : chars) {
                if (rusToEng.get(aChar) != null) {
                    if (codePhraseBits.get(numOfChangeableSymbol)) {
                        newChars.add(rusToEng.get(aChar));
                    } else {
                        newChars.add(aChar);
                    }
                    numOfChangeableSymbol++;
                } else {
                    newChars.add(aChar);
                }
            }
            resultLines.add(newChars.stream().map(Object::toString).collect(Collectors.joining()));
        }
        FileUtils.saveFile(dirPath + "/encoded4TaskFile.txt", resultLines);

    }

}
