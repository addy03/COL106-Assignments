import java.util.Scanner;
import java.io.*;
import java.lang.*;

public class Compress
{
    static int hashCode(String s, MyList[] ht)
    {
        int g = 11;
        long hash1 = 0;
//        System.out.println(s);
        for(int i=0; i<s.length(); i++)
        {
            hash1 = g*hash1 + s.charAt(i);
//            System.out.println(s.charAt(i));
        }
//        System.out.println(hash);
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

    public static void main(String args[])
    {
        try
        {
            String s = new Scanner(new File(args[0]))
                    .useDelimiter("\\A").next();
            try
            {
                OutputStream os = new FileOutputStream(new File(args[1]));
//                os.write(code);
                MyList[] ht = new MyList[65544];
                for(int i=0; i<65544; i++)
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
//                    System.out.println(s1);
                    ht[hash].AddNode(s2,code);
                }

                int b1 = -128;
                int b2 = 0;

                int i=1;
                String w = Character.toString(s.charAt(i-1));
//                        System.out.println(w);
                while(i<s.length())
                {
                    String x = Character.toString(s.charAt(i));
                    String a = w + x;
//                                System.out.println(a);
                    int h = hashCode(a, ht);
                    boolean stat = get_stat(a, h, ht);

                    if(stat)
                    {
                        w = a;
                        i = i+1;
                    }
                    else
                    {
                        int h2 = hashCode(w, ht);
                        node iter2 = ht[h2].first;
                        do
                        {
                            if(iter2.st.equals(w))
                            {
                                break;
                            }
                            iter2 = iter2.next;
                        }
                        while(iter2.next != null);

//                                    System.out.println(iter2);
                        os.write(iter2.bit);
                        byte[] code = new byte[2];
                        code[0] = (byte)b2;
                        code[1] = (byte)b1;

//                        System.out.println(a + " " + b1 + " " + b2);
                        ht[h].AddNode(a, code);
                        if(b1 == 127)
                        {
                            b1 = -128;
                        }
                        else
                        {
                            b1 = b1 + 1;
                        }
                        if(b1 == 0)
                        {
                            if(b2 == 127)
                            {
                                b2 = -128;
                            }
                            else
                            {
                                b2 = b2 + 1;
                            }
                        }
                        w = x;
                        i = i+1;
                    }
                }
                int h2 = hashCode(w, ht);
//                        System.out.println(h2);
                node iter = ht[h2].first;
                do
                {
                    if(iter.st.equals(w))
                    {
                        break;
                    }
                    iter = iter.next;
                }
                while(iter.next != null);

//                        System.out.println(iter);
                os.write(iter.bit);
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
