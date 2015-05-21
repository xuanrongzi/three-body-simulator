package kernel;



import inter_face.Type;
import inter_face.Universe;
import inter_face.masspoint;
import inter_face.physics;
import exception.LimitReachedException;
import exception.OutofRangeException;
import exception.null_object;

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
	private tuple<String, Integer> flowchart;
	
	
	
	public void Universe(){
		speedoflight=300000000;
		collision_coif=0.92;
		univer=new int[3];
		camera=new int[4];
		currentSize=new int[4];
		gravi=new double[12][4];
		camera[0]=200;
		camera[1]=0;
		camera[2]=0;
		camera[3]=0;
		
		//for camera, [0]means how big the scence should be,the rest is the center of the view
		
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
		
		//here is a flow chart check list used to make sure everything is ok
		//The Procedure of each moment is : in each step, 0 means undone, 1 means
		
		/*		1: Calculate gravity 
		 * 		2: Calculate displacement according to gravity
		 * 		3: get next position
		 * 		4: if collision  0=undone; 1=without colon; 2=collide
		 * 		5: if collision==2; this mean everything related has processed
		 * 		6: if manually modified cam;
		 * 		7: if 6 is not(0), the automatically set it
		 * 		8: inspecting all before, updating, find if some object is out of bound, and find escaping speed, 
		 */
		init_flowchart();
	}	
	
	private void init_flowchart(){
		flowchart.add("Gravity", 0);
		flowchart.add("Displacement", 0);
		flowchart.add("Positon", 0);
	}
	
	@Override
	public void addMass(masspoint m) throws LimitReachedException, null_object {
		
		valid(m);
		
		try{
		if (currentSize[0]>=max[3]){
			throw new LimitReachedException("Total Number of Object reaches limits");
		}
		}catch(LimitReachedException e){
			System.out.println("universe_addMass.the number of current mass object exceed maxium");
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
		//pior to this step, we should get the volecity vectors for this moment, and everything
		//is down, all error elimited and call this method to move the objects and end the moment
		
	}

	@Override
	public void setCam(int[] cam) {
		if(cam!=null&cam.length==4){
			camera=cam;
		}
		
	}

	@Override
	public int[] getCam() {
		return camera;
	}

	@Override
	public void MoveCam() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setC(int v) {
		speedoflight=v;
	}

	@Override
	public int getC() {
		return speedoflight;
	}

	@Override
	public void collision(masspoint m1, masspoint m2) throws null_object {
		valid(m1);
		valid(m2);
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
			
			int id=m2.getID();
			int tp=stars_db[id][1];
			
			currentSize[0]--;
			if(tp==0){
				currentSize[1]--;
			}else if(tp==1){
				currentSize[2]--;
			}else{currentSize[3]--;
			
			}
			for(int i=id; i<=currentSize[0]; i++){
				stars[i]=stars[i+1];
				stars[i].setID(i);
				//stars_db[i]=stars_db[i+1];
				//stars_db[i][0]=i;
				//if(stars_db[i][1]==tp){
				//	stars_db[i][2]--;
				//}
			}
			stars_db[currentSize[0]]=null;
		}else{
			double k=dis*m1.getMass()/(m1.getMass()+m2.getMass());
			double[] delta=getdisplacememt(m1, m2);
			double[] np=m2.getPosition();
			for(int i=0; i<3; i++){
				np[i]=delta[i]*k+np[i];
			}
			m1.setPosition(np);
			m1.setMass(m1.getMass()+m2.getMass());
			m1.setRadious(r);
			
			int id=m1.getID();
			int tp=stars_db[id][1];
			
			currentSize[0]--;
			if(tp==0){
				currentSize[1]--;
			}else if(tp==1){
				currentSize[2]--;
			}else{currentSize[3]--;
			
			}
			for(int i=id; i<=currentSize[0]; i++){

				
				stars[i]=stars[i+1];
				stars[i].setID(i);
				//stars_db[i]=stars_db[i+1];
				//stars_db[i][0]=i;
				//if(stars_db[i][1]==tp){
				//	stars_db[i][2]--;
				//}
			}
			stars_db[currentSize[0]]=null;
		}
		
		
		
		
	}

	@Override
	public int getCurrentSize() {
		return currentSize[0];
	}

	@Override
	public void MoveCamMan(int[] adj) {
		//input should be delta value
		try{
			camera[0]=camera[0]+adj[0];
			camera[1]=camera[1]+adj[1];
			camera[2]=camera[2]+adj[2];
			camera[3]=camera[3]+adj[3];
		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Universe.MoveCamMan Input list is currputed");
		}
		
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
	public boolean if_colide(masspoint m1, masspoint m2) throws null_object {
		valid(m1);
		valid(m2);
		
		double dis=m1.getDistance(m2);
		double r=m1.getRadious()+m2.getRadious();
		if (dis<=r*1.1){
			return true;
		}
		return false;
	}

	@Override
	public double[] getdisplacememt(masspoint m1, masspoint m2) throws null_object {
		valid(m1);
		valid(m2);
		
		double[] x1=m1.getPosition();
		double[] x2=m2.getPosition();
		double[] x3=new double[3];
		x3[0]=Math.abs(x1[0]-x2[0]);
		x3[0]=Math.abs(x1[1]-x2[1]);
		x3[0]=Math.abs(x1[2]-x2[2]);
		return x3;
	}
	
	public void valid(masspoint m) throws null_object{
		if (m==null){
			throw new null_object("The object called is empty");
		}
		if(!(m instanceof masspoint)){
				
			throw new null_object("The object called is not ");

		}
			
		
		
	}



}
