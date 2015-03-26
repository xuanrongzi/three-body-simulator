package inter_face;

public interface masspoint {
	
  
  public double getMass();
  public double[] getPosition();
  public double getX();
  public double getY();
  public double getZ();
  public double[] getForce();
  public String getName();
  public String getType();
  public double getDistance(masspoint m);
  
  
  
  public void setPosition();
  public void setName();
  public void setType();
  public void setMass();
  
}
