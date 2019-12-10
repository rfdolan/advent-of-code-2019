package problem10;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static int problem10part1() {
		String data = "";
		ArrayList<String> input = new ArrayList<String>();

		try {
	      File myObj = new File("problem10.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {

			data = myReader.nextLine();
			input.add(data);
	      }
	      myReader.close();
		}
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		int height = input.size();
		int width = input.get(0).length();
		int maxSeen = 0;
		int bestX = -1;
		int bestY = -1;

		for(int currY=0; currY<height; currY++) {
			for(int currX=0; currX<width; currX++) {
				ArrayList<CoordinateChange> changes = new ArrayList<CoordinateChange>();
				int count = -1;
				// If this is an asteroid, check if it would be a good base.
				if(input.get(currY).charAt(currX) == '#') {
					//System.out.println("Testing (" + currX + "," + currY + ")");
					for(int y=0; y<height; y++) {
						for(int x=0; x<width; x++) {

							if(input.get(y).charAt(x) == '#') {
								int xChange = x-currX;
								int yChange = y-currY;
								int gcf = GCF(Math.abs(xChange), Math.abs(yChange));
								xChange = xChange/gcf;
								yChange = yChange/gcf;
								CoordinateChange change = new CoordinateChange(xChange, yChange);
								boolean blocked = false;
								for(CoordinateChange c: changes) {
									if(c.equals(change)) {
										//System.out.println(xChange + "," + yChange + " is blocked by " + c.getXChange() + "," +c.getYChange());
										blocked = true;
									}
								}
								if(!blocked) {
									//System.out.println(xChange + "," + yChange + " is not blocked!");
									/*
									for(int i=0; i<changes.size(); i++) {
										if(change.isBlocking(changes.get(i))) {
											changes.remove(i);
											count--;
											i--;
										}
									}
									*/
									changes.add(change);
									count++;

								}
								if(count > maxSeen) {
									bestX = currX;
									bestY = currY;
									maxSeen = count;
								}

							}
						}
					}
					//System.out.println("Count: " + count);
				}

				
			}
		}

		
		System.out.println("(" + bestX + "," + bestY + ")");
		return maxSeen;
	}

	static int problem10part2() {
		String data = "";
		ArrayList<String> input = new ArrayList<String>();

		try {
	      File myObj = new File("problem10.txt");
	      Scanner myReader = new Scanner(myObj);
	      while (myReader.hasNextLine()) {

			data = myReader.nextLine();
			input.add(data);
	      }
	      myReader.close();
		}
		catch (FileNotFoundException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		int height = input.size();
		int width = input.get(0).length();
		int maxSeen = 0;
		ArrayList<CoordinateChange> bestChanges = new ArrayList<CoordinateChange>();

		for(int currY=0; currY<height; currY++) {
			for(int currX=0; currX<width; currX++) {
				ArrayList<CoordinateChange> changes = new ArrayList<CoordinateChange>();
				int count = -1;
				// If this is an asteroid, check if it would be a good base.
				if(input.get(currY).charAt(currX) == '#') {
					//System.out.println("Testing (" + currX + "," + currY + ")");
					for(int y=0; y<height; y++) {
						for(int x=0; x<width; x++) {

							if(input.get(y).charAt(x) == '#') {
								int xChange = x-currX;
								int yChange = y-currY;
								int gcf = GCF(Math.abs(xChange), Math.abs(yChange));
								xChange = xChange/gcf;
								yChange = yChange/gcf;
								CoordinateChange change = new CoordinateChange(xChange, yChange);
								boolean blocked = false;
								for(CoordinateChange c: changes) {
									if(c.equals(change)) {
										//System.out.println(xChange + "," + yChange + " is blocked by " + c.getXChange() + "," +c.getYChange());
										blocked = true;
									}
								}
								if(!blocked) {
									//System.out.println(xChange + "," + yChange + " is not blocked!");
									/*
									for(int i=0; i<changes.size(); i++) {
										if(change.isBlocking(changes.get(i))) {
											changes.remove(i);
											count--;
											i--;
										}
									}
									*/
									changes.add(change);
									count++;

								}
								if(count > maxSeen) {
									maxSeen = count;
									bestChanges = changes;
								}

							}
						}
					}
					//System.out.println("Count: " + count);
				}

				
			}
		}

		
		return maxSeen;
	}

	ArrayList<CoordinateChange> sort(ArrayList<CoordinateChange> list) {
		ArrayList<CoordinateChange> newList = new ArrayList<CoordinateChange>();
		int numInserted = 0;
		int currX = 0;
		int currY = 0;
		int minX = 0;
		int minY = 0;
		int maxX = 0;
		int maxY = 0;
		for(CoordinateChange c: list) {
			int x = c.getXChange();
			int y = c.getYChange();
			if(x < minX) {
				minX = x;
			}
			if(y < minY) {
				minY = y;
			}
			if(x > maxX) {
				maxX = x;
			}
			if(y > maxY) {
				maxY = y;
			}
		}
		int inserted = 0;
		while(inserted < list.size()) {
			CoordinateChange c = new CoordinateChange(currX, currY);
			if(list.contains(c)) {
				newList.add(c);
				inserted++;
			}
			
		}

		return newList;
	}
	public static int GCF(int a, int b) {
		if(a==0) {
			if(b==0)
				return 1;
			else
				return b;
		}
	    if (b == 0) {
	      return a;
	    } else {
	      return (GCF(b, a % b));
	    }
	  }
	public static void main(String[] args) {
		System.out.println(problem10part1());
		System.out.println(problem10part2());

	}

}
