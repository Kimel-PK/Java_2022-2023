import java.util.List;
import java.util.Set;

/**
 * Intefejs dla programu wyznaczającego położenie pionków.
 */
public interface MeetingInterface {
	/**
	 * Metoda dodaje listę położeń, w których początkowo znajdują się pionki.
	 * Ponieważ kolejność pionków decyduje o kolejności ruchu użyta jest lista.
	 * 
	 * @param positions położenia pionków
	 */
	public void addPawns(List<PawnPosition> positions);

	/**
	 * Metoda ustawia punkt zborny - do tego punktu starają się dotrzeć pionki
	 * 
	 * @param meetingPointPosition położenie punktu zbornego
	 */
	public void addMeetingPoint(Position meetingPointPosition);

	/**
	 * Ruch pionków. Metoda kończy się, gdy żaden z pionków nie może przysunąć się
	 * bliżej punktu zbornego, bo blokowany jest przez inne pionki.
	 */
	public void move();

	/**
	 * Metoda zwraca końcowe położenia wszystkich pionków. Położenie pionka o danym
	 * numerze identyfikacyjnym zazwyczaj ulegnie zmianie, jego numer musi jednak
	 * pozostać niezmieniony. Każdy pionek musi znajdować się na innej pozycji.
	 * 
	 * @return końcowe położenia pionków
	 */
	public Set<PawnPosition> getAllPawns();

	/**
	 * Metoda zwraca położenia pionków, które sąsiadują z pionkiem o podanym pawnId.
	 * 
	 * @param pawnId numer pionka
	 * @return sąsiedzi pionka pawnId
	 */
	public Set<PawnPosition> getNeighbours(int pawnId);
}
