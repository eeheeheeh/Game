package main;

import obj.OBJ_Key;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;

public class UI {

    GamePanel gp;
    Font customFont, customFont2;
    BufferedImage image;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GamePanel gp) {
        this.gp = gp;
        // Cargar la fuente personalizada
        try (InputStream is = getClass().getResourceAsStream("/fonts/JMH_Typewriter.ttf")) {
            if (is == null) {
                throw new IOException("Fuente no encontrada");
            }
            customFont = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(40f); // Tamaño 40 puntos
            customFont2 = Font.createFont(Font.TRUETYPE_FONT, is).deriveFont(70f);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsEnvironment ge2 = GraphicsEnvironment.getLocalGraphicsEnvironment();

            ge.registerFont(customFont);
            ge2.registerFont(customFont2);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            // Fallback a Arial si la fuente personalizada no se puede cargar
            customFont = new Font("Arial", Font.PLAIN, 40);
            customFont2 = new Font("Arial", Font.BOLD, 70);
        }

        OBJ_Key key = new OBJ_Key();
        image = key.image;
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gameFinished == true) {
            g2.setFont(customFont);
            g2.setColor(Color.white);
            String text;
            int textLength;
            int x;
            int y;

            text = "Encontraste el cofre";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 - (gp.tileSize*3);
            g2.drawString(text, x, y);

            g2.setFont(customFont);
            g2.setColor(Color.yellow);
            text = "El vago del creador aun no lo termina";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*3);
            g2.drawString(text, 70, 490);

            text = "Tu tiempo de juego fue de: " + dFormat.format(playTime) + "!";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*3);
            g2.drawString(text, 70, 530);

            g2.setFont(customFont2);
            g2.setColor(Color.yellow);
            text = "Terminastes el juego";
            textLength = (int)g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth/2 - textLength/2;
            y = gp.screenHeight/2 + (gp.tileSize*3);
            g2.drawString(text, x, y);
            gp.gameThread = null;
        } else {
            g2.setFont(customFont);
            g2.setColor(Color.white);

            g2.drawImage(image, gp.tileSize / 2, gp.tileSize / 2, gp.tileSize, gp.tileSize, null);
            g2.drawString("x " + gp.player.hasKey, 74, 50);

            playTime += (double)1/60;
            g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize *11, 65);

            if (messageOn) {
                g2.setFont(customFont.deriveFont(30F));
                g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);

                messageCounter++;
                if (messageCounter > 120) {
                    messageCounter = 0;
                    messageOn = false;
                }
            }
        }
    }
}
