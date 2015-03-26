package kernel;

import inter_face.masspoint;

public class mass implements masspoint {
	double mass=0;
	double[] xyz=new double[3];
	double[] force_vector=new double[3];
	@Override
	public double getMass() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] getPosition() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getForce() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getZ() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double getDistance(masspoint m) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setPosition() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setName() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setType() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setMass() {
		// TODO Auto-generated method stub
		
	}

}

