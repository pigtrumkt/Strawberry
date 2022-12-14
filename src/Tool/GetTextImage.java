package Tool;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.ibm.icu.text.Transliterator;

import Common.ReadImageUtil;

public class GetTextImage extends JPanel {

    public static final int WIDTH_IMG_SHOW = 585;
    public static final int HEIGHT_IMG_SHOW = 310;

    JPanel panel;
    JTextArea textArea;

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private static BufferedImage imgBuff = null;

    public GetTextImage() {
        super();
        setLayout(null);

        JComboBox<String> comboBox = new JComboBox<String>();
        comboBox.setModel(new DefaultComboBoxModel<String>(new String[] { "Tiếng Nhật 1", "Tiếng Nhật 2", "Tiếng Anh" }));
        comboBox.setBounds(215, 345, 133, 22);
        add(comboBox);

        panel = new JPanel();
        panel.setBorder(new LineBorder(Color.DARK_GRAY));
        panel.setBounds(215, 23, WIDTH_IMG_SHOW, HEIGHT_IMG_SHOW);
        panel.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    JFileChooser fc = new JFileChooser();
                    FileNameExtensionFilter filter = new FileNameExtensionFilter("Image", "jpg", "png", "jpeg");
                    fc.setFileFilter(filter);
                    int returnVal = fc.showOpenDialog(null);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fc.getSelectedFile();
                        imgBuff = ImageIO.read(file);
                        drawImagePanel();
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        add(panel);
        panel.setLayout(null);

        JLabel label = new JLabel("Chọn file hoặc CTRL + V");
        label.setFont(new Font("Tahoma", Font.PLAIN, 30));
        label.setBounds(126, 89, 429, 120);
        panel.add(label);

        textArea = new JTextArea();
        JScrollPane jScrollPane1 = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jScrollPane1.setBounds(215, 378, 585, 310);
        add(jScrollPane1);

        JButton btnConvertFullwidth = new JButton("Full-width");
        btnConvertFullwidth.setBounds(807, 633, 95, 23);
        btnConvertFullwidth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Transliterator transliterator = Transliterator.getInstance("Halfwidth-Fullwidth");
                String converted = transliterator.transliterate(textArea.getText());
                textArea.setText(converted);
            }
        });
        add(btnConvertFullwidth);

        JButton btnConvertHalfwidth = new JButton("Half-width");
        btnConvertHalfwidth.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Transliterator transliterator = Transliterator.getInstance("Fullwidth-Halfwidth");
                String converted = transliterator.transliterate(textArea.getText()); // half-width
                textArea.setText(converted);
            }
        });
        btnConvertHalfwidth.setBounds(807, 662, 95, 23);
        add(btnConvertHalfwidth);

        JButton btnNewButton = new JButton("Start");
        btnNewButton.setBounds(358, 344, 89, 23);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (imgBuff != null) {
                    String language = ReadImageUtil.EN;
                    if (comboBox.getSelectedIndex() == 0) {
                        language = ReadImageUtil.JP_BEST;
                    } else if (comboBox.getSelectedIndex() == 1) {
                        language = ReadImageUtil.JP;
                    } else if (comboBox.getSelectedIndex() == 2) {
                        language = ReadImageUtil.EN;
                    }

                    String rs = ReadImageUtil.crackImage(imgBuff, language);
                    textArea.setText(rs.replace(" ", ""));
                } else {
                    JOptionPane.showMessageDialog(null, "Chưa có hình nào");
                }
            }
        });
        add(btnNewButton);

        JButton btnNewButton_1 = new JButton("Clear");
        btnNewButton_1.setBounds(728, 344, 72, 23);
        btnNewButton_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clear();
            }
        });
        add(btnNewButton_1);

        KeyStroke ctrlV = KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK);
        registerKeyboardAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
                imgBuff = pasteImageFromClipboard();
                drawImagePanel();

            }
        }, ctrlV, JComponent.WHEN_IN_FOCUSED_WINDOW);

        textArea.registerKeyboardAction(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                clear();
                imgBuff = pasteImageFromClipboard();
                drawImagePanel();

            }
        }, ctrlV, JComponent.WHEN_FOCUSED);
    }

    public void drawImagePanel() {
        if (imgBuff != null) {
            panel.removeAll();
            JLabel imgShow = new JLabel(new ImageIcon(resize(imgBuff)));
            imgShow.setBounds(0, 0, WIDTH_IMG_SHOW, HEIGHT_IMG_SHOW);
            panel.add(imgShow);
            panel.repaint();
        } else {
            clear();
        }
    }

    public void clear() {
        panel.removeAll();
        imgBuff = null;
        JLabel label = new JLabel("Chọn file hoặc CTRL + V");
        label.setFont(new Font("Tahoma", Font.PLAIN, 30));
        label.setBounds(126, 89, 429, 120);
        panel.add(label);
        panel.repaint();
        textArea.setText("");
    }

    public BufferedImage resize(BufferedImage img) {
        double scaleWidth = (double) WIDTH_IMG_SHOW / (double) img.getWidth();
        double scaleHeight = (double) HEIGHT_IMG_SHOW / (double) img.getHeight();
        double scale = 1d;

        if (scaleWidth < scaleHeight) {
            scale = scaleWidth;
        } else {
            scale = scaleHeight;
        }

        double width = (double) img.getWidth() * scale - 2;
        double height = (double) img.getHeight() * scale - 2;

        Image tmp = img.getScaledInstance((int) width, (int) height, Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage((int) width, (int) height, img.getType());

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

    /**
     * Obtains a BufferedImage from the clipboard.
     *
     * @return the obtained image, null if not available
     * @see BufferedImage#TYPE_INT_RGB
     */
    public BufferedImage pasteImageFromClipboard() {
        java.awt.image.BufferedImage result;
        int width;
        int height;
        Graphics g;
        Image img = pasteFromClipboard();

        result = null;
        if (img != null) {
            width = img.getWidth(null);
            height = img.getHeight(null);
            result = new java.awt.image.BufferedImage(width, height, java.awt.image.BufferedImage.TYPE_INT_RGB);
            g = result.createGraphics();
            g.drawImage(img, 0, 0, null);
            g.dispose();
        }

        return result;
    }

    /**
     * Obtains an object from the clipboard.
     *
     * @param flavor the type of object to obtain
     * @return the obtained object, null if not available
     */
    public Image pasteFromClipboard() {
        Clipboard clipboard;
        Image result;
        Transferable content;

        result = null;
        try {
            clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            content = clipboard.getContents(null);
            if ((content != null))
                if (content.isDataFlavorSupported(DataFlavor.imageFlavor)) {
                    result = (Image) content.getTransferData(DataFlavor.imageFlavor);
                } else if (content.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    @SuppressWarnings("unchecked")
                    List<File> listFile = (List<File>) content.getTransferData(DataFlavor.javaFileListFlavor);
                    File file = listFile.get(0);
                    result = ImageIO.read(file);
                }
        } catch (Exception e) {
            result = null;
        }

        return result;
    }
}
