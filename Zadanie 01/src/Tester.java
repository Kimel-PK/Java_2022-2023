public class Tester {
	
	public static byte[][] input = {
		// #1 - 1 test z przykladu 1
		{
			4, 8, 2, 1, 4, 8, 0, 0, 0, 0, 1, '#'
		},
		// #2 - 2 test z przykladu 1
		{
			4, 8, 2, 1, 4, 8, -1, 0, 0, 0, 0, 1, '#'
		},
		// #3 - 3 test z przykladu 1
		{
			4, 8, 2, 1, 4, 8, 12, 0, 0, 0, 0, 1, '#'
		},
		// #4 - 4 test z przykladu 1
		{
			2, 0, 0, 0, 1, 4, '#'
		},
		// #5 - 5 test z przykladu 1
		{
			2, 4, 1, 0, 0, 0, 0, 4, '#'
		},
		// #6 - 6 test z przykladu 1
		{
			1, 2, 1, 0, 0, 0, 0, 2, 4, 4, 0, 0, 0, 0, 3, '#'
		},
		// #7 - 7 test z przykladu 1
		{
			1, 2, 1, 0, 'r', 2, 4, 4, 0, 0, 0, 0, 3, '#'
		},
		// #8, #9 - 1 test z przykladu 2
		{
			1, 2, 1, 0, 0, 0, 0, 2, '#', 4, 4, 0, 0, 0, 0, 3, '#'
		},
		// #10 - 1 test z przykladu 2
		{
			1, 2, 1, 0, 0, 0, 0, 2, 4, 4, 0, 0, 0, 0, 3, '#'
		},
		// #11, #12 - test odczytu zanim skończymy przetwarzac wejscie
		{
			4, 5, '#', 6, 0, 0, 0, 1, 0, 1, 0, 0, 0, 0, 4, '#'
		},
		// #13 pusty string
		{
			'#'
		},
		// #14 reset w trakcie
		{
			9, 8, 7, 'r', 6, 0, 0, 0, 0, 5, '#'
		},
		// #15 niepoprawne symbole
		{
			2, 'e', 'f', -7, 1, '.', 3, 7, 0, 0, 0, 0, 8, '#'
		}
	};
	
	public static String[] expectedOutput = {
		"482148", // #1
		"482148", // #2
		"482148", // #3
		"22222222222222", // #4
		"241241241241", // #5
		"121121444444", // #6
		"244244244", // #7
		"121121", // #8
		"121121444444", // #9
		"121121444444", // #10
		"", // #11,
		"4564564564564564564564564564561111", // #12
		"", // #13
		"66666", // #14
		"21372137213721372137213721372137" // #15
	};
	
	public static void main(String[] args) {
		
		int passed = 0;
		int currentTest = 0;
		
		for (int i = 0; i < input.length; i++) {
			Decoder decoder = new Decoder();
			for (int j = 0; j < input[i].length; j++) {
				
				if (input[i][j] == 'r') {
					decoder.reset();
				} else if (input[i][j] == '#') {
					
					String output = decoder.output();
					
					if (output.equals(expectedOutput[currentTest])) {
						System.out.println("Test nr " + (currentTest + 1) + " zaliczony!");
						passed++;
					} else
						System.out.println("Test nr " + (currentTest + 1) + " niezaliczony!\nOczekiwano: " + expectedOutput[currentTest] + "\n Otrzymano: " + output);
						
					currentTest++;
					
				} else
					decoder.input(input[i][j]);
			}
		}
		
		System.out.println("Zaliczono " + passed + " z " + currentTest + " testow!");
	}
}