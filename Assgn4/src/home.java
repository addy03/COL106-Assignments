import java.util.Scanner;
import java.io.*;

public class home
{
    public static void main(String args[])
    {
        try
        {
            Scanner input = new Scanner(new File("inp.txt"));
            try
            {
                FileWriter os = new FileWriter("out.txt");
                String s;
                while(input.hasNext())
                {
                    s = input.nextLine();
                    os.write(s);
                    os.write("\r\n");
                }
                os.close();
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
