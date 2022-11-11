import java.util.Map;
import java.util.Set;

import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Tester {
	
	static List<Test> testy = new LinkedList<Test> ();
	
	static List<List<Point>> punkty = new LinkedList<>();
	static List<Set<Segment>> segmenty = new LinkedList<>();
	static List<Map<LinesInterface.Point, Set<LinesInterface.Segment>>> sasiednieKrawedzie = new LinkedList<>();
	static List<Map<String, Map<Integer, Set<String>>>> mapaPolaczen = new LinkedList<>();
	static List<Point> start = new LinkedList<>();
	static List<Point> koniec = new LinkedList<>();
	
	public static void main (String[] args) {
		
		// test 1 - czy program w ogole cos robi
		
		punkty.add(List.of (
			new Point ("A"),
			new Point ("B"),
			new Point ("C")
		));
		segmenty.add(Set.of (
			new Segment (punkty.get(0).get(0), punkty.get(0).get(1)), // A => B
			new Segment (punkty.get(0).get(1), punkty.get(0).get(2))  // B => C
		));
		sasiednieKrawedzie.add (Map.of (
			new Point("A"), Set.of(
				new Segment(punkty.get(0).get(0), punkty.get(0).get(1))
			),
			new Point("B"), Set.of(
				new Segment(punkty.get(0).get(0), punkty.get(0).get(1)),
				new Segment(punkty.get(0).get(1), punkty.get(0).get(2))
			),
			new Point("C"), Set.of(
				new Segment(punkty.get(0).get(1), punkty.get(0).get(2))
			)
		));
		mapaPolaczen.add (Map.of (
			"A", Map.of (
				1, Set.of ("B"),
				2, Set.of ("C"),
				3, Set.of (),
				4, Set.of ()
			),
			"B", Map.of (
				1, Set.of ("A", "C"),
				2, Set.of (),
				3, Set.of (),
				4, Set.of ()
			),
			"C", Map.of (
				1, Set.of ("B"),
				2, Set.of ("A"),
				3, Set.of (),
				4, Set.of ()
			)
		));
		
		start.add (punkty.get(0).get(0)); // A
		koniec.add (punkty.get(0).get(2)); // C
		
		testy.add(
			new Test (
				new HashSet<LinesInterface.Point> (punkty.get(0)),
				new HashSet<LinesInterface.Segment> (segmenty.get (0)),
				sasiednieKrawedzie.get (0),
				mapaPolaczen.get (0),
				start.get (0),
				koniec.get (0)
			)
		);
		
		// test 2 - bardziej skomplikowany
		
		punkty.add (List.of (
			new Point ("A"), // 0
			new Point ("B"), // 1
			new Point ("C"), // 2
			new Point ("D"), // 3
			new Point ("E"), // 4
			new Point ("F"), // 5
			new Point ("G"), // 6
			new Point ("H")  // 7
		));
		segmenty.add (Set.of (
			new Segment (punkty.get (1).get(0), punkty.get (1).get(1)), // A => B
			new Segment (punkty.get (1).get(1), punkty.get (1).get(2)), // B => C
			new Segment (punkty.get (1).get(1), punkty.get (1).get(3)), // B => D
			new Segment (punkty.get (1).get(3), punkty.get (1).get(4)), // D => E
			new Segment (punkty.get (1).get(3), punkty.get (1).get(5)), // D => F
			new Segment (punkty.get (1).get(5), punkty.get (1).get(6)), // F => G
			new Segment (punkty.get (1).get(6), punkty.get (1).get(0)), // G => A
			new Segment (punkty.get (1).get(2), punkty.get (1).get(7)), // C => H
			new Segment (punkty.get (1).get(7), punkty.get (1).get(3))  // H => D
		));
		sasiednieKrawedzie.add (Map.of (
			new Point("A"), Set.of(
				new Segment(punkty.get (1).get(0), punkty.get (1).get(1)),
				new Segment(punkty.get (1).get(0), punkty.get (1).get(6))
			),
			new Point("B"), Set.of(
				new Segment(punkty.get (1).get(1), punkty.get (1).get(0)),
				new Segment(punkty.get (1).get(1), punkty.get (1).get(2)),
				new Segment(punkty.get (1).get(1), punkty.get (1).get(3))
			),
			new Point("C"), Set.of(
				new Segment(punkty.get (1).get(2), punkty.get (1).get(1)),
				new Segment(punkty.get (1).get(2), punkty.get (1).get(7))
			),
			new Point("D"), Set.of(
				new Segment(punkty.get (1).get(3), punkty.get (1).get(1)),
				new Segment(punkty.get (1).get(3), punkty.get (1).get(4)),
				new Segment(punkty.get (1).get(3), punkty.get (1).get(5)),
				new Segment(punkty.get (1).get(3), punkty.get (1).get(7))
			),
			new Point("E"), Set.of(
				new Segment(punkty.get (1).get(4), punkty.get (1).get(3))
			),
			new Point("F"), Set.of(
				new Segment(punkty.get (1).get(5), punkty.get (1).get(3)),
				new Segment(punkty.get (1).get(5), punkty.get (1).get(6))
			),
			new Point("G"), Set.of(
				new Segment(punkty.get (1).get(6), punkty.get (1).get(0)),
				new Segment(punkty.get (1).get(6), punkty.get (1).get(5))
			),
			new Point("H"), Set.of(
				new Segment(punkty.get (1).get(7), punkty.get (1).get(2)),
				new Segment(punkty.get (1).get(7), punkty.get (1).get(3))
			)
		));
		mapaPolaczen.add (Map.of (
			"A", Map.of (
				1, Set.of ("B", "G"),
				2, Set.of ("C", "D", "F"),
				3, Set.of ("H", "E"),
				4, Set.of ()
			),
			"B", Map.of (
				1, Set.of ("A", "C", "D"),
				2, Set.of ("E", "G", "H", "F"),
				3, Set.of (),
				4, Set.of ()
			),
			"C", Map.of (
				1, Set.of ("B", "H"),
				2, Set.of ("A", "D"),
				3, Set.of ("E", "F", "G"),
				4, Set.of ()
			),
			"D", Map.of (
				1, Set.of ("B", "F", "E", "H"),
				2, Set.of ("G", "A", "C"),
				3, Set.of (),
				4, Set.of ()
			),
			"E", Map.of (
				1, Set.of ("D"),
				2, Set.of ("F", "B", "H"),
				3, Set.of ("G", "C", "A"),
				4, Set.of ()
			),
			"F", Map.of (
				1, Set.of ("G", "D"),
				2, Set.of ("E", "A", "B", "H"),
				3, Set.of ("C"),
				4, Set.of ()
			),
			"G", Map.of (
				1, Set.of ("A", "F"),
				2, Set.of ("D", "B"),
				3, Set.of ("C", "H"),
				4, Set.of ()
			),
			"H", Map.of (
				1, Set.of ("C", "D"),
				2, Set.of ("B", "F", "E"),
				3, Set.of ("G", "A"),
				4, Set.of ()
			)
		));
		
		start.add (punkty.get (1).get(0)); // A
		koniec.add (punkty.get (1).get(4)); // E
		
		testy.add(
			new Test (
				new HashSet<LinesInterface.Point> (punkty.get (1)),
				new HashSet<LinesInterface.Segment> (segmenty.get (1)),
				sasiednieKrawedzie.get (1),
				mapaPolaczen.get (1),
				start.get (1),
				koniec.get (1)
			)
		);
		
		// wykonanie testow
		int n = 1;
		
		for (Test test : testy) {
			
			System.out.println("Start testu " + n + "...");
			
			if (test.WykonajTest ())
				System.out.println("Test " + n + " zakonczono pomyslnie!");
			else
				System.out.println("W tescie " + n + " wystapily bledy!");
			
			n++;
		}
	}
}

