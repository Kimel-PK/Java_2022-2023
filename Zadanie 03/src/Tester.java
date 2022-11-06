import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Tester {
	
	public static String[] spodziewaneWyniki = {
		"1 - (3,3)\n2 - (2,3)\n",
		"1 - (2,2)\n3 - (5,4)\n2 - (4,4)\n7 - (5,3)\n6 - (6,5)\n5 - (3,3)\n4 - (4,5)\n10 - (7,5)\n9 - (5,5)\n8 - (2,3)\n14 - (6,3)\n13 - (6,4)\n12 - (3,4)\n11 - (4,6)\n15 - (6,6)\n",
		"1 - (14,14)\n2 - (16,17)\n3 - (17,17)\n4 - (17,18)\n5 - (15,15)\n6 - (19,19)\n7 - (16,16)\n8 - (14,15)\n9 - (18,18)\n10 - (20,20)\n11 - (18,19)\n12 - (15,16)\n13 - (13,14)\n14 - (13,13)\n15 - (19,20)\n"
	};
	
	public static List<Set<PawnPosition>> spodziewaniSasiedzi = new ArrayList<Set<PawnPosition>>();
	
	public static void main(String[] args) {
		
		int zaliczoneTesty = 0;
		
		// test 1 - czy program w ogole cos robi
		
		spodziewaniSasiedzi.add(new HashSet<>());
		spodziewaniSasiedzi.get(0).add(new PawnPosition2D (2,2,3));
		
		Meeting meeting = new Meeting();
		Szachownica szachownica = new Szachownica(5, 5);
		
		List<PawnPosition> pawnPositions = List.of(
			new PawnPosition2D(1, 2, 1),
			new PawnPosition2D(2, 1, 2)
		);
		
		meeting.addPawns(pawnPositions);
		meeting.addMeetingPoint(new Position2D (3, 3));
		meeting.move();
		
		if (spodziewaneWyniki[0].equals(MeetingToString (meeting)) && spodziewaniSasiedzi.get(0).equals(meeting.getNeighbours(1))) {
			System.out.println("Test 1 zaliczony!");
			zaliczoneTesty++;
		} else {
			System.out.println("Test 1 niezaliczony!");
			System.out.println("Spodziewana szachownica:");
			System.out.println("-- -- -- -- --");
			System.out.println("-- -- -- -- --");
			System.out.println("-- -- -- 02 --");
			System.out.println("-- -- -- 01 --");
			System.out.println("-- -- -- -- --");
			System.out.println("Otrzymana szachownica:");
			szachownica.WypiszSzachownice(meeting.getAllPawns());
			
			System.out.println("Spodziewani sasiedzi 01:");
			for (PawnPosition pionek : spodziewaniSasiedzi.get(0)) {
				System.out.println(pionek.pawnId() + " - (" + pionek.x() + ", " + pionek.y() + ")");
			}
			System.out.println("Otrzymani sasiedzi 01:");
			for (PawnPosition pionek : meeting.getNeighbours(1)) {
				System.out.println(pionek.pawnId() + " - (" + pionek.x() + ", " + pionek.y() + ")");
			}
		}
		
		// test 2 - bardziej skomplikowany ruch
		
		spodziewaniSasiedzi.add(new HashSet<>());
		spodziewaniSasiedzi.get(1).add(new PawnPosition2D (5, 3, 3));
		spodziewaniSasiedzi.get(1).add(new PawnPosition2D (12, 3, 4));
		spodziewaniSasiedzi.get(1).add(new PawnPosition2D (4, 4, 5));
		spodziewaniSasiedzi.get(1).add(new PawnPosition2D (7, 5, 3));
		spodziewaniSasiedzi.get(1).add(new PawnPosition2D (3, 5, 4));
		spodziewaniSasiedzi.get(1).add(new PawnPosition2D (9, 5, 5));
		
		meeting = new Meeting();
		szachownica = new Szachownica(9, 8);
		pawnPositions = List.of(
			new PawnPosition2D(1, 1, 1),
			new PawnPosition2D(2, 2, 3),
			new PawnPosition2D(3, 5, 7),
			new PawnPosition2D(4, 4, 9),
			new PawnPosition2D(5, 1, 3),
			new PawnPosition2D(6, 7, 7),
			new PawnPosition2D(7, 14, 2),
			new PawnPosition2D(8, 2, 2),
			new PawnPosition2D(9, 5, 8),
			new PawnPosition2D(10, 9, 5),
			new PawnPosition2D(11, 4, 10),
			new PawnPosition2D(12, 2, 7),
			new PawnPosition2D(13, 8, 4),
			new PawnPosition2D(14, 22, 3),
			new PawnPosition2D(15, 12, 8)
		);
		meeting.addPawns(pawnPositions);
		meeting.addMeetingPoint(new Position2D (4, 4));
		meeting.move();
		
		if (spodziewaneWyniki[1].equals(MeetingToString (meeting)) && spodziewaniSasiedzi.get(1).equals(meeting.getNeighbours(2))) {
			System.out.println("Test 2 zaliczony!");
			zaliczoneTesty++;
		} else {
			System.out.println("Test 2 niezaliczony!");
			System.out.println("Spodziewana szachownica:");
			System.out.println("-- -- -- -- -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- --");
			System.out.println("-- -- 01 08 -- -- -- --");
			System.out.println("-- -- -- 05 12 -- -- --");
			System.out.println("-- -- -- -- 02 04 11 --");
			System.out.println("-- -- -- 07 03 09 -- --");
			System.out.println("-- -- -- 14 13 06 15 --");
			System.out.println("-- -- -- -- -- 10 -- --");
			System.out.println("-- -- -- -- -- -- -- --");
			System.out.println("Otrzymana szachownica:");
			szachownica.WypiszSzachownice(meeting.getAllPawns());
			
			System.out.println("Spodziewani sasiedzi 02:");
			for (PawnPosition pionek : spodziewaniSasiedzi.get(1)) {
				System.out.println(pionek.pawnId() + " - (" + pionek.x() + ", " + pionek.y() + ")");
			}
			System.out.println("Otrzymani sasiedzi 02:");
			for (PawnPosition pionek : meeting.getNeighbours(2)) {
				System.out.println(pionek.pawnId() + " - (" + pionek.x() + ", " + pionek.y() + ")");
			}
		}
		
		// test 3 - zmiana punktu zbornego
		
		spodziewaniSasiedzi.add(new HashSet<>());
		spodziewaniSasiedzi.get(2).add(new PawnPosition2D (6,19,19));
		spodziewaniSasiedzi.get(2).add(new PawnPosition2D (15,19,20));
		
		meeting = new Meeting();
		szachownica = new Szachownica(22, 22);

		meeting.addMeetingPoint(new Position2D(4, 4));
		meeting.addPawns(pawnPositions);
		meeting.move();
		meeting.addMeetingPoint(new Position2D(20, 20));
		meeting.move();
		
		if (spodziewaneWyniki[2].equals(MeetingToString (meeting)) && spodziewaniSasiedzi.get(2).equals(meeting.getNeighbours(10))) {
			System.out.println("Test 3 zaliczony!");
			zaliczoneTesty++;
		} else {
			System.out.println("Test 3 niezaliczony!");
			System.out.println("Spodziewana szachownica:");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- 14 13 -- -- -- -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- 01 08 -- -- -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 05 12 -- -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 07 02 -- -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 03 04 -- -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 09 11 -- --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 06 15 --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- 10 --");
			System.out.println("-- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- -- --");
			System.out.println("Otrzymana szachownica:");
			szachownica.WypiszSzachownice(meeting.getAllPawns());
			
			System.out.println("Spodziewani sasiedzi 10:");
			for (PawnPosition pionek : spodziewaniSasiedzi.get(2)) {
				System.out.println(pionek.pawnId() + " - (" + pionek.x() + ", " + pionek.y() + ")");
			}
			System.out.println("Otrzymani sasiedzi 10:");
			for (PawnPosition pionek : meeting.getNeighbours(10)) {
				System.out.println(pionek.pawnId() + " - (" + pionek.x() + ", " + pionek.y() + ")");
			}
		}
		
		if (spodziewaneWyniki.length == zaliczoneTesty)
			System.out.println("Wszystkie testy zaliczone pomyslnie!");
	}
	
    public static String MeetingToString (Meeting meeting) {
		Set<PawnPosition> pionki = meeting.getAllPawns();
		String string = "";
		for (PawnPosition pionek : pionki) {
			string += pionek.pawnId() + " - (" + pionek.x() + "," + pionek.y() + ")\n";
		}
		return string;
    }
}

class Szachownica {
	int szerokosc;
	int wysokosc;
	
	byte[][] szachownica;
	
	public Szachownica (int x, int y) {
		szachownica = new byte[x][y];
		szerokosc = x;
		wysokosc = y;
	}
	
	public void WypiszSzachownice (Set<PawnPosition> pionki) {
		for (PawnPosition pionek : pionki) {
			szachownica[pionek.x()][pionek.y()] = (byte)pionek.pawnId();
		}
		
		for (int x = 0; x < szerokosc; x++) {
			String linia = "";
			for (int y = 0; y < wysokosc; y++) {
				Formatter formatter = new Formatter();
				if (szachownica[x][y] > 0)
					linia += formatter.format("%02d", szachownica[x][y]) + " ";
				else
					linia += "-- ";
				formatter.close();
			}
			System.out.println(linia);
		}
	}
}