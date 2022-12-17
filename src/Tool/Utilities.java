package Tool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Common.CmdUtils;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JLabel;
import java.awt.Font;

public class Utilities extends JPanel {

    public static final int WIDTH_IMG_SHOW = 600;
    public static final int HEIGHT_IMG_SHOW = 600;
    JCheckBox temp;
    JCheckBox tempWin;
    JCheckBox prefetch;
    JCheckBox softwareDistribution;
    JCheckBox windowsOld;
    JCheckBox recycleBin;
    JCheckBox download;
    JCheckBox chckbxDisableComputerrEstore;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public Utilities() {
        super();
        setLayout(null);
        setSize(1024, 768);
        
        JPanel panel = new JPanel();
        panel.setBorder(new LineBorder(new Color(0, 0, 0)));
        panel.setBounds(10, 38, 993, 243);
        add(panel);
        
                recycleBin = new JCheckBox("Thùng rác");
                recycleBin.setSelected(true);
        
                download = new JCheckBox("Thư mục Downloads");
        
                windowsOld = new JCheckBox("Windows.old");
                windowsOld.setSelected(true);
        
                softwareDistribution = new JCheckBox("Windows/SoftwareDistribution");
                softwareDistribution.setSelected(true);
        
                prefetch = new JCheckBox("Windows/Prefetch");
                prefetch.setSelected(true);
        
                tempWin = new JCheckBox("Windows/temp");
                tempWin.setSelected(true);
        
                temp = new JCheckBox("Temp");
                temp.setSelected(true);
        
                chckbxDisableComputerrEstore = new JCheckBox("Disable computer restore");
                chckbxDisableComputerrEstore.setSelected(true);
        
                JButton start = new JButton("Start");
                start.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    }
                });
                
                        // handle
                        start.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                if (!CmdUtils.isAdmin()) {
                                    JOptionPane.showMessageDialog(null, "Hãy Run as administrator");
                                    return;
                                }
                
                                Object[] options = { "Yes", "No" };
                                int result = JOptionPane.showOptionDialog(null, "Hãy tắt hết chương trình đang chạy", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
                                if (result != JOptionPane.YES_OPTION) {
                                    return;
                                }
                
                                if (temp.isSelected()) {
                                    CmdUtils.runCmd("del %temp%\\*.* /s /q");
                                }
                
                                if (tempWin.isSelected()) {
                                    CmdUtils.runCmd("del %systemdrive%\\Windows\\Temp\\*.* /s /q");
                                }
                
                                if (prefetch.isSelected()) {
                                    CmdUtils.runCmd("del %systemdrive%\\Windows\\Prefetch\\*.* /s /q");
                                }
                
                                if (softwareDistribution.isSelected()) {
                                    CmdUtils.runCmd("del %systemdrive%\\Windows\\SoftwareDistribution\\*.* /s /q");
                                }
                
                                if (windowsOld.isSelected()) {
                                    CmdUtils.runCmd("del %systemdrive%\\windows.old\\*.* /s /q");
                                }
                
                                if (recycleBin.isSelected()) {
                                    CmdUtils.runPowershell("Clear-RecycleBin -Force");
                                }
                
                                if (download.isSelected()) {
                                    CmdUtils.runCmd("del %systemdrive%\\Users\\%username%\\Downloads\\*.* /s /q");
                                }
                
                                if (chckbxDisableComputerrEstore.isSelected()) {
                                    CmdUtils.runPowershell("Disable-ComputerRestore -Drive \"C:\\\"");
                                    CmdUtils.runPowershell("Disable-ComputerRestore -Drive \"D:\\\"");
                                    CmdUtils.runPowershell("Disable-ComputerRestore -Drive \"E:\\\"");
                                }
                
                                int result1 = JOptionPane.showOptionDialog(null, "Restart PC?", "Cảnh báo", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
                                if (result1 != JOptionPane.YES_OPTION) {
                                    return;
                                }
                
                                // restart pc
                                CmdUtils.runCmd("shutdown /r -t 0");
                            }
                        });
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addComponent(temp, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
                            .addGap(15)
                            .addComponent(chckbxDisableComputerrEstore, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE))
                        .addComponent(tempWin, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
                        .addComponent(prefetch, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
                        .addComponent(softwareDistribution, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
                        .addComponent(windowsOld, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
                        .addComponent(recycleBin, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
                        .addGroup(gl_panel.createSequentialGroup()
                            .addComponent(download, GroupLayout.PREFERRED_SIZE, 245, GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(ComponentPlacement.RELATED, 652, Short.MAX_VALUE)
                            .addComponent(start, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap())
        );
        gl_panel.setVerticalGroup(
            gl_panel.createParallelGroup(Alignment.LEADING)
                .addGroup(gl_panel.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                        .addComponent(temp)
                        .addComponent(chckbxDisableComputerrEstore))
                    .addGap(3)
                    .addComponent(tempWin)
                    .addGap(3)
                    .addComponent(prefetch)
                    .addGap(3)
                    .addComponent(softwareDistribution)
                    .addGap(3)
                    .addComponent(windowsOld)
                    .addGap(3)
                    .addComponent(recycleBin)
                    .addGap(3)
                    .addComponent(download)
                    .addContainerGap(113, Short.MAX_VALUE))
                .addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
                    .addContainerGap(265, Short.MAX_VALUE)
                    .addComponent(start)
                    .addContainerGap())
        );
        panel.setLayout(gl_panel);
        
        JLabel lblNewLabel = new JLabel("Dọn dẹp windows");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        lblNewLabel.setBounds(10, 11, 212, 25);
        add(lblNewLabel);
    }
}
