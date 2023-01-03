package main;

import object.Chest;
import object.Door;
import object.Key;
import object.Star;

public class ObjectSetter {
    GamePanel gp;

    public ObjectSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() { // set position of obj on map
        gp.object[0] = new Key();
        gp.object[0].worldX = 25 * gp.tileSize;
        gp.object[0].worldY = 25 * gp.tileSize;

        gp.object[1] = new Star();
        gp.object[1].worldX = 28 * gp.tileSize;
        gp.object[1].worldY = 27 * gp.tileSize;

        gp.object[2] = new Door();
        gp.object[2].worldX = 28 * gp.tileSize;
        gp.object[2].worldY = 28 * gp.tileSize;

    }
}
