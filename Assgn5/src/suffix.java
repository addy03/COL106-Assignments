import java.lang.*;

public class suffix
{
    node root;

    public suffix()
    {
        root = new node(" ");
    }

    public void AddElement(String s)
    {
        if(root.child.first == null)
        {
            node x = new node(s);
            x.parent = root;
            root.child.AddNode(x);
        }
        else
        {
            node f = root;
            int pos = 0;
            while(f != null)
            {
                listNode a = f.child.first;
                while(a != null)
                {
                    int j = 0;

                    String s1 = Character.toString(a.elem.s.charAt(j));
                    String s2 = Character.toString(s.charAt(0));
//                    System.out.println(s1.equals(s2));
                    while(s1.equals(s2))
                    {
                        s = s.substring(1);
                        j += 1;
                        if(s.length()>0 && a.elem.s.length()>j)
                        {
                            s1 = Character.toString(a.elem.s.charAt(j));
                            s2 = Character.toString(s.charAt(0));
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
                    break;
                }
                else if(s.length() == 0)
                {
                    f = a.elem;
                    break;
                }
                else
                {
                    f = a.elem;
                }
            }
//            System.out.println(f.s + "   !!!!!!!!!!!!!!!!!!!!!!!");
            if(pos > 0)
            {
                node x;
                if(s.length() > 0)
                {
                    x = new node(s);
                }
                else
                {
                    x = new node("#");
                }
                node x2 = new node(f.s.substring(pos));
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
                f.s = f.s.substring(0,pos);
                f.child.AddNode(x);
                x.parent = f;
                x2.parent = f;
            }
            else
            {
                node x = new node(s);
                f.child.AddNode(x);
                x.parent = f;
            }
        }
        System.out.println("===================");
        print_elem(root);
        System.out.println("===================");
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
