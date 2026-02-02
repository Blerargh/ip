package spongebob.parser;

import spongebob.exceptions.SpongebobException;
import spongebob.tasklistmanager.KrustyKrabTaskList;

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
    DELETE_ERROR;

    public static ActionParser fromString(String actionStr) {
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

    public static void executeAction(ActionParser action, KrustyKrabTaskList taskList, String userInput) {
        switch (action) {
        case LIST:
            taskList.printTasks();
            break;
        case BYE:
            break;
        case MARK:
            int indexToMark = Integer.parseInt(userInput.split(" ")[1]) - 1;
            taskList.markTask(indexToMark);
            break;
        case UNMARK:
            int indexToUnmark = Integer.parseInt(userInput.split(" ")[1]) - 1;
            taskList.unmarkTask(indexToUnmark);
            break;
        case MARK_ERROR:
            System.out.println("Which task are you referring to?");
            break;
        case UNMARK_ERROR:
            System.out.println("Which task are you referring to?");
            break;
        case ADD_ORDER:
            try {
                String orderDetails = userInput.substring(6);
                taskList.addOrder(orderDetails);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("What Krabby Patty order would you like to make?");
            }
            break;
        case ADD_DELIVERY:
            try {
                String deliveryDetails = userInput.substring(9);
                taskList.addDelivery(deliveryDetails);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("What delivery order would you like to make?");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("By when should I deliver the Krabby Patty?");
            } catch (SpongebobException e) {
                System.out.println(e.getMessage());
            }
            break;
        case ADD_RESERVATION:
            try {
                String reservationDetails = userInput.substring(12);
                taskList.addReservation(reservationDetails);
            } catch (StringIndexOutOfBoundsException e) {
                System.out.println("What reservation would you like to make?");
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("From when to when do you want to reserve the Krusty Krab?");
            } catch (SpongebobException e) {
                System.out.println(e.getMessage());
            }
            break;
        case DELETE:
            int indexToDelete = Integer.parseInt(userInput.split(" ")[1]) - 1;
            taskList.deleteTask(indexToDelete);
            break;
        case DELETE_ERROR:
            System.out.println("Which task are you referring to?");
            break;
        case ERROR:
            System.out.println("What are you saying, Mr. Krabs?");
            break;
        case EMPTY:
            System.out.println("Did you make a request?");
            break;
        }
    }
}
