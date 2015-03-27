package inter_face;

public interface physics {
	public double[] gravity(masspoint receiver, masspoint exerter);
	public double[] escapeSpeed(masspoint m);
	public double[] relativegravity(masspoint receiver, masspoint exerter);
	public double[] acceration(masspoint m, double[] force);
	public double[] est_trajectory(masspoint est, masspoint[] all);
	public double[] delta_v(masspoint m, double[] delta);
	public boolean is_collide(masspoint m1, masspoint m2);
	public masspoint collision(masspoint m1, masspoint m2);

}
