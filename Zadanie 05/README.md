# Zadanie 05

## Idea zadania

Idea jest prosta - chodzi o implementację podstawowej logiki serwisu aukcyjnego. A przy okazji będzie to kolejne ćwiczenie, gdzie listy i mapy mogą bardzo pomóc. Ponadto, ćwiczenie pozwoli opanować użycie wzorca projektowego obserwator.

## Obserwator

Wzorzec `Obserwator` pojawia się tam, gdzie pewien obiekt (a możne nawet obiekty) zainteresowany jest stanem obiektu innego. Chcemy jednak uniknąć problemu z ciągłym odpytywaniem czy stan się zmienił czy nie. Pomysł polega na tym, że obiekt obserwowany pozwala zarejestrować swoich obserwatorów (także obiekty). Jeśli stan obiektu obserwowanego ulegnie zmianie, on sam powiadomi o tym swoich obserwatorów.

W naszym przypadku mamy do czynienia z serwisem aukcyjnym. Pojawiają się na nim różne przedmioty, które licytują użytkownicy. Aby uniknąć ciągłego sprawdzania ofert przez użytkowników zastosowany został wzorzec obserwator. Jeśli użytkownik zainteresowany jest pewnym przedmiotem informuje o tym serwis. Gdy ktokolwiek przebije ofertę użytkownika, serwis sam go o tym powiadomi.

## Powiadomienia - kiedy?

Użytkownicy oferują za przedmioty pieniądze. Serwis musi zapamiętać kto, za co i ile zaoferował. Każda oferta powoduje, że serwis automatycznie powiadamia tych, których oferty za dany przedmiot były niższe i są takimi powiadomieniami zainteresowani.

## Kto wygrywa?

W przypadku zakończenia aukcji danego przedmiotu osoba, która zaoferowała najwięcej wygrywa.

## Dostarczanie rozwiązania

Proszę o dostarczenie kodu klasy `SerwisAukcyjny`. Klasa ma implementować interfejs `Aukcja` zgodnie z dokumentacją i opisem. Jeśli pojawi się w niej konstruktor, to musi on być konstruktorem bezparametrowym.
