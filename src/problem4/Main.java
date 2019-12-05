package problem4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	static long problem4() {
		System.out.println("<=== Problem 4 part 1 ===>");

		String data = "";

		try {
	      File myObj = new File("problem4.txt");
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
		String[] nums = data.split("-");
		int lowerBound = Integer.parseInt(nums[0]);
		int upperBound = Integer.parseInt(nums[1]);
		int numPasswords = 0;


		for(int i=lowerBound; i<upperBound; i++) {
			//System.out.println(i);
			int divisor = 10;
			boolean hasAdjacent = false;
			boolean doesntDecrease = true;
			int prevDig = i%divisor;
			int subtract = prevDig;
			for(int digit=0; digit < 5; digit++) {
				//System.out.println(prevDig);
				divisor *= 10;
				int currDig = (i%divisor - subtract) / (divisor/10);
				subtract = i%divisor;
				if(currDig == prevDig) {
					//System.out.println(i + " has matching digits");
					hasAdjacent = true;
				}
				if(currDig > prevDig) {
					//System.out.println(i + " decreases");
					doesntDecrease = false;
				}
				prevDig = currDig;
			}
			if(hasAdjacent && doesntDecrease) {
				numPasswords++;
			}
			
		}
		
		return numPasswords;
	}

	static long problem4part2() {
		System.out.println("<=== Problem 4 part 2 ===>");

		String data = "";

		try {
	      File myObj = new File("problem4.txt");
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
		String[] nums = data.split("-");
		int lowerBound = Integer.parseInt(nums[0]);
		int upperBound = Integer.parseInt(nums[1]);
		int numPasswords = 0;


		for(int i=lowerBound; i<upperBound; i++) {
			//System.out.println(i);
			int divisor = 10;
			boolean hasAdjacent = false;
			boolean doesntDecrease = true;
			int prevDig = 0;
			int currDig = i%divisor;
			int subtract = currDig;
			boolean prevAdjacent = false;
			boolean bad = false;


			for(int digit=0; digit < 5; digit++) {
				prevDig = currDig;
				//System.out.println(prevDig);
				divisor *= 10;
				currDig = (i%divisor - subtract) / (divisor/10);
				subtract = i%divisor;
				// Part of bigger group, this is bad
				if(currDig == prevDig && prevAdjacent) {
					prevAdjacent = true;
					bad = true;
					//System.out.println(i + " has multiple in a row");
				}
				// Not yet part of bigger group, could be good
				else if(currDig == prevDig && !prevAdjacent) {
					prevAdjacent = true;
					bad = false;
				}
				// We just passed a pair that was good, nice
				else if(currDig != prevDig && !bad && prevAdjacent) {
					hasAdjacent = true;
					bad = false;
				}

				else {
					prevAdjacent = false;
					bad = false;
				}
				if(currDig > prevDig) {
					//System.out.println(i + " decreases");
					doesntDecrease = false;
				}
			}
				// We just passed a pair that was good, nice
				if( !bad && prevAdjacent) {
					hasAdjacent = true;
				}
			if(hasAdjacent && doesntDecrease) {
				//System.out.println(i+ " is good");
				numPasswords++;
			}
			
		}
		
		return numPasswords;
	}
	public static void main(String[] args) {
		System.out.println(problem4());
		System.out.println(problem4part2());

	}

}
