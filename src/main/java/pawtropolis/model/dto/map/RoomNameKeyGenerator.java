package pawtropolis.model.dto.map;

public class RoomNameKeyGenerator {
    public static String giveKeyForRoom(int y, int x){
        return "Y:"+y+"X:"+x;
    }
}
