package inter_face;

public interface masspoint {
	
  public int getID();
  public double getMass();
  public double[] getPosition();
  public double getX();
  public double getY();
  public double getZ();
  public double[] getForce();
  public String getName();
  public Type getType();
  public double getDistance(masspoint m);
  public double getRadious();
  public double[] getVelocity(masspoint m);
  
  
  public void setRadious(double  rad);
  public void setPosition(double[] posi);
  public void setName(String name);
  public void setType(Type type);
  public void setMass(double mass);
  
}
