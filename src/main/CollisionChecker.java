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

    public int checkObject(Entity entity, boolean player) {
        int index = 999;

        for (int i = 0; i < gp.object.length; i++) {
            if (gp.object[i] != null) {
                // get entity's solid area position
                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;
                // get object's solid area position
                gp.object[i].solidArea.x = gp.object[i].worldX + gp.object[i].solidArea.x;
                gp.object[i].solidArea.y = gp.object[i].worldY + gp.object[i].solidArea.y;

                if (entity.direction == "up") {
                    entity.solidArea.y -= entity.speed; // predict location after movement
                    if (entity.solidArea.intersects(gp.object[i].solidArea)) {
                        if (gp.object[i].collision) {
                            entity.collisionOn = true;
                        }
                        if (player) {
                            index = i;
                        }
                    }
                } else if (entity.direction == "down") {
                    entity.solidArea.y += entity.speed;
                    if (entity.solidArea.intersects(gp.object[i].solidArea)) {
                        if (gp.object[i].collision) {
                            entity.collisionOn = true;
                        }
                        if (player) {
                            index = i;
                        }
                    }
                } else if (entity.direction == "left") {
                    entity.solidArea.x -= entity.speed;
                    if (entity.solidArea.intersects(gp.object[i].solidArea)) {
                        if (gp.object[i].collision) {
                            entity.collisionOn = true;
                        }
                        if (player) {
                            index = i;
                        }
                    }
                } else if (entity.direction == "right") {
                    entity.solidArea.x += entity.speed;
                    if (entity.solidArea.intersects(gp.object[i].solidArea)) {
                        if (gp.object[i].collision) {
                            entity.collisionOn = true;
                        }
                        if (player) {
                            index = i;
                        }
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.object[i].solidArea.x = gp.object[i].solidAreaDefaultX;
                gp.object[i].solidArea.y = gp.object[i].solidAreaDefaultY;
            }
        }

        return index;
        // if tom is hitting any objects, return the object's index
    }
}
