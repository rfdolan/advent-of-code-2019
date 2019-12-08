package problem8;

import java.util.ArrayList;

public class Layer {
	public ArrayList<Integer> content;
	public int width;
	public int height;
	public int numZeroes;

	Layer(int width, int height) {
		this.width = width;
		this.height = height;
		content = new ArrayList<Integer>();
	}
	Layer(int width, int height, int numZeroes) {
		this.width = width;
		this.height = height;
		content = new ArrayList<Integer>();
		this.numZeroes = numZeroes;
	}
	
	void addNum(int num) {
		content.add(num);
	}
	
	void calcNumZeroes() {
		int num = 0;
		for(Integer i: content) {
			if(i==0)
				num++;
		}
		numZeroes = num;
		
	}
	
	int getNumZeroes() {
		return numZeroes;
	}
	
	int getTwosTimesOnes() {
		int numTwos = 0;
		int numOnes = 0;
		for(Integer i: content) {
			if(i==1) {
				numOnes++;
			}
			if(i==2) {
				numTwos++;
			}
		}
		return numTwos*numOnes;
	}
	
	int getPixelAt(int pos) {
		return content.get(pos);
	}
	
	void setPixelAt(int pos, int val) {
		content.set(pos, val);
	}
	
	void draw() {
		for(int i=0; i<height; i++) {
			System.out.println(content.subList(i*width, (i*width)+height));
		}
	}

}
