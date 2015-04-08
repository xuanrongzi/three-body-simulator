package kernel;

import inter_face.Type;
import inter_face.Universe;
import inter_face.masspoint;
import inter_face.physics;
import exception.LimitReachedException;
import exception.OutofRangeException;

public class univer_impl implements Universe{
	private masspoint[] stars;
	private int[][] stars_db;
	private int[] currentSize;
	private double[][] gravi;
	private int num[];
	private int max[];
	private int[] univer;
	private int[] camera;
	private int speedoflight;
	private double collision_coif;
	
	
	public void Universe(){
		speedoflight=300000000;
		collision_coif=0.92;
		univer=new int[3];
		camera=new int[3];
		currentSize=new int[4];
		gravi=new double[12][4];
		
		for(int i=0; i<4; i++){
			currentSize[i]=-1;
		}
		//0 is total size, 1 is sun size, 2 is planet size, 3 is space craft size
		stars=new masspoint[12];
		
		//initially the program support 12 objects
		stars_db=new int[16][4];
		//the first column is the id of the star
		//the second is the type of the star, 0=sun, 1=planet, 2=spaceship
		//the third column is used to indicate it's the N'th star in the same category
		//the fourth column is a mark of the star is still exist, 0=empty, 1= exist
		max=new int[4];
		max[0]=3;
		max[1]=24;
		max[2]=64;
		max[3]=64;
		//max is not eligible for editing during operation
		//it might be increased if optimization and memory usage is acceptable
		//but sizes of stars is expandable, to minimize memory usage at most case
	}
	
	@Override
	public void addMass(masspoint m) throws LimitReachedException {
		if (currentSize[0]>=max[3]){
			throw new LimitReachedException("Total Number of Object reaches limits");
		}
		currentSize[0]++;
		stars[currentSize[0]]=m;
		
		if ((m.getType()==Type.solid_planet||m.getType()==Type.gas_planet)&&currentSize[2]<max[1]){
			currentSize[2]++;
			stars_db[currentSize[0]][0]=m.getID();
			stars_db[currentSize[0]][1]=1;
			stars_db[currentSize[0]][2]=currentSize[2];
			stars_db[currentSize[0]][3]=1;
		}else if ((m.getType()==Type.spacecraft)&&currentSize[3]<max[2]){
			currentSize[3]++;
			stars_db[currentSize[0]][0]=m.getID();
			stars_db[currentSize[0]][1]=2;
			stars_db[currentSize[0]][2]=currentSize[3];
			stars_db[currentSize[0]][3]=1;
		}else if (currentSize[1]<max[0]){
			currentSize[1]++;
			stars_db[currentSize[0]][0]=m.getID();
			stars_db[currentSize[0]][1]=0;
			stars_db[currentSize[0]][2]=currentSize[1];
			stars_db[currentSize[0]][3]=1;
		}
		
	}
	
	@Override
	public void removeMass(int id) throws OutofRangeException {
		if (id<0||id>currentSize[0]){
			throw new OutofRangeException("Remove id out of Range, check it again");
		}
		int t=stars[id].gettype();
		if (t==0){
			currentSize[1]--;
		}else if (t==1){
			currentSize[2]--;
		}else{
			currentSize[3]--;
		}

		stars[id]=null;
		currentSize[0]--;
		//to do the best, it should  delete everything in records and release memory
		//but temporarily I prefer to mark it as deleted. that could be fine
		for (int i=id; i<currentSize[0];i++){
			stars[i]=stars[i+1];
			stars_db[i]=stars_db[i+1];
		}
		stars[currentSize[0]+1]=null;
		stars_db[currentSize[0]+1]=null;
	}

	@Override
	public void caculate_gravity() {
		gravi=new double[64][4];
		for(int i=0; i<currentSize[0]; i++){
			
			for(int j=0; j<currentSize[0];j++){
				if(i!=j){
					gravi[i]=phy_impl.gravity(stars[i], stars[j]);
				}
				
			}
			gravi[i][3]=Math.sqrt(gravi[i][0]*gravi[i][0]+gravi[i][1]*gravi[i][1]+gravi[i][2]*gravi[i][2]);
		}
		
	}

	@Override
	public void getNextPosition() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCam(Integer[] cam) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer[] getCam() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void MoveCam() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setC(Integer v) {
		speedoflight=v;
	}

	@Override
	public Integer getC() {
		return speedoflight;
	}

	@Override
	public void collision(masspoint m1, masspoint m2) {
		// ignore the tide force for now
		double dis=m1.getDistance(m2);
		double r=phy_impl.mergeRadius(m1, m2);
		//only kept the bigger masspoint
		if(m1.getMass()>=m2.getMass()){
			
			double k=dis*m2.getMass()/(m1.getMass()+m2.getMass());
			double[] delta=getdisplacememt(m1, m2);
			double[] np=m1.getPosition();
			for(int i=0; i<3; i++){
				np[i]=delta[i]*k+np[i];
			}
			m1.setPosition(np);
			m1.setMass(m1.getMass()+m2.getMass());
			m1.setRadious(r);
		}else{
			
		}
		
		
		
		
	}

	@Override
	public int getCurrentSize() {
		return currentSize[0];
	}

	@Override
	public void MoveCamMan(int[] adj) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setCollision_coif(double coi) {
		if (coi>=0&&coi<=1){
		collision_coif=coi;
		}else if(coi<=100){
			collision_coif=coi/100;
		}
	}

	@Override
	public void update() {
		// according to the list of mass points, update all the other data
		
	}

	@Override
	public boolean if_colide(masspoint m1, masspoint m2) {
		double dis=m1.getDistance(m2);
		double r=m1.getRadious()+m2.getRadious();
		if (dis<=r*1.1){
			return true;
		}
		return false;
	}

	@Override
	public double[] getdisplacememt(masspoint m1, masspoint m2) {
		// TODO Auto-generated method stub
		return null;
	}



}