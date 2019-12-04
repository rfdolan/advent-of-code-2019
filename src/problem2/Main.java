package problem2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	static long problem2() {
		System.out.println("<=== Problem 2 part 1 ===>");
		String data = "";

		try {
	      File myObj = new File("problem2.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
			data = myReader.nextLine();
	      }
	      myReader.close();
		}
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		String[] parts = data.split(",");
		int[] ints = new int[parts.length];
		Integer index = 0;
		for(String s: parts) {
			ints[index] = Integer.parseInt(s);
			index++;
		}

		ints[1] = 12;
		ints[2] = 2;
		boolean shouldHalt = false;
		for(int i=0; i<ints.length && !shouldHalt; i+=4) {
			//System.out.println("i = " + i);
			switch(ints[i]) {
			case 1:
				ints[ints[i+3]] = ints[ints[i+1]] + ints[ints[i+2]];
				break;
			case 2:
				ints[ints[i+3]] = ints[ints[i+1]] * ints[ints[i+2]];
				break;
			case 99:
				shouldHalt = true;
				break;
			default:
				break;
			}
		}
		return ints[0];
	}

	static long problem2part2() {
		System.out.println("<=== Problem 2 part 2 ===>");

		String data = "";

		try {
	      File myObj = new File("problem2.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
			data = myReader.nextLine();
	      }
	      myReader.close();
		}
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		String[] parts = data.split(",");
		int[] ints = new int[parts.length];
		Integer index = 0;
		for(String s: parts) {
			ints[index] = Integer.parseInt(s);
			index++;
		}
		int[] originalState = new int[ints.length];
		for(int i=0; i< ints.length; i++) {
			originalState[i] = ints[i];
		}

			for(int one = 0; one < 100; one++) {
				for(int two = 0; two < 100; two++) {
					for(int i=0; i<ints.length; i++) {
						ints[i] = originalState[i];
					}
					ints[1] = one;
					ints[2] = two;
					boolean shouldHalt = false;
					for(int i=0; i<ints.length && !shouldHalt; i+=4) {
						if(ints[i+1] < ints.length && ints[i+2] < ints.length && ints[i+3] < ints.length) {
							switch(ints[i]) {
							case 1:
								ints[ints[i+3]] = ints[ints[i+1]] + ints[ints[i+2]];
								break;
							case 2:
								ints[ints[i+3]] = ints[ints[i+1]] * ints[ints[i+2]];
								break;
							case 99:
								shouldHalt = true;
								break;
							default:
								break;
							}
						}
						else {
							shouldHalt = true;
						}
					}
					if(ints[0] == 19690720) {
						return 100 * ints[1] + ints[2];
					}
				}
			}
	
		return -1;
		
	}
	public static void main(String[] args) {
		problem2();
		problem2part2();

	}

}
