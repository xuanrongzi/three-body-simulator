package inter_face;

import exception.LimitReachedException;
import exception.OutofRangeException;

public interface Universe {
	void addMass(masspoint m) throws LimitReachedException;
	void removeMass(int id) throws OutofRangeException;
	void caculate_gravity();
	void getNextPosition();
	void setCam(Integer[] cam);
	Integer[] getCam();
	void MoveCam();
	void MoveCamMan(int[] adj);
	void setC(Integer v);
	Integer getC();
	void collision(masspoint m1, masspoint m2);
	int getCurrentSize();
	void setCollision_coif(double coi);
	void update();
	boolean if_colide(masspoint m1, masspoint m2);
	double[] getdisplacememt(masspoint m1, masspoint m2);
}
