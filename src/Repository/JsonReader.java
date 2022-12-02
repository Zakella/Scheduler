package Repository;

import data.Task;


import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import view.AppViewer;
import view.TaskViewer;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class JsonReader implements Reader {

    private final  String filename = "tasks.json";

    public String getFilename() {
        return filename;
    }

    @Override
    public void writeData(List<Task> tasks) {

        JSONObject mainData = new JSONObject();

        for (Task task: tasks) {
            JSONArray array = new JSONArray();
            JSONObject details = new JSONObject();
            details.put("Theme", task.getHandler());
            details.put("Description", task.getDescription());
            details.put("Author", task.getAuthor());
            details.put("priority", task.getPriority());
            details.put("Done", task.getDone());
            mainData.put(task.getId(), array.put(details));

        }


        try (FileWriter file = new FileWriter(this.getFilename(), true)) {
            file.write(mainData.toJSONString());
            file.flush();
            AppViewer.notifyInConsole("Successfully written file " + getFilename());

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void readData() {

        JSONParser parser = new JSONParser();

    }
}
