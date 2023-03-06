package pawtropolis.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import pawtropolis.model.entity.Entity;
import pawtropolis.model.entity.npc.animal.species.Eagle;
import pawtropolis.model.entity.npc.animal.species.Lion;
import pawtropolis.model.entity.npc.animal.species.Tiger;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
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
            default:{
                log.info("Not create an Npc!");
            }
        }
        return null;
    }
}
