package Tool;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;

import com.ibm.icu.text.Transliterator;

public class XuLyChuoi extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private JTextField txtCharCount;
    private JTextField txtLineCount;

    JTextArea stringOld;
    JTextArea stringNew;
    JLabel lblNewLabel;
    JLabel lblNewLabel_1;
    JLabel lblNewLabel_2;
    JLabel lblNewLabel_2_1;
    
    public XuLyChuoi() {
        super();
        setSize(1024, 768);
        
        stringOld = new JTextArea();
        stringOld.setLineWrap(true);
        JScrollPane jScrollPane1 = new JScrollPane(stringOld, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        stringNew = new JTextArea();
        stringNew.setEditable(false);

        stringNew.setLineWrap(true);
        JScrollPane jScrollPane3 = new JScrollPane(stringNew, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        lblNewLabel = new JLabel("String:");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));

        lblNewLabel_1 = new JLabel("Result:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        lblNewLabel_2 = new JLabel("Character Count");
        lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
        
        txtCharCount = new JTextField();
        txtCharCount.setEditable(false);
        txtCharCount.setHorizontalAlignment(SwingConstants.CENTER);
        txtCharCount.setText("0");
        txtCharCount.setColumns(10);
        
        txtLineCount = new JTextField();
        txtLineCount.setText("0");
        txtLineCount.setHorizontalAlignment(SwingConstants.CENTER);
        txtLineCount.setEditable(false);
        txtLineCount.setColumns(10);
        
        lblNewLabel_2_1 = new JLabel("Line Count");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        
        stringOld.addKeyListener(new KeyListener() {
            
            @Override
            public void keyTyped(KeyEvent e) {
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                txtCharCount.setText(String.valueOf(stringOld.getText().length()));
                txtLineCount.setText(String.valueOf(stringOld.getText().split("\n").length));
            }
            
            @Override
            public void keyPressed(KeyEvent e) {
            }
        });
        
        JButton lowerCase = new JButton("Lower case");
        lowerCase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stringNew.setText(stringOld.getText().toLowerCase());
            }
        });
        
        JButton upperCase = new JButton("Upper case");
        upperCase.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                stringNew.setText(stringOld.getText().toUpperCase());
            }
        });
        
        JButton fullWidth = new JButton("Full-width");
        fullWidth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Transliterator transliterator = Transliterator.getInstance("Halfwidth-Fullwidth");
                String converted = transliterator.transliterate(stringOld.getText());
                stringNew.setText(converted);
            }
        });
        
        JButton haflwidth = new JButton("Half-width");
        haflwidth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Transliterator transliterator = Transliterator.getInstance("Fullwidth-Halfwidth");
                String converted = transliterator.transliterate(stringOld.getText());
                stringNew.setText(converted);
            }
        });
        
        GroupLayout groupLayout = new GroupLayout(this);
        groupLayout.setHorizontalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 421, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 177, GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNewLabel_2, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtCharCount, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNewLabel_2_1, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtLineCount, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)
                        .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                            .addComponent(haflwidth, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fullWidth, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(upperCase, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lowerCase, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 107, Short.MAX_VALUE)))
                    .addPreferredGap(ComponentPlacement.RELATED)
                    .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                        .addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 437, GroupLayout.PREFERRED_SIZE))
                    .addContainerGap(35, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
            groupLayout.createParallelGroup(Alignment.LEADING)
                .addGroup(groupLayout.createSequentialGroup()
                    .addGap(11)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(lblNewLabel)
                        .addComponent(lblNewLabel_1))
                    .addGap(11)
                    .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 620, GroupLayout.PREFERRED_SIZE)
                        .addComponent(jScrollPane3, GroupLayout.PREFERRED_SIZE, 620, GroupLayout.PREFERRED_SIZE)
                        .addGroup(groupLayout.createSequentialGroup()
                            .addComponent(lblNewLabel_2)
                            .addGap(9)
                            .addComponent(txtCharCount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(22)
                            .addComponent(lblNewLabel_2_1)
                            .addGap(9)
                            .addComponent(txtLineCount, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addGap(41)
                            .addComponent(lowerCase)
                            .addGap(17)
                            .addComponent(upperCase)
                            .addGap(18)
                            .addComponent(fullWidth)
                            .addGap(19)
                            .addComponent(haflwidth)))
                    .addGap(112))
        );
        setLayout(groupLayout);
    }
}
