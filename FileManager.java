package editor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager
{
    public void confirmName(String path)
    {
        try
        {
            path = "editor/files/" + path + ".txt";

            File file = new File(path);
            FileWriter write = new FileWriter(file);
            Scanner read = new Scanner(file);
            String mapData = "";

            if (file.createNewFile()) System.out.println("\nCreated.");
            else System.out.println("\nName taken.");

            write.write(mapData);
            write.close();
            System.out.println("\nDone.");

            while (read.hasNextLine())
            {
                String data = read.nextLine();
                System.out.println(data);
            }

            read.close();
        }
        catch (IOException e)
        {
            System.out.println("\nSomething bad happened.");
            e.printStackTrace();
        }
    }
}
