import java.util.Random;
import java.util.ArrayList;

public class Snake {

	//enum directions for each Node of the snake
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;


	//data members
	private int m_length;
	private Node head;
	/* This list does not contain the head Node; only
	 * the Nodes that follow.
	 */
	private ArrayList<Node> nodes;


	//stores an x and y value
	private class Node {
		int x_coord;
		int y_coord;
		int dir;

		Node(int x, int y, int d) {
			x_coord = x;
			y_coord = y;
			dir = d;
		}
	}


	//constructor
	Snake() {
		Random rand = new Random();
		nodes = new ArrayList<Node>();
		int x = Grid.WIDTH/2;
		int y = Grid.HEIGHT/2;
		int d = rand.nextInt(4);
		head = new Node(x, y, d);

		m_length = 0;
	}


	//getter methods
	public int length() {
		return m_length;
	}
	public int x() {
		return head.x_coord;
	}
	public int y() {
		return head.y_coord;
	}


	/* Moves one Node in the direction of the head.
	 * The parameters width and height indicate one more than
	 * the maximum index of the x and y Nodes respectively.
	 */
	public boolean move(int width, int height) {
		if (m_length > 0) {
			for (int i = m_length-1; i > 0; i--) {
				Node temp = nodes.get(i);
				Node prev = nodes.get(i-1);
				temp.x_coord = prev.x_coord;
				temp.y_coord = prev.y_coord;
				temp.dir = prev.dir;
			}
			nodes.get(0).x_coord = head.x_coord;
			nodes.get(0).y_coord = head.y_coord;
		}

		switch(head.dir) {
			case UP:
				head.y_coord--;
				Potato.delay(20);
				break;
			case LEFT:
				head.x_coord--;
				break;
			case DOWN:
				head.y_coord++;
				Potato.delay(20);
				break;
			case RIGHT:
				head.x_coord++;
				break;
		}
		System.out.println("Length: " + m_length);
		for (int i = 0; i < m_length; i++) {
			Node temp = nodes.get(i);
			if (head.x_coord == temp.x_coord && head.y_coord == temp.y_coord) {
				return false;
			}
		}
		return (head.x_coord >= 0 && head.x_coord < width
			&& head.y_coord >= 0 && head.y_coord < height);
	}


	/* This function is called when an arrow key is pressed.
	 * It changes the direction of the head according to the
	 * key pressed.
	 */
	public void turn(int key_pressed) {
		if ((key_pressed == UP && head.dir == DOWN ||
				 key_pressed == DOWN && head.dir == UP ||
				 key_pressed == LEFT && head.dir == RIGHT ||
				 key_pressed == RIGHT && head.dir == LEFT) &&
				 m_length > 0) {  
			return;
		}
		head.dir = key_pressed;
	}


	/* This function takes in a 2D array of spaces and fills 
	 * it in with an 'o' at every Node of the snake.
	 */
	public void fill_grid(char[][] grid) {
		grid[head.x_coord][head.y_coord] = 'o';
		for (int i = 0; i < m_length; i++) {
			Node current = nodes.get(i);
			if (current.x_coord >= 0 && current.y_coord >= 0 &&
					current.x_coord < Grid.WIDTH && current.y_coord < Grid.HEIGHT){
				grid[current.x_coord][current.y_coord] = 'o';
			}
		}
	}


	/* This function is called to check whether or not the
	 * snake has hit the x on the grid.
	 */
	public boolean get_point(int x, int y) {
		if (head.x_coord == x && head.y_coord == y) {
			this.append();
			return true;
		}
		else return false;
	}


	/* This function is called when the snake gets a point.
	 * It adds another Node to the end of the snake's tail.
	 */
	private void append() {
		Node last;
		if (m_length > 0) {
			last = nodes.get(m_length-1);
		}
		else {
			last = head;
		}

		int new_x = last.x_coord;
		int new_y = last.y_coord;

		switch(last.dir) {
			case UP:
				new_y++;
				break;
			case LEFT:
				new_x++;
				break;
			case DOWN:
				new_y--;
				break;
			case RIGHT:
				new_x--;
				break;
		}

		Node new_coord = new Node(new_x, new_y, last.dir);
		nodes.add(new_coord);

		m_length++;
	}
}