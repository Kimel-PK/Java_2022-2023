public class Drawing implements SimpleDrawing {
	
	// rozmiar naszego plotna, inaczej obrazek.length
	int rozmiar;
	// przechowuje plotno z obrazkiem
	int[][] obrazek;
	// przechowuje obecne wspolrzedne rysowania
	Punkt obecnyPunkt = new Punkt();
	
	public void setCanvasGeometry(Geometry input) {
		// otrzymujemy plotno, tworzymy nowa tablice
		rozmiar = input.getSize();
		obrazek = new int[rozmiar][rozmiar];
		
		// kopiujemy koordynaty poczatkowe
		obecnyPunkt.x = input.getInitialFirstCoordinate();
		// plotno ma os Y odwrocona wzgledem informatycznej tablicy
		obecnyPunkt.y = rozmiar - 1 - input.getInitialSecondCoordinate();
	}
	
	public void draw(Segment segment) {
		
		int kolor = segment.getColor();
		int dlugosc = segment.getLength();
		
		switch (segment.getDirection()) {
			case 1:
				// kierunek: prawo
				for (int i = 0; i < dlugosc && obecnyPunkt.x < rozmiar; i++, obecnyPunkt.x++) {
					// to co uznajemy za X i Y w ukladzie wspolrzednych w tablicy wyglada odwrotnie z tad ten zapis
					obrazek [obecnyPunkt.y][obecnyPunkt.x] = kolor;
				}
				// po wykonaniu petli przesuwamy sie o 1 za daleko, wiec wracamy
				obecnyPunkt.x--;
				break;
			case 2:
				// kierunek: gora
				for (int i = 0; i < dlugosc && obecnyPunkt.y >= 0; i++, obecnyPunkt.y--) {
					obrazek [obecnyPunkt.y][obecnyPunkt.x] = kolor;
				}
				obecnyPunkt.y++;
				break;
			case -1:
				// kierunek: lewo
				for (int i = 0; i < dlugosc && obecnyPunkt.x >= 0; i++, obecnyPunkt.x--) {
					obrazek [obecnyPunkt.y][obecnyPunkt.x] = kolor;
				}
				obecnyPunkt.x++;
				break;
			case -2:
				// kierunek: dol
				for (int i = 0; i < dlugosc && obecnyPunkt.y < rozmiar; i++, obecnyPunkt.y++) {
					obrazek [obecnyPunkt.y][obecnyPunkt.x] = kolor;
				}
				obecnyPunkt.y--;
				break;
			// zignoruj inne wartosci
		}
	}
	
	public int[][] getPainting () {
		return obrazek;
	}
	
	public void clear () {
		// w razie gdyby ktos sprobowal wyczyscic nieistniejace plotno
		if (obrazek == null)
			return;
		
		// zerujemy cale plotno
		for (int i = 0; i < rozmiar; i++) {
			for (int j = 0; j < rozmiar; j++) {
				obrazek[i][j] = 0;
			}
		}
	}
}

// klasa reprezentujaca punkt
class Punkt {
	public int x;
	public int y;
}