package Common;

import java.awt.image.BufferedImage;

import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class ReadImageUtil {

    public static String TESSDATA_PREFIX = "resources/";
    public static String EN = "eng";
    public static String JP = "jpn";
    public static String JP_BEST = "jpn_best";

    public static String crackImage(BufferedImage img, String language) {
        ITesseract instance = new Tesseract();
        instance.setDatapath(TESSDATA_PREFIX);
        instance.setLanguage(language);

        try {
            String result = instance.doOCR(img);
            return result;
        } catch (TesseractException e) {
            return "Error while reading image";
        }
    }

}
