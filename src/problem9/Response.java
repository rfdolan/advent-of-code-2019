package problem9;

public class Response {
	public int endCode;
	public long output;
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
	
	void setOutput(long output) {
		this.output = output;
	}
	
	
	long getOutput() {
		return output;
	}
	int getEndCode() {
		return endCode;
	}
	

}
