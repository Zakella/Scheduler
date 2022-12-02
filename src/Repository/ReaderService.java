package Repository;

import data.Task;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;

public class ReaderService {

    public void writeData(List<Task> listTasks, String format){

        if (format.equals("json")) {
            new JsonReader().writeData(listTasks);
            return;

        }
        if (format.equals("csv")) {
            new CSVReader().writeData(listTasks);
            return;
        }


        if (format.equals("xml")) {
            new XmlReader().writeData(listTasks);
            return;
        }

        else {
          throw new IllegalStateException("Format not found!");
        }

    }

    public void readData(String format)  {

        if (format.equals("json")) {
            new JsonReader().readData();

        }
        if (format.equals("csv")) {
            new CSVReader().readData();

        }


        if (format.equals("xml")) {
            new XmlReader().readData();

        }

        else {
            throw new IllegalStateException("Format not found!");
        };

    }
}
