package problem8;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int width = 25;
	static int height = 6;
	static int problem8part1() {
		ArrayList<Layer> layers = getLayers();
		Layer minZeroes = new Layer(width, height, Integer.MAX_VALUE);
		for(Layer l: layers) {
			l.calcNumZeroes();
			System.out.println(l.getNumZeroes());
			if(l.getNumZeroes() < minZeroes.getNumZeroes()) {
				minZeroes = l;
			}
			
		}

		
		return minZeroes.getTwosTimesOnes();
	}
	
	static ArrayList<Layer> getLayers() {
		String data = "";

		try {
	      File myObj = new File("problem8.txt");
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
		
		System.out.println("Data length: " + data.length());
		int content = width*height;
		ArrayList<Layer> layers = new ArrayList<Layer>();
		Layer currLayer = new Layer(width, height);
		for(int i=0; i<data.length(); i++) {
			if(i%content == 0) {
				currLayer = new Layer(width, height);
				layers.add(currLayer);
			}
			currLayer.addNum(Integer.parseInt(data.substring(i, i+1)));
			
		}
		return layers;
		
	}

	static int problem8part2() {
		System.out.println("hfjkslahfjkdsf");
		ArrayList<Layer> layers = getLayers();
		Layer result = new Layer(width, height);
		for(int i=0; i<width*height; i++) {
			result.addNum(2);
		}
		for(Layer l: layers) {
			for(int i=0; i<width*height; i++) {
				if(result.getPixelAt(i) == 2) {
					result.setPixelAt(i, l.getPixelAt(i));
				}
				
			}
		}
		for(int i=0; i<width*height; i++) {
			if(i%width == 0) {
				System.out.println("");
			}
			if(result.getPixelAt(i) == 1) {
				System.out.print(0);
			}
			else {
				System.out.print(" ");
				
			}
		}
		
		
		return 0;
	}
	public static void main(String[] args) {
		System.out.println(problem8part1());
		problem8part2();

	}

}
