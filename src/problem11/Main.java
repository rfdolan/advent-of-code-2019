package problem11;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static long problem11part1() {
		String data = "";

		try {
	      File myObj = new File("problem11.txt");
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
		
		Coordinate toMove = new Coordinate(0,-1);
		Coordinate currLocation = new Coordinate(0,0);
		ArrayList<Coordinate> blackTiles = new ArrayList<Coordinate>();
		ArrayList<Coordinate> whiteTiles = new ArrayList<Coordinate>();
		ArrayList<Coordinate> paintedTiles = new ArrayList<Coordinate>();
		IntcodeComputer A = new IntcodeComputer(ints, "A");
		boolean halt = false;
		Response r = new Response();
		int currInput = 0;
		int nextInput = 0;
		int numPainted = 0;
		while(!halt) {
			// Run with initial input.
			// Returns color to paint
			r=A.run(currInput);
			if(!hasCoordinate(paintedTiles,currLocation)) {
				paintedTiles.add(currLocation);
				numPainted++;
			}
			if(r.getOutput() == 0) {
				//System.out.println("Painting tile (" + currLocation.getX() +"," + currLocation.getY() +") black");
				if(!hasCoordinate(blackTiles,currLocation)) {
					blackTiles.add(currLocation);
					whiteTiles=evict(whiteTiles,currLocation);
				}
			}
			else {
				//System.out.println("Painting tile (" + currLocation.getX() +"," + currLocation.getY() +") white");
				if(!hasCoordinate(whiteTiles,currLocation)) {
					whiteTiles.add(currLocation);
					blackTiles=evict(blackTiles,currLocation);
				}
			}
			// Returns direction to turn
			r=A.run(currInput);
			// Turn right
			if(r.getOutput() == 1) {
				//System.out.println("Turn right");
				if(toMove.getX() == 1) {
					toMove = new Coordinate(0,1);
				}
				else if (toMove.getX() == -1) {
					toMove = new Coordinate(0,-1);
				}
				else if(toMove.getY() == 1) {
					toMove = new Coordinate(-1,0);
				}
				else if(toMove.getY() == -1) {
					toMove = new Coordinate(1,0);
				}
				
			}
			// turn left
			else {
				//System.out.println("Turn left");
				if(toMove.getX() == 1) {
					toMove = new Coordinate(0,-1);
				}
				else if (toMove.getX() == -1) {
					toMove = new Coordinate(0,1);
				}
				else if(toMove.getY() == 1) {
					toMove = new Coordinate(1,0);
				}
				else if(toMove.getY() == -1) {
					toMove = new Coordinate(-1,0);
				}
				
			}
			currLocation = new Coordinate(currLocation.getX() + toMove.getX(), currLocation.getY() + toMove.getY());
			if(hasCoordinate(whiteTiles, currLocation)) {
				//System.out.println("Ovewr a white tile");
				currInput = 1;
			}
			else
			{
				currInput = 0;
			}
			//System.out.println("Moved to (" + currLocation.getX() + "," + currLocation.getY() + ")");
			if(r.getEndCode() == 99) {
				System.out.println("HALT");
				halt = true;
			}
		}
		return numPainted;
	}

	static long problem11part2() {
		String data = "";

		try {
	      File myObj = new File("problem11.txt");
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
		
		Coordinate toMove = new Coordinate(0,-1);
		Coordinate currLocation = new Coordinate(0,0);
		ArrayList<Coordinate> blackTiles = new ArrayList<Coordinate>();
		ArrayList<Coordinate> whiteTiles = new ArrayList<Coordinate>();
		ArrayList<Coordinate> paintedTiles = new ArrayList<Coordinate>();
		IntcodeComputer A = new IntcodeComputer(ints, "A");
		boolean halt = false;
		Response r = new Response();
		int currInput = 1;
		int nextInput = 0;
		int numPainted = 0;
		while(!halt) {
			// Run with initial input.
			// Returns color to paint
			r=A.run(currInput);
			if(!hasCoordinate(paintedTiles,currLocation)) {
				paintedTiles.add(currLocation);
				numPainted++;
			}
			if(r.getOutput() == 0) {
				//System.out.println("Painting tile (" + currLocation.getX() +"," + currLocation.getY() +") black");
				if(!hasCoordinate(blackTiles,currLocation)) {
					blackTiles.add(currLocation);
					whiteTiles=evict(whiteTiles,currLocation);
				}
			}
			else {
				//System.out.println("Painting tile (" + currLocation.getX() +"," + currLocation.getY() +") white");
				if(!hasCoordinate(whiteTiles,currLocation)) {
					whiteTiles.add(currLocation);
					blackTiles=evict(blackTiles,currLocation);
				}
			}
			// Returns direction to turn
			r=A.run(currInput);
			// Turn right
			if(r.getOutput() == 1) {
				//System.out.println("Turn right");
				if(toMove.getX() == 1) {
					toMove = new Coordinate(0,1);
				}
				else if (toMove.getX() == -1) {
					toMove = new Coordinate(0,-1);
				}
				else if(toMove.getY() == 1) {
					toMove = new Coordinate(-1,0);
				}
				else if(toMove.getY() == -1) {
					toMove = new Coordinate(1,0);
				}
				
			}
			// turn left
			else {
				//System.out.println("Turn left");
				if(toMove.getX() == 1) {
					toMove = new Coordinate(0,-1);
				}
				else if (toMove.getX() == -1) {
					toMove = new Coordinate(0,1);
				}
				else if(toMove.getY() == 1) {
					toMove = new Coordinate(1,0);
				}
				else if(toMove.getY() == -1) {
					toMove = new Coordinate(-1,0);
				}
				
			}
			currLocation = new Coordinate(currLocation.getX() + toMove.getX(), currLocation.getY() + toMove.getY());
			if(hasCoordinate(whiteTiles, currLocation)) {
				//System.out.println("Ovewr a white tile");
				currInput = 1;
			}
			else
			{
				currInput = 0;
			}
			//System.out.println("Moved to (" + currLocation.getX() + "," + currLocation.getY() + ")");
			if(r.getEndCode() == 99) {
				System.out.println("HALT");
				halt = true;
			}
		}
		int maxX = 0;
		int minX = 0;
		int maxY = 0;
		int minY = 0;
		for(Coordinate c: paintedTiles) {
			if(c.getX() > maxX) {
				maxX = c.getX();
			}
			if(c.getX() < minX) {
				minX = c.getX();
			}
			if(c.getY() > maxY) {
				maxY = c.getY();
			}
			if(c.getY() < minY) {
				minY = c.getY();
			}
		}
		char[] empty = new char[maxX+1];
		for(int i=0; i<empty.length; i++) {
			empty[i] = '#';
		}
		String empt = String.valueOf(empty);
		ArrayList<String> canvas = new ArrayList<String>();
		for(int i=0; i<= maxY; i++) {
			canvas.add(empt);
		}
		for(Coordinate c: blackTiles) {
			String curr = canvas.get(c.getY());
			char[] arr = curr.toCharArray();
			arr[c.getX()] = '.';
			canvas.remove(c.getY());
			String newstr = String.valueOf(arr);
			canvas.add(c.getY(), newstr);

		}
		for(String s: canvas) {
			System.out.println(s);
		}
		
		

		

		return 0;
		
		
	}
	
	static ArrayList<Coordinate> evict(ArrayList<Coordinate> list, Coordinate c) {
		for(int i=0; i<list.size(); i++ ) {
			Coordinate cord = list.get(i);
			if(cord.equals(c)) {
				list.remove(cord);
				i--;
			}
		}
		return list;
	}
	
	 static boolean hasCoordinate(ArrayList<Coordinate> list, Coordinate c) {
		 for(Coordinate cord: list) {
			 if(cord.equals(c)) {
				 return true;
			 }
		 }
		 return false;
		 
	 }
	public static void main(String[] args) {
		System.out.println(problem11part1());
		System.out.println(problem11part2());

	}

}
