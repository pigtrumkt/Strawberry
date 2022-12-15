package Tool;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.plaf.FontUIResource;

import Common.StringUtils;

public class LamDepCmt extends JPanel {

    JPopupMenu popup;
    JTextArea oldTxt;
    JTextArea newTxt;
    JCheckBox removeEmptyLine;
    JCheckBox removeSpace;
    JComboBox<String> comboBox;
    int selectedIdx = 0;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public LamDepCmt() {
        super();
        setLayout(null);
        setSize(1024, 768);
        
        popup = new JPopupMenu();

        JButton copyBtn = new JButton("Copy");
        copyBtn.setBounds(916, 651, 75, 23);
        copyBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                newTxt.selectAll();
                newTxt.copy();
                copyBtn.setText("Copied");
            }
        });
        add(copyBtn);

        oldTxt = new JTextArea();
        JScrollPane jScrollPane1 = new JScrollPane(oldTxt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane1.setBounds(10, 114, 450, 530);
        add(jScrollPane1);

        JButton btnNewButton = new JButton(">");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 11));
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                executeStart();
            }
        });
        btnNewButton.setBounds(470, 350, 63, 23);
        add(btnNewButton);

        JTextPane txtpnFormsetaaAbc = new JTextPane();
        txtpnFormsetaaAbc.setBackground(Color.LIGHT_GRAY);
        txtpnFormsetaaAbc.setText("form.setA(\"a\"); // set a\r\n       form.setB(\"b\"); // set b\r\n   form.setC(\"c\");       // set c");
        txtpnFormsetaaAbc.setBounds(10, 47, 450, 56);
        add(txtpnFormsetaaAbc);

        JTextPane txtpnFormsetaaSet = new JTextPane();
        txtpnFormsetaaSet.setFont(new FontUIResource(Font.MONOSPACED, Font.CENTER_BASELINE, 12));
        txtpnFormsetaaSet.setText("form.setA(\"a\");    // set a\r\nform.setB(\"b\");    // set b\r\nform.setC(\"c\");    // set c");
        txtpnFormsetaaSet.setBackground(Color.LIGHT_GRAY);
        txtpnFormsetaaSet.setBounds(543, 47, 450, 56);
        add(txtpnFormsetaaSet);

        JLabel lblNewLabel = new JLabel(">");
        lblNewLabel.setBounds(497, 66, 12, 14);
        add(lblNewLabel);

        newTxt = new JTextArea();
        newTxt.setFont(new FontUIResource(Font.MONOSPACED, Font.CENTER_BASELINE, 12));
        newTxt.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                copyBtn.setText("Copy");
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                copyBtn.setText("Copy");

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                copyBtn.setText("Copy");
            }
        });
        JScrollPane jScrollPane2 = new JScrollPane(newTxt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane2.setBounds(543, 114, 450, 530);
        add(jScrollPane2);

        JLabel mauLabel = new JLabel("Loại:");
        mauLabel.setBounds(10, 15, 46, 14);
        add(mauLabel);

        removeEmptyLine = new JCheckBox("Xóa line trống");
        removeEmptyLine.setBounds(6, 650, 110, 23);
        removeEmptyLine.setSelected(false);
        add(removeEmptyLine);
        
        removeSpace = new JCheckBox("Xóa space phía trước");
        removeSpace.setBounds(120, 650, 150, 23);
        removeSpace.setSelected(false);
        add(removeSpace);

        comboBox = new JComboBox<String>(new String[] { "Loại 1", "Loại 2" });
        comboBox.setBounds(41, 11, 79, 22);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectedIdx = comboBox.getSelectedIndex();
                if (selectedIdx == 0) {
                    txtpnFormsetaaAbc.setText("form.setA(\"a\"); // set a\r\n       form.setB(\"b\"); // set b\r\n   form.setC(\"c\");       // set c");
                    txtpnFormsetaaSet.setText("form.setA(\"a\");    // set a\r\nform.setB(\"b\");    // set b\r\nform.setC(\"c\");    // set c");
                } else if (selectedIdx == 1) {
                    txtpnFormsetaaAbc.setText(
                            "--- {\"columnA\",ItemInfo.TYPE_STR,\"2\",\"Cot A\"},// cot A\r\n--- getFormValidity().addCheckInfo(new FormCheckInfo(\"sakiKojoCode\",...");
                    txtpnFormsetaaSet.setText(
                            "{\"columnA\"  , ItemInfo.TYPE_STR, \"2\",\"Cot A\"},    // cot A\r\n{\"columnBBB\", ItemInfo.TYPE_STR, \"2\",\"Cot B\"},    // cot B\r\n{\"columnCC\" , ItemInfo.TYPE_STR, \"2\",\"Cot C\"},    // cot C");
                }
            }
        });
        comboBox.setSelectedIndex(0);
        add(comboBox);
    }

    public void executeStart() {
        if (comboBox.getSelectedIndex() == 0) {
            startLoai1(null);
        } else if (comboBox.getSelectedIndex() == 1) {
            startLoai2();
        }
    }

    public void startLoai1(String newInput) {
        int maxLength = 0;
        String[] oldArr = null;
        if (newInput != null) {
            oldArr = newInput.split("\n");
        } else {
            oldArr = oldTxt.getText().split("\n");
        }

        List<String> newList = new ArrayList<>();
        for (String line : oldArr) {
            line = checkTrim(line);
            if (line.isEmpty()) {
                continue;
            }

            String[] arr = line.split("//");
            if (checkTrim(arr[0]).length() > maxLength && arr.length != 1) {
                maxLength = checkTrim(arr[0]).length();
            }
        }

        maxLength += 4;

        for (String line : oldArr) {
            line = checkTrim(line);
            String[] arr = line.split("//");
            int idxKeyCmt = checkTrim(arr[0]).length();
            if (idxKeyCmt > -1 && arr.length > 1) {
                newList.add(line.substring(0, idxKeyCmt) + addSpace(maxLength - idxKeyCmt) + "// " + arr[1].trim() + "\n");
            } else {
                newList.add(line + "\n");
            }
        }

        String newStr = "";
        for (String line : newList) {
            line = checkTrim(line);
            if (removeEmptyLine.isSelected()) {
                if (!line.isEmpty()) {
                    newStr += line + "\n";
                }
            } else {
                newStr += line + "\n";
            }
        }

        // remove line empty cuoi
        if (newStr.length() > 1) {
            newStr = newStr.substring(0, newStr.length() - 1);
        }

        newTxt.setText(newStr);

    }

    public void startLoai2() {
        removeSpace.setSelected(true);
        String[] arrLineOld = oldTxt.getText().split("\n");
        List<Integer> listMaxLengthDauPhay = new ArrayList<>();
        // lặp từng line để kiếm maxlength và tổng dấu phẩy
        for (String lineOld : arrLineOld) {
            lineOld = lineOld.trim();
            if (lineOld.equals("")) {
                continue;
            }

            String[] arrDauPhayOld = lineOld.split(",");
            if (arrDauPhayOld[0].trim().indexOf("//") == 0) {
                continue;
            }

            // lặp kiếm maxlength và tổng dấu phẩy
            for (int i = 0; i < arrDauPhayOld.length; i++) {
                String s = arrDauPhayOld[i].trim();
                if (listMaxLengthDauPhay.size() < i + 1) {
                    listMaxLengthDauPhay.add(s.length());
                } else {
                    if (listMaxLengthDauPhay.get(i) < s.length()) {
                        listMaxLengthDauPhay.set(i, s.length());
                    }
                }
            }
        }

        // apply từng line
        List<String> newLineList = new ArrayList<>();
        for (int i = 0; i < arrLineOld.length; i++) {
            String lineOld = arrLineOld[i].trim();
            if (removeEmptyLine.isSelected() && lineOld.equals("")) {
                continue;
            }

            String[] arrDauPhayOld = lineOld.split(",");

            List<String> itemInLine = new ArrayList<>();
            for (int j = 0; j < arrDauPhayOld.length; j++) {
                String valDauPhay = arrDauPhayOld[j].trim();
                itemInLine.add(valDauPhay + addSpace(listMaxLengthDauPhay.get(j) - valDauPhay.length()));
            }

            // ghép các sub string
            String newLine = "";
            for (int j = 0; j < itemInLine.size(); j++) {
                newLine += itemInLine.get(j) + ",   ";
            }

            newLineList.add(newLine.substring(0, newLine.length() - 4));
        }

        // set lên màn hình
        String newStr = "";
        for (String line : newLineList) {
            line = line.trim();
            if (removeEmptyLine.isSelected()) {
                if (!line.isEmpty()) {
                    newStr += line + "\n";
                }
            } else {
                newStr += line + "\n";
            }
        }

        // remove line empty cuoi
        if (newStr.length() > 1) {
            newStr = newStr.substring(0, newStr.length() - 1);
        }

        newTxt.setText(newStr);

        startLoai1(newStr);
    }

    public static String addSpace(int num) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < num; i++) {
            stringBuilder.append(" ");
        }

        return stringBuilder.toString();
    }
    
    public String checkTrim(String s) {
        return removeSpace.isSelected() ? s.trim() : StringUtils.rtrim(s);
    }
}
