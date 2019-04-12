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
            System.out.println(f.s);
            int pos = 0;
            while(f.child.first != null)
            {
                listNode a = f.child.first;
                System.out.println(s.charAt(0));
                System.out.println(a.elem.s.charAt(0));
                while(a != null)
                {
                    int j = 0;

                    String s1 = Character.toString(a.elem.s.charAt(j));
                    String s2 = Character.toString(s.charAt(0));
                    System.out.println(s1.equals(s2));
                    while(s1.equals(s2))
                    {
                        s = s.substring(1);
                        j += 1;
                        s1 = Character.toString(a.elem.s.charAt(j));
                        s2 = Character.toString(s.charAt(0));
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
                else
                {
                    f = a.elem;
                }
            }
            if(pos > 0)
            {
                node x = new node(s);
                node x2 = new node(f.s.substring(pos));
                f.s = f.s.substring(0,pos);
                f.child.AddNode(x);
                f.child.AddNode(x2);
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
        System.out.println();
    }

    public void print_elem(node a)
    {
        listNode x = a.child.first;
        System.out.println(a.s);
        while(x != null)
        {
            System.out.print(x.elem.s + " ");
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
