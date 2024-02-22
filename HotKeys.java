package editor;

import java.util.ArrayList;

public class HotKeys
{
    FileManager fileManager = new FileManager();
    Cursor cursor = new Cursor();

    public void close(ArrayList<ArrayList<Character>> line)
    {
        cursor.changeColorWhite();
        System.out.println("\nbye");

        // Print lines.
        for (int i = 0; i < line.size(); i++)
            for (int j = 0; j < line.get(i).size(); j++)
                System.out.print(line.get(i).get(j));
        
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
