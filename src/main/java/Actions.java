public enum Actions {
    EMPTY, LIST, MARK, UNMARK, BYE, ADD, MARK_ERROR, UNMARK_ERROR;

    public static Actions fromString(String actionStr) {
        String[] words = actionStr.split(" ");
        String firstWord = words[0].toLowerCase();
        switch (firstWord) {
            case "list":
                if (words.length == 1) {
                    return LIST;
                }
                return ADD;
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
                return ADD;
            case "":
                return EMPTY;
            default:
                return ADD;
        }
    }
}
