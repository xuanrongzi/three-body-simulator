package kernel;

import inter_face.masspoint;
import inter_face.physics;

public class phy_impl implements physics {
	static double g=6.67E-11;

	public static double[] gravity(masspoint receiver, masspoint exerter) {
		double[] gravi=new double[4];
		
		double[] ap=receiver.getPosition();
		double[] bp=exerter.getPosition();
		double am=receiver.getMass();
		double bm=exerter.getMass();
		double[] delta=new double[3];
		for (int i=0; i<3; i++){
			delta[i]=bp[i]-ap[i];
		}
		for (int j=0; j<3; j++){
			gravi[j]=g*am*bm/(delta[0]*delta[0]);
		}
		gravi[3]=Math.sqrt(gravi[0]*gravi[0]+gravi[1]*gravi[1]+gravi[2]*gravi[2]);
		return gravi;
	}

	@Override
	public double[] escapeSpeed(masspoint m) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] relativegravity(masspoint receiver, masspoint exerter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] acceration(masspoint m, double[] force) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] est_trajectory(masspoint est, masspoint[] all) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] delta_v(masspoint m, double[] delta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean is_collide(masspoint m1, masspoint m2) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public masspoint collision(masspoint m1, masspoint m2) {
		// TODO Auto-generated method stub
		return null;
	}

	public static double mergeRadius(masspoint m1, masspoint m2) {
		// TODO Auto-generated method stub212
		return 0;
	}

}
