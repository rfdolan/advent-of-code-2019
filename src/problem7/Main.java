package problem7;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	static int getVal(int[] array, int inst, int paramNum, int currIndex) {

		int divisor = (int) Math.pow(10, 1+paramNum);
		int mode = inst - (inst%divisor);
		mode = mode % (divisor * 10);
		// Return val at position
		if(mode == 0) {
			return array[array[currIndex + paramNum ]];
		}
		// Return val
		else {
			return array[currIndex + paramNum];
		}
		
	}
	
	static int getOpCode(int code) {
		int opCode = code%100;
		return opCode;

	}

	static int problem7part1() {
		String data = "";

		try {
	      File myObj = new File("problem7.txt");
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

		for(int i=0; i<ints.length; i++) {
			ints[i] = originalState[i];
		}
		
		int result = permutation("01234", ints, 0);

		return result;
		
		
	}
	
	static int permutation(String str, int[] ints, int max) {
		return permutation("", str, ints, max);
	}

	static int permutation(String prefix, String str, int[] sequence, int max) {
	    int n = str.length();
	    if (n == 0) {
	    	int curr = process(sequence, prefix);
	    	if(curr > max) {
	    		//System.out.println(curr + " is bigger");
	    		return curr;
	    	}
	    }
	    else {
	        for (int i = 0; i < n; i++)
	            max = permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), sequence, max);
	    }
	    return max;
	}
	
	static int process(int[] sequence, String settings) {
		//System.out.println("Processing " + settings);
		int result = 0;
		for(int i=0; i<settings.length(); i++) {
			
			//System.out.println("Running computer " + i);
			int code = Integer.parseInt(settings.substring(i,i+1));
			result = runComputer(sequence, code, result);
			
		}
		return result;
			
	}
	
	static int runComputer(int[] sequence, int phaseSetting, int input) {
		boolean shouldHalt = false;
		int output = 0;
		int newPos = 0;
		//int increaseAmount = 0;
		int currInput = phaseSetting;
		for(int i=0; i<sequence.length && !shouldHalt; i=newPos) {
			int opCode = getOpCode(sequence[i]);
			//System.out.println("Opcode " + opCode);
				switch(opCode) {
				case 1:
					sequence[sequence[i+3]] = getVal(sequence, sequence[i], 1, i) + getVal(sequence, sequence[i], 2,i);
					newPos = i+4;
					break;
				case 2:
					sequence[sequence[i+3]] = getVal(sequence, sequence[i], 1,i) * getVal(sequence, sequence[i], 2,i);
					newPos = i+ 4;
					break;
				case 3:
					sequence[sequence[i+1]] = currInput;
					currInput = input;
					//System.out.println(ints[ints[i+1]]);
					newPos = i+2;
					break;
				case 4:
					//System.out.println("Outputting " + getVal(ints, ints[i], 1,i));
					output += getVal(sequence, sequence[i], 1,i);
					newPos = i+ 2;
					break;
				case 5:
					if(getVal(sequence, sequence[i], 1, i) != 0) {
						//System.out.println("Jumpin");
						newPos = getVal(sequence, sequence[i], 2, i);
					}
					else {
						newPos = i+3;
					}
					break;
				case 6:
					if(getVal(sequence, sequence[i], 1, i) == 0) {
						newPos = getVal(sequence, sequence[i], 2, i);
					}
					else {
						newPos = i+3;
					}
					break;
				case 7:
					if(getVal(sequence, sequence[i], 1, i) < getVal(sequence, sequence[i], 2, i) ) {
						sequence[sequence[i+3]] = 1;
					}
					else {
						sequence[sequence[i+3]] = 0;
					}
					newPos = i+4;
					break;
				case 8:
					if(getVal(sequence, sequence[i], 1, i) == getVal(sequence, sequence[i], 2, i) ) {
						sequence[sequence[i+3]] = 1;
					}
					else {
						sequence[sequence[i+3]] = 0;
					}
					newPos = i+4;
					break;
				case 99:
					shouldHalt = true;
					//System.out.println("HALT");
					break;
				default:
					break;
				}
		}
		return output;
		
	}

	static int problem7part2() {
		String data = "";

		try {
	      File myObj = new File("problem7.txt");
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

		for(int i=0; i<ints.length; i++) {
			ints[i] = originalState[i];
		}
		
		//int result = permutation("56789", ints, 0);
		//int result = process2(ints, "98765");
		int result = permutation2("56789", ints, 0 );

		return result;
		
		
	}
	static int process2(int[] sequence, String settings) {
		//System.out.println("Processing " + settings);
		IntcodeComputer A = new IntcodeComputer(sequence, "A");
		IntcodeComputer B = new IntcodeComputer(sequence, "B");
		IntcodeComputer C = new IntcodeComputer(sequence, "C");
		IntcodeComputer D = new IntcodeComputer(sequence, "D");
		IntcodeComputer E = new IntcodeComputer(sequence, "E");
		A.setPhaseSetting(Integer.parseInt(settings.substring(0,1)));
		B.setPhaseSetting(Integer.parseInt(settings.substring(1,2)));
		C.setPhaseSetting(Integer.parseInt(settings.substring(2,3)));
		D.setPhaseSetting(Integer.parseInt(settings.substring(3,4)));
		E.setPhaseSetting(Integer.parseInt(settings.substring(4,5)));
		boolean halt = false;
		Response r = new Response();
		r.setOutput(0);
		while(!halt) {
			r = A.run(r.getOutput());
			r = B.run(r.getOutput());
			r = C.run(r.getOutput());
			r = D.run(r.getOutput());
			r = E.run(r.getOutput());
			if(r.getEndCode() == 99) {
				halt = true;
			}
			
		}
		return r.getOutput();
	}
	static int permutation2(String str, int[] ints, int max) {
		return permutation2("", str, ints, max);
	}

	static int permutation2(String prefix, String str, int[] sequence, int max) {
	    int n = str.length();
	    if (n == 0) {
	    	int curr = process2(sequence, prefix);
	    	if(curr > max) {
	    		//System.out.println(curr + " is bigger");
	    		return curr;
	    	}
	    }
	    else {
	        for (int i = 0; i < n; i++)
	            max = permutation2(prefix + str.charAt(i), str.substring(0, i) + str.substring(i+1, n), sequence, max);
	    }
	    return max;
	}

	public static void main(String[] args) {
		System.out.println(problem7part1());
		System.out.println(problem7part2());

	}

}
