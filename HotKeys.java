package editor;

import java.io.File;
import java.util.ArrayList;

public class HotKeys
{
    FileManager fileManager = new FileManager();
    Cursor cursor = new Cursor();

    public void close(ArrayList<ArrayList<Character>> lines)
    {
        cursor.changeColorWhite();
        System.out.println("\nbye");

        for (int i = 0; i < lines.size(); i++)
            lines.get(i).add('\n');

        // Print lines.
        for (int i = 0; i < lines.size(); i++)
            for (int j = 0; j < lines.get(i).size(); j++)
                System.out.print(lines.get(i).get(j));
        
        System.out.println();
        System.exit(0);

        // String fileName = "";
        // for (int i = 0; i < column.size(); i++)
        // {
        //     fileName += column.get(i);
        // }

        // path = fileName;

        // fileManager.confirmName(path);
    }
}
