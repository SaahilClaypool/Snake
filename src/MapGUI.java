import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/*
 * NEED: 
 * TIMERS
 * KEYBOARD LISTENDER
 * 
 */
public class MapGUI extends JPanel  {
	Timer t;
	boolean hasGrid = true;
	private int speed;
	private MapGUI self = this;
	private Map m;
	private int rows;
	private int cols;

	public MapGUI(Map m) {
		this.rows = m.getRows();
		this.cols = m.getCols();
		this.m = m;
		this.speed = 175;
	}
	public MapGUI(Map m, int speed){
		this(m);
		this.speed = speed;
	}
	public MapGUI(int rows, int cols) {
		this(new Map(rows, cols));
	}
	public MapGUI(int rows, int cols, int speed){
		this(new Map(rows, cols));
		this.speed = speed;
	}
	public void paint(Graphics g) {
		g.setColor(Color.black);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		double sHeight = this.getHeight() / (double) rows;
		double sWidth = this.getWidth() / (double) cols;
		 System.out.println(m);
		String s = m.toString();
		if(s == "END"){
			t.stop();
			g.setColor(Color.red);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());
			g.setColor(Color.black);
			g.drawString("GAME OVER", this.getWidth()/2, this.getHeight()/2);
		}
		// REMOVE LATER
		// REMOVE LATER
		// REMOVE LATER
		// REMOVE LATER
		// REMOVE LATER

		m.updateBoard();

		// REMOVE LATER
		// REMOVE LATER
		// REMOVE LATER

		// strings linear, \n is returns
		int row = 0;
		int col = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '*') {
				// blank
				g.setColor(Color.black);
				g.fillRect((int) (col * sWidth), (int) (row * sHeight),
						(int) (sWidth), (int) (sHeight));
			} else if (s.charAt(i) == '\n') {
				row++;
				col = -1;
			} else if (s.charAt(i) == 'f') {
				//System.out.println("FRUIT");
				// paint fruit
				g.setColor(Color.red);
				g.fillRect((int) (col * sWidth), (int) (row * sHeight),
						(int) (sWidth), (int) (sHeight));
			} else if (s.charAt(i) == 's') {
				g.setColor(Color.green);
				g.fillRect((int) (col * sWidth), (int) (row * sHeight),
						(int) (sWidth), (int) (sHeight));
			}
			col++;
		}
		g.setColor(Color.red);
		for(int i = 0; i < rows; i++){
			g.drawLine(0, this.getHeight() * i / rows ,this.getWidth(), this.getHeight() * i/ rows);
		}
		for(int i = 0; i < rows; i++){
			g.drawLine(this.getWidth() * i / cols, 0 ,this.getWidth() * i / rows, this.getHeight());
		}
	}

	// ActionListener timerListener = new ActionListener() {
	// public void actionPerformed(ActionEvent actionEvent) {
	// world.movePaddle(mouseX - 60);
	// world.worldStep();
	// panel.repaint();
	// ArrayList<Ball> a = world.getBalls();
	// for(Ball b: a){
	// //System.out.println("Steps" + steps);
	// if(steps == 121){
	// steps = 0;
	// }
	// b.setySpeed((int)(b.getySpeed() + b.getySpeed() / Math.abs(b.getySpeed())
	// * steps / 120. ));
	// //System.out.println(b.getySpeed());
	// }
	// steps++;
	// }
	// };
	// time = new Timer((int) (1. / 60 * 850), timerListener);

	public void run() {
		ActionListener ap = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				m.updateBoard();
				m.moveSnake(m.getS1());
				m.moveSnake(m.getS2());

				self.repaint();

			}
		};
		System.out.println("SPEED HERE " + speed);
		t = new Timer(speed, ap);
		t.start();

	}

	/*
	 * int keyCode = e.getKeyCode(); switch( keyCode ) { case KeyEvent.VK_UP: //
	 * handle up break; case KeyEvent.VK_DOWN: // handle down break; case
	 * KeyEvent.VK_LEFT: // handle left break; case KeyEvent.VK_RIGHT : //
	 * handle right break; }
	 */
	
	public static void Launch (){
		Launch(175);
	}
	public static void Launch (int speed){
		Runnable r = new Runnable(){

			@Override
			public void run() {
				JFrame j = new JFrame();
				j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);;
				j.setSize(1000, 1000);
				MapGUI m = new MapGUI(20, 20, speed);
				j.add(m);
				j.setVisible(true);
				m.setFocusable(true);
				
				m.addKeyListener(new KeyListener(){

					@Override
					public void keyPressed(KeyEvent e) {
						m.t.restart();
						//System.out.println("HEREREERERER");
						int keyCode = e.getKeyCode();
						switch (keyCode) {
						case KeyEvent.VK_UP:
							//System.out.println("HERE");
							m.m.setDirS(Snake.UP, m.m.getS1());
							m.m.moveSnake(m.m.getS1());
							m.m.setDirS(Snake.UP, m.m.getS2());
							m.m.moveSnake(m.m.getS2());
							m.repaint();
							break;
						case KeyEvent.VK_DOWN:
							m.m.setDirS(Snake.DOWN, m.m.getS1());
							m.m.moveSnake(m.m.getS1());
							m.m.setDirS(Snake.DOWN, m.m.getS2());
							m.m.moveSnake(m.m.getS2());
							m.repaint();
							break;
						case KeyEvent.VK_LEFT:
							m.m.setDirS(Snake.LEFT, m.m.getS1());
							m.m.moveSnake(m.m.getS1());
							m.m.setDirS(Snake.LEFT, m.m.getS2());
							m.m.moveSnake(m.m.getS2());
							m.repaint();
							break;
						case KeyEvent.VK_RIGHT:
							m.m.setDirS(Snake.RIGHT,m.m.getS1());
							m.m.moveSnake(m.m.getS1());
							m.m.setDirS(Snake.RIGHT,m.m.getS2());
							m.m.moveSnake(m.m.getS2());
							m.repaint();
							break;

						}
						
					}

					@Override
					public void keyReleased(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void keyTyped(KeyEvent e) {
						// TODO Auto-generated method stub
						
					}
					
				});
				//m.keyListener();
				m.run();
				
			}
			
		};
		SwingUtilities.invokeLater(r);
	}
	public static void main(String[] args) {
		
		MapGUI.Launch(175);
		

	}

}