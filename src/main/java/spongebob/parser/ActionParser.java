package spongebob.parser;

import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;
import spongebob.ui.components.MainWindow;

/**
 * Parses user actions and executes corresponding methods in KrustyKrabTaskList.
 */
public enum ActionParser {
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
    DELETE_ERROR,
    FIND;

    /**
     * Extracts the corresponding ActionParser enum from the user input.
     *
     * @param userInput The user input string.
     * @return The corresponding ActionParser enum.
     */
    public static ActionParser fromString(String userInput) {
        String[] words = userInput.split(" ");
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
        case "find":
            return FIND;
        case "":
            return EMPTY;
        default:
            return ERROR;
        }
    }

    /**
     * Executes the action corresponding to the ActionParser enum on the given
     * KrustyKrabTaskList and original user input.
     *
     * @param action    The ActionParser enum representing the action to be
     *                  executed.
     * @param taskList  The KrustyKrabTaskList on which the action is to be
     *                  executed.
     * @param userInput The original user input string.
     * @param guiWindow The main GUI window to display the results in.
     */
    public static void executeAction(ActionParser action, KrustyKrabTaskList taskList, String userInput,
            MainWindow guiWindow) {
        switch (action) {
        case LIST:
            taskList.printTasks(guiWindow);
            break;
        case BYE:
            System.exit(0);
            break;
        case MARK:
            int indexToMark = Integer.parseInt(userInput.split(" ")[1]) - 1;
            taskList.markTask(indexToMark, guiWindow);
            break;
        case UNMARK:
            int indexToUnmark = Integer.parseInt(userInput.split(" ")[1]) - 1;
            taskList.unmarkTask(indexToUnmark, guiWindow);
            break;
        case MARK_ERROR:
            guiWindow.displaySpongebobResponse("Which task are you referring to?");
            break;
        case UNMARK_ERROR:
            guiWindow.displaySpongebobResponse("Which task are you referring to?");
            break;
        case ADD_ORDER:
            try {
                String orderDetails = userInput.substring(6);
                taskList.addOrder(orderDetails, guiWindow);
            } catch (StringIndexOutOfBoundsException e) {
                guiWindow.displaySpongebobResponse("What Krabby Patty order would you like to make?");
            }
            break;
        case ADD_DELIVERY:
            try {
                String deliveryDetails = userInput.substring(9);
                taskList.addDelivery(deliveryDetails, guiWindow);
            } catch (StringIndexOutOfBoundsException e) {
                guiWindow.displaySpongebobResponse("What delivery order would you like to make?");
            } catch (ArrayIndexOutOfBoundsException e) {
                guiWindow.displaySpongebobResponse("By when should I deliver the Krabby Patty?");
            } catch (SpongebobException e) {
                guiWindow.displaySpongebobResponse(e.getMessage());
            }
            break;
        case ADD_RESERVATION:
            try {
                String reservationDetails = userInput.substring(12);
                taskList.addReservation(reservationDetails, guiWindow);
            } catch (StringIndexOutOfBoundsException e) {
                guiWindow.displaySpongebobResponse("What reservation would you like to make?");
            } catch (ArrayIndexOutOfBoundsException e) {
                guiWindow.displaySpongebobResponse("From when to when do you want to reserve the Krusty Krab?");
            } catch (SpongebobException e) {
                guiWindow.displaySpongebobResponse(e.getMessage());
            }
            break;
        case DELETE:
            int indexToDelete = Integer.parseInt(userInput.split(" ")[1]) - 1;
            taskList.deleteTask(indexToDelete, guiWindow);
            break;
        case DELETE_ERROR:
            guiWindow.displaySpongebobResponse("Which task are you referring to?");
            break;
        case FIND:
            try {
                String keyword = userInput.substring(5).trim();
                if (keyword.isEmpty()) {
                    guiWindow.displaySpongebobResponse("Please enter valid keyword(s) to search for!");
                } else {
                    taskList.findTasks(keyword, guiWindow);
                }
            } catch (StringIndexOutOfBoundsException e) {
                guiWindow.displaySpongebobResponse("Please enter valid keyword(s) to search for!");
            }
            break;
        case ERROR:
            guiWindow.displaySpongebobResponse("What are you saying, Mr. Krabs?");
            break;
        case EMPTY:
            guiWindow.displaySpongebobResponse("Did you make a request?");
            break;
        default:
            guiWindow.displaySpongebobResponse("What are you saying, Mr. Krabs?");
        }
    }
}
