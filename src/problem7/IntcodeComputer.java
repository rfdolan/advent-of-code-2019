package problem7;

public class IntcodeComputer {
	public int[] state;
	public int currPos;
	public int output;
	public int phaseSetting;
	public String id;
	
	IntcodeComputer() {
		this.state = new int[0];
		this.currPos = 0;
		this.output = 0;
		this.phaseSetting = -1;
		this.id = "";
	}
	IntcodeComputer(int[] init_state, String id) {
		setState(init_state);
		this.currPos = 0;
		this.output = 0;
		this.phaseSetting = -1;
		this.id = id;
	}
	
	void setState(int[] new_state) {
		this.state = new int[new_state.length];
		for(int i=0; i<new_state.length; i++) {
			this.state[i] = new_state[i];
		}
	}
	
	public void setPhaseSetting(int phaseSetting) {
		this.phaseSetting = phaseSetting;
	}
	
	
	
	// Run computer, returning output.
	public Response run(int input) {

		/*
		 * System.out.println("Running computer " + id + " with input " + input);
		 * System.out.println("Strating position " + currPos);
		 */		Response r = new Response();
		int currInput = input;
		boolean shouldHalt = false;
		//int increaseAmount = 0;
		if(phaseSetting != -1) {
			currInput = phaseSetting;
		}
		for(int i=currPos; i<state.length && !shouldHalt; i=currPos) {
			int opCode = getOpCode(state[i]);
			//System.out.println(opCode);
			//System.out.println("Opcode " + opCode);
				switch(opCode) {
				case 1:
					state[state[i+3]] = getVal(state, state[i], 1, i) + getVal(state, state[i], 2,i);
					currPos = i+4;
					break;
				case 2:
					state[state[i+3]] = getVal(state, state[i], 1,i) * getVal(state, state[i], 2,i);
					currPos = i+ 4;
					break;
				case 3:
					state[state[i+1]] = currInput;
					//System.out.println(ints[ints[i+1]]);
					setPhaseSetting(-1);
					currInput = input;
					currPos = i+2;
					break;
				case 4:
					//System.out.println("Outputting " + getVal(ints, ints[i], 1,i));
					output = getVal(state, state[i], 1,i);
					currPos = i+ 2;
					r.setEndCode(4);
					r.setOutput(output);
				/*
				 * System.out.println("Outputting: " + output);
				 * System.out.println("Ending position: " + currPos);
				 */					return r;
				case 5:
					if(getVal(state, state[i], 1, i) != 0) {
						//System.out.println("Jumpin");
						currPos = getVal(state, state[i], 2, i);
					}
					else {
						currPos = i+3;
					}
					break;
				case 6:
					if(getVal(state, state[i], 1, i) == 0) {
						currPos = getVal(state, state[i], 2, i);
					}
					else {
						currPos = i+3;
					}
					break;
				case 7:
					if(getVal(state, state[i], 1, i) < getVal(state, state[i], 2, i) ) {
						state[state[i+3]] = 1;
					}
					else {
						state[state[i+3]] = 0;
					}
					currPos = i+4;
					break;
				case 8:
					if(getVal(state, state[i], 1, i) == getVal(state, state[i], 2, i) ) {
						state[state[i+3]] = 1;
					}
					else {
						state[state[i+3]] = 0;
					}
					currPos = i+4;
					break;
				case 99:
					shouldHalt = true;
					//System.out.println("HALT");
					r.setEndCode(99);
					r.setOutput(output);
					return r;
					
				default:
					break;
				}
		}
		return r;
		
		
	}
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

	

}