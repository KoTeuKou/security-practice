package task6;

import util.FileUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String alphabet =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyzÀÁÂÃÄÅ¨ÆÇÈÊËÌÍÎÏĞÑÒÓÔÕÖ×ØÙÚÛÜİŞßàáâãäå¸æçèéêëìíîïğñòóôõö÷øùúûüışÿ0123456789.?!,;:-()\"/\\ ";


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

        Task6Decoder task6Decoder = new Task6Decoder();
        Task6Encoder task6Encoder = new Task6Encoder();

        task6Encoder.encrypt(vigenereMatrix, "êîòÿğà", "./src/main/resources/testDirectory");
        task6Decoder.decrypt(vigenereMatrix, "êîòÿğà", "./src/main/resources/encoded6TaskFile.cat", "C:/Users/Koteuko/Desktop");

    }
}
