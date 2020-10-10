package task4;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

class Main {

    static HashMap<Character, Character> rusToEng;

    static {
        rusToEng = new HashMap<>();
        rusToEng.put('�', 'e');
        rusToEng.put('�', 'y');
        rusToEng.put('�', 'o');
        rusToEng.put('�', 'p');
        rusToEng.put('�', 'a');
        rusToEng.put('�', 'x');
        rusToEng.put('�', 'c');
        rusToEng.put('�', 'E');
        rusToEng.put('�', 'O');
        rusToEng.put('�', 'P');
        rusToEng.put('�', 'A');
        rusToEng.put('�', 'X');
        rusToEng.put('�', 'C');
        rusToEng.put('�', 'B');
        rusToEng.put('�', 'M');
        rusToEng.put('�', 'K');
        rusToEng.put('�', 'T');
    }

        public static void main(String[] args) throws IOException {
            formatDate(new Date());
            Task4Encoder task4Encoder = new Task4Encoder(rusToEng);
            task4Encoder.encode("./src/main/resources/text_for_task4", "���");
            Task4Decoder task4Decoder = new Task4Decoder(rusToEng);
            task4Decoder.decode("./src/main/resources/encoded4TaskFile.txt");
        }

    public static String formatDate(Date originalTradeTime) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sssssss'Z'");
        String format = dateFormat.format(originalTradeTime);
        return format;
    }
    }

