import java.util.List;
import java.util.Map;
import java.util.Set;

public interface LinesInterface {

	/**
	 * Punkt
	 */
	public interface Point {
		/**
		 * Nazwa odcinka. Różne punkty mają różne nazwy.
		 * 
		 * @return nazwa odcinka
		 */
		public String getName();
	}

	/**
	 * Odcinek. Odcinek nie ma kierunku. Równie dobrze prowadzi od krańca 1 do 2 jak
	 * i od 2 do 1.
	 */
	public interface Segment {
		/**
		 * Pierwszy kraniec odcinka
		 * 
		 * @return punkt będący pierwszym krańcem odcinka
		 */
		public Point getEndpoint1();

		/**
		 * Drugi kraniec odcinka
		 * 
		 * @return punkt będący drugim krańcem odcinka
		 */
		public Point getEndpoint2();
	}

	/**
	 * Metoda pozwala na dodanie zbioru punktów.
	 * 
	 * @param points zbiór punktów
	 */
	public void addPoints(Set<Point> points);

	/**
	 * Metoda pozwala na dodanie zbioru odcinków łączących punkty.
	 * 
	 * @param segments zbiór odcinków
	 */
	public void addSegments(Set<Segment> segments);

	/**
	 * Metoda wyszukuje połączenie pomiędzy parą punktów. Każde poprawne połączenie
	 * podanej pary i bez pętli uznane zostanie za poprawne. W przypadku braku
	 * połączenia metoda zwraca listę o rozmiarze 0.
	 * 
	 * @param start punkt początkowy
	 * @param end   punkt końcowy
	 * @return lista kolejnych odcinków prowadzących od start do end.
	 */
	public List<Segment> findConnection(Point start, Point end);

	/**
	 * Metoda generuje mapę, której kluczem jest punkt. Punkt wskazuje na zbiór
	 * odcinków, których jednym z punktów krańcowych jest ten punkt.
	 * 
	 * @return mapa powiązań punktów z odcinkami
	 */
	public Map<Point, Set<Segment>> getMapEndpointToSegments();

	/**
	 * Metoda generuje mapę, której kluczem jest punkt. Punkt jest punktem startowym
	 * ścieżek. We wskazywanej przez punkt mapie mają znaleźć się punkty, które
	 * można osiągnąć poprzez trasę zawierającą 1, 2, 3 lub 4 odcinki. Jeśli trasa o
	 * określonej długości nie istnieje zbiór punktów nią dostępny jest zbiorem
	 * pustym (zbiór o rozmiarze 0).
	 * 
	 * @return mapa powiązań punktów z innymi punktami
	 */
	public Map<Point, Map<Integer, Set<Point>>> getReachableEndpoints();

}
