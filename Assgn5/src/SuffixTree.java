import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class SuffixTree
{
    public static void main(String args[])
    {
        try
        {
            Scanner input = new Scanner(new File("input.txt"));
            String text = input.nextLine();
            suffix tree = new suffix();
            for(int i=0; i<text.length(); i++)
            {
                System.out.println("____________________");
                System.out.println("String added: " + text.substring(i));
                tree.AddElement(text.substring(i));
            }
//            tree.print_elem(tree.root);
//            String s = input.nextLine();
//            int cases = Integer.parseInt(s);
//            for(int i=0; i<cases; i++)
//            {
//                String inp = input.nextLine();
//            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
    }
}
