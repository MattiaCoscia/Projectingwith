package pawtropolis.utility.marshall.entity.player;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.dto.entity.PlayerDTO;
import pawtropolis.model.entity.Player;
import pawtropolis.utility.marshall.entity.BranchEntityMarshaller;
import pawtropolis.utility.marshall.item_related.BagMarshaller;

@Component
public class PlayerEntityMarshaller extends BranchEntityMarshaller<PlayerDTO,Player> {
    private final BagMarshaller bagMarshaller;
    @Autowired
    public PlayerEntityMarshaller(BagMarshaller bagMarshaller){
        super(Player.class, PlayerDTO.class);
        this.bagMarshaller = bagMarshaller;
    }
    public Player marshall(PlayerDTO playerDTO){
        if(ObjectUtils.isEmpty(playerDTO)){
            Player player = new Player();
            player.setLifePoints(playerDTO.getLifePoints());
            player.setName(playerDTO.getName());
            player.setId(playerDTO.getId());
            player.setPositionY(playerDTO.getPositionY());
            player.setPositionX(player.getPositionX());
            if(playerDTO.getBagDTO() != null){
                player.setBag(bagMarshaller.marshallFromDTO(playerDTO.getBagDTO()));
            }
            return player;
        }
        return null;
    }

    public PlayerDTO marshall(Player player){
        if(ObjectUtils.isEmpty(player)){
            PlayerDTO playerDTO = new PlayerDTO();
            playerDTO.setId(player.getId());
            playerDTO.setName(player.getName());
            playerDTO.setLifePoints(player.getLifePoints());
            playerDTO.setPositionY(player.getPositionY());
            playerDTO.setPositionX(player.getPositionX());
            if(playerDTO.getBagDTO() != null){
                playerDTO.setBagDTO(bagMarshaller.marshallToDTO(player.getBag()));
            }
            return playerDTO;
        }
        return null;
    }
}
