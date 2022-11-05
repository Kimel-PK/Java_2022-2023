import java.util.*;

public class Meeting implements MeetingInterface {
	
	Position punktZborny;
	// lista par pionek -> jego pozycja
	List<ParaPionekPozycja> pionki = new LinkedList<>();
	// mapa korzystajac z ktorej okreslamy czy na danym polu znajdue sie pionek
	Map<Position, Integer> szachownica = new HashMap<>();
	
	public void addPawns (List<PawnPosition> positions) {
		// dla kazdego pionka jaki mamy dodac
		for (PawnPosition pionek : positions) {
			Position pozycja = new Position2D (pionek.x(), pionek.y());
			// dodaj pionek na liste do przetwarzania
			pionki.add(new ParaPionekPozycja(pionek.pawnId(), pozycja));
			// dodaj wpis na szachownicy
			szachownica.put(pozycja, pionek.pawnId());
		}
	}
	
	public void addMeetingPoint (Position meetingPointPosition) {
		// ustaw punkt zborny
		punktZborny = meetingPointPosition;
	}
	
	public void move () {
		
		// liczymy ile ruchow wykonalismy w jednym przejsciu petli
		int wykonaneRuchy;
		
		// petla do-while poniewaz kod musi sie wykonac przynajmniej raz
		do {
			
			wykonaneRuchy = 0;
			
			// dla kazdego pionka z listy
			for (ParaPionekPozycja pionek : pionki) {
				
				// wyliczamy dx, dy - roznice w polozeniu X, Y pionka w stosunku do polozenia punktu zbornego
				int dx = Math.abs (punktZborny.x() - pionek.position.x());
				int dy = Math.abs (punktZborny.y() - pionek.position.y());
				
				// jesli |dx| = |dy| = 0 - brak ruchu, bo pionek dotarl do punktu zbornego
				if (dx == 0 && dy == 0)
					continue;
				
				// zmienne pomocnicze zeby poprawic czytelnosc kodu
				Position obecnaPozycja = pionek.position;
				Position nowaPozycja = null;
				
				if (dx > dy) {
					// okreslamy kierunek na ruch w poziomie
					if (obecnaPozycja.x() < punktZborny.x()) {
						// ruch w prawo
						nowaPozycja = new Position2D (obecnaPozycja.x() + 1, obecnaPozycja.y());
					} else {
						// ruch w lewo
						nowaPozycja = new Position2D (obecnaPozycja.x() - 1, obecnaPozycja.y());
					}
				} else if (dx <= dy) {
					// okreslamy kierunek na ruch w pionie
					if (obecnaPozycja.y() < punktZborny.y()) {
						// ruch do gory
						nowaPozycja = new Position2D (obecnaPozycja.x(), obecnaPozycja.y() + 1);
					} else {
						// ruch w dol
						nowaPozycja = new Position2D (obecnaPozycja.x(), obecnaPozycja.y() - 1);
					}
				}
				
				// sprawdzamy czy mozemy wykonac ruch w kierunku nowaPozycja
				if (szachownica.get(nowaPozycja) == null) {
					
					// usuwamy wpis z szachownicy
					szachownica.remove(obecnaPozycja);
					// dodajemy wpis w nowym miejscu
					szachownica.put(nowaPozycja, pionek.pawnId);
					// zmieniamy aktualna pozycje pionka na liscie
					pionek.position = nowaPozycja;
					wykonaneRuchy++;
				}
			}
			
			// ruch w turach parzystych realizowany jest w odwrotnej kolejnosci
			Collections.reverse(pionki);
			
			// wykonujemy dopoki istnieja jakiekolwiek mozliwe ruchy
		} while (wykonaneRuchy > 0);
	}
	
	public Set<PawnPosition> getAllPawns () {
		
		Set<PawnPosition> pozycjePionkow = new HashSet<>();
		
		// przepisujemy nasz format listy pionkow z powrotem na Set
		for (ParaPionekPozycja pionek : pionki) {
			pozycjePionkow.add(new PawnPosition2D (pionek.pawnId, pionek.position.x(), pionek.position.y()));
		}
		
		return pozycjePionkow;
	}
	
	public Set<PawnPosition> getNeighbours (int pawnId) {
		
		Set<PawnPosition> sasiedzi = new HashSet<>();
		ParaPionekPozycja pionek = null;
		
		// szukamy na liscie pionka dla ktorego mamy znalezc sasiada
		for (ParaPionekPozycja x : pionki) {
			if (x.pawnId == pawnId) {
				pionek = x;
				break;
			}
		}
		
		// failsafe jesli ktos poprosi o sasiadow pionka ktory nie istnieje
		if (pionek == null)
			return sasiedzi;
		
		// sprawdzamy czy w naszej mapie istnieja wpisy na sasiednich polach
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0)
					continue;
				
				if (szachownica.get (new Position2D (pionek.position.x() + i, pionek.position.y() + j)) != null) {
					int sasiad = szachownica.get (new Position2D (pionek.position.x() + i, pionek.position.y() + j));
					sasiedzi.add(new PawnPosition2D (sasiad, pionek.position.x() + i, pionek.position.y() + j));
				}
			}
		}
		
		return sasiedzi;
	}
	
	// przeciazenie metody toString() potrzebne tylko w testerze
    public String toString() {
		String string = "";
		for (PawnPosition pionek : getAllPawns()) {
			string += pionek.pawnId() + " - (" + pionek.x() + "," + pionek.y() + ")\n";
		}
		return string;
    }
}

// klasa reprezentujaca pare id pionka -> pozycja (mozna tez uzyc Pair<,>)
class ParaPionekPozycja {
	int pawnId;
	Position position;
	
	public ParaPionekPozycja (int _pawnId, Position _position) {
		pawnId = _pawnId;
		position = _position;
	}
}