import java.util.Random;
import java.util.ArrayList;

public class Grid {

	public static int WIDTH;
	public static int HEIGHT;

	/* Coordinates of the x where the snake can get 
	 * its next point.
	 */
	private int x;
	private int y;

	private char[][] m_grid;
	private ArrayList<Enemy> enemies;

	//constructor
	Grid(int w, int h) {
		WIDTH = w;
		HEIGHT = h;
		m_grid = new char[WIDTH][HEIGHT];
		x = 12;
		y = 13;
	}

	public void display(Snake s) {
		Random r = new Random();
		if (s.get_point(x, y)) {
			x = r.nextInt(WIDTH-2) + 1;
			y = r.nextInt(HEIGHT-2) + 1;
		}

		Potato.clear_screen();

		//create a grid of spaces
		for (int i = 0; i < WIDTH; i++) {
			for (int j = 0; j < HEIGHT; j++) {
				m_grid[i][j] = ' ';
			}
		}

		s.fill_grid(m_grid);
		m_grid[x][y] = 'x';

		for (int i = 0; i < WIDTH + 2; i++) {
			System.out.print('#');
		}
		System.out.println();

		for (int i = 0; i < HEIGHT; i++) {
			System.out.print('#');
			for (int j = 0; j < WIDTH; j++) {
				System.out.print(m_grid[j][i]);
			}
			System.out.println('#');
		}
		for (int i = 0; i < WIDTH + 2; i++) {
			System.out.print('#');
		}
		System.out.println();
	}
}