class Point implements LinesInterface.Point {
	
	String nazwa;
	Point poprzednik;
	List<String> sasiedzi = new LinkedList<>();
	
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
	
	public static boolean Porownaj (LinesInterface.Segment segment1, LinesInterface.Segment segment2) {
		if ((segment1.getEndpoint1().getName().equals(segment2.getEndpoint1().getName()) && segment1.getEndpoint2().getName().equals(segment2.getEndpoint2().getName())) || (segment1.getEndpoint1().getName().equals(segment2.getEndpoint2().getName()) && segment1.getEndpoint2().getName ().equals(segment2.getEndpoint1().getName()))) {
			return true;
		}
		return false;
	}
	
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

class Graf {
	Map<String, Point> punkty = new HashMap<String, Point> ();
	
	public Graf (Set<LinesInterface.Point> _punkty, Set<LinesInterface.Segment> _segmenty) {
		
		for (LinesInterface.Point punkt : _punkty)
			punkty.put (punkt.getName(), new Point (punkt.getName()));
			
		for (LinesInterface.Segment segment : _segmenty) {
			punkty.get (segment.getEndpoint1 ().getName ()).sasiedzi.add (segment.getEndpoint2 ().getName());
			punkty.get (segment.getEndpoint2 ().getName ()).sasiedzi.add (segment.getEndpoint1 ().getName());
		}
	}
}

class Test {
		
