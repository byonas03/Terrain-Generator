import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Window {
	JFrame f = new JFrame("Perlin Noiseless Terrain Generation");
	Map m = new Map();

	public Window() {
		m = new Map();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setExtendedState(JFrame.MAXIMIZED_BOTH);
		f.setVisible(true);
		paint(f.getGraphics());
	}

	public void paint(Graphics g) {
		BufferedImage bi = new BufferedImage(1920, 1080, BufferedImage.TYPE_INT_RGB);
		Graphics2D f = (Graphics2D) bi.getGraphics();
		Color wheel = null;

		double[][] density_map = m.density_map;
		int quality = 5;
		for (int i = 0; i < density_map.length; i += quality) {
			for (int j = 0; j < density_map[i].length; j += quality) {

				System.out.println(i + "/" + j);
				if (density_map[i][j] < 120)
					g.setColor(new Color(0, 0, (int) (2 * density_map[i][j]) + 15));
				else if (density_map[i][j] < 140)
					g.setColor(Color.YELLOW);
				else if (density_map[i][j] < 200)
					g.setColor(new Color(0, 250 - (int) density_map[i][j], 0));
				else if (density_map[i][j] < 220)
					g.setColor(new Color(300 - (int) (density_map[i][j]), 300 - (int) (density_map[i][j]),
							300 - (int) (density_map[i][j])));
				else if (density_map[i][j] < 230)
					g.setColor(Color.WHITE);
				// g.setColor(new Color((int)(density_map[i][j]), (int)(density_map[i][j]),
				// (int)(density_map[i][j])));

				g.fillRect(i, j, quality, quality);
			}
		}
		// g.drawImage(bi, 1920, 1080, null);
	}

	private int colorToRGB(int alpha, int red, int green, int blue) {
		int newPixel = 0;
		newPixel += alpha;
		newPixel = newPixel << 8;
		newPixel += red;
		newPixel = newPixel << 8;
		newPixel += green;
		newPixel = newPixel << 8;
		newPixel += blue;

		return newPixel;
	}

	public static void main(String args[]) {
		Window w = new Window();
	}
}
