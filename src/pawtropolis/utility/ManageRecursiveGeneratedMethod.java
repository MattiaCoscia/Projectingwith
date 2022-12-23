package pawtropolis.utility;

import pawtropolis.model.map.GameMap;
import pawtropolis.model.map.Room;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class ManageRecursiveGeneratedMethod extends Thread{
    private static Map<String, Queue<Method>> nodeMethodes=new HashMap<>();

    private String node;
    private int x;
    private int y;
    private GameMap map;

    private Room room;

    ManageRecursiveGeneratedMethod(String node,int x,int y,GameMap map){
        this.node=node;
        this.x=x;
        this.y=y;
        this.map=map;
    }

    public ManageRecursiveGeneratedMethod addRequest(Method method, String node){
            nodeMethodes.computeIfAbsent(node,k->new LinkedBlockingQueue<Method>()).add(method);
        return this;
    }

    public synchronized Room processRequest(String node, int x, int y, GameMap map) throws InvocationTargetException, IllegalAccessException, InterruptedException {
        Room room=null;
            Method method=nodeMethodes.get(node).poll();
            method.setAccessible(true);
            room= (Room) method.invoke(null,x,y,map,Integer.parseInt(node)+1);
        return room;
    }
    @Override
    public void run(){
        try {
            this.room=processRequest(this.node,this.x,this.y,this.map);
        } catch (InvocationTargetException | InterruptedException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public String getNode() {
        return node;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public GameMap getMap() {
        return map;
    }

    public Room getRoom() {
        return room;
    }
}
