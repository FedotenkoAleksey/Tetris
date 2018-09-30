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

class MainGamePanel extends JPanel implements ActionListener {

    private JFrame frame;
    /**
     * Text field to register key listeners
     *
     */
    private JTextField textField;

    /**
     * Action for KeyBinding of SPACE KEY
     */
    Action spaceAction;
    /**
     * Action for KeyBinding of DOWN KEY
     */
    Action downAction;
    /**
     * Action for KeyBinding of LEFT KEY
     */
    Action leftAction;
    /**
     * Action for KeyBinding of RIGHT KEY
     */
    Action rightAction;
    /**
     * Action for KeyBinding of UP KEY
     */
    Action upAction;

    Heap heap; // heap of figures
    ShapeFactory shapeFactory;
    /**
     * Variable shapeCount counts shapes since the beginning of a game session
     */
    int shapeCount;
    Score score;

    /**
     * Variable timeCount counts number of "frames" which is actually calls to
     * actionPerformed() method of Timer object. It is used mainly to make a
     * fall move every 15 counts.
     */
    int timeCount = 0;

    private final int TIME_COUNTS_IN_ONE_FALL_MOVE = 15;

    /** Current shape which is falling onto a heap.
     * 
     */
    Shape shape;

    /** Swing object to implement animation. 
     * 
     */
    Timer timer;
    /** Constant SPEED which is a parameter for Timer of Swing constructor and 
     * determines a speed of frames change.
     */
    private final int SPEED = 50;

    /** ControllersPanel constructor which invokes prepareGUI() method.
     * 
     */
    public MainGamePanel() {
        prepareGUI();
    }

    public static void main(String[] args) {
        new MainGamePanel();
    }

    /** prepareGUI() method prepares all the GUI elements of the game, 
     * does some key binding, initializes Heap, Score and Shape objects and sets
     * variable shapeCount to 1.
     */
    public void prepareGUI() {
        // JTextField to add Actions to
        textField = new JTextField();

        // set size of textField to zero because it's 
        // only needed to register key listeners
        textField.setPreferredSize(new Dimension(0, 0));
        
        // =============== START OF BLOCK OF KEY BINDGING STATEMENTS ========== 

        // initialize an action and specify functionality of the action
        leftAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape.leftMove(heap);
            }
        };

        // initialize an action and specify functionality of the action
        rightAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape.rightMove(heap);
            }
        };

        // initialize an action and specify functionality of the action
        downAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape.dive(heap);
            }
        };

        // initialize an action and specify functionality of the action
        spaceAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape.rotate(heap);
            }
        };

        // initialize an action and specify functionality of the action
        upAction = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shape.rotate(heap);
            }
        };

        // the next lines do key binding
        textField.getInputMap().put(KeyStroke.getKeyStroke("LEFT"),
                "doLeftAction");
        textField.getActionMap().put("doLeftAction", leftAction);

        textField.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"),
                "doRightAction");
        textField.getActionMap().put("doRightAction", rightAction);

        textField.getInputMap().put(KeyStroke.getKeyStroke("DOWN"),
                "doDownAction");
        textField.getActionMap().put("doDownAction", downAction);

        textField.getInputMap().put(KeyStroke.getKeyStroke("SPACE"),
                "doSpaceAction");
        textField.getActionMap().put("doSpaceAction", spaceAction);

        textField.getInputMap().put(KeyStroke.getKeyStroke("UP"),
                "doUpAction");
        textField.getActionMap().put("doUpAction", upAction);
        
        // =============== END OF BLOCK OF KEY BINDGING STATEMENTS ==========

        this.add(textField);

        score = new Score();
        heap = new Heap(score);

        // Initialize a random shape object.
        shape = shapeFactory.createShape(ShapeType.L_SHAPE.getRandomShapeType(), heap);
        shapeCount = 1;

        frame = new JFrame();
        frame.add(this);
        frame.setSize(540, 500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Placing a game window in the 1/4 of screen width and 1/40 of
        // screen height.
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();
        int screenWidth = screenSize.width;
        int screenHeight = screenSize.height;
        frame.setLocation(screenWidth / 4, screenHeight / 40);

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
        g2.drawString(shapeCount, Box.SIZE * 10 + 60, 50); // TODO any access to this constant should be made only through the getter method.

        String scoreText = "Score:                  " + score.getScore();
        g2.drawString(scoreText, Box.SIZE * 10 + 60, 90); // TODO any access to this constant should be made only through the getter method.

        int efficiency = (int) Math.round((((double) score.getScore() * 10.0) / ((double) this.shapeCount * 4.0)) * 100.0);
        String effText = "Efficiency:           " + efficiency;
        g2.drawString(effText, Box.SIZE * 10 + 60, 130); // TODO any access to this constant should be made only through the getter method.

        if (score.isGameOver()) {
            String gameOverString = "GAME OVER";
            font = new Font("Calibri", Font.PLAIN, 45);
            g2.setColor(Color.ORANGE);
            g2.setFont(font);
            g2.drawString(gameOverString, Box.SIZE * 10 + 60, 250); // TODO any access to this constant should be made only through the getter method.
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

        if ((timeCount % TIME_COUNTS_IN_ONE_FALL_MOVE) == 0) {
            timeCount -= TIME_COUNTS_IN_ONE_FALL_MOVE;
            shape.fall(heap);
        }
        if (!score.isGameOver()) {
            launchShapeListener();
        }

        repaint();
    }

    public void launchShapeListener() {
        if (shape.getIsPetrified()) {
            shape = shapeFactory.createShape(ShapeType.L_SHAPE.getRandomShapeType(), heap);
            shapeCount++;
            System.out.println("Shape count: " + shapeCount); // debug line
        }
    }
}
