package pawtropolis.model.entity;

public abstract class Entity {
    protected String name;
    protected double lifePoints;
    protected int positionX;
    protected int positionY;

    public Entity(String name, double lifePoints, int positionX, int positionY) {
		this.name = name;
		this.lifePoints = lifePoints;
		this.positionX = positionX;
		this.positionY = positionY;
	}
    
	public void setName(String name) {
        this.name = name;
    }

    public void setPositionX(int positionX) {
    	this.positionX = positionX;
    }

    public void setPositionY(int positionY) {
    	this.positionY = positionY;
    }

    public String getName() {
        return this.name;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

}
