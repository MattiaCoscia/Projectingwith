package pawtropolis.model.entity;

import pawtropolis.model.map.Room;

public class Player {

    private static final Player player = new Player();
    private String name;
    private double lifePoints;
    private int positionX;
    private int positionY;
    private Player(){}

    public static Player getInstance(String name){
        player.name=name;
        player.lifePoints=20.0;
        player.positionX=0;
        player.positionY=0;
        return player;
    }

    public void setName(String name) {
        player.name = name;
    }

    public void setLifePoints(double lifePoints) {
        player.lifePoints = lifePoints;
    }

    public void setPositionX(int positionX) {
        player.positionX = positionX;
    }

    public void setPositionY(int positionY) {
        player.positionY = positionY;
    }

    public String getName() {
        return player.name;
    }

    public double getLifePoints() {
        return player.lifePoints;
    }

    public int getPositionX() {
        return player.positionX;
    }

    public int getPositionY() {
        return player.positionY;
    }
}
