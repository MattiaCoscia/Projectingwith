package pawtropolis.model.entity;

public class Player extends Entity{

    private static final Player player = new Player("",0,0,0);
    
    private Player(String name, double lifePoints, int positionX, int positionY){
    	super(name,lifePoints,positionX,positionY);
    }
    
	public static Player getInstance(String name) {
		player.setName(name);
		return player;
	}
}
