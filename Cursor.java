package editor;
import java.util.ArrayList;

public class Cursor
{
    int x = 0;
    int y = 0;

    final char EMPTY_SPACE = ' ';
    final String CURSOR_UP = "\033[A";
    final String CURSOR_DOWN = "\033[B";
    final String CURSOR_FORWARD = "\033[C";
    final String CURSOR_BACKWARD = "\033[D";
    final String SAVE_CURSOR_POSITION = "\033[s";
    final String RESTORE_CURSOR_POSITION = "\033[u";
    final String CLEAR_LINE_AFTER_CURSOR = "\033[K";
    final String CLEAR_SCREEN_AFTER_CURSOR = "\033[J";
    final String CURSOR_COLOR_RED = "\033]12;red\007";
    final String CURSOR_COLOR_WHITE = "\033]12;white\007";

    public void up()
    {
        if (y > 0)
        {
            y--;
            System.out.print(CURSOR_UP);
        }
    }

    public void down(ArrayList<ArrayList<Character>> lines)
    {
        if (y + 1 < lines.size())
        {
            y++;
            System.out.print(CURSOR_DOWN);
        }
    }

    public void backward()
    {
        if (x > 0)
        {
            x--;
            System.out.print(CURSOR_BACKWARD);
        }
    }

    public void forward(ArrayList<Character> col)
    {
        if (x < col.size())
        {
            x++;
            System.out.print(CURSOR_FORWARD);
        }
    }

    public void clearScreenAfterCursor()
    {
        System.out.print(CLEAR_SCREEN_AFTER_CURSOR);
    }

    public void savePosition()
    {
        System.out.print(SAVE_CURSOR_POSITION);
    }

    public void restorePosition()
    {
        System.out.print(RESTORE_CURSOR_POSITION);
    }

    public void changeColorRed()
    {
        System.out.print(CURSOR_COLOR_RED);
    }

    public void changeColorWhite()
    {
        System.out.print(CURSOR_COLOR_WHITE);
    }

    public void printLineAfterCursor(ArrayList<Character> col)
    {
        System.out.print(SAVE_CURSOR_POSITION);

        for (int i = x; i < col.size(); i++)
            System.out.print(col.get(i));

        System.out.print(' ');
        System.out.print(RESTORE_CURSOR_POSITION);
    }

    public void jumpBackward(ArrayList<Character> col)
    {
        if (col.get(x - 1) != EMPTY_SPACE)
        {
            while (col.get(x - 1) != EMPTY_SPACE)
            {
                backward();
            }
        }
        else
        {
            while (col.get(x - 1) == EMPTY_SPACE)
            {
                backward();
            }
        }
    }

    public void jumpForward(ArrayList<Character> col)
    {
        if (col.get(x) != EMPTY_SPACE)
        {
            while (col.get(x) != EMPTY_SPACE)
            {
                forward(col);
            }
        }
        else
        {
            while (col.get(x) == EMPTY_SPACE)
            {
                forward(col);
            }
        }
    }

    public void jumpToLineStart()
    {}

    public void jumpToLineEnd()
    {}
}
