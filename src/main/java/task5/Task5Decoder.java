package task5;

import util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class Task5Decoder {

    public Task5Decoder() {
    }
    
    public void vigenereDecrypt(List<String> matrix, String keyWord, String path) throws IOException {

        Path path1 = Paths.get(path);
        List<String> lines = Files.readAllLines(path1, Charset.forName("windows-1251"));
        String encrypt = String.join("", lines);
        String dirPath = path1.getParent().toString();

        int posKeyWord = 0;
        StringBuilder result = new StringBuilder();

        StringBuilder firstLetterInStr = new StringBuilder();
        matrix.forEach(str -> firstLetterInStr.append(str.charAt(0)));

        for (int i = 0; i < encrypt.length(); i++) {
            int posKeyInList = firstLetterInStr.indexOf(Character.toString(keyWord.charAt(posKeyWord)));
            int posLetterChange = matrix.get(posKeyInList).indexOf(encrypt.charAt(i));
            result.append(matrix.get(0).charAt(posLetterChange));

            posKeyWord++;
            if (posKeyWord >= keyWord.length()) {
                posKeyWord = 0;
            }
        }
        FileUtils.saveFile(dirPath + "/decoded5TaskFile.txt", Collections.singletonList(result.toString()));
    }
}
