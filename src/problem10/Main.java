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
		int bestX = -1;
		int bestY = -1;
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
									bestX = currX;
									bestY = currY;
								}

							}
						}
					}
				}
			}
		}
		bestChanges = sort(bestChanges);
		int numZapped = 0;
		int angToZap = 0;
		Coordinate source = new Coordinate(bestX, bestY);
		//System.out.println("Source: (" + bestX + "," + bestY + ")");
		Coordinate lastZapped = new Coordinate(0,0);
		while(numZapped < 200) {
			Coordinate zapped = zapInDirection(bestChanges.get(angToZap),source, input);
			if(zapped.getX() != -1) {
				numZapped++;
				//System.out.println("Zapped asteroid at (" + zapped.getX() + "," + zapped.getY() +")");
				//System.out.println(numZapped);
				input = zap(zapped, input);
				lastZapped = zapped;
				//System.out.println("Zapped asteroid at (" + zapped.getX() + "," + zapped.getY() + ")");
			}
			angToZap++;
			if(angToZap >= bestChanges.size()) {
				angToZap = 0;
			}
		}

		System.out.println("The last asteroid zapped was at (" + lastZapped.getX() + "," + lastZapped.getY() + ")");
		return (10 * lastZapped.getY()) + lastZapped.getX();
	}
	
	static ArrayList<String> zap(Coordinate c, ArrayList<String> field) {
		String row = field.get(c.getY());
		char[] array = row.toCharArray();
		array[c.getX()] = '.';
		field.remove(c.getY());
		String newStr = String.valueOf(array);
		field.add(c.getY(), newStr);
		
		return field;
	}
	
	static Coordinate zapInDirection(CoordinateChange ang, Coordinate source,ArrayList<String> field) {
		//System.out.println("Zap in direction " + ang.getXChange() + "," + ang.getYChange());
		
		int targetX = source.getX() + ang.getXChange();
		int targetY = source.getY() + ang.getYChange();
		while(targetY >= 0 && targetY < field.get(0).length() && targetX >= 0 && targetX < field.size()) {
			//System.out.println("Trying to zap (" + targetX + "," + targetY + ")");
			if(field.get(targetY).charAt(targetX) == '#') {
				//System.out.println("Good zap in direction " + ang.getXChange() + "," + ang.getYChange());
				return new Coordinate(targetX, targetY);
			}
			targetX += ang.getXChange();
			targetY += ang.getYChange();
		}
		return new Coordinate(-1,-1);
	}
	

	static ArrayList<CoordinateChange> sort(ArrayList<CoordinateChange> list) {
		ArrayList<CoordinateChange> newList = new ArrayList<CoordinateChange>();
		//System.out.println("List size is " + list.size());
		int initSize = list.size();
		int numInserted = 0;
		int inserted = 0;
		int negativeXPointer = 1;
		int positiveXPointer = 0;
			newList.add(new CoordinateChange(0,-1));
			list.remove(new CoordinateChange(0, -1));
			positiveXPointer++;
			//System.out.println("Inserted change with undefined slope");
			newList.add(new CoordinateChange(0,1));
			list.remove(new CoordinateChange(0, 1));
			negativeXPointer++;
			//System.out.println("Inserted change with undefined slope");
		newList.add(new CoordinateChange(0,0));
		while(list.size()> 3) {

			float maxSlopePos = Integer.MIN_VALUE;
			float maxSlopeNeg = Integer.MIN_VALUE;
			CoordinateChange maxChangePos = new CoordinateChange(0,0);
			CoordinateChange maxChangeNeg = new CoordinateChange(0,0);
			//System.out.println("LIST SIZE: " + list.size());
			for(int i=0; i<list.size(); i++) {
				CoordinateChange curr = list.get(i);
				if((curr.getSlope() >= maxSlopePos) && curr.getXChange() > 0) {
					maxSlopePos = curr.getSlope();
					maxChangePos = curr;
				}
				else if((curr.getSlope() >= maxSlopeNeg) && curr.getXChange() < 0) {
					maxSlopeNeg = curr.getSlope();
					maxChangeNeg = curr;
				}
				
			}
			//System.out.println("Max pos slope: " + maxSlopePos);
			//System.out.println(maxChangePos.getXChange() + ","+ maxChangePos.getYChange());
			//System.out.println("Max neg slope: " + maxSlopeNeg);
			//System.out.println(maxChangeNeg.getXChange() + ","+ maxChangeNeg.getYChange());
			// If we have another max
			if(!(maxChangePos.getXChange() == 0 && maxChangePos.getYChange() == 0)) {
				newList.add(positiveXPointer, maxChangePos);
				list.remove(maxChangePos);
				positiveXPointer++;
				negativeXPointer++;
				
			}
			if(!(maxChangeNeg.getXChange() == 0 && maxChangeNeg.getYChange() == 0)) {
				newList.add(negativeXPointer, maxChangeNeg);
				list.remove(maxChangeNeg);
				negativeXPointer++;
			}
			
		}
		newList.remove(initSize-1);
		/*
		 * for(CoordinateChange c: newList) { if(c.getXChange() == 0) {
		 * //System.out.println("Undefined"); } else {
		 * //System.out.println("Added change (" + c.getXChange() + "," + c.getYChange()
		 * + ")"); } }
		 */
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
