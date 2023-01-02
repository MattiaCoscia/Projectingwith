package pawtropolis.utility;

import pawtropolis.model.entity.Entity;
import pawtropolis.model.entity.npc.animal.specie.Eagle;
import pawtropolis.model.entity.npc.animal.specie.Lion;
import pawtropolis.model.entity.npc.animal.specie.Tiger;

import java.time.LocalDate;

public class NpcsFactory {
    public static Entity getInstanceEntity(int number){
        switch(number){
            case 1:{
                return new Eagle("","",(int)Math.random()*100, LocalDate.now(),Math.random()*10,Math.random()*23
                        ,Math.random()*100,20,0,0);
            }
            case 2:{
                return new Lion("","",(int)Math.random()*100, LocalDate.now(),Math.random()*200,Math.random()*103
                        ,Math.random()*20,20,0,0);
            }
            case 3:{
                return new Tiger("","",(int)Math.random()*100, LocalDate.now(),Math.random()*300,Math.random()*101
                        ,Math.random()*20,20,0,0);
            }
        }
        return null;
    }
}
