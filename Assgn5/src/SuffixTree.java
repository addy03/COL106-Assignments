import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class SuffixTree
{
    public static List<Integer[]> get_ind(String inp, suffix tree)
    {
        List<node> node_list = new ArrayList<>();
        List<Integer> node_ind = new ArrayList<>();
        listNode a = tree.root.child.first;
        while(a != null)
        {
            node_list.add(a.elem);
            node_ind.add(0);
            a = a.next;
        }
        for(int j=0; j<inp.length(); j++)
        {
            char x = inp.charAt(j);
            if(x == '?')
            {
                int size = node_list.size();
                for(int n=0; n<size; n++)
                {
                    if(node_list.get(n).s.length() > node_ind.get(n))
                    {
                        node_ind.set(n, node_ind.get(n) + 1);
                    }
                    else
                    {
                        node rep = node_list.remove(n);
                        node_ind.remove(n);
                        n = n - 1;
                        size = size - 1;
                        listNode rep_child = rep.child.first;
                        while(rep_child != null)
                        {
                            node_list.add(rep_child.elem);
                            node_ind.add(1);
                            rep_child = rep_child.next;
                        }
                    }
                }
            }
            else
            {
                int size = node_list.size();
                for(int n=0; n<size; n++)
                {
//                            System.out.println("n = " + n + " "+ node_list.get(n).s + " " + node_ind.get(n));
                    if(node_list.get(n).s.length() > node_ind.get(n))
                    {
                        if(node_list.get(n).s.charAt(node_ind.get(n)) == x)
                        {
                            node_ind.set(n, node_ind.get(n) + 1);
                        }
                        else
                        {
                            node_list.remove(n);
                            node_ind.remove(n);
                            n = n-1;
                            size = size - 1;
                        }
                    }
                    else
                    {
                        node rep = node_list.remove(n);
                        node_ind.remove(n);
                        n = n-1;
                        size = size - 1;
                        listNode rep_child = rep.child.first;
                        int count = 0;
                        while(rep_child != null)
                        {
                            node_list.add(rep_child.elem);
                            node_ind.add(0);
                            count++;
                            rep_child = rep_child.next;
                        }
                        int new_ind = node_list.size();
                        for(int k = (new_ind-count); k<new_ind; k++)
                        {
                            if(node_list.get(k).s.charAt(node_ind.get(k)) == x)
                            {
                                node_ind.set(k, node_ind.get(k) + 1);
                            }
                            else
                            {
                                node_list.remove(k);
                                node_ind.remove(k);
                                new_ind = new_ind - 1;
                                k = k-1;
                            }
                        }
                    }

                }
            }
        }

        List<Integer[]> final_ind = new ArrayList<>();

        for(int t=0; t<node_list.size(); t++)
        {
            node x = node_list.get(t);
            for(int p=0; p<x.ind.size(); p++)
            {
                int val = x.ind.get(p) + node_ind.get(t) - 1;
                Integer[] l = {(val - inp.length() + 1),val};
                final_ind.add(l);
//                System.out.println((val - inp.length() + 1) + " " + val);
            }
        }
        return final_ind;
    }

    public static void main(String args[]) throws IOException
    {
        try
        {
            Scanner input = new Scanner(new File("inp_big.txt"));
            FileWriter file = new FileWriter("out.txt");
            BufferedWriter fileWriter = new BufferedWriter(file);

            String text = input.nextLine();
            suffix tree = new suffix();
            for(int i=0; i<text.length(); i++)
            {
                tree.AddElement(text.substring(i), i);
            }

            System.out.println();
            String s = input.nextLine();
            int cases = Integer.parseInt(s);
            for(int i=0; i<cases; i++)
            {
                String inp = input.nextLine();

                int ind = -1;
                for(int j=0; j<inp.length(); j++)
                {
                    if(inp.charAt(j) == '*')
                    {
                        ind = j;
                        break;
                    }
                }

                if(ind != -1)
                {
                    if(ind == 0 && inp.length() == 1)
                    {
                        for(int j=0; j<text.length(); j++)
                        {
                            for(int k=0; k<text.length(); k++)
                            {
                                fileWriter.write(j + " " + k);
                                System.out.println((j + " " + k));
                                fileWriter.newLine();
                            }
                        }
                    }
                    else if(ind == 0)
                    {
                        inp = inp.substring(1);
                        List<Integer[]> l = get_ind(inp, tree);
                        for(int j=0; j<l.size(); j++)
                        {
                            Integer[] m = l.get(j);
                            for(int k=0; k<=m[0]; k++)
                            {
                                fileWriter.write(k + " " + m[1]);
                                System.out.println(k + " " + m[1]);
                                fileWriter.newLine();
                            }
                        }
                    }
                    else if(ind == inp.length()-1)
                    {
                        inp = inp.substring(0,inp.length()-1);
                        List<Integer[]> l = get_ind(inp, tree);
                        for(int j=0; j<l.size(); j++)
                        {
                            Integer[] m = l.get(j);
                            for(int k=m[1]; k<text.length(); k++)
                            {
                                fileWriter.write(m[0] + " " + k);
                                System.out.println(m[0] + " " + k);
                                fileWriter.newLine();
                            }
                        }
                    }
                    else
                    {
                        String s1, s2;
                        s1 = inp.substring(0,ind);
                        s2 = inp.substring(ind+1);
                        List<Integer[]> l1 = get_ind(s1, tree);
                        List<Integer[]> l2 = get_ind(s2, tree);
                        for(int j=0; j<l1.size(); j++)
                        {
                            Integer[] m1 = l1.get(j);
                            for(int k=0; k<l2.size(); k++)
                            {
                                Integer[] m2 = l2.get(k);
                                if(m1[1] < m2[0])
                                {
                                    fileWriter.write(m1[0] + " " + m2[1]);
                                    System.out.println(m1[0] + " " + m2[1]);
                                    fileWriter.newLine();
                                }
                            }
                        }
                    }
                }
                else
                {
                    List<Integer[]> l = get_ind(inp, tree);
                    for(int j=0; j<l.size(); j++)
                    {
                        Integer[] m = l.get(j);
                        fileWriter.write(m[0] + " " + m[1]);
                        System.out.println(m[0] + " " + m[1]);
                        fileWriter.newLine();
                    }
                }
            }
        }
        catch(FileNotFoundException e)
        {
            System.out.println("File not found.");
        }
    }
}
