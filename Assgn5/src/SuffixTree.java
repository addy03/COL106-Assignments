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
                                n = n-1;
                                size = size - 1;
                                int new_ind = node_list.size();
                                listNode rep_child = rep.child.first;
                                while(rep_child != null)
                                {
                                    node_list.add(rep_child.elem);
                                    node_ind.add(0);
                                    rep_child = rep_child.next;
                                }
                                for(int k=0; k<new_ind; k++)
                                {
                                    if(node_list.get(n).s.charAt(node_ind.get(n)) == x)
                                    {
                                        node_ind.set(n, node_ind.get(n) + 1);
                                    }
                                    else
                                    {
                                        node_list.remove(n);
                                        node_ind.remove(n);
                                    }
                                }
                            }

                        }
                    }
                }

                for(int t=0; t<node_list.size(); t++)
                {
                    node x = node_list.get(t);
                    for(int p=0; p<x.ind.size(); p++)
                    {
                        System.out.println(x.ind.get(p) - inp.length() + " " + x.ind.get(p));
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
