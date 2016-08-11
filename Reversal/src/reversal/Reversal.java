package reversal;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JFrame;


/**
 * @author name1
 * @author name2
 */
public class Reversal extends JFrame {
    
    boolean grid = true;
    int difficulty = 5;
    int windowSize = 800;
    int gridSize = 4;
    int cellWidth = windowSize / gridSize;
    int[] lastClicked = new int[2];
    boolean[][] cells = new boolean[windowSize / cellWidth][windowSize / cellWidth];
    
    
    /**
     * @param title the game title
     */
    public Reversal(String title) {
        super(title);
        
        this.setBackground(Color.black);
        this.setSize(windowSize, windowSize );
        this.setUndecorated(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        
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
            }
            @Override
            public void mousePressed(MouseEvent e) {
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int length = windowSize / gridSize;
                    lastClicked[0] = (int) Math.floor(e.getX() / length);
                    lastClicked[1] = (int) Math.floor(e.getY() / length);
                    System.out.println("Col: " + lastClicked[0] + "\t\tRow: " + lastClicked[1]);
                    
                    reverse(lastClicked[0], lastClicked[1]);
                }
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
        repaint();
    }
    
    
    public void generate() {
        Random random = new Random();
        for (int i = 0; i < difficulty; i++) {
            int col = random.nextInt(cells.length);
            int row = random.nextInt(cells.length);
            reverse(col, row);
            sleep(100);
        }
    }
    
    
    public boolean won() {
        for (boolean[] cell : cells) {
            for (boolean b : cell) {
                if (b) return false;
            }
        }
        return true;
    }
    
    
    public void sleep(int numMilliseconds) {
        try {
            Thread.sleep(numMilliseconds);
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

        for (int j = 0; j < gridSize; j++) {
            for (int i = 0; i < gridSize; i++) {
                if (cells[j][i]) {
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
        game.generate();
        
        while (true) {
            if (game.won()) {
                System.out.println("You won!");
                break;
            }
        }
    }
}