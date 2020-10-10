package task6;

import util.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;


public class Task6Encoder {

    public Task6Encoder() {
    }


    public void encrypt(List<String> matrix, String keyWord, String root) throws IOException {
        File rootDir = new File(root);
        ArrayList<String> result = new ArrayList<>();
        PriorityQueue<File> fileTree = new PriorityQueue<>();
        String dirPath = Paths.get(root).getParent().toString();
        Collections.addAll(fileTree, Objects.requireNonNull(rootDir.listFiles()));

        while (!fileTree.isEmpty()) {
            File currentFile = fileTree.remove();
            if (currentFile.isDirectory()) {
                Collections.addAll(fileTree, Objects.requireNonNull(currentFile.listFiles()));
            } else {
                String absolutePath = currentFile.getAbsolutePath();
                result.add(absolutePath);
            }
        }
        List<String> encryptedStrings = new ArrayList<>();
        encryptedStrings.add(getVigenereEncrypt(matrix, keyWord, root, true));

        result.forEach(s -> {
            try {
                encryptedStrings.add(getVigenereEncrypt(matrix, keyWord, s, true));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            try {
                encryptedStrings.add(getVigenereEncrypt(matrix, keyWord, s, false));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        FileUtils.saveFile(dirPath + "/encoded6TaskFile.cat", Collections.singletonList(String.join("~", encryptedStrings)));
    }


    public String getVigenereEncrypt(List<String> matrix, String keyWord, String path, Boolean isNameOfFileOrDirectory) throws IOException {

        Path path1 = Paths.get(path);
        String name = path1.getFileName().toString();
        String text;
        if (isNameOfFileOrDirectory) {
            String separator = "/";
            String replace = path.replace('\\', '/');
            String[] split = replace.split(Pattern.quote(separator));
            int index = 0;
            for (int i = 0; i < split.length; i++) {
                index = i;
                if (split[i].equals(name)){
                    break;
                }
            }
            text = (String.join("\\", Arrays.copyOfRange(split, index , split.length)));
        } else {
            text = FileUtils.readFileToString(path1);
        }

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
        return result.toString();
    }

}
