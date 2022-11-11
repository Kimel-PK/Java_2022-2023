# Zadanie 04

## Idea zadania

Gdzieś w przestrzeni znajdują się punkty. Punkty mają nazwy (`A`, `B`, `C` itd.) i połączone są odcinkami (np. `A-C`). Zadaniem Państwa będzie przetwarzanie zbioru punktów i odcinków. W tym zadaniu pojawią się listy, zbiory i mapy.

## Wejście

Do Państwa programu dostarczam dwa zbiory: zbiór punktów oraz odcinków łączących punkty.

Zakładamy, że odcinki używają tylko tych punktów, które zostały podane w zbiorze punktów.

Nie ma gwarancji na to, że wszystkie punkty będą połączone odcinkami.

## Wyjście

Jednym z zadań jest odnalezienie połączenia pomiędzy parą punktów. Każda poprawna i niezawierająca pętli lista odcinków zostanie potraktowana jako prawidłowe rozwiązanie. Nie ma więc konieczności wyszukiwania np. połączeń najkrótszych. Problem będzie można rozwiązań metodą "brutalnej siły" testując wszelkie możliwe rozwiązania. Górna liczba odcinków łączących punkty nie jest podana.

W przypadku mapy typu `punkt -> zbiór odcinków` do zbioru należy wprowadzić wszystkie odcinki, bez względu na to czy punkt jest początkiem czy końcem odcinka.

W przypadku mapy typu `punkt -> (liczba odcinków -> zbiór punktów)` wystarczy ograniczyć poszukiwania do maksymalnie 4 odcinków. Wyznaczając punkty końcowe nie uwzględnia się ścieżek zawierających pętle typu: `A-B-A-C` lub `A-B-C-A-D`.

## Uwagi

Tak, jeszcze raz podkreślam, że w układzie punkty-odcinki mogą pojawić się pętle. Np. `A-B-C-A`.

Istnienie pętli nie może doprowadzić do "zawieszenia" się programu.

## Dostarczanie rozwiązania

Proszę o dostarczenie kodu klasy `Lines`. Klasa ma implementować interfejs `LinesInterface` zgodnie z dokumentacją i opisem. Jeśli pojawi się w niej konstruktor, to musi on być konstruktorem bezparametrowym.

## Tester

Tester sprawdza poprawność działania klasy `Lines` z wykorzystaniem poniższych grafów.

### Graf 1

Trasa `A -> C`

![graf 1](https://user-images.githubusercontent.com/57668948/201437909-4a20c662-6a2b-42d4-8596-cd0055843956.png)

### Graf 2

Trasa `A -> E`

![graf 2](https://user-images.githubusercontent.com/57668948/201437929-24b806d8-5b59-47b2-b0df-3a08bb20a52e.png)
