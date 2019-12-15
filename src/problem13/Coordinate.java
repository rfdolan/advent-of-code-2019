package problem13;

public class Coordinate {
	int x;
	int y;
	char type;
	
	Coordinate() {
		
	}
	Coordinate(int x, int y ,char type) {
		this.x = x; 
		this.y = y;
		this.type = type;
	}
	
	int getX() {
		return x;
	}
	void setX(int x) {
		this.x = x;
	}
	int getY() {
		return y;
	}
	void setY(int y) {
		this.y = y;
	}
	char getType() {
		return type;
	}
	void setType(char type) {
		this.type = type;
	}
	public boolean equals(Coordinate other) {
		if(other.getX() == getX() && other.getY() == getY()) {
			return true;
		}
		return false;
	}

}
