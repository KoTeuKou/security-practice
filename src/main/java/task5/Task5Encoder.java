package task5;

import util.FileUtils;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;


public class Task5Encoder {

    public Task5Encoder() {
    }

    public void getVigenereEncrypt(List<String> matrix, String keyWord, String path) throws IOException {

        Path path1 = Paths.get(path);
        String text = FileUtils.readFileToString(path1);
        String dirPath = path1.getParent().toString();

        int posKeyWord = 0;
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            int positionLetter = matrix.get(0).indexOf(text.charAt(i));
            StringBuilder firstLetterInStr = new StringBuilder();
            matrix.forEach(str -> firstLetterInStr.append(str.charAt(0)));
            int positionKeyLetter = firstLetterInStr.indexOf(Character.toString(keyWord.charAt(posKeyWord)));
            result.append(matrix.get(positionKeyLetter).charAt(positionLetter));

            posKeyWord++;
            if (posKeyWord >= keyWord.length()) {
                posKeyWord = 0;
            }
        }
        FileUtils.saveFile(dirPath + "/encoded5TaskFile.txt", Collections.singletonList(result.toString()));
    }

}
