/**
 * Interfejs systemu akukcyjnego
 *
 */
public interface Aukcja {

	/**
	 * Interfejs reprezentujący przedmiot aukcji.
	 */
	public interface PrzedmiotAukcji {
		/**
		 * Unikalny, liczbowy identyfikator przedmiotu. Każdy przedmiot aukcji będzie
		 * posiadać inny identyfikator.
		 * 
		 * @return identyfikator liczbowy
		 */
		public int identyfikator();

		/**
		 * Nazwa przedmiotu aukcji
		 * 
		 * @return nazwa przedmiotu
		 */
		public String nazwaPrzedmiotu();

		/**
		 * Nowa oferta za przedmiot. Dla proszczenia, aby uniknąć ułamków, oferta
		 * podawana jest w groszach. W przypadku użycia w powiadomieniach metoda
		 * przekazuje ofertę, która przebiła kwotę zaoferowaną przez użytkownika serwisu
		 * aukcyjnego. Niekoniecznie aktualna oferta będzie większa od aktualnej ceny.
		 * 
		 * @return aktualna oferta w groszach
		 */
		public int aktualnaOferta();

		/**
		 * Aktualna cena za przedmiot. Dla proszczenia, aby uniknąć ułamków, cena
		 * podawana jest w groszach. W momencie dodawania przedmiotu do aukcji pozwala
		 * na przekazanie ceny minimalnej. W przypadku użycia w powiadomieniach metoda
		 * przekazuje ofertę, która aktualnie jest najwyższa.
		 * 
		 * @return aktualna cena w groszach
		 */
		public int aktualnaCena();

	}

	/**
	 * Powiadomienie użytkownika o przebiciu jego oferty.
	 */
	public interface Powiadomienie {
		/**
		 * Przekazanie informacji o przebiciu oferty na obserwowany przedmiot aukcji.
		 * 
		 * @param przedmiot obiekt reprezentujący przedmiot aukcji z ustawioną aktualną
		 *                  ofertą.
		 */
		public void przebitoTwojąOfertę(PrzedmiotAukcji przedmiot);
	}

	/**
	 * Metoda do dodawania użytkowika do systemu aukcyjnego. Użytkownicy rozróżniani
	 * są za pomocą ich unikalnego username.
	 * 
	 * @param username unikalne nazwa użytkownika
	 * @param kontakt  obiekt za pomocą należy powiadamiać tego użytkownika, gdy
	 *                 ktoś inny przebije ofertę na przedmiot, którym użytkownik
	 *                 jest zainteresowany.
	 */
	public void dodajUżytkownika(String username, Powiadomienie kontakt);

	/**
	 * Metoda pozwala na dodanie przedmiotu do serwisu aukcyjnego.
	 * 
	 * @param przedmiot dodawany do serwisu przedmiot
	 */
	public void dodajPrzedmiotAukcji(PrzedmiotAukcji przedmiot);

	/**
	 * Użytkownik o podanym username zgłasza zainteresowanie przedmiotem aktucji o
	 * podanym identyfikatorze. Od chwili wykonania tej metody użytkownik jest
	 * powiadamiany każdorazowo, gdy jego oferta zostanie przebita.
	 * 
	 * @param username                      nazwa użytkownika serwisu
	 * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
	 */
	public void subskrypcjaPowiadomień(String username, int identyfikatorPrzedmiotuAukcji);

	/**
	 * Metoda kończy obserwację danego przedmiotu przez użytkownika o podanym
	 * username. Rezygnacja z powiadomień oznacza zaprzestanie wysyłania
	 * powiadomień.
	 * 
	 * @param username                      nazwa użytkownika serwisu
	 * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
	 */
	public void rezygnacjaZPowiadomień(String username, int identyfikatorPrzedmiotuAukcji);

	/**
	 * Użytkownik przekazuje ofertę zakupu przedmiotu za podaną kwotę. Wszystkie
	 * osoby obserwujące ten sam przedmiot, a oferujące niższą kwotę powinny zostać
	 * automatycznie powiadomione o przebiciu ich oferty.
	 * 
	 * @param username                      nazwa użytkownika serwisu
	 * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
	 * @param oferowanaKwota                zaoferowana kwota w groszach
	 */
	public void oferta(String username, int identyfikatorPrzedmiotuAukcji, int oferowanaKwota);

	/**
	 * Za pomocą tej metody zamykana jest aukcja. Najlepsza oferta wygrywa. Nowe
	 * oferty są ignorowane.
	 * 
	 * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
	 */
	public void koniecAukcji(int identyfikatorPrzedmiotuAukcji);

	/**
	 * Metoda pozwala poznać nazwę użytkownika, który zaoferował nawyższą kwotę za
	 * przedmiot aukcji. Jeśli aukcja została zakończona, metoda pozwala poznać dane
	 * osoby, która aukcję na dany przedmiot wygrała.
	 * 
	 * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
	 * @return nazwa użytkownika wygrywającego aukcję
	 */
	public String ktoWygrywa(int identyfikatorPrzedmiotuAukcji);

	/**
	 * Metoda pozwala poznać najlepszą ofertę za dany przedmiot. Kwota podawna jest
	 * w groszach.
	 * 
	 * @param identyfikatorPrzedmiotuAukcji identyfikator przedmiotu aukcji
	 * @return najwyższa ofera za przedmiot
	 */
	public int najwyższaOferta(int identyfikatorPrzedmiotuAukcji);

}
