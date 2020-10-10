package task6;

import util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task6Decoder {

    public Task6Decoder() {
    }
    
    public String vigenereDecrypt(List<String> matrix, String keyWord, String encrypt) throws IOException {

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
        return result.toString();
    }

    public void decrypt(List<String> vigenereMatrix, String keyword, String archivePath, String unzipPath) throws IOException {
        String stringOfEncryptedDirectory = FileUtils.readFileToString(Paths.get(archivePath));
        String[] strings = stringOfEncryptedDirectory.split("~");
        ArrayList<String> decryptedStrings = new ArrayList<>();
        for (String string : strings) {
            decryptedStrings.add(vigenereDecrypt(vigenereMatrix, keyword, string));
        }
        String separator = "/";
        String pathname = unzipPath + separator+ decryptedStrings.get(0);
        File rootDir = new File(pathname);
        boolean isCreated = rootDir.mkdir();
        if (isCreated) {
            for (int i = 1; i < decryptedStrings.size() - 1; i += 2) {
                String[] split = decryptedStrings.get(i).split(separator);
                StringBuilder pathOfFile = new StringBuilder(separator);
                for (int j = 0; j < split.length - 1; j ++) {
                    pathOfFile.append(split[j]);
                    new File(pathname + pathOfFile).mkdir();
                    pathOfFile.append(separator);
                }
                FileUtils.saveFile(pathname + separator + decryptedStrings.get(i), Collections.singletonList(decryptedStrings.get(i + 1)));
            }
        }
    }
}
