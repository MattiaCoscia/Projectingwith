package pawtropolis.model.map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
@Slf4j
public enum DirectionEnum {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public static DirectionEnum fromValue(String direction) {
        if (!ObjectUtils.isEmpty(direction)) {
            try {
                return DirectionEnum.valueOf(direction.toUpperCase());
            } catch (IllegalArgumentException e) {
                String msg = "valore on esistente {}";
                log.info(msg,direction);
            }
        }
        return NORTH;
    }
}
