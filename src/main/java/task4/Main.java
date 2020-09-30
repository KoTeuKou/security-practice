package task4;

import java.io.IOException;
import java.util.HashMap;

class Main {

    static HashMap<Character, Character> rusToEng;

    static {
        rusToEng = new HashMap<>();
        rusToEng.put('е', 'e');
        rusToEng.put('у', 'y');
        rusToEng.put('о', 'o');
        rusToEng.put('р', 'p');
        rusToEng.put('а', 'a');
        rusToEng.put('х', 'x');
        rusToEng.put('с', 'c');
        rusToEng.put('Е', 'E');
        rusToEng.put('О', 'O');
        rusToEng.put('Р', 'P');
        rusToEng.put('А', 'A');
        rusToEng.put('Х', 'X');
        rusToEng.put('С', 'C');
        rusToEng.put('В', 'B');
        rusToEng.put('М', 'M');
        rusToEng.put('К', 'K');
        rusToEng.put('Т', 'T');
    }

        public static void main(String[] args) throws IOException {

            Task4Encoder task4Encoder = new Task4Encoder(rusToEng);
            task4Encoder.encode("./src/main/resources/text_for_task4", "кот");
            Task4Decoder task4Decoder = new Task4Decoder(rusToEng);
            System.out.println(task4Decoder.decode("./src/main/resources/encoded4TaskFile.txt"));
        }
    }

