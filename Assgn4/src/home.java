import java.util.Scanner;
import java.io.*;

public class home
{
    static int hashCode(String s, MyList[] ht)
    {
        int g = 31;
        int hash = 0;
//        System.out.println(s);
        for(int i=0; i<s.length(); i++)
        {
            hash = g*hash + s.charAt(i);
//            System.out.println(s.charAt(i));
        }
//        System.out.println(hash);
        hash = hash%3001;
        System.out.println(hash);
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
                System.out.println(a.st + " " + s);
                System.out.println("_____________________");
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
                    hash = (hash + x*x)%3001;
                    x = x+1;
                }
            }
        }
        while(hash != init);

        return hash;
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
            Scanner input = new Scanner(new File("inp_big.txt"));
            try
            {
                OutputStream os = new FileOutputStream("out.txt");
//                os.write(code);
                MyList[] ht = new MyList[3002];
                for(int i=0; i<3002; i++)
                {
                    ht[i] = new MyList();
                }

                for(int i=0; i<128; i++)
                {
                    char s1 = (char)i;
                    System.out.println(s1);
                    String s = Character.toString(s1);
                    int hash = hashCode(s, ht);
                    byte[] code = new byte[2];
                    code[0] = 0;
                    code[1] = (byte)i;
                    ht[hash].AddNode(s,code);
                }

//                for(int i=33; i<128; i++)
//                {
//                    System.out.println(i);
//                    ht[i].print_list();
//                }

                int b1 = -128;
                int b2 = 0;
//                node up = null;

                while(input.hasNext())
                {
                    String s = input.nextLine();
                    if(s != null)
                    {
                        int i=1;
//                    while(Character.toString(s.charAt(i)) != " ")
//                    {
//                        i = i + 1;
//                    }
//                    String w;
//                    if(Character.toString(s.charAt(i-1)) != " ")
//                    {
//                        w = Character.toString(s.charAt(i-1));
//                    }
//                    else
//                    {
//                        w = Character.toString(s.charAt(i));
//                        i = i+1;
//                    }
                        String w = Character.toString(s.charAt(i-1));
                        System.out.println(w);
                        while(i<s.length())
                        {
                            if(Character.toString(s.charAt(i)) == " ")
                            {
                                i = i+1;
                            }
                            else
                            {
                                String x = Character.toString(s.charAt(i));
                                String a = w + x;
                                System.out.println(a);
                                int h = hashCode(a, ht);
                                boolean stat = get_stat(a, h, ht);

                                if(stat)
                                {
                                    w = a;
                                    i = i+1;
                                }
                                else
                                {
                                    System.out.println(w);
                                    int h2 = hashCode(w, ht);
                                    System.out.println(h2);
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

                                    System.out.println(iter2);
                                    os.write(iter2.bit);
                                    byte[] code = new byte[2];
                                    code[0] = (byte)b2;
                                    code[1] = (byte)b1;
                                    if(b1 == 127)
                                    {
                                        b1 = -128;
                                    }
                                    else
                                    {
                                        b1 = b1 + 1;
                                    }
                                    if(b1 == -1)
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
                                    ht[h].AddNode(a, code);
                                    w = x;
                                    i = i+1;
                                }
                            }
                        }
                        int h2 = hashCode(w, ht);
                        System.out.println(h2);
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

                        System.out.println(iter);
                        os.write(iter.bit);
                    }
                    else
                    {
                        byte[] code1 = new byte[2];
                        code1[0] = 0;
                        code1[1] = 0;
                        os.write(code1);
                    }
                }
//                String j = " ";
//                System.out.println((int)j);
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