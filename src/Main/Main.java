package Main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import Common.HistoryFieldInput;
import Tool.CopyFile;
import Tool.DonDepWin;
import Tool.GetTextImage;
import Tool.GiamDungLuongImage;
import Tool.LamDepCmt;
import Tool.MappingParamSql;

public class Main extends JFrame {
    // todo
    // làm tiếp giảm dung lượng img
    // sửa lại format code. thêm checkbox trimleft
    // thêm dọn dẹp rác window

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public static HistoryFieldInput historyFieldInput;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    // get historyFieldInput
                    try (FileInputStream fos = new FileInputStream(HistoryFieldInput.fileTemp); ObjectInputStream oos = new ObjectInputStream(fos);) {
                        historyFieldInput = (HistoryFieldInput) oos.readObject();
                    } catch (Exception e) {
                        historyFieldInput = new HistoryFieldInput();
                    }

                    // start GUI
                    ImageIcon iconTitle = new ImageIcon("img\\strawberry.png");
                    Main frame = new Main();
                    frame.setVisible(true);
                    frame.setSize(1024, 768);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                    frame.setResizable(false);
                    frame.setTitle("Strawberry");
                    frame.setIconImage(iconTitle.getImage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Main() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(0, 0, 1024, 768);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        getContentPane().add(tabbedPane, BorderLayout.CENTER);

        CopyFile copyFile = new CopyFile();
        tabbedPane.addTab("COPY FILE", null, copyFile, null);

        LamDepCmt lamDepCmt = new LamDepCmt();
        tabbedPane.addTab("Làm đẹp SOURCE", null, lamDepCmt, null);

        MappingParamSql mappingParamSql = new MappingParamSql();
        tabbedPane.addTab("Mapping param SQL", null, mappingParamSql, null);

        JPanel panel_7 = new JPanel();
        tabbedPane.addTab("Xử lý chuỗi", null, panel_7, null);
        tabbedPane.setBackgroundAt(3, Color.LIGHT_GRAY);

        GetTextImage getTextImage = new GetTextImage();
        tabbedPane.addTab("Get text from IMAGE", null, getTextImage, null);

        GiamDungLuongImage giamDungLuongImage = new GiamDungLuongImage();
        tabbedPane.addTab("Giảm dung lượng IMAGE", null, giamDungLuongImage, null);
        tabbedPane.setBackgroundAt(5, Color.LIGHT_GRAY);
        
        DonDepWin donDepWin = new DonDepWin();
        tabbedPane.addTab("Dọn dẹp windows", null, donDepWin, null);
        tabbedPane.setBackgroundAt(6, Color.LIGHT_GRAY);
    }
}
