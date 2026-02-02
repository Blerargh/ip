package spongebob;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class KrustyKrabTaskStorage {
    public static void saveTasks(ArrayList<KrustyKrabTask> taskList) {
        Path path = Paths.get("src/main/java/data");
        try {
            Files.createDirectories(path);
            FileWriter fileWriter = new FileWriter("src/main/java/data/orders.txt");
            for (KrustyKrabTask task : taskList) {
                fileWriter.write(task.toString() + "\n");
            }
            fileWriter.close();
            System.out.println("Tasks saved successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving tasks.");
        }
    }

    public static KrustyKrabTaskList loadTasks() {
        Path filePath = Paths.get("src/main/java/data/orders.txt");
        KrustyKrabTaskList taskList = new KrustyKrabTaskList();

        try {
            if (Files.exists(filePath)) {
                ArrayList<String> lines = (ArrayList<String>) Files.readAllLines(filePath);
                for (int i = 0; i < lines.size(); i++) {
                    String line = lines.get(i);
                    char taskType = line.charAt(1);
                    boolean isDone = line.charAt(4) == 'X';

                    // Reconstruct task list based on saved data
                    switch (taskType) {
                    case 'O':
                        String details = line.substring(7);
                        ActionParser.executeAction(ActionParser.ADD_ORDER,
                                taskList, String.format("order %s", details));
                        break;
                    case 'D':
                        String deliveryDetails = line.substring(7, line.indexOf("(by:") - 1);
                        String deliveryBy = line.substring(line.indexOf("(by:") + 5, line.length() - 1);

                        ActionParser.executeAction(ActionParser.ADD_DELIVERY,
                                taskList, String.format("delivery %s /by %s", deliveryDetails, deliveryBy));
                        break;
                    case 'R':
                        String reservationDetails = line.substring(7, line.indexOf("(from:") - 1);
                        String reservationFrom = line.substring(line.indexOf("(from:") + 7,
                                line.indexOf("to:") - 1);
                        String reservationTo = line.substring(line.indexOf("to:") + 4, line.length() - 1);

                        ActionParser.executeAction(ActionParser.ADD_RESERVATION,
                                taskList, String.format("reservation %s /from %s /to %s",
                                        reservationDetails, reservationFrom, reservationTo));
                        break;
                    }

                    // Mark task as done if applicable
                    if (isDone) {
                        ActionParser.executeAction(ActionParser.MARK,
                                taskList, String.format("mark %d", i + 1));
                    }
                }
                System.out.println("Tasks loaded successfully.");
            } else {
                System.out.println("No saved tasks found.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred while loading tasks.");
        }

        return taskList;
    }
}
