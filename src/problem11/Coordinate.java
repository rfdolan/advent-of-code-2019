package problem11;

public class Coordinate {
	int x;
	int y;
	
	Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	int getX() {
		return x;
	}
	int getY() {
		return y;
	}
	public boolean equals(Coordinate other) {
		if(other.getX() == getX() && other.getY() == getY()) {
			return true;
		}
		return false;
	}

}
