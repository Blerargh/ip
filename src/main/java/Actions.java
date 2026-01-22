public enum Actions {
    EMPTY, 
    LIST, 
    MARK, 
    UNMARK, 
    BYE, 
    ERROR, 
    MARK_ERROR, 
    UNMARK_ERROR, 
    ADD_ORDER, 
    ADD_DELIVERY, 
    ADD_RESERVATION,
    DELETE,
    DELETE_ERROR;

    public static Actions fromString(String actionStr) {
        String[] words = actionStr.split(" ");
        String firstWord = words[0].toLowerCase();
        switch (firstWord) {
            case "list":
                if (words.length == 1) {
                    return LIST;
                }
                return ERROR;
            case "mark":
                if (words.length == 1) {
                    return MARK_ERROR;
                } else if (words.length == 2 && words[1].matches("\\d+")) {
                    return MARK;
                }
                return MARK_ERROR;
            case "unmark":
                if (words.length == 1) {
                    return UNMARK_ERROR;
                } else if (words.length == 2 && words[1].matches("\\d+")) {
                    return UNMARK;
                }
                return UNMARK_ERROR;
            case "bye":
                if (words.length == 1) {
                    return BYE;
                }
                return ERROR;
            case "order":
                return ADD_ORDER;
            case "delivery":
                return ADD_DELIVERY;
            case "reservation":
                return ADD_RESERVATION;
            case "delete":
                if (words.length == 1) {
                    return DELETE_ERROR;
                } else if (words.length == 2 && words[1].matches("\\d+")) {
                    return DELETE;
                }
                return DELETE_ERROR;
            case "":
                return EMPTY;
            default:
                return ERROR;
        }
    }
}
