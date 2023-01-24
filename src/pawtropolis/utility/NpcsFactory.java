package pawtropolis.utility;

import pawtropolis.model.entity.Entity;
import pawtropolis.model.entity.npc.animal.species.Eagle;
import pawtropolis.model.entity.npc.animal.species.Lion;
import pawtropolis.model.entity.npc.animal.species.Tiger;

public class NpcsFactory {
    public static Entity getInstanceEntity(int number){
        switch(number){
            case 0:{
                return new Eagle();
            }
            case 1:{
                return new Lion();
            }
            case 2:{
                return new Tiger();
            }
        }
        return null;
    }
}
