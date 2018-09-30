/* The code is a test canvas for Tetris project */

/* TODO:
   By some reason paintComponent() can't be repaint()
*/

package tests;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;

public class RotateTest extends JPanel implements ActionListener, KeyListener {

	private JFrame frame;
	private final int winWidth = 350;
	private final int winHeight = 560;
	private final int boxSize = 25;
	private JTextField textfield;
	private Graphics2D g2;
    private Line newLine;
    // debug variable
    private int countPaint = 0;
    private Timer timer;

	RotateTest() {
		prepareGUI();
	}

	public static void main(String[] args) {
		RotateTest rotatetest = new RotateTest();
	}

	public void prepareGUI() {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // create JTextField of size zero to add KeyListener to it
		textfield = new JTextField();
		textfield.addKeyListener(this);
		this.add(textfield);
		textfield.setPreferredSize(new Dimension(0, 0));

		frame.add(this);
		frame.setSize(winWidth, winHeight);
		frame.setLocation(300, 10);
		frame.setVisible(true);
        
        int speedFrame = 20;
        timer = new Timer(speedFrame, this);


	}
    
    // processing key events
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left is pressed!");
            newLine.rotate();
            repaint();
            
		}
	}

	public void paintComponent(Graphics g) {
        countPaint++;
        // System.out.println("CountPaint: " + countPaint);
        
		g2 = (Graphics2D) g;
		super.paintComponent(g2);

		// set black background
		g2.setColor(Color.BLACK);
		g2.fillRect(0, 0, getWidth(), getHeight());

		       
        // draw Line Tetris Figure
        newLine = new Line();
        g2.setPaint(newLine.boxes[0].getColor());
        
        // System.out.println();
        // System.out.println("Drawing Line Figure");
        for (int i = 0; i < newLine.boxes.length; i++) {
            g2.fill(new Rectangle2D.Float(newLine.boxes[i].getX() * boxSize,
                                            newLine.boxes[i].getY() * boxSize,
                                            boxSize,
                                            boxSize));
        }
        
        // draw lines of a GameBox
        g2.setColor(Color.WHITE);
        g2.drawLine(10, 10, 260, 10);
        g2.drawLine(260, 10, 260, 510);
        g2.drawLine(260, 510, 10, 510);
        g2.drawLine(10, 510, 10, 10);
        
        timer.start();
	}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

	

}