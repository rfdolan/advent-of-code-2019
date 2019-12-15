package problem12;

public class Coordinate {

	int x;
	int y;
	int z;
	
	public Coordinate(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getX() {return x;}
	public int getY() { return y;}
	public int getZ() {return z;}
	public boolean equals(Coordinate other) {
		if(other.getX() == getX() && 
				other.getY() == getY() &&
				other.getZ() == getZ()) {
			return true;
		}
		return false;
			
	}
}
