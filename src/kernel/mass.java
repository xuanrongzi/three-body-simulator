package kernel;

import inter_face.Type;
import inter_face.masspoint;

public class mass implements masspoint {
	private double mass=0;
	private double radious=0;
	private String name;
	private Type star_type;
	private double[] xyz=new double[3];
	private double[] force_vector=new double[3];
	private double[] velocity_vector=new double[3];
	
	
	public void masspoint(double mass, double rad, double[] position, String name, Type type){
		this.mass=mass;
		radious=rad;		
		xyz=position;
		this.name=name;
		star_type=type;
	}
	
	@Override
	public double getMass() {
		return mass;
	}

	@Override
	public double[] getPosition() {
		return xyz;
	}

	@Override
	public double[] getForce() {
		return force_vector;
	}

	@Override
	public double getX() {
		return xyz[0];
	}

	@Override
	public double getY() {
		return xyz[1];
	}

	@Override
	public double getZ() {
		return xyz[2];
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Type getType() {
		return star_type;
	}

	@Override
	public double getDistance(masspoint m) {
		double[] target=m.getPosition();
		double[] delta=new double[3];
		for(int i=0; i<3; i++){
		delta[i]=xyz[i]-target[i];
		}
		return Math.sqrt(delta[0]*delta[0]+delta[1]*delta[1]+delta[2]*delta[2]);
	}

	@Override
	public void setPosition(double[] posi) {
		xyz=posi;		
	}

	@Override
	public void setName(String name) {
		this.name=name;
	}

	@Override
	public void setType(Type type) {
		star_type=type;
	}

	@Override
	public void setMass(double mass) {
		this.mass=mass;
	}

	@Override
	public double getRadious() {
		return radious;
	}

	@Override
	public double[] getVelocity(masspoint m) {
		return velocity_vector;
	}

	@Override
	public void setRadious(double rad) {
		radious=rad;
	}


}

