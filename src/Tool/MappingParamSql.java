package Tool;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.commons.lang3.StringUtils;

public class MappingParamSql extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public MappingParamSql() {
        super();
        setLayout(null);
        setSize(1024, 768);
        
        JTextArea sqlTxt = new JTextArea();
        sqlTxt.setLineWrap(true);
        JScrollPane jScrollPane1 = new JScrollPane(sqlTxt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane1.setBounds(10, 82, 298, 574);
        add(jScrollPane1);

        JTextPane txtpnSelectFrom = new JTextPane();
        txtpnSelectFrom.setText("SELECT * FROM abc WHERE column1 = ? AND column2 = ?");
        txtpnSelectFrom.setBackground(Color.LIGHT_GRAY);
        txtpnSelectFrom.setBounds(10, 11, 298, 39);
        add(txtpnSelectFrom);

        JTextArea paramsTxt = new JTextArea();
        paramsTxt.setLineWrap(true);
        JScrollPane jScrollPane2 = new JScrollPane(paramsTxt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setBounds(318, 82, 298, 574);
        add(jScrollPane2);

        JTextArea resultTxt = new JTextArea();

        JButton btnNewButton = new JButton("Copy");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                resultTxt.selectAll();
                resultTxt.copy();
                btnNewButton.setText("Copied");
            }
        });
        btnNewButton.setBounds(916, 662, 75, 23);
        add(btnNewButton);

        resultTxt.setLineWrap(true);
        resultTxt.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                btnNewButton.setText("Copy");
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                btnNewButton.setText("Copy");

            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                btnNewButton.setText("Copy");
            }
        });
        JScrollPane jScrollPane3 = new JScrollPane(resultTxt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane3.setBounds(695, 82, 298, 574);
        add(jScrollPane3);

        JTextPane txtpnvalueValue = new JTextPane();
        txtpnvalueValue.setText("[value1, value2]");
        txtpnvalueValue.setBackground(Color.LIGHT_GRAY);
        txtpnvalueValue.setBounds(318, 11, 298, 39);
        add(txtpnvalueValue);

        JTextPane txtpnSelectFrom_2 = new JTextPane();
        txtpnSelectFrom_2.setText("SELECT * FROM abc WHERE column1 = 'value1' AND column2 = 'value2'");
        txtpnSelectFrom_2.setBackground(Color.LIGHT_GRAY);
        txtpnSelectFrom_2.setBounds(695, 11, 298, 39);
        add(txtpnSelectFrom_2);

        JLabel lblNewLabel = new JLabel("SQL:");
        lblNewLabel.setBounds(10, 61, 46, 14);
        add(lblNewLabel);

        JLabel lblParams = new JLabel("Parameter:");
        lblParams.setBounds(318, 61, 135, 14);
        add(lblParams);

        JLabel lblNewLabel_1 = new JLabel("Result:");
        lblNewLabel_1.setBounds(695, 61, 46, 14);
        add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel(">");
        lblNewLabel_2.setBounds(653, 23, 20, 14);
        add(lblNewLabel_2);

        JButton submit = new JButton(">");
        submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sql = sqlTxt.getText().trim();
                int countParamSql = (int) sql.chars().filter(ch -> ch == '?').count();
                if (countParamSql == 0) {
                    JOptionPane.showMessageDialog(null, "Câu sql không có param");
                    return;
                }
                
                String params = paramsTxt.getText().trim();
                if (StringUtils.isEmpty(params)) {
                    JOptionPane.showMessageDialog(null, "Chưa nhập param");
                    return;
                }
                
                if (params.charAt(0) == '[' && params.charAt(params.length() - 1) == ']') {
                    params = params.substring(1, params.length() - 1);
                }

                String[] arrParam = params.split(",");
                
                if (arrParam.length != countParamSql) {
                    JOptionPane.showMessageDialog(null, "Số lượng param ko mapping với sql");
                } else {
                    for (String s : arrParam) {
                        s = s.trim();
                        sql = sql.replaceFirst("\\?", "'" + s + "'");
                    }

                    resultTxt.setText(sql);
                }
            }
        });
        submit.setBounds(626, 321, 59, 23);
        add(submit);
    }
}
