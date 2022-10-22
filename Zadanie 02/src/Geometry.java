
public interface Geometry {
	/**
	 * Rozmiar płótna (dwuwymiarowej tablicy). Dla uproszczenia zakładamy, że
	 * tablica jest kwadratowa.
	 * 
	 * @return rozmiar boku płótna
	 */
	public int getSize();

	/**
	 * Pierwsza współrzędna (indeks) pozycji, od której rysowany będzie pierwszy
	 * odcinek. Pierwszy odcinek zaczyna się na pozycji:
	 * <tt>canvas[ getInitialFirstCoordinate() ][ getInitialSecondCoordinate() ]</tt>
	 * 
	 * @return pierwsza współrzędna punktu, od którego rozpocznie się rysowanie
	 */
	public int getInitialFirstCoordinate();

	/**
	 * Druga współrzędna (indeks) pozycji, od której rysowany będzie pierwszy
	 * odcinek. Pierwszy odcinek zaczyna się na pozycji:
	 * <tt>canvas[ getInitialFirstCoordinate() ][ getInitialSecondCoordinate() ]</tt>
	 * 
	 * @return druga współrzędna punktu, od którego rozpocznie się rysowanie
	 */
	public int getInitialSecondCoordinate();
}
