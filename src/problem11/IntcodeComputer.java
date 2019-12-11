package problem11;

public class IntcodeComputer {
	public long[] state;
	public int currPos;
	public long output;
	public int phaseSetting;
	public long relativeBase;
	public String id;
	
	IntcodeComputer() {
		this.state = new long[0];
		this.currPos = 0;
		this.output = 0;
		this.phaseSetting = -1;
		this.id = "";
		this.relativeBase = 0;
	}
	IntcodeComputer(long[] init_state, String id) {
		setState(init_state);
		this.currPos = 0;
		this.output = 0;
		this.phaseSetting = -1;
		this.id = id;
		this.relativeBase = 0;
	}
	
	void setRelativeBase(long new_base) {
		this.relativeBase = getRelativeBase() + new_base;
	}
	long getRelativeBase() {
		return this.relativeBase;
	}
	
	void setState(long[] new_state) {
		this.state = new long[new_state.length];
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
			int opCode = getOpCode((int)state[i]);
			//System.out.println(opCode);
			//System.out.println("Opcode " + opCode);
				switch(opCode) {
				case 1:
					while(state[i+3] > state.length-1) {
						doubleSize();
					}
					state[(int)getTarget(state[i], i, state[i+3], 3)] = getVal(state[i], 1, i) + getVal(state[i], 2,i);
					currPos = i+4;
					break;
				case 2:
					while((int)getTarget(state[i],i,state[i+3],3)> state.length-1) {
						doubleSize();
					}
					state[(int)getTarget(state[i], i, state[i+3], 3)] = getVal(state[i], 1, i) * getVal(state[i], 2,i);
					currPos = i+ 4;
					break;
				case 3:
					while(state[i+1] > state.length-1) {
						doubleSize();
					}
					placeInput(currInput, state[i], i);
					//System.out.println(ints[ints[i+1]]);
					setPhaseSetting(-1);
					currInput = input;
					currPos = i+2;
					break;
				case 4:
					//System.out.println("Outputting " + getVal(state[i], 1,i));
					output = getVal(state[i], 1,i);
					currPos = i+ 2;
					r.setEndCode(4);
					r.setOutput(output);
				/*
				 * System.out.println("Outputting: " + output);
				 * System.out.println("Ending position: " + currPos);
				 */					
					return r;
				case 5:
					if(getVal( state[i], 1, i) != 0) {
						//System.out.println("Jumpin");
						currPos = (int)getVal( state[i], 2, i);
					}
					else {
						currPos = i+3;
					}
					break;
				case 6:
					if(getVal(state[i], 1, i) == 0) {
						currPos = (int)getVal(state[i], 2, i);
					}
					else {
						currPos = i+3;
					}
					break;
				case 7:
					if(getVal(state[i], 1, i) < getVal(state[i], 2, i) ) {
						state[(int)getTarget(state[i], i, state[i+3], 3)] = 1;
					}
					else {
						state[(int)getTarget(state[i], i, state[i+3], 3)] = 0;
					}
					currPos = i+4;
					break;
				case 8:
					if(getVal(state[i], 1, i) == getVal(state[i], 2, i) ) {
						state[(int)getTarget(state[i], i, state[i+3], 3)] = 1;
					}
					else {
						state[(int)getTarget(state[i], i, state[i+3], 3)] = 0;
					}
					currPos = i+4;
					break;
				case 9:
					setRelativeBase(getVal(state[i], 1,i)); 
					while(getRelativeBase() > this.state.length) {
						doubleSize();
					}
					currPos = i+2;
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
	private long getVal( long inst, int paramNum, int currIndex) {

		long mode = calcMode(inst, paramNum);
		// Return val at position
		if(mode == 0) {
			while(currIndex + paramNum > state.length-1 || state[currIndex + paramNum ] > state.length-1) {
				doubleSize();
			}
			return state[(int)state[currIndex + paramNum ]];
		}
		// Return val
		else if (mode == 1){
			while(currIndex + paramNum > state.length-1) {
				doubleSize();
			}
			return state[currIndex + paramNum];
		}
		else if(mode ==2) {
			while(currIndex + paramNum > state.length-1 || getRelativeBase() + state[currIndex + paramNum] > state.length-1  ) {
				doubleSize();
			}
			long val1 = getRelativeBase() + state[currIndex + paramNum];
			return state[(int)val1];
		}
		return 0;
		
	}
	
	static long calcMode(long inst, int paramNum) {
		long divisor = (long) Math.pow(10, 1+paramNum);
		long mode = inst - (inst%divisor);
		mode = (mode % (divisor * 10)) / divisor;
		return mode;
		
	}
	
	private void placeInput(long input, long inst, int currIndex) {
		long content = state[currIndex + 1];
		long mode = calcMode(inst, 1);
		if(mode == 0) {
			state[(int)content] = input;
		}
		else if(mode == 2) {
			state[(int)(getRelativeBase() + content)] = input;
		}

		
	}
	
	private long getTarget(long inst, int currIndex, long content, int paramNum) {
		long mode = calcMode(inst, paramNum);
		if(mode == 0) {
			return content;
		}
		else if(mode == 2) {
			return (getRelativeBase() + content);
		}
		return -1;
	}
	
	static int getOpCode(int code) {
		int opCode = code%100;
		return opCode;

	}

	void doubleSize() {
		long[] new_array = new long[state.length * 2];
		for(int i=0; i<state.length; i++) {
			new_array[i] = state[i];
		}
		for(int i=state.length; i<new_array.length; i++) {
			new_array[i] = 0;
		}
		this.state = new_array;
	}
	

}