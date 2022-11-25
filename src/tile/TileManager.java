package tile;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10]; //amount of tiles in game
        mapTileNum = new int[gp.maxScreenColumn][gp.maxScreenRow];

        getTileImage();
        loadMap("/maps/testmap.txt");
    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass-1-pixilart.png/"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grassFlower-pixilart.png/"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water-pixilart.png/"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/brick-wall-pixilart.png/"));
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/stone-wall-pixilart.png/"));
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

             while(col < gp.maxScreenColumn && row < gp.maxScreenRow) {
                String line = br.readLine();

                while(col < gp.maxScreenColumn) {
                    String numbers[] = line.split(" ");
                    int num = Integer.parseInt(numbers[col]);
                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gp.maxScreenColumn) {
                    col = 0;
                    row++;
                }
             }
             br.close();

         } catch (Exception e) {
         }
    }

    public void draw(Graphics2D g2D) {
      int col = 0;
      int row = 0;
      int x = 0;
      int y = 0;

      while(col < gp.maxScreenColumn && row < gp.maxScreenRow) {
          int tileNum = mapTileNum[col][row];

          g2D.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
          col++;
          x += gp.tileSize;

          if(col == gp.maxScreenColumn) {
              col = 0;
              x = 0;
              row++;
              y += gp.tileSize;
          }
      }
    }
}
