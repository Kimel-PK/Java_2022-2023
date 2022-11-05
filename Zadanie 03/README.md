# Zadanie 03

## Idea zadania

W zadaniu pojawi się wiele pionków, które będą poruszać się w kierunku wspólnego dla wszystkich pojedynczego punktu zbornego.

## Ruch pionków

Pionki poruszać będą się na dwuwymiarowej planszy. Położenie pionka to para liczb całkowitych `x` i `y`. Każdy pionek otrzyma dodatkowo unikany numer (`pawnID`). Numer pionka musi być utrzymany, choć jego położenie ulegać będzie zmianie.

O kolejność ruchu decyduje kolejność pionków na przekazanej do programu liście.

W turach nieparzystych ruch pionków realizowany jest od początku do końca listy. W turach parzystych - w kolejności odwrotnej.

## Ruch jednego pionka

Pojedynczy ruch pojedynczego pionka realizowany jest wg. schematu

- wyliczamy `dx`, `dy` - różnicę w położeniu `X`, `Y` pionka w stosunku do położenia punktu zbornego
- jeśli `|dx| > |dy|` - wykonujemy ruch w kierunku `X` o ile jest on możliwy (*)
- jeśli `|dx| <= |dy|` - wykonujemy ruch w kierunku `Y` o ile jest on możliwy (*)
- jeśli `|dx| = |dy| = 0` - brak ruchu, bo pionek dotarł do punktu zbornego
- ruch pionka zawsze realizowany jest w kierunku punktu zbornego
- w jednym ruchu zmianie ulega tylko jedna współrzędna położenia pionka.
- długość pojedynczego kroku pionka to `1` lub `0`

### Najważniejsza zasada (*)

Na jednym polu planszy może znajdować się tylko jeden pionek. Pionki będą się więc wzajemnie blokować w marszu do punktu zbornego.

> UWAGA: to, że pionek nie poruszył się w danej turze, nie oznacza, że nie może poruszyć się w turze kolejnej.

## Koniec ruchu pionków

Koniec ruchu następuje, gdy w turze żaden pionek nie został przysunięty bliżej do punktu zbornego.

## UWAGI

W przypadku metody `getNeighbours` za sąsiadów rozumiemy pionki, których odległość od wskazanego pionka wynosi `1` dla współrzędniej `x` i/lub `y`. Centralny pionek może mieć do ośmiu sąsiadów.

W zadaniu pojawiają się `rekordy`. To nowa rzecz w `Java`. Pozwalają one szybko stworzyć obiekty do przekazywania danych. Mają jednak pewną ciekawą cechę: są `read-only`! Jak więc przesunąć pionka na nowe położenia? Trzeba start obiekt zastąpić nowym. To jedyna droga. Dlaczego własność `read-only` jest taka ciekawa, mam nadzieję, że jeszcze opowiem.

Uwaga: położenie punktu zbornego może ulec zmianie przed lub po wykonaniu `move`. Oznacza to możliwość wykonania ponownie metody `move`, która powinna rozpocząć marsz pionków od położeń, będących wynikiem poprzedniego wykonania `move` (lub od położeń początkowych, jeśli `move` wcześniej nie wykonano).

> UWAGA: na 90% zadanie pojawi się jeszcze raz, lecz w formie zadania współbieżnego.

## Dostarczanie rozwiązania

Proszę o dostarczenie kodu klasy `Meeting`. Klasa ma implementować interfejs `MeetingInterface` zgodnie z dokumentacją i opisem. Jeśli pojawi się w niej konstruktor, to musi on być konstruktorem bezparametrowym.
