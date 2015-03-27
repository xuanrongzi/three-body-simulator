package library;

import inter_face.masspoint;
import inter_face.physics;

public class phy_impl implements physics {

	@Override
	public double[] gravity(masspoint receiver, masspoint exerter) {
		// TODO Auto-generated method stub
		return null;
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

}
