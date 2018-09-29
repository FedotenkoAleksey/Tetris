package rotation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;

class ControllersPanel extends JPanel implements ActionListener {
    JFrame frame;
    JTextField textfield;
    Action spaceAction;
    Action downAction;
    Action leftAction;
    Action rightAction;
    Action upAction;
    Heap heap;
    ShapeFactory shapeFactory;
    int shapeCount;
    Score score;
    
    
    
    int timeCount = 0;
    
    Shape shape;
    
    Timer timer;
    int SPEED = 50;
    
    public ControllersPanel() {
        prepareGUI();
    }
    
    public static void main(String[] args) {
        new ControllersPanel();
    }
    
    public void prepareGUI() {         
        // JTextField to add Actions to
        textfield = new JTextField();
        textfield.setPreferredSize(new Dimension(0, 0));
        
        leftAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape.leftMove(heap);
            }
        };
        
        rightAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape.rightMove(heap);
            }
        };
        
        downAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape.dive(heap);
            }
        };
        
        spaceAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                shape.rotate(heap);
            }
        };
        
        upAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                shape.rotate(heap);
            }
        };
        
        // the next two lines do key binding
        textfield.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),   
                                    "doLeftAction");
        textfield.getActionMap().put("doLeftAction", leftAction);
        
        textfield.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),
                                    "doRightAction");
        textfield.getActionMap().put("doRightAction", rightAction);
        
        textfield.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),
                                    "doDownAction");
        textfield.getActionMap().put("doDownAction", downAction);
        
        textfield.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                                    "doSpaceAction");
        textfield.getActionMap().put("doSpaceAction", spaceAction);
        
        textfield.getInputMap().put(KeyStroke.getKeyStroke("UP"),
                                    "doUpAction");
        textfield.getActionMap().put("doUpAction", upAction);
        
        
        this.add(textfield);
        
        
        
        score = new Score();
        heap = new Heap(score);
        
        shape = shapeFactory.createShape(ShapeType.LSHAPE.getRandomShapeType(), heap);              
        shapeCount = 1;
        
        
        frame = new JFrame();
        frame.add(this);
        frame.setSize(540, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setLocation(screenWidth/4, screenHeight/40);
        
        timer = new Timer(SPEED, this);
    }
    
     public void drawBlackField(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
     
     public void drawFrameLines(Graphics2D g2) {
         // let's get box's size first
        int size = Box.SIZE; // TODO any access to this constant should be made only through the getter method.
        int width = size * 10;
        int height = width * 2;
         
        g2.setColor(Color.WHITE);
        g2.drawLine(size, size, width + size, size);
        g2.drawLine(width + size, size, width + size, height + size);
        g2.drawLine(width + size, height + size, size, height + size);
        g2.drawLine(size, height + size, size, size);
        
    }
     
    public void drawScore(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        Font font = new Font("Calibri", Font.PLAIN, 27);
        g2.setFont(font);
        String shapeCount = "Shape Number: " + this.shapeCount;
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.drawString(shapeCount, Box.SIZE*10 + 60, 50); // TODO any access to this constant should be made only through the getter method.
        
        String scoreText = "Score:                  " + score.getScore();
        g2.drawString(scoreText, Box.SIZE*10 + 60, 90); // TODO any access to this constant should be made only through the getter method.
        
        int efficiency = (int) Math.round((((double)score.getScore() * 10.0) / ((double) this.shapeCount * 4.0)) * 100.0);    
        String effText = "Efficiency:           " + efficiency;
        g2.drawString(effText, Box.SIZE*10 + 60, 130); // TODO any access to this constant should be made only through the getter method.
        
        if (score.isGameOver()) {
            String gameOverString = "GAME OVER";
            font = new Font("Calibri", Font.PLAIN, 45);
            g2.setColor(Color.ORANGE);
            g2.setFont(font);
            g2.drawString(gameOverString, Box.SIZE*10 + 60, 250); // TODO any access to this constant should be made only through the getter method.
        }
        
    } // end of method
     
     @Override
     public void paintComponent(Graphics g) {
         Graphics2D g2 = (Graphics2D) g;
         drawBlackField(g2);
         drawFrameLines(g2);
         heap.draw(g2);
         shape.draw(g2);
         
         drawScore(g2);
         
         timer.start();
         
     }
     
     @Override
     public void actionPerformed(ActionEvent event) {         
        timeCount++;
        if ((timeCount % 15) == 0) {
            timeCount -= 15;
            shape.fall(heap);
        }
        if (!score.isGameOver()) {
            launchShapeListener();
        }
        
         
         repaint();
     }
     
     public void launchShapeListener() {
         if (shape.getIsPetrified()) {
             shape = shapeFactory.createShape(ShapeType.LSHAPE.getRandomShapeType(), heap);
             shapeCount++;
             System.out.println("Shape count: " + shapeCount); // debug line
         }
     }
}