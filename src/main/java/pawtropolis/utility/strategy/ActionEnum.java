package pawtropolis.utility.strategy;

import org.springframework.util.ObjectUtils;

public enum ActionEnum {
    GO,
    BAG,
    GET,
    DROP,
    LOOK,
    UNKNOWN_COMMAND;

    public static ActionEnum fromValue(String action) {
        if (!ObjectUtils.isEmpty(action)) {
            try {
                return ActionEnum.valueOf(action.toUpperCase());
            } catch (IllegalArgumentException e) {
            }
        }
        return UNKNOWN_COMMAND;
    }
}
