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
    public int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10]; //amount of tiles in game
        mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];

        getTileImage();
        loadMap("/maps/worldMap.txt");
    }

    public void getTileImage() {
        try {
            // collision is false by default
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass-1-pixilart.png/"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grassFlower-pixilart.png/"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/path-pixilart.png/"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water-pixilart.png/"));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick-wall-pixilart.png/"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stone-wall-pixilart.png/"));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/tiles/dirt-pixilart.png/"));

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree-pixilart.png/"));
            tile[7].collision = true;

        } catch(IOException e) {
            e.printStackTrace();
        }
    }
    public void loadMap(String filePath) {
         try {
             InputStream is = getClass().getResourceAsStream(filePath);
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
         }
    }

    public void draw(Graphics2D g2D) {
      int worldCol = 0;
      int worldRow = 0;

      while(worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {
          int tileNum = mapTileNum[worldCol][worldRow];

          int worldX = worldCol * gp.tileSize;
          int worldY = worldRow * gp.tileSize;
          int screenX = worldX - gp.player.worldX + gp.player.screenX;
          int screenY = worldY - gp.player.worldY + gp.player.screenY;

          if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
                  worldX - gp.tileSize  < gp.player.worldX + gp.player.screenX &&
                  worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                  worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
             g2D.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
          }
          worldCol++;

          if(worldCol == gp.maxWorldCol) {
                  worldCol = 0;
                  worldRow++;
          }
      }
    }
}
