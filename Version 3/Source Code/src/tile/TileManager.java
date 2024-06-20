package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[20];
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/obj/grass.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/obj/brick.png"));
            tile[1].collision = true;
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/obj/water.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/obj/wood.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/obj/shit.png"));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/obj/sand.png"));
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/obj/tree.png"));
            tile[6].collision = true;
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/obj/d.png"));
            tile[8] = new Tile();
            tile[8].image = ImageIO.read(getClass().getResourceAsStream("/obj/e.png"));
            tile[9] = new Tile();
            tile[9].image = ImageIO.read(getClass().getResourceAsStream("/obj/v.png"));
            tile[10] = new Tile();
            tile[10].image = ImageIO.read(getClass().getResourceAsStream("/obj/r.png"));
            tile[11] = new Tile();
            tile[11].image = ImageIO.read(getClass().getResourceAsStream("/obj/o.png"));
            tile[12] = new Tile();
            tile[12].image = ImageIO.read(getClass().getResourceAsStream("/obj/m.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadMap() {
        try {
            InputStream is = getClass().getResourceAsStream("/maps/map2.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col = 0;
            int row = 0;
            while(col < gp.maxWorldCol && row < gp.maxWorldRow) {
                String line = br.readLine();
                while(col < gp.maxWorldCol) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldCol][worldRow];
            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;
            int screenX = worldX - gp.player.worldX + gp.player.screenX;
            int screenY = worldY - gp.player.worldY + gp.player.screenY;
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
                    worldY + gp.tileSize  > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize  < gp.player.worldY + gp.player.screenY) {
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }
            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

}
