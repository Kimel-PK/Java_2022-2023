/**
 * Klasa dekodera.
 */
public class Decoder {
	
	// przechowuje string, ktory otrzymamy gdy wywolamy output()
	String wyjscie = "";
	// przechowuje obecnie budowany string, do ktorego nie odczytalismy jeszcze liczby powtorzen
	String obecne_wyjscie = "";
	// okresla tryb odczytu: false - czytamy cyfry, true - czytamy ilosc powtorzen
	boolean czytaj_powtorzenia = false;
	// odczytana liczba powtorzen
	int ilosc_powtorzen = 0;
	// przechowuje numer cyfry w liczbie powtorzen, ktora przetwarzamy
	int cyfra_powtorzenia = 0;
	
	/**
	 * Metoda pozwalajaca na wprowadzanie danych.
	 * @param value dostarczona wartosc
	 */
	public void input (byte value) {
		
		if (czytaj_powtorzenia) {
			
			// przejdz do odczytu ilosci powtorzen
			
			// przesun liczbe w lewo
			ilosc_powtorzen *= 10;
			// dodaj obecnie odczytana liczbe
			ilosc_powtorzen += value;
			
			if (cyfra_powtorzenia == 3) { // odczytalismy juz wszystkie cyfry
				// dodaj obecnie przetwarzany ciag na wyjscie
				wyjscie += obecne_wyjscie.repeat (ilosc_powtorzen);
				// wyzeruj zmienne pomocnicze
				obecne_wyjscie = "";
				czytaj_powtorzenia = false;
				ilosc_powtorzen = 0;
				cyfra_powtorzenia = 0;
				return;
			}
			
			cyfra_powtorzenia++;
			return;
		}
		
		if (value > 0 && value < 10) { // cyfra z zakresu 1-9
			// dopisz cyfre na wyjscie normalnie
			obecne_wyjscie += value;
		} else if (value == 0) { // odczytano 0
			// przejdz do trybu czytania liczby powtorzen
			czytaj_powtorzenia = true;
		}
		// odczytano inny symbol, zignoruj go
	}
	
	/**
	 * Metoda pozwalajaca na pobranie wyniku dekodowania danych.
	 * @return wynik dzialania
	 */
	public String output() {
		return wyjscie;
	}
	
	/**
	 * Przywrocenie poczatkowego stanu obiektu.
	 */
	public void reset() {
		// wyzeruj zmienne pomocnicze
		wyjscie = "";
		obecne_wyjscie = "";
		czytaj_powtorzenia = false;
		ilosc_powtorzen = 0;
		cyfra_powtorzenia = 0;
	}
}