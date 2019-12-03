import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
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

	static long problem3part1() {
		System.out.println("<=== Problem 3 part 1 ===>");

		String data = "";
		int wireNum = 1;
		String[] firstWire = null;
		String[] secondWire = null;

		try {
	      File myObj = new File("problem3.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
			data = myReader.nextLine();
			//System.out.println("Fdhjkls");
			if(wireNum == 1) {
				firstWire = data.split(",");
				wireNum++;
			}
			else {
				secondWire = data.split(",");
			}
	      }
	      myReader.close();
		}
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		int AStartX = 0;
		int AStartY = 0;
		int AEndX = 0;
		int AEndY = 0;
		int BStartX = 0;
		int BStartY=0;
		int BEndX = 0;
		int BEndY = 0;
		int shortestDistance = Integer.MAX_VALUE;
		boolean firstGo = true;
		for(String sA: firstWire) {
			AStartX = AEndX;
			AStartY = AEndY;
			int changeAmount = Integer.parseInt(sA.substring(1));
			switch(sA.charAt(0)) {
			case 'U':
				AEndY += changeAmount;
				break;
			case 'D':
				AEndY -= changeAmount;
				break;
			case 'L':
				AEndX -= changeAmount;
				break;
			case 'R':
				AEndX += changeAmount;
				break;
			}
			int lowerAX = Math.min(AEndX,  AStartX);
			int lowerAY = Math.min(AEndY,  AStartY);
			int upperAX = Math.max(AEndX,  AStartX);
			int upperAY = Math.max(AEndY,  AStartY);
			BStartX = 0;
			BStartY = 0;
			BEndX = 0;
			BEndY = 0;
			for(String sB: secondWire) {
				
				BStartX = BEndX;
				BStartY = BEndY;
				int BchangeAmount = Integer.parseInt(sB.substring(1));
				switch(sB.charAt(0)) {
				case 'U':
					BEndY += BchangeAmount;
					break;
				case 'D':
					BEndY -= BchangeAmount;
					break;
				case 'L':
					BEndX -= BchangeAmount;
					break;
				case 'R':
					BEndX += BchangeAmount;
					break;
				}
				
				// Check if b contains a
				int lowerBX = Math.min(BEndX,  BStartX);
				int lowerBY = Math.min(BEndY,  BStartY);
				int upperBX = Math.max(BEndX,  BStartX);
				int upperBY = Math.max(BEndY,  BStartY);
				if(!firstGo && linesIntersect(lowerAX, upperAX, lowerAY, upperAY, lowerBX, upperBX, lowerBY, upperBY)) {
					//System.out.println("hefjhkls");
					if(lowerAX == upperAX) {
						//System.out.println("fd");
						int intersectionX = lowerAX;
						int intersectionY = lowerBY;
						shortestDistance = Math.min(shortestDistance, Math.abs(intersectionX) + Math.abs(intersectionY));
					}
					else if(lowerBX == upperBX) {
						//System.out.println("fddfjsf");
						int intersectionX = lowerBX;
						int intersectionY = lowerAY;
						shortestDistance = Math.min(shortestDistance, Math.abs(intersectionX) + Math.abs(intersectionY));
					}
					
					
				}
				firstGo = false;
			}
		}
		
		return shortestDistance;
	}
	
	static boolean linesIntersect(int lineAX1, int lineAX2, int lineAY1, int lineAY2, int lineBX1, int lineBX2, int lineBY1, int lineBY2) {
		//System.out.println("Line A: (" + lineAX1 + "," + lineAY1 + ") to ("+lineAX2+","+lineAY2+")");
		//System.out.println("Line B: (" + lineBX1 + "," + lineBY1 + ") to ("+lineBX2+","+lineBY2+")");
		if((lineAX1 <= lineBX1 && lineBX1 <= lineAX2) && (lineBY1 <= lineAY1 && lineAY1 <= lineBY2)) {
			return true;
		}
		if((lineBX1 <= lineAX1 && lineAX1 <= lineBX2) && (lineAY1 <= lineBY1 && lineBY1 <= lineAY2)) {
			return true;
		}
		return false;
		
	}

	static long problem3part2() {
		System.out.println("<=== Problem 3 part 2 ===>");

		String data = "";
		int wireNum = 1;
		String[] firstWire = null;
		String[] secondWire = null;

		try {
	      File myObj = new File("problem3.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {
			data = myReader.nextLine();
			//System.out.println("Fdhjkls");
			if(wireNum == 1) {
				firstWire = data.split(",");
				wireNum++;
			}
			else {
				secondWire = data.split(",");
			}
	      }
	      myReader.close();
		}
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		int AStartX = 0;
		int AStartY = 0;
		int AEndX = 0;
		int AEndY = 0;
		int BStartX = 0;
		int BStartY=0;
		int BEndX = 0;
		int BEndY = 0;
		int shortestDelay = Integer.MAX_VALUE;
		int numASteps = 0;
		int numBSteps = 0;
		boolean firstGo = true;
		for(String sA: firstWire) {
			AStartX = AEndX;
			AStartY = AEndY;
			int changeAmount = Integer.parseInt(sA.substring(1));
			switch(sA.charAt(0)) {
			case 'U':
				AEndY += changeAmount;
				break;
			case 'D':
				AEndY -= changeAmount;
				break;
			case 'L':
				AEndX -= changeAmount;
				break;
			case 'R':
				AEndX += changeAmount;
				break;
			}
			numASteps += changeAmount;
			int lowerAX = Math.min(AEndX,  AStartX);
			int lowerAY = Math.min(AEndY,  AStartY);
			int upperAX = Math.max(AEndX,  AStartX);
			int upperAY = Math.max(AEndY,  AStartY);
			BStartX = 0;
			BStartY = 0;
			BEndX = 0;
			BEndY = 0;
			numBSteps = 0;
			for(String sB: secondWire) {
				
				BStartX = BEndX;
				BStartY = BEndY;
				int BchangeAmount = Integer.parseInt(sB.substring(1));
				switch(sB.charAt(0)) {
				case 'U':
					BEndY += BchangeAmount;
					break;
				case 'D':
					BEndY -= BchangeAmount;
					break;
				case 'L':
					BEndX -= BchangeAmount;
					break;
				case 'R':
					BEndX += BchangeAmount;
					break;
				}
				numBSteps += BchangeAmount;
				
				// Check if b contains a
				int lowerBX = Math.min(BEndX,  BStartX);
				int lowerBY = Math.min(BEndY,  BStartY);
				int upperBX = Math.max(BEndX,  BStartX);
				int upperBY = Math.max(BEndY,  BStartY);
				if(!firstGo && linesIntersect(lowerAX, upperAX, lowerAY, upperAY, lowerBX, upperBX, lowerBY, upperBY)) {
					if(lowerAX == upperAX) {
						//System.out.println("fd");
						int intersectionX = lowerAX;
						int intersectionY = lowerBY;
						int tempASteps = numASteps;
						int tempBSteps = numBSteps;
						tempBSteps -= Math.abs(BEndX - intersectionX);
						tempASteps -= Math.abs(AEndY - intersectionY);
						shortestDelay = Math.min(shortestDelay, tempASteps+tempBSteps);
					}
					else if(lowerBX == upperBX) {
						//System.out.println("fddfjsf");
						int intersectionX = lowerBX;
						int intersectionY = lowerAY;
						int tempASteps = numASteps;
						int tempBSteps = numBSteps;
						tempBSteps -= Math.abs(BEndY - intersectionY);
						tempASteps -= Math.abs(AEndX - intersectionX);
						shortestDelay = Math.min(shortestDelay, tempASteps+tempBSteps);
					}
					//System.out.println(shortestDelay);
					
					
				}
				firstGo = false;
			}
		}
		
		return shortestDelay;
	}

	public static void main(String[] args) {
		System.out.println("=== Welcome to advent of code!! ===");
		System.out.println("Answer: " + problem1());
		System.out.println("Answer: " + problem2());
		System.out.println("Answer: " + problem2part2());
		System.out.println("Answer: " + problem3part1());
		System.out.println("Answer: " + problem3part2());

	}

}
