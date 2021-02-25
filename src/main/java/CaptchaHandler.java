import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class CaptchaHandler {
    public void createCaptcha(String CAPTCHA_URL) {
        Image image = null;
        try {
            URL url = new URL(CAPTCHA_URL);
            image = ImageIO.read(url);
            ImageIO.write((RenderedImage) image, "jpg", new File("captcha.jpg"));
        } catch(IOException e) {
            System.out.println("Error: " + e);
        };

    }
}
