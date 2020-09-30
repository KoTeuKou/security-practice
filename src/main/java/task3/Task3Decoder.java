package task3;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.List;

public class Task3Decoder {

    public Task3Decoder() {
    }

    public String decode(String path) throws IOException {

        List<String> lines = Files.readAllLines(Paths.get(path), Charset.forName("windows-1251"));
        int numOfWhitespace = 0;
        BitSet res = new BitSet();

        for (String line : lines) {
            char[] chars = line.toCharArray();
            for (int i = 0; i < chars.length - 1; i++) {
                if (chars[i] == ' ') {
                    if (chars[i + 1] == ' ') {
                        res.set(numOfWhitespace);
                        i++;
                    }
                    numOfWhitespace++;
                }
            }
        }
        byte[] bytes = res.toByteArray();
        return new String(bytes);
    }
}
