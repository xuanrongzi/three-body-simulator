package inter_face;

import exception.LimitReachedException;

public interface Universe {
	void addMass(masspoint m) throws LimitReachedException;
	void caculate_gravity();
	void getNextPosition();
	void setCam(Integer[] cam);
	Integer[] getCam();
	void MoveCam();
	void setC(Integer v);
	Integer getC();
	void collision(masspoint m1, masspoint m2);
	int getCurrentSize();
	

}
