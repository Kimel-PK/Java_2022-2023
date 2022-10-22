
public interface Segment {
	/**
	 * Kierunek rysowania odcinka. Kierunek ustalany jest poprzez wskazanie
	 * inkrementacji/dekrementacji odpowiedniej współrzędnej tablicy reprezentującej
	 * płótno, po którym rysujemy: <tt>
	 * canvas[ pierwszy indeks ][ drugi_indeks ]
	 * </tt>
	 * 
	 * Obowiązuje następująca umowa:
	 * <ul>
	 * <li>1 - inkrementacja pierwszego indeksu tablicy
	 * <li>2 - inkrementacja drugiego indeksu tablicy
	 * <li>-1 - dekrementacja pierwszego indeksu tablicy
	 * <li>-2 - dekrementacja drugiego indeksu tablicy
	 * <li>Inne wartości są niedozwolone i jeśli wystąpią odcinek nie powinien być
	 * narysowany na płótnie
	 * </ul>
	 * 
	 * @return liczba całkowita odpowiadająca kierunkowi rysowania
	 */
	public int getDirection();

	/**
	 * Metoda zwraca długość odcinka. Długość to liczba "pikseli", które należą do
	 * odcinka.
	 * 
	 * @return liczba "pikseli" wchodzących w skład odcinka
	 */
	public int getLength();

	/**
	 * Metoda zwraca liczbę całkowitą reprezentującą kolor rysowanego odcinka.
	 * 
	 * @return kolor odcinka
	 */
	public int getColor();
}
