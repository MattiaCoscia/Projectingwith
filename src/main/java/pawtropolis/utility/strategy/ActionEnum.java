package pawtropolis.utility.strategy;

public enum ActionEnum {
    GO,
    BAG,
    GET,
    DROP,
    LOOK,
    EXIT,
    UNKNOWN_COMMAND;

    public static ActionEnum fromValue(String action) {
        try {
            return ActionEnum.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN_COMMAND;
        }
    }
}
