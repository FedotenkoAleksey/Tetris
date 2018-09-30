package rotation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.Timer;

class RotationPanel extends JPanel implements ActionListener {
    JFrame frame;
    JTextField textfield;
    Action spaceAction;
    List<Shape>shapes;
    int timeCount = 0;
    Heap heap;
    
    Shape rod;    
    Shape lShape;
    Shape jShape;
    Shape tShape;
    Shape zShape;
    Shape sShape;
    Shape cube;
    
    Timer timer;
    int SPEED = 50;
    
    public RotationPanel() {        
        prepareGUI();        
        
    }
    
    public static void main(String[] args) {
        new RotationPanel();
    }
    
    public void prepareGUI() {           
        // JTextField to add SpaceAction to it
        textfield = new JTextField();        
        textfield.setPreferredSize(new Dimension(0, 0));
        
        spaceAction = new SpaceAction();
        
        // the following two lines do key binding
        textfield.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), 
                "doSpaceAction");
        textfield.getActionMap().put("doSpaceAction", spaceAction);
        
        this.add(textfield);         
        
        shapes = new ArrayList<>(); 
        
        rod = new Rod();
        rod.setMainBox(5, 6);
        
        lShape = new LShape();
        lShape.setMainBox(11, 5);
        
        jShape = new JShape();
        jShape.setMainBox(17, 5);
        
        tShape = new TShape();
        tShape.setMainBox(5, 13);
        
        zShape = new ZShape();
        zShape.setMainBox(10, 13);
        
        sShape = new SShape();
        sShape.setMainBox(18, 13);
        
        cube = new Cube();
        cube.setMainBox(6, 18);
        
        shapes.add(rod);
        shapes.add(lShape);
        shapes.add(jShape);
        shapes.add(tShape);
        shapes.add(zShape);
        shapes.add(sShape);
        shapes.add(cube);
        
        
        frame = new JFrame();
        frame.setSize(500, 500);
        frame.add(this);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        frame.setLocation(screenWidth/4, screenHeight/20);
        
        timer = new Timer(SPEED, this);
    }
    
        // class SpaceAction defines what will occur when space key is pressed
    class SpaceAction extends AbstractAction {
        @Override
        public void actionPerformed(ActionEvent event) {
            for (Shape shape: shapes) {
                shape.rotate(heap);
            }
        }
    } // end of SpaceAction class
    
    public void drawBlackField(Graphics2D g2) {
        g2.setColor(Color.BLACK);
        g2.fillRect(0, 0, getWidth(), getHeight());
    }
    
    public void drawFrameLines(Graphics2D g2) {
        g2.setColor(Color.WHITE);
        g2.drawLine(20, 20, 220, 20);
        g2.drawLine(220, 20, 220, 420);
        g2.drawLine(220, 420, 20, 420);
        g2.drawLine(20, 420, 20, 20);
        
    }
    
    @Override
    public void paintComponent (Graphics g) {               
        Graphics2D g2 = (Graphics2D) g;        
        drawBlackField(g2);
        
        // drawFrameLines(g2);
        
        for (Shape shape: shapes) {
            shape.draw(g2);
        }        
        
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent event) {
        
        // fuctional falling method
        /*
        timeCount++;
        if ((timeCount % 15) == 0) {
            timeCount -= 15;
            for (Shape shape : shapes) {
                shape.fall();
            }
        }
        */
        repaint();
    }
    
    
} // end of RotationPanel class