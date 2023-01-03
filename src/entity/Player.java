package entity;
import main.GamePanel;
import main.KeyInput;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyInput keyI;
    public final int screenX;
    public final int screenY;
    int hasKey = 0;
    boolean hasPowerup = false;

    public Player(GamePanel gp, KeyInput keyI) {
        this.gp = gp;
        this.keyI = keyI;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle(18, 18, 15, 30); // times scale of 3
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 24;
        worldY = gp.tileSize * 24;
        speed = 3;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/tom-down1-pixilart.png/"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/tom-down2-pixilart.png/"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/tom-up1-pixilart.png/"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/tom-up2-pixilart.png/"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/tom-left1-pixilart.png/"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/tom-left2-pixilart.png/"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/tom-right1-pixilart.png/"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/tom-right2-pixilart.png/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if (keyI.upPressed || keyI.downPressed ||
                keyI.leftPressed || keyI.rightPressed) {

            // assign direction
            if (keyI.upPressed) {
                direction = "up";
            } else if (keyI.downPressed) {
                direction = "down";
            } else if (keyI.leftPressed) {
                direction = "left";
            } else if (keyI.rightPressed) {
                direction = "right";
            }

            // check collision
            collisionOn = false;
            gp.collisionChecker.checkTile(this);
            int objectIndex = gp.collisionChecker.checkObject(this, true);
            pickUpObject(objectIndex);

            // if collision is false, player can move
            if (!collisionOn) {
                if (hasPowerup && keyI.shiftHeld) {
                   speed = 5;
                } else {
                    speed = 3;
                }
                if (direction == "up") {
                    worldY -= speed; // world moves, makes it look like tom is
                } else if (direction == "down") {
                    worldY += speed;
                } else if (direction == "left") {
                    worldX -= speed;
                } else if (direction == "right") {
                    worldX += speed;
                }
            }

            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void pickUpObject(int i) {
        if (i != 999) { // 999 is default index
           String objectName = gp.object[i].name;
           if (objectName == "Key") {
               hasKey++;
               gp.object[i] = null;
           } else if (objectName == "Door") {
               if (hasKey > 0) {
                   gp.object[i] = null;
                   hasKey--;
               }
           } else if (objectName == "Star") {
               hasPowerup = true;
               gp.object[i] = null;
           }
        }
    }

    public void draw(Graphics2D g2D) {

        BufferedImage image = null;

        if (direction == "up") {
            if (spriteNum == 1) {
                image = up1;
            } if (spriteNum == 2) {
                    image = up2;
                }
            }
         else if (direction == "down") {
            if (spriteNum == 1) {
                image = down1;
            }
            if (spriteNum == 2) {
                image = down2;
            }
        } else if (direction == "left") {
            if (spriteNum == 1) {
                image = left1;
            }
            if (spriteNum == 2) {
                image = left2;
            }
        } else if (direction == "right") {
            if (spriteNum == 1) {
                image = right1;
            }
            if (spriteNum == 2) {
                image = right2;
            }
        }
        g2D.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}