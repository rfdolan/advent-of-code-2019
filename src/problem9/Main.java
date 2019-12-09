package problem9;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
	static long problem9part1() {
		String data = "";

		try {
	      File myObj = new File("problem9.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {

			data = myReader.nextLine();
			//System.out.println(data);
	      }
	      myReader.close();
		}
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		String[] parts = data.split(",");
		long[] ints = new long[parts.length];
		Integer index = 0;
		for(String s: parts) {
			ints[index] = Long.parseLong(s);
			index++;
		}
		
		IntcodeComputer A = new IntcodeComputer(ints, "A");
		boolean halt = false;
		Response r = new Response();
		while(!halt) {
			r=A.run(2);
			System.out.println(r.getOutput());
			if(r.getEndCode() == 99) {
				System.out.println("HALT");
				halt = true;
			}
		}
		

		long result = r.getOutput();
		return result;
		
		
	}

	public static void main(String[] args) {
		System.out.println(problem9part1());


	}

}
