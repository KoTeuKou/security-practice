package task4;

import util.FileUtils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Task4Decoder {

    private Map<Character, Character> rusToEng;

    private Map<Character, Character> engToRus;

    public Task4Decoder(HashMap<Character, Character> rusToEng) {
        this.rusToEng = rusToEng;
        this.engToRus = rusToEng.entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey));
    }

    public void decode(String path) throws IOException {

        Path path1 = Paths.get(path);
        List<String> lines = Files.readAllLines(path1, Charset.forName("windows-1251"));

        BitSet res = new BitSet();
        int numOfChangableSymbol = 0;
        for (String line : lines) {
            char[] chars = line.toCharArray();
            for (char aChar : chars) {
                if (rusToEng.get(aChar) != null || engToRus.get(aChar) != null) {
                    if (engToRus.get(aChar) != null) {
                        res.set(numOfChangableSymbol);
                    }
                    numOfChangableSymbol++;
                }
            }
        }

        byte[] bytes = res.toByteArray();
        FileUtils.saveFile(path1.getParent().toString() + "/decoded4TaskFile.txt", Collections.singletonList(new String(bytes)));

    }
}
