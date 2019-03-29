import java.util.Scanner;
import java.io.*;

public class home
{
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
                    byte[] code = new byte[2];
                    code[0] = 0; // 0 is the signed 8-bit value of 00000000
                    code[1] = (byte)i; // -128 is the signed 8-bit value of 1000000
                    ht.put((char)i, code);
//                    System.out.println((char)65);
                }
                String s;
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
