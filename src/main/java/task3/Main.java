package task3;

import java.io.IOException;

class Main {

        public static void main(String[] args) throws IOException {
            Task3Encoder task3Encoder = new Task3Encoder();
            task3Encoder.encode("./src/main/resources/text_for_task3", "кошка");
            Task3Decoder task3Decoder = new Task3Decoder();
            System.out.println(task3Decoder.decode("./src/main/resources/encoded3TasFile.txt"));
        }
    }

