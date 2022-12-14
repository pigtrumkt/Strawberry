package Tool;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;

import org.apache.commons.io.FileUtils;

import Common.Constants;
import Common.Suggestion.SuggestionDropDownDecorator;
import Common.Suggestion.TextComponentSuggestionClient;
import Main.Main;

public class CopyFile extends JPanel {

    JTextField folderInputTxt;
    JTextField folderOutputTxt;
    JLabel lblNewLabel_1;
    JLabel lblNewLabel;
    JTextArea fileAreaTxt;
    JScrollPane jScrollPane;
    JLabel lblNewLabel_2;
    JLabel lblNewLabel_2_1;
    JLabel lblNewLabel_2_1_1;
    JLabel totalFileTxt;
    JLabel fileOKTxt;
    JLabel fileNGTxt;
    JButton startCollect;
    JButton delKoTonTaiBtn;
    JButton delTrungBtn;
    JCheckBox clearOutFol;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public CopyFile() {
        super();
        setLayout(null);
        folderInputTxt = new JTextField();
        folderInputTxt.setName("folderInputTxt");
        folderInputTxt.setBounds(111, 8, 871, 25);
        folderInputTxt.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkStatus();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkStatus();

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkStatus();
            }
        });
        SuggestionDropDownDecorator.decorate(folderInputTxt, new TextComponentSuggestionClient(Main.historyFieldInput::getSuggestions));
        add(folderInputTxt);

        folderOutputTxt = new JTextField();
        folderOutputTxt.setName("folderOutputTxt");
        folderOutputTxt.setBounds(111, 44, 871, 25);
        folderOutputTxt.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkStatus();
                clearOutFol.setSelected(false);
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkStatus();
                clearOutFol.setSelected(false);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkStatus();
                clearOutFol.setSelected(false);
            }
        });
        SuggestionDropDownDecorator.decorate(folderOutputTxt, new TextComponentSuggestionClient(Main.historyFieldInput::getSuggestions));
        add(folderOutputTxt);

        lblNewLabel_1 = new JLabel("Folder source");
        lblNewLabel_1.setBounds(25, 13, 91, 14);
        add(lblNewLabel_1);

        lblNewLabel = new JLabel("Folder output");
        lblNewLabel.setBounds(25, 49, 91, 14);
        add(lblNewLabel);

        fileAreaTxt = new JTextArea();
        fileAreaTxt.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                checkStatus();
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                checkStatus();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                checkStatus();
            }
        });
        fileAreaTxt.setSelectedTextColor(Color.GRAY);

        jScrollPane = new JScrollPane(fileAreaTxt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane.setBounds(25, 110, 806, 565);
        add(jScrollPane);

        lblNewLabel_1 = new JLabel("List file output ");
        lblNewLabel_1.setBounds(25, 90, 91, 14);
        add(lblNewLabel_1);

        lblNewLabel_2 = new JLabel("Tổng files:");
        lblNewLabel_2.setBounds(841, 110, 65, 14);
        add(lblNewLabel_2);

        lblNewLabel_2_1 = new JLabel("OK:");
        lblNewLabel_2_1.setForeground(Color.BLACK);
        lblNewLabel_2_1.setBounds(841, 135, 52, 14);
        add(lblNewLabel_2_1);

        lblNewLabel_2_1_1 = new JLabel("NG:");
        lblNewLabel_2_1_1.setForeground(Color.BLACK);
        lblNewLabel_2_1_1.setBounds(841, 160, 52, 14);
        add(lblNewLabel_2_1_1);

        totalFileTxt = new JLabel("0");
        totalFileTxt.setBounds(913, 110, 46, 14);
        add(totalFileTxt);

        fileOKTxt = new JLabel("0");
        fileOKTxt.setBounds(913, 135, 46, 14);
        add(fileOKTxt);

        fileNGTxt = new JLabel("0");
        fileNGTxt.setBounds(913, 160, 46, 14);
        add(fileNGTxt);

        startCollect = new JButton("Start");
        startCollect.setBounds(841, 634, 141, 41);
        add(startCollect);

        startCollect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                executeStart();
            }
        });

        startCollect.setEnabled(false);

        clearOutFol = new JCheckBox("Clean folder output");
        clearOutFol.setBounds(842, 603, 200, 23);
        add(clearOutFol);

        JPanel panel = new JPanel();
        panel.setBackground(Constants.errorColor);
        panel.setBounds(841, 210, 18, 18);
        add(panel);

        JPanel panel_4 = new JPanel();
        panel_4.setBackground(Constants.orangeColor);
        panel_4.setBounds(841, 240, 18, 18);
        add(panel_4);

        JLabel lblNewLabel = new JLabel("File không tồn tại");
        lblNewLabel.setBounds(869, 212, 124, 14);
        add(lblNewLabel);

        JLabel lblTrngFile = new JLabel("Trùng file");
        lblTrngFile.setBounds(869, 242, 124, 14);
        add(lblTrngFile);

        delKoTonTaiBtn = new JButton("-");
        delKoTonTaiBtn.setBounds(972, 210, 20, 18);
        delKoTonTaiBtn.setMargin(new Insets(1, 1, 1, 1));
        add(delKoTonTaiBtn);

        delKoTonTaiBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xoaKoTonTai();
            }
        });

        delTrungBtn = new JButton("-");
        delTrungBtn.setBounds(972, 240, 20, 18);
        delTrungBtn.setMargin(new Insets(1, 1, 1, 1));
        add(delTrungBtn);

        delTrungBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                xoaTrung();
            }
        });
    }

    public String convertUrl(String url) {
        url = url.replace("/", "\\");
        while (url.indexOf("\\\\") > -1) {
            url = url.replace("\\\\", "\\");
        }

        return url;
    }

    public void xoaKoTonTai() {
        String in = folderInputTxt.getText();
        List<String> fileTxt = new ArrayList<>();
        fileTxt.addAll(Arrays.asList(fileAreaTxt.getText().split("\n")));

        File fIn;
        String urlIn;
        for (int i = fileTxt.size() - 1; i >= 0; i--) {
            urlIn = "";
            if (!fileTxt.get(i).trim().equals("")) {
                urlIn = in.trim() + "\\" + fileTxt.get(i).trim();
                urlIn = convertUrl(urlIn);
                fIn = new File(urlIn);
                if (!fIn.exists()) {
                    fileTxt.remove(i);
                }
            }
        }

        String rs = "";
        for (String s : fileTxt) {
            if (!s.trim().equals("")) {
                rs += s.trim() + "\n";
            }
        }

        // remove line empty cuoi
        if (rs.length() > 1) {
            rs = rs.substring(0, rs.length() - 1);
        }

        fileAreaTxt.setText(rs);
    }

    public void xoaTrung() {
        String in = folderInputTxt.getText();
        List<String> fileTxt = new ArrayList<>();
        fileTxt.addAll(Arrays.asList(fileAreaTxt.getText().split("\n")));

        String urlIn1;
        String urlIn2;
        int length = fileTxt.size();
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                urlIn1 = "";
                urlIn2 = "";
                if (!fileTxt.get(i).trim().equals("") && !fileTxt.get(j).trim().equals("")) {
                    urlIn1 = in.trim() + "\\" + fileTxt.get(i).trim();
                    urlIn1 = convertUrl(urlIn1);
                    urlIn2 = in.trim() + "\\" + fileTxt.get(j).trim();
                    urlIn2 = convertUrl(urlIn2);
                    if (urlIn1.equals(urlIn2)) {
                        fileTxt.remove(j);
                        length--;
                        j--;
                    }
                }
            }
        }

        String rs = "";
        for (String s : fileTxt) {
            if (!s.trim().equals("")) {
                rs += s.trim() + "\n";
            }
        }

        // remove line empty cuoi
        if (rs.length() > 1) {
            rs = rs.substring(0, rs.length() - 1);
        }

        fileAreaTxt.setText(rs);
    }

    public void addHighlight(String[] fileTxt, int lineIdx, Color color) {
        int idxStart = 0;
        for (int j = 0; j < lineIdx; j++) {
            idxStart += fileTxt[j].length() + 1;
        }

        try {
            fileAreaTxt.getHighlighter().addHighlight(idxStart, idxStart + fileTxt[lineIdx].length(), new DefaultHighlighter.DefaultHighlightPainter(color));
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }

    public boolean checkStatus() {
        boolean isOk = true;
        int total = 0;
        int ok = 0;
        int ng = 0;

        String in = folderInputTxt.getText();
        String out = folderOutputTxt.getText();
        String[] fileTxt = fileAreaTxt.getText().split("\n");

        File fIn;
        String urlIn;
        fileAreaTxt.getHighlighter().removeAllHighlights();
        for (int i = 0; i < fileTxt.length; i++) {
            urlIn = "";
            if (!fileTxt[i].trim().equals("")) {
                urlIn = in.trim() + "\\" + fileTxt[i].trim();
                urlIn = convertUrl(urlIn);
                fIn = new File(urlIn);
                if (fIn.exists()) {
                    ok++;
                } else {
                    ng++;
                    addHighlight(fileTxt, i, Constants.errorColor);
                }

                total++;
            }
        }

        String urlIn1;
        String urlIn2;
        for (int i = 0; i < fileTxt.length; i++) {
            for (int j = i + 1; j < fileTxt.length; j++) {
                urlIn1 = "";
                urlIn2 = "";
                if (!fileTxt[i].trim().equals("") && !fileTxt[j].trim().equals("")) {
                    urlIn1 = in.trim() + "\\" + fileTxt[i].trim();
                    urlIn1 = convertUrl(urlIn1);
                    urlIn2 = in.trim() + "\\" + fileTxt[j].trim();
                    urlIn2 = convertUrl(urlIn2);
                    if (urlIn1.equals(urlIn2)) {
                        addHighlight(fileTxt, i, Constants.orangeColor);
                        addHighlight(fileTxt, j, Constants.orangeColor);
                    }
                }
            }
        }

        fileOKTxt.setText(String.valueOf(ok));
        fileNGTxt.setText(String.valueOf(ng));
        totalFileTxt.setText(String.valueOf(total));

        if (!in.equals("") && !out.equals("") && in.equals(out)) {
            folderInputTxt.setBackground(Constants.errorColor);
            folderOutputTxt.setBackground(Constants.errorColor);
            isOk = false;
        } else {
            folderInputTxt.setBackground(Color.WHITE);
            folderOutputTxt.setBackground(Color.WHITE);
            isOk = true;
        }

        if (isOk && ng == 0 && !folderOutputTxt.getText().equals("") && total > 0) {
            isOk = true;
        } else {
            isOk = false;
        }

        if (isOk) {
            startCollect.setEnabled(true);
        } else {
            startCollect.setEnabled(false);
        }

        return isOk;
    }

    public void executeStart() {
        try {
            if (checkStatus()) {
                if (clearOutFol.isSelected()) {
                    Object[] options = { "Yes", "No" };
                    int result = JOptionPane.showOptionDialog(this, "Xóa toàn bộ thư mục output?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
                    if (result != JOptionPane.YES_OPTION) {
                        return;
                    }
                }

                String in = folderInputTxt.getText();
                String out = folderOutputTxt.getText();
                String[] fileTxt = fileAreaTxt.getText().split("\n");

                if (clearOutFol.isSelected()) {
                    FileUtils.deleteDirectory(new File(out));
                }

                File fIn;
                File fOut;
                String urlIn;
                String urlOut;
                for (int i = 0; i < fileTxt.length; i++) {
                    urlIn = "";
                    urlOut = "";
                    if (!fileTxt[i].trim().equals("")) {
                        urlIn = in.trim() + "\\" + fileTxt[i].trim();
                        urlIn = convertUrl(urlIn);
                        urlOut = out.trim() + "\\" + fileTxt[i].trim();
                        urlOut = convertUrl(urlOut);

                        fIn = new File(urlIn);
                        fOut = new File(urlOut);

                        fOut.mkdirs();
                        Files.copy(fIn.toPath(), fOut.toPath(), StandardCopyOption.REPLACE_EXISTING);
                    }
                }

                JOptionPane.showMessageDialog(null, "Done " + fileOKTxt.getText() + " files.");
                Main.historyFieldInput.add("folderInputTxt", in.trim());
                Main.historyFieldInput.add("folderOutputTxt", out.trim());
            } else {
                startCollect.setEnabled(false);
                JOptionPane.showMessageDialog(null, "Error " + fileNGTxt.getText() + " files.");
            }
        } catch (IOException e) {
            startCollect.setEnabled(false);
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
