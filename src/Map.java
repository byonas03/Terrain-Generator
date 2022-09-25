import java.util.LinkedList;

public class Map {
	double[][] density_map = new double[1920][1080];

	public Map() {
		generateField();
	}

	public void generateField() {
		LinkedList<Integer[]> que = new LinkedList<Integer[]>();
		int[][] starts = new int[300][2];
		for (int i = 0; i < starts.length; i++) {
			starts[i][0] = (int) (Math.random() * 1920);
			starts[i][1] = (int) (Math.random() * 1080);
			density_map[starts[i][0]][starts[i][1]] = 200 + (Math.random() * 100) - 50;
			que.add(new Integer[] { starts[i][0], starts[i][1] });
		}
		boolean[][] completition_map = new boolean[1920][1080];
		while (!que.isEmpty()) {
			Integer[] cur = que.poll();

			Integer[] a = new Integer[] { cur[0] + 1, cur[1] };
			Integer[] b = new Integer[] { cur[0] - 1, cur[1] };
			Integer[] c = new Integer[] { cur[0], cur[1] + 1 };
			Integer[] d = new Integer[] { cur[0], cur[1] - 1 };

			if (inBound(a[0], a[1]) && !completition_map[a[0]][a[1]]) {
				density_map[a[0]][a[1]] = (density_map[cur[0]][cur[1]]) + Math.random() * 1.5 - 2;
				if (density_map[a[0]][a[1]] < 0)
					density_map[a[0]][a[1]] = 0;
				que.add(a);
				completition_map[a[0]][a[1]] = true;
			}
			if (inBound(b[0], b[1]) && !completition_map[b[0]][b[1]]) {
				density_map[b[0]][b[1]] = (density_map[cur[0]][cur[1]]) + Math.random() * 1.5 - 2;
				if (density_map[b[0]][b[1]] < 0)
					density_map[b[0]][b[1]] = 0;
				que.add(b);
				completition_map[b[0]][b[1]] = true;
			}
			if (inBound(c[0], c[1]) && !completition_map[c[0]][c[1]]) {
				density_map[c[0]][c[1]] = (density_map[cur[0]][cur[1]]) + Math.random() * 1.5 - 2;
				if (density_map[c[0]][c[1]] < 0)
					density_map[c[0]][c[1]] = 0;
				que.add(c);
				completition_map[c[0]][c[1]] = true;
			}
			if (inBound(d[0], d[1]) && !completition_map[d[0]][d[1]]) {
				density_map[d[0]][d[1]] = (density_map[cur[0]][cur[1]]) + Math.random() * 1.5 - 2;
				if (density_map[d[0]][d[1]] < 0)
					density_map[d[0]][d[1]] = 0;
				que.add(d);
				completition_map[d[0]][d[1]] = true;
			}
		}
		noise(2);
		extreme();
	}

	public void noise(int t) {
		for (int a = 0; a < t; a++) {
			for (int i = 0; i < density_map.length; i++) {
				for (int j = 0; j < density_map[i].length; j++) {
					int sum = 0;
					for (int x = i; x < i + 5; x++) {
						for (int y = j; y < j + 5; y++) {
							if (x < 1920 && y < 1080)
								sum += density_map[x][y];
						}
					}
					sum /= 25;
					for (int x = i; x < i + 5; x++) {
						for (int y = j; y < j + 5; y++) {
							if (x < 1920 && y < 1080)
								density_map[x][y] = sum;
						}
					}
				}
			}
		}
	}

	public void extreme() {
		for (int i = 0; i < density_map.length; i++) {
			for (int j = 0; j < density_map[i].length; j++) {
				density_map[i][j] = 255 / (1 + Math.pow(Math.E, -.02 * (density_map[i][j] - 170 / 2)));
			}
		}
	}

	public boolean inBound(int a, int b) {
		return a >= 0 && b >= 0 && a < 1920 && b < 1080;
	}
}
