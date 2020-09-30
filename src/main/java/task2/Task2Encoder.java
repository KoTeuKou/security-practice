package task2;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Task2Encoder extends JFrame {

    public Task2Encoder() {
        super("Second task. Encoder");
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
        AtomicReference<String> path = new AtomicReference<>();

        panel.add(Box.createRigidArea(new Dimension(10, 10)));

        JButton button = new JButton("Select the file to encode the message to");

        button.setAlignmentX(CENTER_ALIGNMENT);
        button.addActionListener(e -> {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Choose file");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileopen.getSelectedFile();
                try {
                    Path path1 = Paths.get(file.getPath());
                    path.set(path1.getParent().toString());
                    List<String> newValue = Files.readAllLines(path1);
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
        JEditorPane jEditorPane = new JEditorPane();
        panel.add(jEditorPane);
        JButton button2 = new JButton("Encode");

        button2.setAlignmentX(CENTER_ALIGNMENT);
        button2.addActionListener(e -> {
            encodeText(path.get(), allLines.get(), jEditorPane.getText());
        });
        panel.add(button2);
        panel.add(Box.createVerticalGlue());
        getContentPane().add(panel);

        setPreferredSize(new Dimension(450, 350));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void encodeText(String path, List<String> lines, String phrase) {
        BitSet codePhraseBits = BitSet.valueOf(phrase.getBytes());
        for (int i = 0; i < lines.size(); i++) {
            if (codePhraseBits.get(i)) {
                lines.set(i, lines.get(i) + " ");
            }
        }

        File file = new File(path + "/encodedFile.txt");
        try {
            if(!file.exists()){
                file.createNewFile();
            }
            try (PrintWriter out = new PrintWriter(file.getAbsoluteFile())) {
                lines.forEach(out::println);
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

    }
}
