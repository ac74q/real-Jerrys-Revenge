package main;

import entity.Entity;

public class CollisionChecker {
    GamePanel gp;

    public CollisionChecker(GamePanel gp) {
       this.gp = gp;
    }

    public void checkTile(Entity entity) { // checks if any entity is colliding
        int entityLeftWorld = entity.worldX + entity.solidArea.x;
        int entityRightWorld = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorld = entity.worldY + entity.solidArea.y;
        int entityBottomWorld = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorld / gp.tileSize;
        int entityRightCol = entityRightWorld / gp.tileSize;
        int entityTopRow = entityTopWorld / gp.tileSize;
        int entityBottomRow = entityBottomWorld / gp.tileSize;

        int tileNum1, tileNum2; // the 2 possible tiles that entity can hit

        // this is the ugliest if statement i've seen in my life
        if (entity.direction == "up") {
            entityTopRow = (entityTopWorld - entity.speed) / gp.tileSize; // predict where entity is after it moves
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow]; // top left tile
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityTopRow]; // top right tile
            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
               entity.collisionOn = true;
            }
        } else if (entity.direction == "down") {
            entityBottomRow = (entityBottomWorld + entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
        } else if (entity.direction == "left") {
            entityLeftCol = (entityLeftWorld - entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityLeftCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityLeftCol][entityBottomRow];
            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
        } else if (entity.direction == "right") {
            entityRightCol = (entityRightWorld + entity.speed) / gp.tileSize;
            tileNum1 = gp.tileM.mapTileNum[entityRightCol][entityTopRow];
            tileNum2 = gp.tileM.mapTileNum[entityRightCol][entityBottomRow];
            if (gp.tileM.tile[tileNum1].collision || gp.tileM.tile[tileNum2].collision) {
                entity.collisionOn = true;
            }
        }
    }
}