	Lines lines = new Lines();
	Graf graf;
	Set<LinesInterface.Point> punkty = new HashSet<>();
	Set<LinesInterface.Segment> segmenty = new HashSet<>();
	Map<LinesInterface.Point, Set<LinesInterface.Segment>> sasiednieKrawedzie;
	Map<String, Map<Integer, Set<String>>> mapaPolaczen;
	Point start;
	Point koniec;
	
	public Test (
		Set<LinesInterface.Point> _punkty,
		Set<LinesInterface.Segment> _segmenty,
		Map<LinesInterface.Point, Set<LinesInterface.Segment>> _sasiednieKrawedzie,
		Map<String, Map<Integer, Set<String>>> _mapaPolaczen,
		Point _start,
		Point _koniec) {
		
		punkty = _punkty;
		segmenty = _segmenty;
		sasiednieKrawedzie = _sasiednieKrawedzie;
		mapaPolaczen = _mapaPolaczen;
		start = _start;
		koniec = _koniec;
		
		graf = new Graf(punkty, segmenty);
	}
	
	public boolean WykonajTest () {
		
		boolean bledy = false;
		
		lines.addPoints(punkty);
		lines.addSegments(segmenty);
		
		List<LinesInterface.Segment> trasa = lines.findConnection (start, koniec);
		
		if ((trasa.get(0).getEndpoint1().getName() != start.getName() && trasa.get(0).getEndpoint2().getName() != start.getName()) || (trasa.get(trasa.size() - 1).getEndpoint1().getName() != koniec.getName() && trasa.get(trasa.size() - 1).getEndpoint2().getName() != koniec.getName())) {
			System.out.println("Poczatek lub koniec wyznaczonej trasy sie nie zgadza!");
			bledy = true;
		}
		
		Point obecnyPunkt = start;
		
		for (LinesInterface.Segment segment : trasa) {
			if (segment.getEndpoint1().getName() == obecnyPunkt.getName() && graf.punkty.get (obecnyPunkt.getName()).sasiedzi.contains(segment.getEndpoint2().getName())) {
				obecnyPunkt = graf.punkty.get(segment.getEndpoint2().getName());
			} else if (segment.getEndpoint2().getName() == obecnyPunkt.getName() && graf.punkty.get (obecnyPunkt.getName()).sasiedzi.contains(segment.getEndpoint1().getName())) {
				obecnyPunkt = graf.punkty.get(segment.getEndpoint1().getName());
			} else {
				bledy = true;
			}
		}
		
		Map<String, Set<LinesInterface.Segment>> sasiednieKrawiedzieTest = new HashMap<>();
		for (Map.Entry<LinesInterface.Point, Set<LinesInterface.Segment>> paraPunktSegmenty : lines.getMapEndpointToSegments().entrySet()) {
			sasiednieKrawiedzieTest.put(paraPunktSegmenty.getKey().getName(), paraPunktSegmenty.getValue());
		}
		
		for (Map.Entry<LinesInterface.Point, Set<LinesInterface.Segment>> rekord : sasiednieKrawedzie.entrySet()) {
			
			if (!sasiednieKrawiedzieTest.containsKey(rekord.getKey().getName())) {
				System.out.println("Blad w getMapEndpointToSegments(), zbior nie zawiera punktu " + rekord.getKey().getName());
				continue;
			}
			
			for (LinesInterface.Segment segment : rekord.getValue()) {
				
				boolean znaleziono = false;
				for (LinesInterface.Segment segment2 : sasiednieKrawiedzieTest.get(rekord.getKey().getName())) {
					if (Segment.Porownaj(segment, segment2)) {
						znaleziono = true;
						break;
					}
				}
				
				if (!znaleziono) {
					System.out.println("Blad w getMapEndpointToSegments(), nie znaleziono krawiedzi [" + segment.getEndpoint1().getName() + " => " + segment.getEndpoint2().getName() + "] dla punktu " + rekord.getKey().getName());
					bledy = true;
				}
			}
		}
		
		Map<String, Map<Integer, Set<String>>> mapaPolaczenTest = new HashMap<>();
		for (Map.Entry<LinesInterface.Point, Map<Integer, Set<LinesInterface.Point>>> paraPunktKrokiPunkty : lines.getReachableEndpoints().entrySet()) {
			mapaPolaczenTest.put(paraPunktKrokiPunkty.getKey().getName(), new HashMap<>());
			for (Map.Entry<Integer, Set<LinesInterface.Point>> paraKrokiPunkty : paraPunktKrokiPunkty.getValue().entrySet()) {
				mapaPolaczenTest.get(paraPunktKrokiPunkty.getKey().getName()).put(paraKrokiPunkty.getKey(), new HashSet<>());
				for (LinesInterface.Point punkt : paraKrokiPunkty.getValue()) {
					mapaPolaczenTest.get(paraPunktKrokiPunkty.getKey().getName()).get(paraKrokiPunkty.getKey()).add(punkt.getName());
				}
			}
		}
		
		for (Map.Entry<String, Map<Integer, Set<String>>> rekord : mapaPolaczen.entrySet()) {
			
			if (!mapaPolaczenTest.containsKey(rekord.getKey())) {
				System.out.println("Blad w getReachableEndpoints(), zbior nie zawiera punktu " + rekord.getKey());
				bledy = true;
				continue;
			}
			
			for (Map.Entry<Integer, Set<String>> paraTrasaPunkty : rekord.getValue().entrySet()) {
				
				int dlugoscTrasy = paraTrasaPunkty.getKey();
				
				if (!mapaPolaczenTest.get(rekord.getKey()).containsKey(dlugoscTrasy)) {
					System.out.println("Blad w getReachableEndpoints(), punkt " + rekord.getKey() + " nie zawiera listy sasiadow oddalonych o " + dlugoscTrasy);
					bledy = true;
					continue;
				}
				
				for (String punkt : paraTrasaPunkty.getValue()) {
					
					boolean znaleziono = false;
					for (String punkt2 : mapaPolaczenTest.get(rekord.getKey()).get(dlugoscTrasy)) {
						if (punkt.equals(punkt2)) {
							znaleziono = true;
							break;
						}
					}
					
					if (!znaleziono) {
						System.out.println("Blad w getReachableEndpoints(), nie znaleziono " + punkt + " dla punktu " + rekord.getKey() + " odleglosc " + dlugoscTrasy);
						bledy = true;
					}
				}
			}
		}
		
		if (bledy)
			return false;
		else
			return true;
	}
}