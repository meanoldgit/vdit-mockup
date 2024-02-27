package editor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileManager
{
    File file;
    String fileName;

    public FileManager(String fileName)
    {
        this.fileName = fileName;
    }

    public void openFile(ArrayList<ArrayList<Character>> lines)
    {
        file = new File(fileName);

        if (file.exists())
        {
            System.out.println("file exists");
            readFile(lines);
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

    public void readFile(ArrayList<ArrayList<Character>> lines)
    {
        try
        {
            Scanner read = new Scanner(file);
            String data;
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
            System.out.println("\nCouldn't read file.");
            e.printStackTrace();
        }
    }

    public void writeFile(ArrayList<ArrayList<Character>> lines)
    {
        try
        {
            FileWriter write = new FileWriter(file);
            write.close();
        }
        catch (IOException e)
        {
            System.out.println("\nCouldn't write file.");
            e.printStackTrace();
        }
    }

    public void confirmName(String path)
    {
        //     String mapData = "";

        //     write.write(mapData);
        //     write.close();
    }
}
