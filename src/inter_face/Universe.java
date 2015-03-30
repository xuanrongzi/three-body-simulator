package inter_face;

public interface Universe {
	void addMass(masspoint m);
	void caculate_gravity();
	void getNextPosition();
	void setCam(Integer[] cam);
	Integer[] getCam();
	void MoveCam();
	void setC(Integer v);
	Integer getC();
	

}
