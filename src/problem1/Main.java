package problem1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

	static long problem1() {
		System.out.println("<=== Problem 1 ===>");
		long solution = 0;
		try {
	      File myObj = new File("problem1.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        int curr = Integer.parseInt(data);
	        while(curr > 0) {
				curr = curr / 3;
				curr -= 2;
				if(curr > 0) {
					solution += curr;
				}
	        }
	      }
	      myReader.close();
		}
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		return solution;
	}
	public static void main(String[] args) {
		problem1();

	}

}
