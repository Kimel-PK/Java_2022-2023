import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.LinkedList;

public class SerwisAukcyjny implements Aukcja {
	
	Map<String, Powiadomienie> uzytkownicy = new HashMap<> ();
	Map<Integer, DaneAukcji> przedmioty = new HashMap<> ();
	
	public void dodajUżytkownika (String username, Powiadomienie kontakt) {
		uzytkownicy.put (username, kontakt);
	}
	
	public void dodajPrzedmiotAukcji (Aukcja.PrzedmiotAukcji przedmiot) {
		PrzedmiotAukcji przedmiotAukcji = new PrzedmiotAukcji(przedmiot.identyfikator(), przedmiot.nazwaPrzedmiotu(), przedmiot.aktualnaCena());
		przedmioty.put (przedmiot.identyfikator(), new DaneAukcji (przedmiotAukcji, new LinkedList<>(), new LinkedList<>()));
	}
	
	public void subskrypcjaPowiadomień (String username, int identyfikatorPrzedmiotuAukcji) {
		przedmioty.get (identyfikatorPrzedmiotuAukcji).subskrybenci.add(username);
	}
	
	public void rezygnacjaZPowiadomień (String username, int identyfikatorPrzedmiotuAukcji) {
		przedmioty.get (identyfikatorPrzedmiotuAukcji).subskrybenci.remove(username);
	}
	
	public void oferta (String username, int identyfikatorPrzedmiotuAukcji, int oferowanaKwota) {
		DaneAukcji daneAukcji = przedmioty.get (identyfikatorPrzedmiotuAukcji);
		
		if (!daneAukcji.otwarta)
			return;
			
		daneAukcji.DodajOferte (new Oferta(username, oferowanaKwota));
	}
	
	public void koniecAukcji (int identyfikatorPrzedmiotuAukcji) {
		przedmioty.get (identyfikatorPrzedmiotuAukcji).otwarta = false;
	}
	
	public String ktoWygrywa (int identyfikatorPrzedmiotuAukcji) {
		return przedmioty.get (identyfikatorPrzedmiotuAukcji).najwyzszaOferta.uzytkownik;
	}
	
	public int najwyższaOferta (int identyfikatorPrzedmiotuAukcji) {
		return przedmioty.get (identyfikatorPrzedmiotuAukcji).najwyzszaOferta.oferowanaCena;
	}
	
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
		
		public int aktualnaCena () {
			if (aktualnaOferta > cena)
				return aktualnaOferta;
			else
				return cena;
		}
	}
	
	class DaneAukcji {
		Boolean otwarta = true;
		PrzedmiotAukcji przedmiot;
		List<Oferta> oferty;
		List<String> subskrybenci;
		
		Oferta najwyzszaOferta;
		
		public DaneAukcji (PrzedmiotAukcji _przedmiot, List<Oferta> _oferty, List<String> _subskrybenci) {
			przedmiot = _przedmiot;
			oferty = _oferty;
			subskrybenci = _subskrybenci;
		}
		
		public void DodajOferte (Oferta nowaOferta) {
			
			oferty.add(nowaOferta);
			
			if (oferty.size() == 1) {
				najwyzszaOferta = nowaOferta;
				przedmiot.aktualnaOferta = nowaOferta.oferowanaCena;
				return;
			}
			
			for (Oferta oferta : oferty) {
				if (nowaOferta.oferowanaCena > oferta.oferowanaCena) {
					najwyzszaOferta = nowaOferta;
					przedmiot.aktualnaOferta = nowaOferta.oferowanaCena;
					for (String subskrybent : subskrybenci) {
						if (!subskrybent.equals(nowaOferta.uzytkownik)) {
							uzytkownicy.get(subskrybent).przebitoTwojąOfertę(przedmiot);
						}
					}
					break;
				}
			}
		}
	}
	
	class Oferta {
		String uzytkownik;
		int oferowanaCena;
		
		public Oferta (String _uzytkownik, int _oferowanaCena) {
			uzytkownik = _uzytkownik;
			oferowanaCena = _oferowanaCena;
		}
	}
}