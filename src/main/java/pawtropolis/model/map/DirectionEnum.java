package pawtropolis.model.map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;

import java.util.Map;

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
                log.info(msg, direction);
            }
        }
        return NORTH;
    }

    public static DirectionEnum oppositeValue(DirectionEnum directionEnum) {
        final Map<DirectionEnum, DirectionEnum> inverseDirectionEnumMap = Map.of(
                DirectionEnum.NORTH, DirectionEnum.SOUTH,
                DirectionEnum.EAST, DirectionEnum.WEST,
                DirectionEnum.WEST, DirectionEnum.EAST,
                DirectionEnum.SOUTH, DirectionEnum.NORTH);
        return inverseDirectionEnumMap.get(directionEnum);
    }
}
