import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class home
{
    public static void main(String args[])
    {
        try
        {
            Scanner input = new Scanner(new File("input.txt"));
            String text = input.nextLine();
            String s = input.nextLine();
            int cases = Integer.parseInt(s);
            for(int i=0; i<cases; i++)
            {

            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
    }
}
