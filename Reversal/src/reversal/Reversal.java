package reversal;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;

/**
 * @author name1
 * @author name2
 */
public class Reversal extends JFrame {
    
    boolean grid = true;
    
    int windowSize = 800;
    int gridSize = 10;
    int cellWidth = windowSize / gridSize;
    boolean[][] cells1 = new boolean[windowSize / cellWidth][windowSize / cellWidth];
    boolean[][] cells2 = new boolean[windowSize / cellWidth][windowSize / cellWidth];
    
    /**
     * @param title the game title
     */
    public Reversal(String title) {
        super(title);
    }
    
    public void nextFrame() {
        
        
        for (int i = 0; i < cells1.length; i++) {
            System.arraycopy(cells2[i], 0, cells1[i], 0, cells1[i].length);
        }
    }
    
    @Override
    public void paint(Graphics g) {

        int xPos = 0;
        int yPos = 0;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                
                if (cells1[i][j]) {
                    g.setColor(Color.white);
                    g.fillRect(xPos, yPos, cellWidth, cellWidth);
                } else {
                    g.clearRect(xPos, yPos, cellWidth, cellWidth);
                }
                if (grid) {
                    g.setColor(Color.gray);
                    g.drawRect(xPos, yPos, cellWidth, cellWidth);
                }
                xPos += cellWidth;
            }
            yPos += cellWidth;
            xPos = 0;
        }
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Reversal game = new Reversal("Reversal");
        
        game.setBackground(Color.black);
        game.setSize(game.windowSize, game.windowSize );
        game.setUndecorated(true);
        game.setDefaultCloseOperation(EXIT_ON_CLOSE);
        game.setVisible(true);
    }
}
