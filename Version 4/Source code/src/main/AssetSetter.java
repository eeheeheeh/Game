package main;

import obj.OBJ_Bread;
import obj.OBJ_Door;
import obj.OBJ_Key;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = 14 * gp.tileSize;
        gp.obj[0].worldY = 6 * gp.tileSize;

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 23 * gp.tileSize;
        gp.obj[1].worldY = 7 * gp.tileSize;

        gp.obj[2] = new OBJ_Bread();
        gp.obj[2].worldX = 23 * gp.tileSize;
        gp.obj[2].worldY = 5 * gp.tileSize;
    }

}
