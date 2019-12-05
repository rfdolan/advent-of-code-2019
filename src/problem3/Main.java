package problem3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

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
		System.out.println(problem3part1());
		System.out.println(problem3part2());

	}

}
