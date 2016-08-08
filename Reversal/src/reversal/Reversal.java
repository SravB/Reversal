package reversal;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
    
    int[] lastClicked = new int[2];
    
    Boolean[][] cells = new Boolean[windowSize / cellWidth][windowSize / cellWidth];
        
    /**
     * @param title the game title
     */
    public Reversal(String title) {
        super(title);
        
        this.setBackground(Color.black);
        this.setSize(windowSize, windowSize );
        this.setUndecorated(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // If <ESCAPE> is pressed, exit the program
                if (e.getKeyCode() == 27) {
                    System.exit(0);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        
        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                lastClicked[0] = e.getX();
                lastClicked[1] = e.getY();
                System.out.println("X: " + lastClicked[0] + "\t\tY: " + lastClicked[1]);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
    
    
    public void reverse(int col, int row) {
        try {
            cells[row-1][col] = !cells[row-1][col];
        } catch (Exception ignored) {}
        try {
            cells[row+1][col] = !cells[row+1][col];
        } catch (Exception ignored) {}
        try {
            cells[row][col-1] = !cells[row][col-1];
        } catch (Exception ignored) {}
        try {
            cells[row][col+1] = !cells[row][col+1];
        } catch (Exception ignored) {}
    }
    
    
    /**
     * 
     * @param g graphics object
     */
    @Override
    public void paint(Graphics g) {

        int xPos = 0;
        int yPos = 0;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                
                if (cells[i][j]) {
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
        
        game.setVisible(true);
    }
}