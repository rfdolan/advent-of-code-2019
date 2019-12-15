package problem12;

public class Moon {
	Coordinate position;
	Coordinate velocity;
	
	public Moon(Coordinate position) {
		this.position = position;
		this.velocity = new Coordinate(0,0,0);
	}
	
	public Coordinate getPosition() {
		return position;
	}
	public Coordinate getVelocity() {
		return velocity;
	}
	
	public void applyGravity(Coordinate other) {
		int newX = other.getX() - this.position.getX();
		newX = (int)Math.signum(newX) + getVelocity().getX();
		int newY = other.getY() - this.position.getY();
		newY = (int)Math.signum(newY) + getVelocity().getY();
		int newZ = other.getZ() - this.position.getZ();
		newZ = (int)Math.signum(newZ) + getVelocity().getZ();
		this.velocity = new Coordinate(newX, newY, newZ);
	}
	public void applyVelocity() {
		int newX = position.getX() + velocity.getX();
		int newY = position.getY() + velocity.getY();
		int newZ = position.getZ() + velocity.getZ();
		position = new Coordinate( newX,  newY,  newZ);
		//this.velocity = new Coordinate(0,0,0);
	}
	public int getPotentialEnergy() {
		int potentialEnergy = Math.abs(position.getX()) + Math.abs(position.getY()) + Math.abs(position.getZ());
		System.out.println("Potential energy: " + potentialEnergy);
		return potentialEnergy;
	}
	public int getKineticEnergy() {
		int kineticEnergy =Math.abs(velocity.getX()) + Math.abs(velocity.getY()) + Math.abs(velocity.getZ());
		System.out.println("Kinetic energy: " + kineticEnergy);
		return kineticEnergy;
	}
	public int getTotalEnergy() {
		int totalEnergy = getPotentialEnergy() * getKineticEnergy();
		System.out.println("Total energy is " + totalEnergy);
		return totalEnergy;
	}
	public boolean equals(Moon other) {
		if(other.getPosition().equals(getPosition()) &&
				other.getVelocity().equals(getVelocity())) {
			return true;
		}
		return false;
	}

}
