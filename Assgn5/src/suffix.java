import java.lang.*;

public class suffix
{
    node root;

    public suffix()
    {
        root = new node(" ");
    }

    public void AddElement(String st)
    {
        if(root.child.first == null)
        {
            node x = new node(st);
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
                    System.out.println(a.elem.s + " " + a.elem.s.length());
                    String s1 = Character.toString(a.elem.s.charAt(j));
                    String s2 = Character.toString(st.charAt(0));
//                    System.out.println(s1.equals(s2));
                    while(s1.equals(s2))
                    {
                        st = st.substring(1);
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
                else if(a.elem.s.length() == pos+1 && st.length() != 0)
                {
                    f = a.elem;
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
                node x = new node(st);
                x.parent = f;
                f.child.AddNode(x);
            }
            else if(flag == 3 || flag == 1)
            {
                node x;
                if(st.length() > 0)
                {
                    x = new node(st);
                }
                else
                {
                    x = new node( "#");
                }
                System.out.println(f.s.length() + " " + f.s);
                node x2;
                if(f.s.substring(pos).length() > 0)
                {
                    x2 = new node(f.s.substring(pos));
                    if(f.child.first != null)
                    {
                        listNode m = f.child.first;
                        while(m != null)
                        {
                            x2.child.AddNode(m.elem);
                            m = m.next;
                        }
                    }
                }
                else
                {
                    x2 = null;
                }

                f.child.first = null;
                if(x2 != null)
                {
                    f.child.AddNode(x2);
                    x2.parent = f;
                }
                f.s = f.s.substring(0,pos);
                f.child.AddNode(x);
                x.parent = f;
            }
        }
//        System.out.println("===================");
//        print_elem(root);
//        System.out.println("===================");
    }

    public void print_elem(node a)
    {
        listNode x = a.child.first;
        System.out.println(a.s);
        while(x != null)
        {
            System.out.println(x.elem.s);
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
