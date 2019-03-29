import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class home
{
    public static void main(String args[])
    {
        BestFit new_fit = new BestFit();
        try
        {
            Scanner input = new Scanner(new File(args[0]));
            String answer;

            while(input.hasNext())
            {
                answer = input.nextLine();
                String[] splited = answer.split(" ");
                int a = Integer.parseInt(splited[0]);

                if (a == 1)
                {
                    new_fit.add_bin(Integer.parseInt(splited[1]), Integer.parseInt(splited[2]));
                } else if (a == 2)
                {
                    int x = new_fit.add_object(Integer.parseInt(splited[1]), Integer.parseInt(splited[2]));
                    System.out.println(x);
                } else if (a == 3)
                {
                    int x = new_fit.delete_object(Integer.parseInt(splited[1]));
                    System.out.println(x);

                } else if (a == 4)
                {
                    List<Pair<Integer, Integer>> x = new_fit.contents(Integer.parseInt(splited[1]));
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Program Error.");
        }

    }
}
