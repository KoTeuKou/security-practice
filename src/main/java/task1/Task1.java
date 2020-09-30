package task1;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Task1 extends JFrame {

    private byte[] signature = new byte[] {};

    private final JLabel resultLabel = new JLabel();


    public Task1() {
        super("First task");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.add(Box.createVerticalGlue());

        final JLabel nameOfFile = new JLabel();
        nameOfFile.setVisible(false);
        nameOfFile.setAlignmentX(CENTER_ALIGNMENT);

        final JLabel signatureOfFile = new JLabel();
        signatureOfFile.setVisible(false);
        signatureOfFile.setAlignmentX(CENTER_ALIGNMENT);

        resultLabel.setVisible(false);
        resultLabel.setAlignmentX(CENTER_ALIGNMENT);

        panel.add(Box.createRigidArea(new Dimension(10, 10)));

        JButton button = new JButton("Choose file to get signature from");
        JButton button2 = new JButton("Choose directory to find files with same signature");

        button.setAlignmentX(CENTER_ALIGNMENT);
        button.addActionListener(e -> {
            JFileChooser fileopen = new JFileChooser();
            int ret = fileopen.showDialog(null, "Choose file");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File file = fileopen.getSelectedFile();
                try {
                    signature = Arrays.copyOf(Files.readAllBytes(Paths.get(file.getPath())), 16);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                nameOfFile.setText("Chosen file: " + file.getName());
                signatureOfFile.setText("It's signature: " + Arrays.toString(getSignature()));
                nameOfFile.setVisible(true);
                signatureOfFile.setVisible(true);
                button2.setVisible(true);
                resultLabel.setVisible(false);
            }
        });
        panel.add(button);
        panel.add(nameOfFile);
        panel.add(signatureOfFile);


        button2.setAlignmentX(CENTER_ALIGNMENT);
        button2.setVisible(false);
        button2.addActionListener(e -> {
            final JFileChooser chooser = new JFileChooser() {
                public void approveSelection() {
                    if (getSelectedFile().isFile()) {
                        return;
                    } else
                        super.approveSelection();
                }
            };
            chooser.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );
            int ret = chooser.showDialog(null, "Choose directory");
            if (ret == JFileChooser.APPROVE_OPTION) {
                File directory = chooser.getSelectedFile();
                process(directory.getAbsolutePath());
            }
        });
        panel.add(button2);
        panel.add(resultLabel);

        panel.add(Box.createVerticalGlue());
        getContentPane().add(panel);

        setPreferredSize(new Dimension(450, 350));
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public JLabel getResultLabel() {
        return resultLabel;
    }

    public void setResultLabelText(List<String> resultLabels) {
        StringBuilder res = new StringBuilder();
        resultLabels.forEach(elem -> res.append(elem).append("<br>"));
        resultLabel.setText("<html>" + res.toString() + "</html>");
        resultLabel.setVisible(true);
    }

    public void process(String root) {
        File rootDir = new File(root);
        ArrayList<String> result = new ArrayList<>();
        PriorityQueue <File> fileTree = new PriorityQueue<>();

        Collections.addAll(fileTree, Objects.requireNonNull(rootDir.listFiles()));

        while (!fileTree.isEmpty())
        {
            File currentFile = fileTree.remove();
            if(currentFile.isDirectory()){
                Collections.addAll(fileTree, Objects.requireNonNull(currentFile.listFiles()));
            } else {
                result.add(currentFile.getAbsolutePath());
            }
        }
        List<String> foundFiles = result.stream().filter(s -> {
            try {
                return isContainsRequiredSignature(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }).collect(Collectors.toList());
        setResultLabelText(foundFiles);
    }
    public boolean isContainsRequiredSignature(String pathToFile) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(pathToFile));
        boolean isSuitable = false;
        byte[] signature = getSignature();
        int signatureLength = signature.length;
        int length = bytes.length - signatureLength + 1;

        for (int i = 0; i < length; i ++) {
            int k = i + signatureLength;
            byte[] a2 = Arrays.copyOfRange(bytes, i, k);
            if (Arrays.equals(signature, a2)) {
                isSuitable = true;
                break;
            }
        }
        return isSuitable;
    }
}
