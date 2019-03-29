import java.util.Scanner;
import java.io.*;

public class home
{
    static int hashCode(String s, MyList[] ht)
    {
        int g = 31;
        int hash = 0;
        for(int i=0; i<s.length(); i++)
        {
            hash = g*hash + s.charAt(i);
        }
        hash = hash%3001;
        int init = hash;
        int x = 1;
        do
        {
            if(ht[hash].first == null)
            {
                break;
            }
            else
            {
                hash = hash + x*x;
                x = x+1;
            }
        }
        while(hash != init);

        return hash;
    }

    public static void main(String args[])
    {
        try
        {
            Scanner input = new Scanner(new File("inp_small.txt"));
            try
            {
                OutputStream os = new FileOutputStream("out.txt");
//                os.write(code);
                MyList[] ht = new MyList[3001];
                for(int i=0; i<128; i++)
                {
                    char s1 = (char)i;
                    String s = Character.toString(s1);
                    int hash = hashCode(s, ht);
                    byte[] code = new byte[2];
                    code[0] = 0;
                    code[1] = (byte)i;
                    ht[hash].AddNode(s,code);
                }
                
                while(input.hasNext())
                {
                    s = input.nextLine();
                    if(ht.get(s.substring(0,0)) != null)
                    {

                    }
                    for(int i=1; i<s.length(); i++)
                    {
                        String x = s.substring(i,i);

                    }
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
