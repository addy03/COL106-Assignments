import java.lang.*;

public class suffix
{
    node root;

    public suffix()
    {
        root = new node(" ", -1);
    }

    public void AddElement(String st, int index)
    {
        if(root.child.first == null)
        {
            node x = new node(st, index);
            x.parent = root;
            root.child.AddNode(x);
        }
        else
        {
            node f = root;
            int pos = 0;
            int flag = 0;
            // flag = 1 : Add #
            // flag = 2 : No string match
            while(flag == 0)
            {
                listNode a = f.child.first;
                while(a != null)
                {
                    int j = 0;
//                    System.out.println(a.elem.s + " " + a.elem.s.length());
                    String s1 = Character.toString(a.elem.s.charAt(j));
                    String s2 = Character.toString(st.charAt(0));
//                    System.out.println(s1.equals(s2));
                    while(s1.equals(s2))
                    {
                        st = st.substring(1);
                        index += 1;
                        j += 1;
                        if(st.length()>0 && a.elem.s.length()>j)
                        {
                            s1 = Character.toString(a.elem.s.charAt(j));
                            s2 = Character.toString(st.charAt(0));
                        }
                        else
                        {
                            break;
                        }
                    }
                    pos = j;

                    if(j > 0)
                    {
                        break;
                    }
                    else
                    {
                        a = a.next;
                    }
                }

                if(a == null)
                {
                    flag = 2;
                }
                else if(st.length() == 0)
                {
                    f = a.elem;
                    flag = 1;
                }
                else if(a.elem.s.length() == pos+1)
                {
                    f = a.elem;
                    f.ind.add(index);
                    flag = 0;
                }
                else
                {
                    f = a.elem;
                    flag = 3;
                }
            }
            System.out.println(flag + " " + pos);
//            System.out.println(f.s + "   !!!!!!!!!!!!!!!!!!!!!!!");
            if(flag == 2)
            {
                node x = new node(st, index);
                x.parent = f;
                f.child.AddNode(x);
            }
            else if(flag == 3 || flag == 1)
            {
                node x;
                if(st.length() > 0)
                {
                    x = new node(st, index);
                }
                else
                {
                    x = new node( "#", index);
                }
                System.out.println(f.s.length() + " " + f.s);
                node x2;
                if(f.s.substring(pos).length() > 0)
                {
                    x2 = new node(f.s.substring(pos), f.ind.get(0)+pos);
                    for(int i=1; i<f.ind.size(); i++)
                    {
                        x2.ind.add(f.ind.get(i) + pos);
                    }
                    if(f.child.first != null)
                    {
                        listNode m = f.child.first;
                        while(m != null)
                        {
                            x2.child.AddNode(m.elem);
                            m = m.next;
                        }
                    }
                    f.child.first = null;
                    f.child.AddNode(x2);
                    x2.parent = f;
                }

                f.s = f.s.substring(0,pos);
                f.child.AddNode(x);
                f.ind.add(index - f.s.length());
                x.parent = f;
            }
        }
        System.out.println("===================");
        print_elem(root);
        System.out.println("===================");
    }

//    public void get_all_ind(node a, String s)
//    {
//        listNode x = a.child.first;
//        while(x != null)
//        {
//            if(s.charAt(0) == '?')
//            {
//                while(x != null)
//                {
//                    get_all_ind(x.elem, s.substring(1));
//                    x = x.next;
//                }
//            }
//            else
//            {
//                if(s.charAt(0) == x.elem.s.charAt(0))
//                {
//
//                }
//            }
//            x = x.next;
//        }
//        System.out.println();
//        System.out.println("--------");
//
//        x = a.child.first;
//        while(x != null)
//        {
//            print_elem(x.elem);
//            x = x.next;
//        }
//    }

    public void print_elem(node a)
    {
        listNode x = a.child.first;
        System.out.println(a.s);
        while(x != null)
        {
            System.out.print(x.elem.s + " ");
            for(int i=0; i<x.elem.ind.size(); i++)
            {
                System.out.print(x.elem.ind.get(i) + " ");
            }
            System.out.println();
            x = x.next;
        }
        System.out.println();
        System.out.println("--------");

        x = a.child.first;
        while(x != null)
        {
            print_elem(x.elem);
            x = x.next;
        }
    }
}
