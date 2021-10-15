package javaJam.jPokedex;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SplashScreen {
    private int width;
    private int height;

    public SplashScreen(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void draw() {
        BufferedImage bufferedImage = new BufferedImage(
                this.width, this.height,
                BufferedImage.TYPE_INT_RGB);
        Graphics graphics = bufferedImage.getGraphics();
        graphics.setFont(new Font("SansSerif", Font.BOLD, 14));

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        graphics2D.drawString("jPokeDex", 5, 10);

        for (int y = 0; y < this.height; y++) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int x = 0; x < this.width; x++) {
                stringBuilder.append(bufferedImage.getRGB(x, y) == -16777216 ? " " : "*");
            }

            if (stringBuilder.toString().trim().isEmpty()) {
                continue;
            }

            System.out.println(ConsoleColors.YELLOW_BRIGHT + stringBuilder + ConsoleColors.RESET);
        }
    }
}
