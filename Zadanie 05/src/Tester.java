// ze względu na polskie znaki w nazwach metod klasy Aukcja musimy kompilować Tester z flagą "-encoding UTF-8"

public class Tester {
	
	public static void main (String[] args) {
		
		System.out.println("Zwróć uwagę na linie z wcięciem na początku");
		
		System.out.println();
		System.out.println("Test 1 - podstawowe działania");
		System.out.println();
		
		SerwisAukcyjny serwisAukcyjny = new SerwisAukcyjny();
		
		System.out.println("Dodajemy nowy przedmiot aukcji - Chleb");
		serwisAukcyjny.dodajPrzedmiotAukcji(new PrzedmiotAukcji(1, "Chleb pszenno - żytni", 480));
		System.out.println("Dodajemy użytkownika Janek");
		serwisAukcyjny.dodajUżytkownika("Janek", new Powiadomienie());
		System.out.println("Dodajemy użytkownika Marek");
		serwisAukcyjny.dodajUżytkownika("Marek", new Powiadomienie());
		System.out.println("Janek składa ofertę 520gr za chleb");
		serwisAukcyjny.oferta("Janek", 1, 520);
		System.out.println("	W tym miejscu nie powinniśmy dostać powiadomienia");
		System.out.println("Janek subskrybuje chleb");
		serwisAukcyjny.subskrypcjaPowiadomień("Janek", 1);
		System.out.println("Marek subskrybuje chleb");
		serwisAukcyjny.subskrypcjaPowiadomień("Marek", 1);
		System.out.println("Marek składa ofertę 550gr");
		System.out.println("	Janek zostaje powiadomiony");
		serwisAukcyjny.oferta("Marek", 1, 550);
		System.out.println("	Najwyższa oferta: " + serwisAukcyjny.najwyższaOferta(1) + " (spodziewana wartość: 550)");
		System.out.println("Marek rezygnuje z powiadomień");
		serwisAukcyjny.rezygnacjaZPowiadomień("Marek", 1);
		System.out.println("Janek składa ofertę 575gr");
		System.out.println("	Marek nie powinien dostać powiadomienia");
		serwisAukcyjny.oferta("Janek", 1, 575);
		System.out.println("Zamykamy aukcję");
		serwisAukcyjny.koniecAukcji(1);
		System.out.println("Marek składa ofertę 620gr");
		serwisAukcyjny.oferta("Marek", 1, 620);
		System.out.println("	Aukcja została już zamknięta, oferta powinna zostać zignorowana");
		System.out.println("	Zwycięzca aukcji: " + serwisAukcyjny.ktoWygrywa(1) + " (spodziewany: Janek)");
		System.out.println();
	}
}

class PrzedmiotAukcji implements Aukcja.PrzedmiotAukcji {
		
	int id;
	String nazwaPrzedmotu;
	int aktualnaOferta = 0;
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

class Powiadomienie implements Aukcja.Powiadomienie {
	public void przebitoTwojąOfertę(Aukcja.PrzedmiotAukcji przedmiot) {
		System.out.println("Przebito twoją ofertę '" + przedmiot.nazwaPrzedmiotu() + "' nową ceną " + przedmiot.aktualnaOferta());
		return;
	}
}