package Tool;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import Common.CmdUtils;

public class DonDepWin extends JPanel {

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

    public DonDepWin() {
        super();
        setLayout(null);
        setSize(1024, 768);

        temp = new JCheckBox("Temp");
        temp.setBounds(6, 7, 245, 23);
        temp.setSelected(true);
        add(temp);

        tempWin = new JCheckBox("Windows/temp");
        tempWin.setBounds(6, 33, 245, 23);
        tempWin.setSelected(true);
        add(tempWin);

        prefetch = new JCheckBox("Windows/Prefetch");
        prefetch.setBounds(6, 59, 245, 23);
        prefetch.setSelected(true);
        add(prefetch);

        softwareDistribution = new JCheckBox("Windows/SoftwareDistribution");
        softwareDistribution.setBounds(6, 85, 245, 23);
        softwareDistribution.setSelected(true);
        add(softwareDistribution);

        windowsOld = new JCheckBox("Windows.old");
        windowsOld.setBounds(6, 111, 245, 23);
        windowsOld.setSelected(true);
        add(windowsOld);

        recycleBin = new JCheckBox("Thùng rác");
        recycleBin.setBounds(6, 137, 245, 23);
        recycleBin.setSelected(true);
        add(recycleBin);

        download = new JCheckBox("Thư mục Downloads");
        download.setBounds(6, 163, 245, 23);
        add(download);

        chckbxDisableComputerrEstore = new JCheckBox("Disable computer restore");
        chckbxDisableComputerrEstore.setBounds(304, 7, 245, 23);
        chckbxDisableComputerrEstore.setSelected(true);
        add(chckbxDisableComputerrEstore);

        JButton start = new JButton("Start");
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            }
        });
        start.setBounds(6, 219, 89, 23);
        add(start);

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
    }

}
