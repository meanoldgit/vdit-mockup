package editor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager
{
    public void openFile(String fileName, ArrayList<ArrayList<Character>> lines)
    {
        fileName = "editor/files/" + fileName;
        File file = new File(fileName);

        if (file.exists())
        {
            System.out.println("file exists");
            readFile(file);
        }
        else
        {
            lines.add(new ArrayList<>());
        }
    }

    public void readFile(File file)
    {
        try
        {
            Scanner read = new Scanner(file);
            while (read.hasNextLine())
            {
                String data = read.nextLine();
                System.out.println(data);
            }
        }
        catch (IOException e)
        {
            System.out.println("\nSomething bad happened.");
            e.printStackTrace();
        }
    }

    public void writeFile(File file)
    {
        try
        {
            FileWriter write = new FileWriter(file);
        }
        catch (IOException e)
        {
            System.out.println("\nSomething bad happened.");
            e.printStackTrace();
        }
    }

    public void confirmName(String path)
    {
        // try
        // {
            
        //     String mapData = "";

        //     if (file.createNewFile())
        //         System.out.println("\nCreated.");
        //     else
        //         System.out.println("\nName taken.");

        //     write.write(mapData);
        //     write.close();
        //     System.out.println("\nDone.");

        //     while (read.hasNextLine())
        //     {
        //         String data = read.nextLine();
        //         System.out.println(data);
        //     }

        //     read.close();
        // }
        // catch (IOException e)
        // {
        //     System.out.println("\nSomething bad happened.");
        //     e.printStackTrace();
        // }
    }
}
