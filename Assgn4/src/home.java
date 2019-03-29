import java.util.Scanner;
import java.io.*;

public class home
{
    public static void main(String args[])
    {
        try
        {
            Scanner input = new Scanner(new File("inp_small.txt"));
            String s = input.nextLine();
            try
            {
                FileWriter os = new FileWriter("out.txt", true);
                os.write(s);
            }
            catch(IOException e)
            {
                System.out.println("Output Error.");
            }

        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
    }
}
