import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class SuffixTree
{

    public static void main(String args[])
    {
        try
        {
            Scanner input = new Scanner(new File("input.txt"));
            String text = input.nextLine();
            suffix tree = new suffix();
            for(int i=0; i<text.length(); i++)
            {
                System.out.println("____________________");
                System.out.println("String added: " + text.substring(i));
                tree.AddElement(text.substring(i), i);
            }
//                    System.out.println("===================");
//                    tree.print_elem(tree.root);
//                    System.out.println("===================");
//            tree.print_elem(tree.root);
//

            String s = input.nextLine();
            int cases = Integer.parseInt(s);
            for(int i=0; i<cases; i++)
            {
                String inp = input.nextLine();
//                List<Integer> ind1 = new ArrayList<>();
//                List<Integer> ind2 = new ArrayList<>();
//                int ind1_index =0;
//                int ind2_index=0;
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
//                    System.out.println("Character: " + x);
//                    System.out.println("Initial List:");
//                    for(int n=0; n < node_list.size(); n++)
//                    {
//                        System.out.println(node_list.get(n).s + " " + node_ind.get(n));
//                    }
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
                                n = n - 1;
                                size = size - 1;
                                listNode rep_child = rep.child.first;
                                while(rep_child != null)
                                {
                                    node_list.add(rep_child.elem);
                                    node_ind.add(0);
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
                                while(rep_child != null)
                                {
                                    node_list.add(rep_child.elem);
                                    node_ind.add(0);
                                    rep_child = rep_child.next;
                                }
                                int new_ind = node_list.size();
//                                System.out.println("List:");
//                                for(int b=0; b < node_list.size(); b++)
//                                {
//                                    System.out.println(node_list.get(b).s + " " + node_ind.get(b));
//                                }
//                                System.out.println(n);
//                                System.out.println(new_ind);
                                for(int k=n+1; k<new_ind; k++)
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
//                    System.out.println("Final List:");
//                    for(int n=0; n < node_list.size(); n++)
//                    {
//                        System.out.println(node_list.get(n).s + " " + node_ind.get(n));
//                    }
//                    System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                }

                for(int t=0; t<node_list.size(); t++)
                {
                    node x = node_list.get(t);
                    for(int p=0; p<x.ind.size(); p++)
                    {
                        int val = x.ind.get(p) + node_ind.get(t) - 1;
                        System.out.println((val - inp.length() + 1) + " " + val);
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
