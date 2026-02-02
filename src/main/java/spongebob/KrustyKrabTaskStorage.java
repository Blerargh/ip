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
}
