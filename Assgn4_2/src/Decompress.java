import java.util.Scanner;
import java.io.*;
import java.lang.*;

public class Decompress
{
    public static long decToBinary(int x)
    {
        long ans;
        if(x >= 0)
        {
            ans = 0;
        }
        else
        {
            ans = 1;
            x = x + 2^7;
        }
        int i=6;
        while(i != -1)
        {
            ans = ans*10;
            ans = ans + x/(2^i);
            x = x%(2^i);
            i = i-1;
        }

        return ans;
    }

    public static int hashCode(byte[] bit, MyList[] ht)
    {
        int g = 11;
        long hash1;
//        System.out.println(s);
        int b1 = (int)bit[0];
        int b2 = (int)bit[1];

        long b1_bin = decToBinary(b1);
        long b2_bin = decToBinary(b2);

        hash1 = b1_bin*(10^8) + b2_bin;

        hash1 = hash1%65543;
        int hash = (int)hash1;
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
                node a = ht[hash].first;
//                System.out.println(a.st + " " + s);
//                System.out.println("_____________________");
                int flag = 0;
                while(a != null)
                {
                    if(a.bit[0] == bit[0] && a.bit[1] == bit[1])
                    {
                        flag = 1;
                    }
                    a = a.next;
                }
                if(flag == 1)
                {
                    break;
                }
                else
                {
                    hash = (hash + x*x)%65543;
                    x = x+1;
                }
            }
        }
        while(hash != init);

        return Math.abs(hash);
    }

    static boolean get_stat(byte[] bit, MyList[] ht)
    {
        int hash = hashCode(bit, ht);
        node a = ht[hash].first;
        boolean x = false;
        while(a != null)
        {
            if(a.bit[0] == bit[0] && a.bit[1] == bit[0])
            {
                x = true;
                break;
            }
            a = a.next;
        }

        return x;
    }

    static String getString(byte[] ch, MyList[] ht)
    {
        int hash = hashCode(ch, ht);
        node iter2 = ht[hash].first;
        do
        {
            if(iter2.bit[0] == ch[0] && iter2.bit[1] == ch[1])
            {
                break;
            }
            iter2 = iter2.next;
        }
        while(iter2.next != null);

        return iter2.st;
    }

    public static void main(String args[]) throws IOException
    {
        try
        {
            Scanner input = new Scanner(new File("output_input.txt"));
            FileWriter os = new FileWriter("decode.txt");
            MyList[] ht = new MyList[65544];
            for(int i=0; i<65543; i++)
            {
                ht[i] = new MyList();
            }
            for(int i=0; i<128; i++)
            {
                char s1 = (char)i;
                String s2 = Character.toString(s1);
                byte[] code = new byte[2];
                code[0] = 0;
                code[1] = (byte)i;
                int hash = hashCode(code, ht);
                System.out.println(s1);
                ht[hash].AddNode(s2,code);
            }

            int b1 = -128;
            int b2 = 0;

            byte[] ch = new byte[2];
            ch[0] = input.nextByte();
            ch[1] = input.nextByte();
            String w = getString(ch, ht);
            os.write(w);

            while(input.hasNext())
            {
                byte[] ch1 = new byte[2];
                ch1[0] = input.nextByte();
                ch1[1] = input.nextByte();
                boolean stat = get_stat(ch1, ht);

                if(stat)
                {
                    String x = getString(ch1, ht);
                    String a = w + x;
                }


            }

        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
    }
}
