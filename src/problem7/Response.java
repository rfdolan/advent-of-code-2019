package problem7;

public class Response {
	public int[] state;
	public int endCode;
	public int output;
	public int pos;
	
	Response() {
		
	}
	Response( int endCode, int output) {
		this.endCode = endCode;
		this.output = output;
	}
	
	
	void setEndCode(int endCode) {
		this.endCode = endCode;
	}
	
	void setOutput(int output) {
		this.output = output;
	}
	
	
	int getOutput() {
		return output;
	}
	int getEndCode() {
		return endCode;
	}
	

}
