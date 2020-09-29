import javax.swing.*;

class Main extends JFrame {

        public static void main(String[] args) {
            javax.swing.SwingUtilities.invokeLater(() -> {
                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);
                new Task2Encoder();
            });
            javax.swing.SwingUtilities.invokeLater(() -> {
                JFrame.setDefaultLookAndFeelDecorated(true);
                JDialog.setDefaultLookAndFeelDecorated(true);
                new Task2Decoder();
            });
        }
    }

