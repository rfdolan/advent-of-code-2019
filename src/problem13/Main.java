package problem13;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	static long problem13part1() {
		String data = "";

		try {
	      File myObj = new File("problem13.txt");
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
		int input = 0;
		int numBlocks = 0;
		ArrayList<Coordinate> gameState = new ArrayList<Coordinate>();
		int score = 0;
		while(!halt) {
			boolean add = true;
			

			Coordinate c = new Coordinate();

			r=A.run(input);
			c.setX((int)r.getOutput());
			r=A.run(input);
			c.setY((int)r.getOutput());

			r=A.run(input);
			if(c.getX() == -1 && c.getY() == 0) {
				//System.out.println("JDHJKFLGHS");
				score = (int)r.getOutput();
			}
			else {
				switch((int)r.getOutput()) {
				case 0: 
					c.setType(' ');
					break;
				case 1:
					c.setType('#');
					break;
				case 2:
					c.setType('B');
					break;
				case 3:
					c.setType('P');
					break;
				case 4:
					c.setType('O');
					break;
				default:
					break;
				}
				//System.out.println("Adding (" + c.getX() + "," + c.getY() +")");
				gameState.add(c);
			}
			if(r.getOutput() == 2) {
				numBlocks++;
			}
			if(r.getEndCode() == 99) {
				halt = true;
			}
		}
		
		drawGame(gameState);
		
		return numBlocks;
		
	}

	static long problem13part2() {
		String data = "";

		try {
	      File myObj = new File("problem13.txt");
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
		int input = 0;
		int numBlocks = 0;
		ArrayList<Coordinate> gameState = new ArrayList<Coordinate>();
		int score = 0;
		while(!halt) {
			boolean add = true;
			

			Coordinate c = new Coordinate();

			r=A.run(input);
			c.setX((int)r.getOutput());
			r=A.run(input);
			c.setY((int)r.getOutput());

			r=A.run(input);
			if(c.getX() == -1 && c.getY() == 0) {
				//System.out.println("JDHJKFLGHS");
				score = (int)r.getOutput();
			}
			else {
				switch((int)r.getOutput()) {
				case 0: 
					c.setType(' ');
					break;
				case 1:
					c.setType('#');
					break;
				case 2:
					c.setType('B');
					break;
				case 3:
					c.setType('P');
					break;
				case 4:
					c.setType('O');
					break;
				default:
					break;
				}
				//System.out.println("Adding (" + c.getX() + "," + c.getY() +")");
				gameState.add(c);
			}
			if(r.getOutput() == 2) {
				numBlocks++;
			}
			if(r.getEndCode() == 99) {
				halt = true;
			}
			input = getBestInput(gameState);
			drawGame(gameState);
			System.out.println("Score: " + score);
		}
		
		
		return score;
		
	}
	
	static int getBestInput(ArrayList<Coordinate> board) {
		Coordinate ball = new Coordinate(-1,-1,' ');
		Coordinate paddle = new Coordinate(-1,-1,' ');
		for(Coordinate c: board) {
			if(c.getType() == 'P') {
				paddle = c;
			}
			if(c.getType() == 'O') {
				ball = c;
			}
		}

		if(ball.getType() == ' ' || paddle.getType() == ' '){
			return 0;
		}
		int dir = ball.getX() - paddle.getX();
		dir = (int)Math.signum(dir);
		return dir;
		
	}
	
	static void drawGame(ArrayList<Coordinate> board) {
		ArrayList<String> canvas = new ArrayList<String>();
		for(int y=0; y<21; y++) {
			canvas.add("                                            ");		
		}

		for(Coordinate c: board) {
			int x = c.getX();
			int y = c.getY();
			//System.out.println("(" + x + "," + y + ")");
			char toDraw = c.getType();
			if(c.getY() < canvas.size()) {
				String line = canvas.get(y);
				char[] array = line.toCharArray();
				array[x] = toDraw;
				String newStr = String.valueOf(array);
				canvas.set(y, newStr);
			}
		}
		for(String s: canvas) {
			System.out.println(s);
		}
		
	}
	public static void main(String[] args) {
		System.out.println("Number of blocks: " + problem13part1());
		System.out.println("Final score: " + problem13part2());
	}
}
