package pawtropolis.model.map;

import org.springframework.util.ObjectUtils;

public enum DirectionEnum {
    NORTH,
    SOUTH,
    EAST,
    WEST,
    NOWHERE;

    public static DirectionEnum fromValue(String direction) {
        if (!ObjectUtils.isEmpty(direction)) {
            try {
                return DirectionEnum.valueOf(direction.toUpperCase());
            } catch (IllegalArgumentException e) {
                return DirectionEnum.NOWHERE;
            }
        }
        return NOWHERE;
    }
    }
