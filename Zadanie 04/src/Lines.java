import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Lines implements LinesInterface {
	
	// klasa graf przechowuje punkty z dodatkowymi informacjami jak lista sasiadow
	Graf graf = new Graf ();
	
	public void addPoints(Set<LinesInterface.Point> points) {
		// dla kazdego punktu otrzymanego z zewnatrz tworzymy wlasny obiekt implementujacy ten sam interfejs
		// dzieki temu mozemy przechowywac w nim dodatkowe informacje
		for (LinesInterface.Point punkt : points)
			graf.punkty.put (punkt.getName(), new Point (punkt.getName()));
	}
	
	public void addSegments(Set<LinesInterface.Segment> segments) {
		// nie potrzebujemy przechowywac nigdzie segmentow jesli nasze punkty posiadaja liste sasiadow
		for (LinesInterface.Segment segment : segments) {
			graf.punkty.get (segment.getEndpoint1 ().getName()).sasiedzi.add (graf.punkty.get (segment.getEndpoint2 ().getName()));
			graf.punkty.get (segment.getEndpoint2 ().getName()).sasiedzi.add (graf.punkty.get (segment.getEndpoint1 ().getName()));
		}
	}
	
	public List<LinesInterface.Segment> findConnection(LinesInterface.Point start, LinesInterface.Point end) {
		
		// wyszukiwanie trasy korzysta a algorytmu DFS (https://www.geeksforgeeks.org/depth-first-search-or-dfs-for-a-graph/)
		// przed kazdym szukaniem musimy wyczyscic powiazania punktow
		graf.WyczyscPolaczenia();
		
		// lista segmentow ktora zwrocimy na koniec
		List<LinesInterface.Segment> trasa = new LinkedList<LinesInterface.Segment> ();
		
		// pobieramy z naszego grafu punkt poczatkowy
		// i tak bedziemy analizowac caly graf dlatego nie potrzebny nam punkt koncowy
		Point startGraf = graf.punkty.get (start.getName());
		
		// ustawiamy w naszym starcie nieistniejacy punkt zeby DFS uznal go za odwiedzonego
		startGraf.poprzednik = new Point("NULL");
		startGraf.krokiOdStartu = 0;
		
		// uruchamiamy wyszukiwanie sciezki z uzyciem algorytmu dfs
		DFS (startGraf, 1);
		
		// tymczasowa zmienna do otworzenia sciezki korzystajac z danych ktore ustalil DFS
		// zaczynamy z pobrania punktu koncowego z naszego grafu
		Point obecnyPunkt = graf.punkty.get(end.getName());
		// dopoki tymczasowy punkt nie jest punktem startowym cofamy sie po Point.poprzednik
		while (obecnyPunkt.getName() != start.getName()) {
			// dodajemy segment do trasy
			trasa.add (new Segment (obecnyPunkt.poprzednik, obecnyPunkt));
			// cofamy sie w sciezce
			obecnyPunkt = obecnyPunkt.poprzednik;
		}
		
		// sciezka jest okreslana od tylu, przed zwroceniem listy odwracamy segmenty
		Collections.reverse (trasa);
		return trasa;
	}
	
	// algorytm DFS do wyszukiwania sciezki w grafie
	public void DFS (Point start, int kroki) {
		
		// dla kazdego punktu
		for (Point punkt : start.sasiedzi) {
			
			// jesli odwiedzilismy juz ten punkt i dotarlismy krotsza trasa to ignorujemy go
			if (punkt.poprzednik != null && punkt.krokiOdStartu <= kroki)
				continue;
			
			// znalezlismy nowa naszybsza trase, zapisujemy skad przybylismy i jak szybko
			punkt.poprzednik = start;
			punkt.krokiOdStartu = kroki;
			// uruchamiamy nowe wyszukiwanie DFS od tego punktu
			DFS (punkt, kroki + 1);
		}
	}
	
	public Map<LinesInterface.Point, Set<LinesInterface.Segment>> getMapEndpointToSegments () {
		
		// lista segmentow ktora zwrocimy na koniec
		Map<LinesInterface.Point, Set<LinesInterface.Segment>> punktSasiedzi = new HashMap<>();
		
		// nasz punkt w grafie przechowuje swoich sasiadow
		// dla kazdego punktu
		for (Point punkt : graf.punkty.values()) {
			// inicjalizujemy nowy zbior w ktorym przechowamy segmenty
			punktSasiedzi.put(punkt, new HashSet<>());
			// dla kazdego sasiada punktu z ktorego zaczynamy
			for (Point sasiad : punkt.sasiedzi) {
				// tworzymy nowy segment i dodajemy go do zbioru
				punktSasiedzi.get(punkt).add(new Segment(punkt, sasiad));
			}
		}
		
		return punktSasiedzi;
	}
	
	public Map<LinesInterface.Point, Map<Integer, Set<LinesInterface.Point>>> getReachableEndpoints () {
		
		Map<LinesInterface.Point, Map<Integer, Set<LinesInterface.Point>>> mapaPolaczen = new HashMap<>();
		
		// dla kazdego punktu w naszym grafie
		for (Point start : graf.punkty.values()) {
			
			graf.WyczyscPolaczenia();
			start.poprzednik = new Point("NULL");
			start.krokiOdStartu = 0;
			
			// dodajemy nowy wpis do mapy, punkt => mapa <ilosc przeskokow, zbior punktow>
			mapaPolaczen.put(start, new HashMap<>());
			
			// analizujemy graf z wykorzystaniem DFS
			DFS (start, 1);
			
			// inicjalizujemy nowe zbiory dla kazdego z 4 mozliwych dlugosci (wynika to z tresci zadania)
			for (int i = 1; i <= 4; i++)
				mapaPolaczen.get(start).put(i, new HashSet<>());
			
			// przechodzimy przez wszystkie punkty w grafie
			// dodajemy je do odpowiedniego zbioru w zaleznosci od odleglosci od punktu startowego
			for (Point punkt : graf.punkty.values()) {
				switch (punkt.krokiOdStartu) {
					case 1:
						mapaPolaczen.get(start).get(1).add(punkt);
						break;
					case 2:
						mapaPolaczen.get(start).get(2).add(punkt);
						break;
					case 3:
						mapaPolaczen.get(start).get(3).add(punkt);
						break;
					case 4:
						mapaPolaczen.get(start).get(4).add(punkt);
						break;
				}
			}
		}
		
		return mapaPolaczen;
	}
	
	class Point implements LinesInterface.Point {
		
		String nazwa; // nazwa punktu
		int krokiOdStartu; // uzywane przez DFS okresla dystans od punktu startowego w segmentach
		Point poprzednik; // uzywane przez DFS z wykorzystaniem poprzednikow mozemy odbudowac najkrotsza trase
		List<Point> sasiedzi = new LinkedList<>(); // lista sasiadow punktu
		
		public Point (String _nazwa) {
			nazwa = _nazwa;
		}
		
		public String getName() {
			return nazwa;
		}
	}
	
	class Segment implements LinesInterface.Segment {
		
		Point punkt1;
		Point punkt2;
		
		public Segment (Point _punkt1, Point _punkt2) {
			punkt1 = _punkt1;
			punkt2 = _punkt2;
		}
		
		public Point getEndpoint1() {
			return punkt1;
		}

		public Point getEndpoint2() {
			return punkt2;
		}
	}
	
	// zwykly zbior punktow, do punktow mamy dostep z wykorzystaniem ich nazwy
	class Graf {
		Map<String, Point> punkty = new HashMap<String, Point> ();
		
		void WyczyscPolaczenia () {
			for (Point punkt : punkty.values())
				punkt.poprzednik = null;
		}
	}
}