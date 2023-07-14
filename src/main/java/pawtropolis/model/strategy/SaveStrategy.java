package pawtropolis.model.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import pawtropolis.model.map.GameMap;
import pawtropolis.persistence.dto.map.GameMapDTO;
import pawtropolis.persistence.repository.map.GameMapRepository;
import pawtropolis.utility.marshall.map.GameMapMarshaller;

import java.util.List;

@Component
public class SaveStrategy extends Strategy{
    private GameMapMarshaller gameMapMarshaller;
    private GameMapRepository gameMapRepository;
    private GameMap gameMap;

    @Autowired
    public SaveStrategy(GameMap gameMap, GameMapMarshaller gameMapMarshaller, GameMapRepository gameMapRepository) {
        super(ActionEnum.SAVE);
        this.gameMapMarshaller = gameMapMarshaller;
        this.gameMapRepository = gameMapRepository;
        this.gameMap = gameMap;
    }

    @Override
    public ActionEnum execute(List<String> parameters) {
        if(!ObjectUtils.isEmpty(gameMap)){
            GameMapDTO gameMapDTO = gameMapMarshaller.marshall(gameMap);
            gameMapRepository.save(gameMapDTO);
        }
        return ActionEnum.SAVE;
    }
}
