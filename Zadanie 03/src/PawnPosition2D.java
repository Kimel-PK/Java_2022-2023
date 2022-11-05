import java.util.List;

// https://www.baeldung.com/java-record-keyword

/**
 * Położenie w dwówymiarowej przestrzeni z numerem pionka.
 */
public record PawnPosition2D(int pawnId, int x, int y) implements PawnPosition {

	/**
	 * Mały przykład działania rekordów w Java
	 * 
	 * @param args - lista argumentów programu - nieużywana
	 */
	public static void main(String[] args) {
		Position pos = new PawnPosition2D(1, 3, 3);
		System.out.println(pos);

		List<Position> positions = List.of(new PawnPosition2D(2, 1, 1), new PawnPosition2D(3, 3, 3));

		System.out.println(positions);
	}
}
