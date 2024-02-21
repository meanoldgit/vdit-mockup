package editor;

import javax.swing.JFrame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Editor
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame();
        String arg;

        if (args.length > 0) arg = args[0];
        else arg = null;
        
        frame.setUndecorated(true);
        frame.setVisible(true);
        frame.setFocusable(true);
        frame.addKeyListener(new Key(arg));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(new java.awt.Color(0, 0, 0, 1));
        frame.setFocusTraversalKeysEnabled(false);
    }
}

class Key implements KeyListener
{
    ArrayList<ArrayList<Character>> line = new ArrayList<>();
    FileManager fileManager = new FileManager();
    HotKeys hotKeys = new HotKeys();
    Cursor cursor = new Cursor();
    
    char action;
    char letter;
    String arg = "";
    String path = "";
    boolean cursorMode = false;
    boolean altPressed = false;
    boolean ctrlPressed = false;
    
    final char EMPTY_SPACE = ' ';
    final String SYMBOLS_REGEX = "[ .,:;_+-/\\*!\"'%$&@#~|()=¿?<>{}\\[\\]]";

    // Modify the constructor to pass the main parameter argument.
    public Key(String arg)
    {
        this.arg = arg;
        clearCommand();
        if (arg != null) System.out.println(arg);
        line.add(new ArrayList<>());
    }

    @Override
    public void keyTyped(KeyEvent event)
    {
        letter = event.getKeyChar();
        
        if ((Character.isLetterOrDigit(letter)
        || String.valueOf(letter).matches(SYMBOLS_REGEX))
        && !cursorMode && line.get(cursor.y).size() < 70)
        {
            System.out.print(letter);
            line.get(cursor.y).add(cursor.x, letter);
            cursor.x++;

            cursor.printTextAfterCursor(cursor.x, line.get(cursor.y));
        }
    }

    @Override
    public void keyPressed(KeyEvent event)
    {
        action = event.getKeyChar();
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_ENTER:
            newLine();
            break;

            case KeyEvent.VK_TAB:
            tabulate();
            break;

            case KeyEvent.VK_CONTROL:
            ctrlPressed = true;
            break;

            case KeyEvent.VK_ALT:
            altPressed = true;
            break;

            case KeyEvent.VK_BACK_SPACE:
            backSpace();
            break;

            // MOVE KEYS
            case KeyEvent.VK_J:
            if (cursorMode || (!cursorMode && ctrlPressed))
            {
                if (cursorMode && ctrlPressed)
                {
                    cursor.jumpBackward(line.get(cursor.y));
                }
                else
                {
                    cursor.backward();
                }
            }
            break;

            case KeyEvent.VK_L:
            if (cursorMode || (!cursorMode && ctrlPressed))
            {
                if (cursorMode && ctrlPressed)
                {
                    cursor.jumpForward(line.get(cursor.y));
                }
                else
                {
                    cursor.forward(line.get(cursor.y));
                }
            }
            break;

            case KeyEvent.VK_K:
                if (ctrlPressed)
                {
                    if (cursorMode)
                    {
                        cursorMode = false;
                        cursor.changeColorWhite();
                    }
                    else
                    {
                        cursorMode = true;
                        cursor.changeColorRed();
                    }
                }

                if (cursorMode && !ctrlPressed)
                {
                    cursor.down();
                }
            break;

            case KeyEvent.VK_I:
            if (cursorMode)
            {
                cursor.up();
            }
            break;

            case KeyEvent.VK_C:
            if (ctrlPressed)
            {
                hotKeys.close(line.get(cursor.y));
            }
            break;

            default:
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent event)
    {
        switch (event.getKeyCode())
        {
            case KeyEvent.VK_E:
            if (cursorMode)
            {
                cursorMode = false;
                cursor.changeColorWhite();
            }
            break;

            case KeyEvent.VK_CONTROL:
            ctrlPressed = false;
            break;

            case KeyEvent.VK_ALT:
            altPressed = false;
            break;
            
            default:
            break;
        }
    }


    // NEW LINE

    public void newLine()
    {
        line.add(new ArrayList<>());
        splitCurrentLine();
        addNewLine();
        cursor.savePosition();
        printNewLine();
        cursor.restorePosition();
    }

    public void splitCurrentLine()
    {
        if (cursor.x != line.get(cursor.y).size())
        {
            for (int i = cursor.x; i <= line.get(cursor.y).size(); i++)
            {
                line.get(cursor.y + 1).add(line.get(cursor.y).get(cursor.x));
                line.get(cursor.y).remove(line.get(cursor.y).get(cursor.x));
                System.out.print(EMPTY_SPACE);
            }
        }
    }

    public void addNewLine()
    {
        line.get(cursor.y).add(cursor.x, action);
        cursor.y++;
        System.out.print(action);
        cursor.x = 0;
    }

    public void printNewLine()
    {
        for (int i = 0; i < line.get(cursor.y).size(); i++)
        {
            System.out.print(line.get(cursor.y).get(i));
        }
    }


    // BACK SPACE

    public void backSpace()
    {
        cursor.x--;
        line.get(cursor.y).remove(cursor.x);

        // Backspace, print empty space, backspace again.
        System.out.print(action + " " + action);

        cursor.printTextAfterCursor(cursor.x, line.get(cursor.y));
    }


    // TABULATION

    public void tabulate()
    {
        for (int i = 0; i < 4; i++)
        {
            line.get(cursor.y).add(cursor.x, EMPTY_SPACE);
            cursor.x++;
            System.out.print(EMPTY_SPACE);
        }
        
        cursor.printTextAfterCursor(cursor.x, line.get(cursor.y));
    }

    public void reverseTab()
    {}


    // COMMANDS

    public static void clearCommand()
    {
        try
        {
            ProcessBuilder clear = new ProcessBuilder("bash", "-c", "clear").inheritIO();
            clear.start().waitFor();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
