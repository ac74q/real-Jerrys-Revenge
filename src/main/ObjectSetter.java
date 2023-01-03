package main;

import object.Key;

public class ObjectSetter {
    GamePanel gp;

    public ObjectSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() { // set position of obj on map
        gp.object[0] = new Key();
        gp.object[0].worldX = 25 * gp.tileSize;
        gp.object[0].worldY = 25 * gp.tileSize;

        gp.object[1] = new Key();
        gp.object[1].worldX = 40 * gp.tileSize;
        gp.object[1].worldY = 30  * gp.tileSize;
    }
}
