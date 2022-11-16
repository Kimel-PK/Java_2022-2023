public class SerwisAukcyjny implements Aukcja {

	/**
	 * Metoda do dodawania użytkowika do systemu aukcyjnego. Użytkownicy rozróżniani
	 * są za pomocą ich unikalnego username.
	 * 
	 * @param username unikalne nazwa użytkownika
	 * @param kontakt  obiekt za pomocą należy powiadamiać tego użytkownika, gdy
	 *                 ktoś inny przebije ofertę na przedmiot, którym użytkownik
	 *                 jest zainteresowany.
	 */
	public void dodajUżytkownika(String username, Powiadomienie kontakt) {}

	/**
	 * Metoda pozwala na dodanie przedmiotu do serwisu aukcyjnego.
	 * 
	 * @param przedmiot dodawany do serwisu przedmiot
	 */
	public void dodajPrzedmiotAukcji(PrzedmiotAukcji przedmiot) {}

	/**
	 * Użytkownik o podanym username zgłasza zainteresowanie przedmiotem aktucji o
	 * podanym identyfikatorze. Od chwili wykonania tej metody użytkownik jest
	 * powiadamiany każdorazowo, gdy jego oferta zostanie przebita.
	 * 
	 * @param username                      nazwa użytkownika serwisu
	 * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
	 */
	public void subskrypcjaPowiadomień(String username, int identyfikatorPrzedmiotuAukcji) {}

	/**
	 * Metoda kończy obserwację danego przedmiotu przez użytkownika o podanym
	 * username. Rezygnacja z powiadomień oznacza zaprzestanie wysyłania
	 * powiadomień.
	 * 
	 * @param username                      nazwa użytkownika serwisu
	 * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
	 */
	public void rezygnacjaZPowiadomień(String username, int identyfikatorPrzedmiotuAukcji) {}

	/**
	 * Użytkownik przekazuje ofertę zakupu przedmiotu za podaną kwotę. Wszystkie
	 * osoby obserwujące ten sam przedmiot, a oferujące niższą kwotę powinny zostać
	 * automatycznie powiadomione o przebiciu ich oferty.
	 * 
	 * @param username                      nazwa użytkownika serwisu
	 * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
	 * @param oferowanaKwota                zaoferowana kwota w groszach
	 */
	public void oferta(String username, int identyfikatorPrzedmiotuAukcji, int oferowanaKwota) {}

	/**
	 * Za pomocą tej metody zamykana jest aukcja. Najlepsza oferta wygrywa. Nowe
	 * oferty są ignorowane.
	 * 
	 * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
	 */
	public void koniecAukcji(int identyfikatorPrzedmiotuAukcji) {}

	/**
	 * Metoda pozwala poznać nazwę użytkownika, który zaoferował nawyższą kwotę za
	 * przedmiot aukcji. Jeśli aukcja została zakończona, metoda pozwala poznać dane
	 * osoby, która aukcję na dany przedmiot wygrała.
	 * 
	 * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
	 * @return nazwa użytkownika wygrywającego aukcję
	 */
	public String ktoWygrywa(int identyfikatorPrzedmiotuAukcji) {
		return null;
	}

	/**
	 * Metoda pozwala poznać najlepszą ofertę za dany przedmiot. Kwota podawna jest
	 * w groszach.
	 * 
	 * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
	 * @return najwyższa ofera za przedmiot
	 */
	public int najwyższaOferta(int identyfikatorPrzedmiotuAukcji) {
		return 0;
	}

}
