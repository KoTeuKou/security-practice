package task2;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Task2Decoder extends JFrame {

    private JLabel codePhrase;

    public Task2Decoder() {
        super("Second task. Decoder");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalGlue());

        final JLabel nameOfFile = new JLabel();
        nameOfFile.setVisible(false);
        nameOfFile.setAlignmentX(CENTER_ALIGNMENT);

        final JLabel text = new JLabel();
        text.setVisible(false);
        text.setAlignmentX(CENTER_ALIGNMENT);
        AtomicReference<List<String>> allLines = new AtomicReference<>();

        panel.add(Box.createRigidArea(new Dimension(10, 10)));

        JButton button = new JButton("Select the file to decode the message from");

        button.setAlignmentX(CENTER_ALIGNMENT);
        button.addActionListener(e -> {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Choose file");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileopen.getSelectedFile();
                try {
                    Path path = Paths.get(file.getPath());
                    List<String> newValue = Files.readAllLines(path, Charset.forName("windows-1251"));
                    allLines.set(newValue);
                    text.setText("<html>" + String.join("<br>", newValue) +"</html>");
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                nameOfFile.setText("Chosen file: " + file.getName());
                nameOfFile.setVisible(true);
                text.setVisible(true);
            }
        });
        panel.add(button);
        panel.add(nameOfFile);
        panel.add(text);
        codePhrase = new JLabel();
        panel.add(codePhrase);
        JButton button2 = new JButton("Decode");

        button2.setAlignmentX(CENTER_ALIGNMENT);
        button2.addActionListener(e -> {
            decodeText(allLines.get());
        });
        panel.add(button2);
        panel.add(Box.createVerticalGlue());
        getContentPane().add(panel);

        setPreferredSize(new Dimension(450, 350));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void decodeText(List<String> lines) {

        BitSet res = new BitSet(lines.size());
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).endsWith(" ")) {
                res.set(i, true);
            }
        }
        byte[] bytes = res.toByteArray();
        codePhrase.setText(new String(bytes));
        codePhrase.setVisible(true);
    }
}
