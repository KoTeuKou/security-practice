package task5;

import util.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String alphabet =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz�����Ũ���������������������������������������������������������0123456789.?!,;:-()\"/\\ ";


    public static List<String> getVigenereMatrix(String alphabet) {
        List<String> matrix = new ArrayList<>();

        char lastChar = alphabet.charAt(alphabet.length() - 1);
        alphabet.chars().forEach(c -> matrix.add(alphabet.substring(alphabet.indexOf(c), alphabet.indexOf(lastChar) + 1) +
                alphabet.substring(0, alphabet.indexOf(c))));
        return matrix;
    }

    public static void main(String[] args) throws IOException {
        List<String> vigenereMatrix = getVigenereMatrix(alphabet);
        FileUtils.saveFile("./src/main/resources/vigenerMatrix.txt", vigenereMatrix);

        Task5Decoder task5Decoder = new Task5Decoder();
        Task5Encoder task5Encoder = new Task5Encoder();

        task5Encoder.getVigenereEncrypt(vigenereMatrix, "������", "./src/main/resources/text_for_task5");
        task5Decoder.vigenereDecrypt(vigenereMatrix, "������", "./src/main/resources/encoded5TaskFile.txt");

    }
}
