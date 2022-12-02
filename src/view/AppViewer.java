package view;

import controller.Controller;
import data.Commands;
import data.Priority;
import data.Task;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import static java.nio.file.AccessMode.READ;
import static java.nio.file.StandardOpenOption.CREATE;
import static javax.swing.plaf.synth.Region.LIST;

public class AppViewer {
    private Commands commands;

    public void startApp()   {
        Commands com = Commands.NONE;
        Priority priorityEnum = Priority.NONE;
        Controller controller = new Controller();

        while (true) {
            clearScreen();
            try {
                String command = input("Введите команду (Add, Reade, Update, Delete, List, Save, Exit): \n");
                com = Commands.valueOf(command.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Unknown command");
                continue;
            }
            if (com == Commands.EXIT) return;

            switch (com) {
                case ADD:
                    try {
                        addHandle(priorityEnum, controller);
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                case READE:
                    readeHandler(controller);
                    continue;
                case DELETE:
                    deleteHandler(controller);

                    continue;
                case LIST:
                    listHandler(controller);
                case UPDATE:
                    continue;

                case SAVE:
                    try {
                        saveHandler(controller);
                    } catch (IllegalStateException e) {
                        System.out.println(e.getMessage());
                        continue;
                    }
                    break;
                case NONE:
                    break;
            }

        }

    }

    private void readeHandler(Controller controller)  {
        String format = input("Please inter format (json, xml, csv): ");
        controller.readeData(format);

    }

    private void listHandler(Controller controller) {
      List<Task> taskList = controller.getTasks();
      if (taskList.isEmpty()){
          notifyInConsole("Task list is empty");
        }
      else {
          controller.showTasks();
      }

    }

    private void deleteHandler(Controller controller) {
        String id = input("Please enter task id: ");
        controller.deleteTask(Integer.parseInt( id));
        notifyInConsole("Task was deleted!");

    }

    public void addHandle(Priority priorityEnum, Controller controller) {

        String theme = input("Theme: ");
        String description = input("Description: ");
        String priority = input("Priority: ");
        priorityEnum = Priority.valueOf(priority.toUpperCase());
        controller.addTask(priorityEnum, theme, description, "VZ");
    }

    public void saveHandler(Controller controller) {
        String format = input("Please inter format (json, xml, csv): ");
        controller.writeData(controller.getTasks(), format);
    }

    private String input(String message) {
        Scanner in = new Scanner(System.in);
        System.out.print(message);
        return in.nextLine();

    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void notifyInConsole(String message){
        Logger.getAnonymousLogger().info(message);

    };

}



