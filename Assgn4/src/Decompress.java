import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class Decompress
{
    public long decToBinary(int x)
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
            i = i+1;
        }

        return ans;
    }

    public int hashCode(byte[] bit, MyList[] ht)
    {
        int g = 11;
        long hash1 = 0;
//        System.out.println(s);
        int a = (int)bit[0];
        int b = (int)bit[1];
        for(int i=0; i<s.length(); i++)
        {
            hash1 = g*hash1 + s.charAt(i);
//            System.out.println(s.charAt(i));
        }
//        System.out.println(hash);
        long a1 = 0;
        while(a != 0)
        {

        }
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
                    if(a.st.equals(s))
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

    static boolean get_stat(String s, int hash, MyList[] ht)
    {
        node a = ht[hash].first;
        boolean x = false;
        while(a != null)
        {
            if(a.st.equals(s))
            {
                x = true;
                break;
            }
            a = a.next;
        }

        return x;
    }

    public Decompress(String s)
    {
        try
        {
            OutputStream os = new FileOutputStream("out.txt");
            MyList[] ht = new MyList[65543];
            for(int i=0; i<65543; i++)
            {
                ht[i] = new MyList();
            }

            for(int i=0; i<128; i++)
            {
                char s1 = (char)i;
                String s2 = Character.toString(s1);
                int hash = hashCode(s2, ht);
                byte[] code = new byte[2];
                code[0] = 0;
                code[1] = (byte)i;
                System.out.println(s1);
                ht[hash].AddNode(s2,code);
            }

            int b1 = -128;
            int b2 = 0;




        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
    }
}
