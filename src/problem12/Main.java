package problem12;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	static int problem12part1() {
		// Honestly don't feel like reading from a file today.
		
		/*
		String data = "";

		try {
	      File myObj = new File("problem12.txt");
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
		    */
		/*
		 * <x=-1, y=0, z=2>
<x=2, y=-10, z=-7>
<x=4, y=-8, z=8>
<x=3, y=5, z=-1>
		 */
		ArrayList<Moon> moons = new ArrayList<Moon>();
		ArrayList<Moon> initMoons = new ArrayList<Moon>();
		/*
		moons.add(new Moon(new Coordinate(-1,0,2)));
		moons.add(new Moon(new Coordinate(2,-10,-7)));
		moons.add(new Moon(new Coordinate(4,-8,8)));
		moons.add(new Moon(new Coordinate(3,5,-1)));
		*/
		moons.add(new Moon(new Coordinate(-2,9,-5)));
		moons.add(new Moon(new Coordinate(16,19,9)));
		moons.add(new Moon(new Coordinate(0,3,6)));
		moons.add(new Moon(new Coordinate(11,0,11)));
		initMoons.add(new Moon(new Coordinate(-2,9,-5)));
		initMoons.add(new Moon(new Coordinate(16,19,9)));
		initMoons.add(new Moon(new Coordinate(0,3,6)));
		initMoons.add(new Moon(new Coordinate(11,0,11)));
		int steps = 0;
		int currEnergy = 0;
		while(steps <= 1000) {
			currEnergy = 0;
			System.out.println("\nAfter " + steps + " steps:");
			showSystem(moons);
			showEnergy(moons);
			for(Moon m: moons) {
				for(Moon other: moons) {
					m.applyGravity(other.getPosition());
				}
			}
			for(Moon m: moons) {
				m.applyVelocity();
			}

			
			steps++;
			
		}

		return currEnergy;

	}
	
	static long problem12part2() {
		// Honestly don't feel like reading from a file today.
		
		/*
		String data = "";

		try {
	      File myObj = new File("problem12.txt");
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
		    */
		/*
		 * <x=-1, y=0, z=2>
<x=2, y=-10, z=-7>
<x=4, y=-8, z=8>
<x=3, y=5, z=-1>
		 */
		ArrayList<Moon> moons = new ArrayList<Moon>();
		ArrayList<Moon> initMoons = new ArrayList<Moon>();
		/*
		 // Test input 1
		moons.add(new Moon(new Coordinate(-1,0,2)));
		moons.add(new Moon(new Coordinate(2,-10,-7)));
		moons.add(new Moon(new Coordinate(4,-8,8)));
		moons.add(new Moon(new Coordinate(3,5,-1)));
		initMoons.add(new Moon(new Coordinate(-1,0,2)));
		initMoons.add(new Moon(new Coordinate(2,-10,-7)));
		initMoons.add(new Moon(new Coordinate(4,-8,8)));
		initMoons.add(new Moon(new Coordinate(3,5,-1)));
		*/
		
		// Real input
		moons.add(new Moon(new Coordinate(-2,9,-5)));
		moons.add(new Moon(new Coordinate(16,19,9)));
		moons.add(new Moon(new Coordinate(0,3,6)));
		moons.add(new Moon(new Coordinate(11,0,11)));
		initMoons.add(new Moon(new Coordinate(-2,9,-5)));
		initMoons.add(new Moon(new Coordinate(16,19,9)));
		initMoons.add(new Moon(new Coordinate(0,3,6)));
		initMoons.add(new Moon(new Coordinate(11,0,11)));
		
		/*
		// test input 2
		moons.add(new Moon(new Coordinate(-8,-10,0)));
		moons.add(new Moon(new Coordinate(5,5,10)));
		moons.add(new Moon(new Coordinate(2,-7,3)));
		moons.add(new Moon(new Coordinate(9,-8,-3)));
		initMoons.add(new Moon(new Coordinate(-8,-10,0)));
		initMoons.add(new Moon(new Coordinate(5,5,10)));
		initMoons.add(new Moon(new Coordinate(2,-7,3)));
		initMoons.add(new Moon(new Coordinate(9,-8,-3)));
		*/
		int steps = 0;
		int currEnergy = 0;
		boolean match = false;
		int timeToSameX = 0;
		int timeToSameY = 0;
		int timeToSameZ = 0;
		
		while(timeToSameX== 0 || timeToSameY== 0 || timeToSameZ== 0 ) {
			//System.out.println("Steps: " + steps);
			for(Moon m: moons) {
				for(Moon other: moons) {
					m.applyGravity(other.getPosition());
				}
			}
			for(Moon m: moons) {
				m.applyVelocity();
			}

			steps++;
			
			if(moons.get(0).getPosition().getX() == initMoons.get(0).getPosition().getX() &&
				moons.get(1).getPosition().getX() == initMoons.get(1).getPosition().getX() &&
				moons.get(2).getPosition().getX() == initMoons.get(2).getPosition().getX() &&
				moons.get(3).getPosition().getX() == initMoons.get(3).getPosition().getX() ) {
					if(moons.get(0).getVelocity().getX() == initMoons.get(0).getVelocity().getX() &&
						moons.get(1).getVelocity().getX() == initMoons.get(1).getVelocity().getX() &&
						moons.get(2).getVelocity().getX() == initMoons.get(2).getVelocity().getX() &&
						moons.get(3).getVelocity().getX() == initMoons.get(3).getVelocity().getX() ) {
						timeToSameX= steps;
					}
			}
			if(moons.get(0).getPosition().getY() == initMoons.get(0).getPosition().getY() &&
				moons.get(1).getPosition().getY() == initMoons.get(1).getPosition().getY() &&
				moons.get(2).getPosition().getY() == initMoons.get(2).getPosition().getY() &&
				moons.get(3).getPosition().getY() == initMoons.get(3).getPosition().getY() ) {
				if(moons.get(0).getVelocity().getY() == initMoons.get(0).getVelocity().getY() &&
				moons.get(1).getVelocity().getY() == initMoons.get(1).getVelocity().getY() &&
				moons.get(2).getVelocity().getY() == initMoons.get(2).getVelocity().getY() &&
				moons.get(3).getVelocity().getY() == initMoons.get(3).getVelocity().getY() ) {
					timeToSameY= steps;
				}
			}
			if(moons.get(0).getPosition().getZ() == initMoons.get(0).getPosition().getZ() &&
				moons.get(1).getPosition().getZ() == initMoons.get(1).getPosition().getZ() &&
				moons.get(2).getPosition().getZ() == initMoons.get(2).getPosition().getZ() &&
				moons.get(3).getPosition().getZ() == initMoons.get(3).getPosition().getZ() ) {
				if(moons.get(0).getVelocity().getZ() == initMoons.get(0).getVelocity().getZ() &&
					moons.get(1).getVelocity().getZ() == initMoons.get(1).getVelocity().getZ() &&
					moons.get(2).getVelocity().getZ() == initMoons.get(2).getVelocity().getZ() &&
					moons.get(3).getVelocity().getZ() == initMoons.get(3).getVelocity().getZ() ) {
					timeToSameZ= steps;
				}
			}
			
		}
		ArrayList<Integer> nums = new ArrayList<Integer>();
		nums.add(timeToSameX);
		nums.add(timeToSameY);
		nums.add(timeToSameZ);
		Collections.sort(nums);
		long max = nums.get(2);
		long min = nums.get(0);
		long numLoops = max;
		boolean found = false;
		for(Integer i: nums) {
			System.out.println(i);
		}
		/*
		while(!found) {
			if(numLoops % nums.get(0) == 0 && numLoops%nums.get(1) == 0 ) {
				found = true;
			}
			else {
				//System.out.println("Not " + numLoops);
				numLoops += max;
			}
		}
		*/

		return numLoops/2;

		
	}
	static boolean hasInitMatch(ArrayList<Moon> init, Moon curr) {
		for(Moon m: init) {
			if(m.equals(curr)) {
				return true;
			}
		}
		return false;
	}
	
	
	static boolean systemsEqual(ArrayList<Moon> moons, ArrayList<Moon> init) {
		boolean hasMatch = false;
		for(Moon m: moons) {
			for(Moon other: init) {
				if(other.equals(m)) {
					hasMatch = true;
				}
			}
			if(!hasMatch) {
				return false;
			}
			hasMatch = false;
		}
		return true;
	}
	static void showSystem(ArrayList<Moon> moons) {
		for(Moon m: moons) {
			Coordinate pos = m.getPosition();
			Coordinate vel = m.getVelocity();
			// Stupid and bad line
			System.out.println("pos=<x= " +pos.getX() + ", y= " + pos.getY() + ", z= " + pos.getZ() + ">, vel=<x= " + vel.getX() + ", y= " + vel.getY() + ", z= "+vel.getZ() + ">" );
		}
	}
	static void showEnergy(ArrayList<Moon> moons) {
		int energy = 0;
		for(Moon m: moons) {
			energy += m.getTotalEnergy();
		}
		System.out.println("Energy: " +  energy);
	}

	public static void main(String[] args) {
		
		System.out.println(problem12part1());
		System.out.println(problem12part2());

	}

}
