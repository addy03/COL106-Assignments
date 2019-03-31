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
            x = x + (int)Math.pow(2,7);
        }
        int i=6;
        while(i != -1)
        {
            ans = ans*10;
            ans = ans + x/((long)(Math.pow(2,i)));
            x = x%((int)Math.pow(2,i));
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
//        System.out.println(b1 + " _____ " + b2);

        long b1_bin = decToBinary(b1);
        long b2_bin = decToBinary(b2);
//        System.out.println(b1_bin + " _____________________________ " + b2_bin);
        hash1 = b1_bin*((long)Math.pow(10,8)) + b2_bin;
//        System.out.println(hash1);

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
//        System.out.println(hash);
        node a = ht[hash].first;
        boolean x = false;
        while(a != null)
        {
            if (a.bit[0] == bit[0] && a.bit[1] == bit[1])
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
//        System.out.println(hash);
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
            RandomAccessFile f = new RandomAccessFile(new File(args[0]), "r");
            byte[] b = new byte[(int)f.length()];
            f.readFully(b);
            FileOutputStream outputStream = new FileOutputStream(new File(args[1]));
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-16");
            BufferedWriter os = new BufferedWriter(outputStreamWriter);
            MyList[] ht = new MyList[65544];
            for(int i=0; i<65544; i++)
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
//                System.out.println(s1 + " " + hash);
                ht[hash].AddNode(s2,code);
            }

            int i1 = 0;
            int b1 = -128;
            int b2 = 0;
//            System.out.println(f.length() + "___________________________");
            byte[] ch = new byte[2];
            ch[0] = b[i1];
            ch[1] = b[i1+1];
//            System.out.println((int)ch[0] + " " + (int)ch[1]);
            String w = getString(ch, ht);
//            System.out.println(w);
            os.write(w);

            while((i1+2) < f.length())
            {
//                System.out.println(i1);
                byte[] ch1 = new byte[2];
                i1 = i1 + 2;
                ch1[0] = b[i1];
                ch1[1] = b[i1+1];
//                System.out.println((int)ch1[0] + " " + (int)ch1[1]);
                boolean stat = get_stat(ch1, ht);
//                System.out.println(stat);

                if(stat)
                {
                    String x = getString(ch1, ht);
                    os.write(x);
//                    System.out.println(x);
                    String a = w + x.charAt(0);

                    byte[] code_new = new byte[2];
                    code_new[0] = (byte)b2;
                    code_new[1] = (byte)b1;
                    int hash_new = hashCode(code_new, ht);
                    ht[hash_new].AddNode(a, code_new);
//                    System.out.println(a);
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
                }
                else
                {
                    String x = w + w.charAt(0);
                    os.write(x);
//                    System.out.println(x);

                    byte[] code_new = new byte[2];
                    code_new[0] = (byte)b2;
                    code_new[1] = (byte)b1;
                    int hash_new = hashCode(code_new, ht);
                    ht[hash_new].AddNode(x, code_new);
//                    System.out.println(x);
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
                }
//                System.out.println();
            }
            os.close();
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
    }
}
