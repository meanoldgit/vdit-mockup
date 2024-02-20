package editor;

import java.util.ArrayList;

public class HotKeys
{
    FileManager fileManager = new FileManager();
    Cursor cursor = new Cursor();

    public void close(ArrayList<Character> col)
    {
        cursor.changeColorWhite();
        System.out.println("\nbye");

        // Print file name.
        for (int i = 0; i < col.size(); i++)
        {
            System.out.print(col.get(i));
        }
        
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
