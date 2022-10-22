
public interface SimpleDrawing {
	/**
	 * Metoda umożliwia przekazanie obiektu Geometry.
	 * 
	 * @param input obiekt Geometry
	 */
	public void setCanvasGeometry(Geometry input);

	/**
	 * Metoda zleca narysowanie pojedynczego odcinka. Początek pierwszego odcinka
	 * wskazuje obiekt Geometry. Kolejny odcinek zaczyna się tam, gdzie poprzedni
	 * się zakończył. Pierwszy punkt nowego odcinka nadpisuje ostatni punkt
	 * poprzedniego. <br>
	 * UWAGA: obszar płótna jest ograniczony. Jeśli długość zleconego do narysowania
	 * odcinka jest zbyt duża i spowodowałaby wyjście poza obszar płótna, należy
	 * odpowiednio długość zmniejszyć, tak, aby ostatni "piksel" odcinka wypadł na
	 * brzegu płótna.
	 * 
	 * @param segment - segment do narysowania
	 */
	public void draw(Segment segment);

	/**
	 * Metoda zwraca dwuwymiatową tablicę liczb całkowitych reprezentującą aktualny
	 * stan obrazka. Przed ustawieniem obiektu Geometry metoda zwraca null.
	 * 
	 * @return tablica zawierająca obrazek
	 */
	public int[][] getPainting();

	/**
	 * Metoda czyści płótno (ustawia wszystkie pozycje-"pixele" na 0).
	 */
	public void clear();
}
