import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class Main {
	private static int key_pressed;
	public static void main(String[] args) {

		//set up key listener
		JFrame frame = new JFrame("Key Listener");
		Container contentPane = frame.getContentPane();
		KeyListener listener = new KeyListener() {
			@Override
			public void keyPressed(KeyEvent event) {
			    if (event.getKeyCode() == KeyEvent.VK_UP) {
			    	key_pressed = Snake.UP;
			    } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
			    	key_pressed = Snake.LEFT;
			    } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
			    	key_pressed = Snake.DOWN;
			    } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
			    	key_pressed = Snake.RIGHT;
			    }
			}
			@Override
			public void keyReleased(KeyEvent event) {}
			@Override
			public void keyTyped(KeyEvent event) {}
  		};	
 		JTextField textField = new JTextField();
		textField.addKeyListener(listener);
		contentPane.add(textField, BorderLayout.NORTH);
		frame.pack();
		frame.setVisible(true);

		//set up snake and run program
		intro();
		Grid grid = new Grid(80, 50);
		Snake s = new Snake();
		key_pressed = -1;

		do {
			grid.display(s);
			if (key_pressed != -1) {
				s.turn(key_pressed);
			}
			delay(50);
		} while (s.move(Grid.WIDTH, Grid.HEIGHT));
	}

	public static void intro() {
		clear_screen();
		System.out.println("Welcome...");
		delay(1500);
	}

	public static void clear_screen() {
		System.out.print("\033[H\033[2J");  
    		System.out.flush();  
	}

	public static void delay(int ms) {
		try {
    			Thread.sleep(ms);
		} catch(InterruptedException ex) {
  			Thread.currentThread().interrupt();
		}
	}
}
