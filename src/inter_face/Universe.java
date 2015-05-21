package inter_face;

import exception.LimitReachedException;
import exception.OutofRangeException;
import exception.null_object;

public interface Universe {
	void addMass(masspoint m) throws LimitReachedException, null_object;
	void removeMass(int id) throws OutofRangeException;
	void caculate_gravity();
	void getNextPosition();
	void setCam(int[] cam);
	int[] getCam();
	void MoveCam();
	void MoveCamMan(int[] adj);
	void setC(int v);
	int getC();
	void collision(masspoint m1, masspoint m2) throws null_object;
	int getCurrentSize();
	void setCollision_coif(double coi);
	void update();
	boolean if_colide(masspoint m1, masspoint m2) throws null_object;
	double[] getdisplacememt(masspoint m1, masspoint m2) throws null_object;
}
