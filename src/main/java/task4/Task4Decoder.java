package task4;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public String decode(String path) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(path), Charset.forName("windows-1251"));

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
        return new String(bytes);
    }
}
