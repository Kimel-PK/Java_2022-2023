# Zadanie 01

## Idea

Zadanie polega na napisaniu kodu programu (klasy), który będzie dekodował dostarczane liczby wg. prostego schematu.

## Kodowanie

- Pojedyncze wywołanie metody wprowadzającej dane dostarczy jednej liczby z zakresu od 0 do 9 włącznie.
- Wprowadzenie liczby spoza dozwolonego zakresu (0-9) ma być ignorowane (nie może wpływać na wynik)
- Wprowadzane dane dzielą się na sekcje.
- Może być wiele sekcji
- W pojedynczej sekcji występuje cześć danych, następnie 0 i liczba powtórzeń części danych.
- W części danych nie będzie występować 0.
- W części danych mogą pojawić się tylko wartości od 1 do 9 włącznie.
- W części powtórzeń może pojawić się 0.
- Sekcja powtórzeń będzie mieć zawsze długość 4 (cztery wywołania metody dostarczającej dane).
- Dozwolona liczba powtórzeń będzie zawierać się w przedziale od 0000 do 9999 włącznie.

## Interfejs użytkownika

Dane wprowadzane będą jako liczby całkowite (typ byte) - każda liczba oddzielnie. Wynik ma być dostępny w postaci ciągu znaków.

W każdym momencie wprowadzania danych może zostać wykonana metoda reset. Ma ona doprowadzić do powrotu obiektu do stanu początkowego, tj. takiego, w którym żadne dane nie zostały do tej pory przez użytkownika wprowadzone.
Przykłady

Aby poprawić czytelność przykładów pomiędzy sekcjami i częściami sekcji dodane zostały spacje.

Literka `r` wskazuje miejsce wykonania metody reset. Liczby `-1`, `12` to błędnie wprowadzone dane, które trzeba zignorować. Tu `12` wprowadzone zostało jako pojedyncza liczba o tej wartości, a nie osobno `1` i `2`.

```text
482148 0 0001 -> 482148
482148"-1" 0 0001 -> 482148
482148"12" 0 0001 -> 482148
2 0 0014 -> 22222222222222
241 0 0004 -> 241241241241
121 0 0002 44 0 0003 -> 121121444444
121 0 r 244 0 0003 -> 244244244
```

Odczyt wyniku konwersji (w przykładzie hash `#` oznacza moment odczytu) wykonywany będzie (może być wykonany, bo nie musi) po wprowadzeniu krotności, ale nie powinien być on traktowany jak operacja reset, czyli możliwe jest przekazanie kolejnej porcji danych, która ma zostać dodana do wyniku.

```text
121 0 0002# 44 0 0003# -> Pierwszy odczyt => 121121  
                          Drugi odczyt    => 121121444444

121 0 0002 44 0 0003# -> Odczyt => 121121444444
```

### Dostarczanie rozwiązania

Proszę o dostarczenie doprowadzonego do stanu działania kodu klasy `Decoder`. W klasie można umieścić własne metody i pola. Nagłówków metod `input`, `output` i `reset` nie wolno zmieniać.
