package task2;

import javax.swing.*;

class Main extends JFrame {

        public static void main(String[] args) {
            SwingUtilities.invokeLater(() -> {
                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);
                new Task2Encoder();
            });
            SwingUtilities.invokeLater(() -> {
                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);
                new Task2Decoder();
            });
        }
    }

