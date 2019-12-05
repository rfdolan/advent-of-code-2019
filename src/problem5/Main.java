package problem5;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	static long problem5part1() {
		System.out.println("<=== Problem 5 part 1 ===>");

		String data = "";

		try {
	      File myObj = new File("problem5.txt");
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

		for(int i=0; i<ints.length; i++) {
			ints[i] = originalState[i];
		}
		boolean shouldHalt = false;
		int input = 1;
		int output = 0;
		int increaseAmount = 0;
		for(int i=0; i<ints.length && !shouldHalt; i+=increaseAmount) {
			int opCode = getOpCode(ints[i]);
			//System.out.println("Opcode " + opCode);
				switch(opCode) {
				case 1:
					ints[ints[i+3]] = getVal(ints, ints[i], 1, i) + getVal(ints, ints[i], 2,i);
					increaseAmount = 4;
					break;
				case 2:
					ints[ints[i+3]] = getVal(ints, ints[i], 1,i) * getVal(ints, ints[i], 2,i);
					increaseAmount = 4;
					break;
				case 3:
					ints[ints[i+1]] = input;
					//System.out.println(ints[ints[i+1]]);
					increaseAmount = 2;
					break;
				case 4:
					//System.out.println("Outputting " + getVal(ints, ints[i], 1,i));
					output += getVal(ints, ints[i], 1,i);
					increaseAmount = 2;
					break;
				case 99:
					shouldHalt = true;
					break;
				default:
					break;
				}
		}
		return output;
	}
	
	static int getVal(int[] array, int inst, int paramNum, int currIndex) {

		int divisor = (int) Math.pow(10, 1+paramNum);
		int mode = inst - (inst%divisor);
		mode = mode % (divisor * 10);
		// Return val at position
		if(mode == 0) {
			return array[array[currIndex + paramNum ]];
		}
		// Return val
		else {
			return array[currIndex + paramNum];
		}
		
	}
	
	static int getOpCode(int code) {
		int opCode = code%100;
		return opCode;

	}
	static long problem5part2() {
		System.out.println("<=== Problem 5 part 2 ===>");

		String data = "";

		try {
	      File myObj = new File("problem5.txt");
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

		for(int i=0; i<ints.length; i++) {
			ints[i] = originalState[i];
		}
		boolean shouldHalt = false;
		int input = 5;
		int output = 0;
		int newPos = 0;
		int increaseAmount = 0;
		for(int i=0; i<ints.length && !shouldHalt; i=newPos) {
			int opCode = getOpCode(ints[i]);
			//System.out.println("Opcode " + opCode);
				switch(opCode) {
				case 1:
					ints[ints[i+3]] = getVal(ints, ints[i], 1, i) + getVal(ints, ints[i], 2,i);
					newPos = i+4;
					break;
				case 2:
					ints[ints[i+3]] = getVal(ints, ints[i], 1,i) * getVal(ints, ints[i], 2,i);
					newPos = i+ 4;
					break;
				case 3:
					ints[ints[i+1]] = input;
					//System.out.println(ints[ints[i+1]]);
					newPos = i+2;
					break;
				case 4:
					//System.out.println("Outputting " + getVal(ints, ints[i], 1,i));
					output += getVal(ints, ints[i], 1,i);
					newPos = i+ 2;
					break;
				case 5:
					if(getVal(ints, ints[i], 1, i) != 0) {
						//System.out.println("Jumpin");
						newPos = getVal(ints, ints[i], 2, i);
					}
					else {
						newPos = i+3;
					}
					break;
				case 6:
					if(getVal(ints, ints[i], 1, i) == 0) {
						newPos = getVal(ints, ints[i], 2, i);
					}
					else {
						newPos = i+3;
						
					}
					break;
				case 7:
					if(getVal(ints, ints[i], 1, i) < getVal(ints, ints[i], 2, i) ) {
						ints[ints[i+3]] = 1;
					}
					else {
						ints[ints[i+3]] = 0;
					}
					newPos = i+4;
					break;
				case 8:
					if(getVal(ints, ints[i], 1, i) == getVal(ints, ints[i], 2, i) ) {
						ints[ints[i+3]] = 1;
					}
					else {
						ints[ints[i+3]] = 0;
					}
					newPos = i+4;
					break;
				case 99:
					shouldHalt = true;
					break;
				default:
					break;
				}
		}
		return output;
	}


	public static void main(String[] args) {
		System.out.println(problem5part1());
		System.out.println(problem5part2());

	}

}
