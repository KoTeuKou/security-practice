package util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileUtils {

    public static void saveFile(String path, List<String> resultLines) {
        File file = new File(path);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (PrintWriter out = new PrintWriter(file.getAbsoluteFile())) {
                resultLines.forEach(out::println);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readFileToString(Path path) throws IOException {
        List<String> lines = Files.readAllLines(path, Charset.forName("windows-1251"));
        return String.join("", lines);
    }
}
