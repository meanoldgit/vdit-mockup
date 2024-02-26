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
            readFile(file, lines);
        }
        else
        {
            try
            {
                file.createNewFile();
            }
            catch (IOException e)
            {
                System.out.println("\nCouldn't create file.");
                e.printStackTrace();
            }

            lines.add(new ArrayList<>());
        }
    }

    public void readFile(File file, ArrayList<ArrayList<Character>> lines)
    {
        try
        {
            Scanner read = new Scanner(file);
            String data = "";
            int line;

            while (read.hasNextLine())
            {
                line = lines.size();
                data = read.nextLine();
                lines.add(new ArrayList<>());

                for (int i = 0; i < data.length(); i++)
                {
                    lines.get(line).add(data.charAt(i));
                }
            }

            read.close();
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
            write.close();
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

        //     write.write(mapData);
        //     write.close();

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
