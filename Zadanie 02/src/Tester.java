public class Tester {
	
	static int[][][] testy = {
		// test 1 - z przykladu
		{
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 5, 5, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 1, 0, 0, 4, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 2, 0, 0, 4, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 2, 0, 0, 4, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 3, 3, 3, 4, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		},
		// test 2 - nakladajace sie linie, linie wychodzace za plotno
		{
			{0, 0, 0, 0, 0, 8, 8, 8, 8, 8, 8, 8, 6},
			{0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 6},
			{0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 6},
			{0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 6},
			{0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 6},
			{0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 6},
			{0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 6},
			{5, 4, 4, 4, 8, 8, 8, 8, 3, 4, 4, 4, 4},
			{5, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0},
			{5, 0, 0, 0, 1, 0, 0, 0, 3, 0, 0, 0, 0},
			{2, 2, 2, 2, 1, 0, 0, 0, 3, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 3, 0, 0, 0, 0}
		},
		// test 3 - plotno wyczyszczone w trakcie
		{
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 3, 3, 3, 4, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		}
	};
	
	public static void main(String[] args) {
		
		int zaliczoneTesty = 0;
		
		// test 1 - z przykladu
		Drawing drawing = new Drawing();
		drawing.setCanvasGeometry(new TestGeometry(13, 3, 6));
		
		drawing.draw(new TestSegment(1, 3, 1));
		drawing.draw(new TestSegment(1, 3, 2));
		drawing.draw(new TestSegment(2, 4, 3));
		drawing.draw(new TestSegment(-1, 5, 4));
		drawing.draw(new TestSegment(-2, 2, 5));
		
		int[][] obraz = drawing.getPainting();
		
		if (Sprawdz(obraz, testy[0])) {
			System.out.println("Test 1 zaliczony!");
			zaliczoneTesty++;
		} else {
			System.out.println("Test 1 niezaliczony!");
			Wypisz (obraz, testy[0], 0);
		}
		
		// test 2 - nakladajace sie linie, linie wychodzace za plotno
		drawing = new Drawing();
		drawing.setCanvasGeometry(new TestGeometry(13, 6, 5));
		
		drawing.draw(new TestSegment(-1, 10, 7));
		drawing.draw(new TestSegment(2, 20, 8));
		drawing.draw(new TestSegment(1, 8, 6));
		drawing.draw(new TestSegment(-2, 30, 4));
		drawing.draw(new TestSegment(1, 4, 5));
		drawing.draw(new TestSegment(2, 5, 2));
		drawing.draw(new TestSegment(-1, 4, 1));
		drawing.draw(new TestSegment(2, 5, 8));
		drawing.draw(new TestSegment(1, 40, 3));
		
		obraz = drawing.getPainting();
		
		if (Sprawdz(obraz, testy[1])) {
			System.out.println("Test 2 zaliczony!");
			zaliczoneTesty++;
		} else {
			System.out.println("Test 2 niezaliczony!");
			Wypisz (obraz, testy[1], 1);
		}
		
		// test 3 - plotno wyczyszczone w trakcie
		drawing = new Drawing();
		drawing.setCanvasGeometry(new TestGeometry(13, 0, 0));
		
		drawing.draw(new TestSegment(1, 5, 1));
		drawing.draw(new TestSegment(2, 5, 2));
		drawing.clear();
		drawing.draw(new TestSegment(2, 4, 3));
		drawing.draw(new TestSegment(1, 4, 4));
		
		obraz = drawing.getPainting();
		
		if (Sprawdz(obraz, testy[2])) {
			System.out.println("Test 3 zaliczony!");
			zaliczoneTesty++;
		} else {
			System.out.println("Test 3 niezaliczony!");
			Wypisz (obraz, testy[2], 2);
		}
		
		if (zaliczoneTesty == 3)
			System.out.println("Wszystkie testy zostaly zaliczone!");
	}
	
	static Boolean Sprawdz (int[][] obrazek, int[][] test) {
		
		for (int i = 0; i < obrazek.length; i++) {
			for (int j = 0; j < obrazek.length; j++) {
				if (obrazek[i][j] != test[i][j])
					return false;
			}
		}
		
		return true;
	}
	
	static void Wypisz (int[][] obrazek, int[][] test, int numerTestu) {
		System.out.println("Otrzymano:                      Oczekiwano:");
		for (int i = obrazek.length - 1; i >= 0; i--) {
			System.out.println();
			for (int j = 0; j < obrazek.length; j++) {
				System.out.print(" " + obrazek[j][i]);
			}
			System.out.print("      ");
			for (int j = 0; j < obrazek.length; j++) {
				System.out.print(" " + testy[numerTestu][j][i]);
			}
		}
		System.out.println();
		System.out.println();
	}
}

class TestGeometry implements Geometry {
	
	int size;
	int x;
	int y;
	
	public TestGeometry (int _size, int _x, int _y) {
		size = _size;
		x = _x;
		y = _y;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getInitialFirstCoordinate() {
		return x;
	}
	
	public int getInitialSecondCoordinate() {
		return y;
	}
}

class TestSegment implements Segment {
	
	int dir;
	int lenght;
	int color;
	
	public TestSegment (int _dir, int _length, int _color) {
		dir = _dir;
		lenght = _length;
		color = _color;
	}
	
	public int getDirection() {
		return dir;
	}
	
	public int getLength() {
		return lenght;
	}
	
	public int getColor() {
		return color;
	}
}