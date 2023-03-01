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
                return null;
            }
        }
        return null;
    }

    public static ActionStrategy getAction(String action){
        switch (ActionEnum.fromValue(action)) {
            case GO: {

                return new GoStrategy();
            }
            case GET: {

                return new GetStrategy();
            }
            case DROP: {

                return new DropStrategy();
            }
            case BAG: {
                return new BagStrategy();
            }
            case LOOK: {
                return new LookStrategy();
            }
            default:{
                return new UnknownStrategy();
            }
        }
    }
}
