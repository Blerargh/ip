package spongebob.tasklistmanager;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;

import spongebob.exceptions.SpongebobException;
import spongebob.tasktype.KrustyKrabDelivery;
import spongebob.tasktype.KrustyKrabOrder;
import spongebob.tasktype.KrustyKrabReservation;
import spongebob.tasktype.KrustyKrabTask;

/**
 * Handles the storage and retrieval of Krusty Krab tasks to and from a file.
 * Tasks are saved in "src/main/java/data/orders.txt".
 * Each task is stored in a specific format to allow for easy reconstruction.
 */
public class KrustyKrabTaskStorage {
    /**
     * Saves the list of tasks to a file.
     * If the file does not exist, it will be created in "src/main/java/data".
     *
     * @param taskList The list of tasks to be saved.
     */
    public static void saveTasks(ArrayList<KrustyKrabTask> taskList) throws SpongebobException {
        assert taskList != null : "Task list should not be null";

        Path path = Paths.get("src/main/java/data");
        try {
            Files.createDirectories(path);
            FileWriter fileWriter = new FileWriter("src/main/java/data/orders.txt");
            for (KrustyKrabTask task : taskList) {
                fileWriter.write(task.toString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new SpongebobException("An error occurred while saving tasks.");
        }
    }

    /**
     * Loads tasks from the file and reconstructs the task list.
     * If the file does not exist in "src/main/java/data", an empty task list is
     * returned.
     *
     * @return The reconstructed KrustyKrabTaskList.
     */
    public KrustyKrabTaskList loadTasks() throws SpongebobException {
        Path filePath = Paths.get("src/main/java/data/orders.txt");
        KrustyKrabTaskList taskList = new KrustyKrabTaskList();

        try {
            Files.lines(filePath).map(line -> this.convertFileLineToTask(line))
                    .forEach(task -> taskList.addTask(taskList.getTaskCount(), task));
        } catch (IOException e) {
            throw new SpongebobException("No saved tasks found.");
        }

        return taskList;
    }

    private KrustyKrabTask convertFileLineToTask(String line) {
        char taskType = line.charAt(1);
        boolean isDone = line.charAt(4) == 'X';

        // Reconstruct task list based on saved data
        KrustyKrabTask loadedTask;
        switch (taskType) {
        case 'D':
            loadedTask = this.parseDeliveryLine(line);
            break;
        case 'R':
            loadedTask = this.parseReservationLine(line);
            break;
        default:
            loadedTask = this.parseOrderLine(line);
        }

        // Mark task as done if applicable
        if (isDone) {
            loadedTask.markComplete();
        }

        return loadedTask;
    }

    private KrustyKrabOrder parseOrderLine(String line) {
        String orderDetails = line.substring(7);
        return new KrustyKrabOrder(orderDetails);
    }

    private KrustyKrabDelivery parseDeliveryLine(String line) {
        String deliveryDetails = line.substring(7, line.indexOf("(by:") - 1);
        LocalDateTime deliveryBy = LocalDateTime
                .parse(line.substring(line.indexOf("(by:") + 5, line.length() - 1),
                        KrustyKrabTaskList.DATE_TIME_FORMATTER);

        return new KrustyKrabDelivery(deliveryDetails, deliveryBy);
    }

    private KrustyKrabReservation parseReservationLine(String line) {
        String reservationDetails = line.substring(7, line.indexOf("(from:") - 1);
        LocalDateTime reservationFrom = LocalDateTime
                .parse(line.substring(line.indexOf("(from:") + 7, line.indexOf("to:") - 1),
                        KrustyKrabTaskList.DATE_TIME_FORMATTER);
        LocalDateTime reservationTo = LocalDateTime
                .parse(line.substring(line.indexOf("to:") + 4, line.length() - 1),
                        KrustyKrabTaskList.DATE_TIME_FORMATTER);

        return new KrustyKrabReservation(reservationDetails, reservationFrom, reservationTo);
    }
}
