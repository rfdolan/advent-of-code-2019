package problem10;

public class CoordinateChange {
	int xChange;
	int yChange;
	
	CoordinateChange(int xChange, int yChange) {
		this.xChange = xChange;
		this.yChange = yChange;
	}
	
	public int getXChange() {
		return xChange;
	}
	public int getYChange() {
		return yChange;
	}
	public float getSlope() {
		if(xChange == 0) {
			return (float) 0.00001;
		}
		return ((float)-yChange / (float)xChange);
	}
	public boolean equals(CoordinateChange other) {
		/*
		int xMod = 1;
		int yMod = 1;
		if(other.getXChange() == getXChange() && 
				other.getYChange() == getYChange()) {
			return true;
		}
		if(Math.signum(other.getXChange()) != Math.signum(getXChange()) || 
				Math.signum(other.getYChange()) != Math.signum(getYChange())){
			return false;
		}
		if(getXChange() != 0) {
			xMod = other.getXChange() % getXChange();
			if(Math.abs(other.getXChange() / getXChange()) == 1) {
				xMod = 1;
			}
		}
		if(getYChange() != 0) {
			yMod = other.getYChange() % getYChange();
			if(Math.abs(other.getYChange() / getYChange()) == 1) {
				yMod = 1;
			}
		}
		if(xMod ==0 && yMod ==0) {
			return true;
		}
		return false;
		*/
		if(other.getXChange() == getXChange() && other.getYChange() == getYChange()) {
			return true;
		}
		return false;
	}
	

}
