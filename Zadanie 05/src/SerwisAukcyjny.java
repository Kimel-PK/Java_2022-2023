import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

public class SerwisAukcyjny implements Aukcja {
	
	// mapa przechowujaca użytkowników serwisu
	Map<String, Powiadomienie> uzytkownicy = new HashMap<> ();
	// mapa przechowująca jednocześnie przedmiot, oferty i subskrybentów aukcji
	Map<Integer, DaneAukcji> przedmioty = new HashMap<> ();
	
	public void dodajUżytkownika (String username, Powiadomienie kontakt) {
		// dodajemy nowego użytkownika
		uzytkownicy.put (username, kontakt);
	}
	
	public void dodajPrzedmiotAukcji (Aukcja.PrzedmiotAukcji przedmiot) {
		// tworzymy nową własną klasę PrzedmiotAukcji na podstawie Aukcja.PrzedmiotAukcji
		// dlatego, nie jesteśmy w stanie zmieniać nic wewnątrz Aukcja.PrzedmiotAukcji 
		PrzedmiotAukcji przedmiotAukcji = new PrzedmiotAukcji(przedmiot.identyfikator(), przedmiot.nazwaPrzedmiotu(), przedmiot.aktualnaCena());
		// dodajemy nowy wpis do mapy identyfikator => dane o aukcji
		przedmioty.put (przedmiot.identyfikator(), new DaneAukcji (przedmiotAukcji, new LinkedList<>(), new LinkedList<>()));
	}
	
	public void subskrypcjaPowiadomień (String username, int identyfikatorPrzedmiotuAukcji) {
		// odwołujemy się do obiektu DaneAukcji i dodajemy na listę nowego subskrybenta
		przedmioty.get (identyfikatorPrzedmiotuAukcji).subskrybenci.add(username);
	}
	
	public void rezygnacjaZPowiadomień (String username, int identyfikatorPrzedmiotuAukcji) {
		// odwołujemy się do obiektu DaneAukcji i usuwamy subskrybenta z listy
		przedmioty.get (identyfikatorPrzedmiotuAukcji).subskrybenci.remove(username);
	}
	
	public void oferta (String username, int identyfikatorPrzedmiotuAukcji, int oferowanaKwota) {
		// składanie nowej oferty, najpierw pobieramy z mapy dane aukcji (będziemy odwoływać się dwa razy)
		// robimy to dla przejrzystości kodu
		DaneAukcji daneAukcji = przedmioty.get (identyfikatorPrzedmiotuAukcji);
		
		// jeśli aukcja jest już zamknięta nie możemy dodać kolejnej oferty
		if (!daneAukcji.otwarta)
			return;
			
		// dodajemy nową ofertę, wysyłamy do specjalnej metody nazwę użytkownika i proponowaną kwotę
		daneAukcji.DodajOferte (new Oferta(username, oferowanaKwota));
	}
	
	public void koniecAukcji (int identyfikatorPrzedmiotuAukcji) {
		// zamykamy aukcję o podanym ID
		// do zamkniętych aukcji nie można dodawać żadnych nowych ofert
		przedmioty.get (identyfikatorPrzedmiotuAukcji).otwarta = false;
	}
	
	public String ktoWygrywa (int identyfikatorPrzedmiotuAukcji) {
		// jeśli jakakolwiek oferta została złożona zwracamy zwycięzce
		// jeśli nie złożono żadnych ofert zwracamy null
		if (przedmioty.get (identyfikatorPrzedmiotuAukcji).najwyzszaOferta != null)
			return przedmioty.get (identyfikatorPrzedmiotuAukcji).najwyzszaOferta.uzytkownik;
		else
			return null;
	}
	
	public int najwyższaOferta (int identyfikatorPrzedmiotuAukcji) {
		// jeśli jakakolwiek oferta została złożona zwracamy najwyższą kwotę
		// jeśli nie złożono żadnych ofert zwracamy 0
		if (przedmioty.get (identyfikatorPrzedmiotuAukcji).najwyzszaOferta != null)
			return przedmioty.get (identyfikatorPrzedmiotuAukcji).najwyzszaOferta.oferowanaCena;
		else
			return 0;
	}
	
	// implementacja interfejsu z klasy Aukcja
	// pozwala nam na tworzenie lokalnych obiektów, których pola możemy zmieniać
	class PrzedmiotAukcji implements Aukcja.PrzedmiotAukcji {
		
		int id;
		String nazwaPrzedmotu;
		int aktualnaOferta;
		int cena;
		
		public PrzedmiotAukcji (int _id, String _nazwaPrzedmotu, int _cena) {
			id = _id;
			nazwaPrzedmotu = _nazwaPrzedmotu;
			cena = _cena;
		}
		
		public int identyfikator () {
			return id;
		}
		
		public String nazwaPrzedmiotu () {
			return nazwaPrzedmotu;
		}
		
		public int aktualnaOferta () {
			return aktualnaOferta;
		}
		
		// jeśli ktoś złożył ofertę niższą niż początkowa cena produktu to zwracamy początkową cenę
		public int aktualnaCena () {
			if (aktualnaOferta > cena)
				return aktualnaOferta;
			else
				return cena;
		}
	}
	
	// nasza własna klasa przechowująca dane o przedmiocie, złożonych ofertach i subskrybentach aukcji
	class DaneAukcji {
		Boolean otwarta = true;
		PrzedmiotAukcji przedmiot;
		List<Oferta> oferty;
		List<String> subskrybenci;
		
		// zamiast przeszukiwać za każdym razem listę, odwołanie do najwyższej oferty przechowujemy osobno
		Oferta najwyzszaOferta;
		
		public DaneAukcji (PrzedmiotAukcji _przedmiot, List<Oferta> _oferty, List<String> _subskrybenci) {
			przedmiot = _przedmiot;
			oferty = _oferty;
			subskrybenci = _subskrybenci;
		}
		
		public void DodajOferte (Oferta nowaOferta) {
			
			// dodajemy ofertę do listy jakakolwiek by nie była
			oferty.add(nowaOferta);
			
			// jeśli na liście jest tylko jedna oferta, ta dodana przed momentem to jest pierwszą i zarazem najwyższą
			if (oferty.size() == 1 || nowaOferta.oferowanaCena > najwyzszaOferta.oferowanaCena) {
				najwyzszaOferta = nowaOferta;
				przedmiot.aktualnaOferta = nowaOferta.oferowanaCena;
				
				// powiadom subskrybentów
				for (String subskrybent : subskrybenci) {
					// nie powiadamiamy samych siebie
					if (subskrybent.equals(nowaOferta.uzytkownik))
						continue;
						
					uzytkownicy.get(subskrybent).przebitoTwojąOfertę(przedmiot);
				}
			}
		}
	}
	
	// klasa oferta to zwykła para użytkownik => kwota
	// pomaga nam przekazywać danę sprawniej
	class Oferta {
		String uzytkownik;
		int oferowanaCena;
		
		public Oferta (String _uzytkownik, int _oferowanaCena) {
			uzytkownik = _uzytkownik;
			oferowanaCena = _oferowanaCena;
		}
	}
}