package task1;

import task2.Task2Decoder;
import task2.Task2Encoder;

import javax.swing.*;

class Main extends JFrame {

        public static void main(String[] args) {
            javax.swing.SwingUtilities.invokeLater(() -> {
                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);
                new Task1();
            });
        }
    }

