package pawtropolis.utility.marshall;

import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.PlayerDTO;
import pawtropolis.model.entity.Player;

public class PlayerMarshaller {

    public static Player marshallFromDTO(PlayerDTO playerDTO){
        if(ObjectUtils.isEmpty(playerDTO)){
            Player player = new Player();
            player.setLifePoints(playerDTO.getLifePoints());
            player.setName(playerDTO.getName());
            player.setId(playerDTO.getId());
            player.setPositionY(playerDTO.getPositionY());
            player.setPositionX(player.getPositionX());
            if(playerDTO.getBagDTO() != null){
            }
        }
        return null;
    }
}
