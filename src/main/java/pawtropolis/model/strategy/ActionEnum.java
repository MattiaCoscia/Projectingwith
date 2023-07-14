package pawtropolis.model.strategy;

public enum ActionEnum {
    GO,
    BAG,
    GET,
    DROP,
    LOOK,
    SAVE,
    EXIT,
    UNKNOWN;

    public static ActionEnum fromValue(String action) {
        try {
            return ActionEnum.valueOf(action.toUpperCase());
        } catch (IllegalArgumentException e) {
            return UNKNOWN;
        }
    }
}
