import java.lang.Math;

public class avl_bin
{
    node_bin root;

    public avl_bin(int id, int cap)
    {
        root = new node_bin(id, cap, 0);
    }

    public node_bin Search(int s)
    {
        // Time complexity is O(h);
        node_bin x = root;
        while (x != null)
        {
            if(x.get_id() == s)
            {
                break;
            }
            else if(x.get_id() > s)
            {
                x = x.left;
            }
            else
            {
                x = x.right;
            }
        }
        return x;
    }

    public void update_height(node_bin mid, node_bin l, node_bin r)
    {
        mid.height = 0;
        if(l != null && r != null)
        {
            if(l.height >= r.height)
            {
                mid.height = l.height + 1;
            }
            else
            {
                mid.height = r.height + 1;
            }
        }
        else if(!(l == null && r == null))
        {
            if(l == null)
            {
                mid.height = r.height + 1;
            }
            if(r == null)
            {
                mid.height = l.height + 1;
            }

        }

    }

    public void node_rotate(node_bin x)
    {
        node_bin par = x.parent;
        node_bin y,z,t1,t2,t3,t4;
        if(x.left != null && x.right != null)
        {
            if(x.left.height > x.right.height)
            {

                y = x.left;
                t1 = x.right;
            }
            else
            {
                y = x.right;
                t1 = x.left;
            }
        }
        else
        {
            if(x.left == null)
            {
                y = x.right;
                t1 = null;
            }
            else
            {
                y = x.left;
                t1 = null;
            }
        }

        if(y.left != null && y.right != null)
        {
            if(y.left.height > y.right.height)
            {
                z = y.left;
                t2 = y.right;
            }
            else
            {
                z = y.right;
                t2 = y.left;
            }
        }
        else
        {
            if(y.left == null)
            {
                z = y.right;
                t2 = null;
            }
            else
            {
                z = y.left;
                t2 = null;
            }
        }

        t3 = z.left;
        t4 = z.right;

        if(y == x.left)
        {
            if(z == y.left)
            {
                if(par.left == x)
                {
                    par.left = y;
                }
                else
                {
                    par.right = y;
                }
                y.parent = par;
                t2.parent = x;
                x.left = t2;
                x.parent = y;
                y.right = x;

                if(t1.height >= t2.height)
                {
                    x.height = t1.height + 1;
                }
                else
                {
                    x.height = t2.height + 1;
                }

                if(x.height >= z.height)
                {
                    y.height = x.height + 1;
                }
                else
                {
                    y.height = z.height + 1;
                }
            }
            else
            {
                if(x.parent != null)
                {
                    if(x.parent.left == x)
                    {
                        x.parent.left = z;
                    }
                    else
                    {
                        x.parent.right = z;
                    }
                }
                if(t3 != null)
                {
                    t3.parent = y;
                }

                if(t4 != null)
                {
                    t4.parent = x;
                }
                y.right = t3;
                x.left = t4;
                y.parent = z;
                z.left = y;
                z.right = x;

                update_height(y, t2, t3);
                update_height(x, t4, t1);
                update_height(z, x, y);
            }
        }
        else
        {
            if(z == x.right)
            {
                if(par.left == x)
                {
                    par.left = y;
                }
                else
                {
                    par.right = y;
                }
                y.parent = par;
                t2.parent = x;
                x.right = t2;
                x.parent = y;
                y.left = x;

                if(t1.height >= t2.height)
                {
                    x.height = t1.height + 1;
                }
                else
                {
                    x.height = t2.height + 1;
                }

                if(x.height >= z.height)
                {
                    y.height = x.height + 1;
                }
                else
                {
                    y.height = z.height + 1;
                }
            }
            else
            {
                if(par.left == x)
                {
                    par.left = z;
                }
                else
                {
                    par.right = z;
                }
                t4.parent = y;
                y.left = t4;
                x.right = t3;
                t3.parent = x;
                z.left = x;
                z.right = y;

                if(t4.height >= t2.height)
                {
                    y.height = t4.height + 1;
                }
                else
                {
                    y.height = t2.height + 1;
                }

                if(t3.height >= t1.height)
                {
                    x.height = t3.height + 1;
                }
                else
                {
                    x.height = t1.height + 1;
                }

                if(x.height >= y.height)
                {
                    z.height = x.height + 1;
                }
                else
                {
                    z.height = y.height + 1;
                }
            }
        }

    }

    public void AddNode(int id, int cap)
    {
        //For AVL Tree;
        // Time complexity is O(h);
        // New node is always added in place of leaf node;
        node_bin y, x = null;
        y = root;
        while (y != null)
        {
            x = y;
            if(y.id_b == id)
            {
                break;
            }
            if(id > y.id_b)
            {
                y = y.right;
            }
            else
            {
                y = y.left;
            }
        }

        if(x.id_b == id)
        {
            System.out.println("Number already exists.");
        }
        else
        {
            node_bin a = new node_bin(id, cap,0);
            a.parent = x;
            if(x.id_b < id)
            {
                x.right = a;
            }
            else
            {
                x.left = a;
            }

            if (x.left != null && x.right != null)
            {
                int max = x.left.height;
                if (x.right.height > x.left.height)
                {
                    max = x.right.height;
                }
                x.height = max + 1;
            }
            else
            {
                if(x.left == null)
                {
                    x.height = x.right.height + 1;
                }
                else
                {
                    x.height = x.left.height + 1;
                }
            }

            System.out.println(x.id_b + " " + x.height);
            while(x != root)
            {
                x = x.parent;

                if (x.left != null && x.right != null)
                {
                    int max = x.left.height;
                    if (x.right.height > x.left.height)
                    {
                        max = x.right.height;
                    }
                    x.height = max + 1;
                }
                else
                {
                    if(x.left == null)
                    {
                        x.height = x.right.height + 1;
                    }
                    else
                    {
                        x.height = x.left.height + 1;
                    }
                }

                System.out.println(x.id_b + " " + x.height);
            }

            node_bin rot = a;
            System.out.println(rot.id_b + " " + rot.height);
            int diff = 0;
            while(diff < 2 && rot != null) // Since height of the last node in any branch is 0 not 1;
            {
                rot = rot.parent;
                if(rot != null)
                {
                    if (rot.left != null && rot.right != null)
                    {
                        diff = Math.abs(rot.right.height - rot.left.height);
                    }
                    else
                    {
                        if(rot.left == null)
                        {
                            diff = rot.right.height + 1;
                        }
                        else
                        {
                            diff = rot.left.height + 1;
                        }
                    }
                }
                System.out.println(diff);
            }

            if(rot != null)
            {
                System.out.println(rot.id_b + " " + rot.height);
                node_rotate(rot);
            }
            System.out.println();
        }
    }

    public void DeleteNode(int s)
    {
        node_bin x = Search(s);
        if(x.left == null && x.right == null)
        {
            // If both children are null;
            if(x == x.parent.left)
            {
                x.parent.left = null;
            }
            else
            {
                x.parent.right = null;
            }
        }
        else if(x.left == null || x.right == null)
        {
            // If only one child is null;
            node_bin a;
            if(x.left == null)
            {
                a = x.right;
            }
            else
            {
                a = x.left;
            }
            if(x == x.parent.left)
            {
                x.parent.left = a;
            }
            else
            {
                x.parent.right = a;
            }
        }
        else
        {
            node_bin a = x.left;
            while (a != null)
            {
                a = a.right;
            }
            int val = a.id_b;
            DeleteNode(a.id_b);
            x.id_b = val;
        }
    }

    public void InorderTraversal(node_bin x)
    {
        if(x == null)
        {

        }
        else
        {
            InorderTraversal(x.left);
            System.out.println(x.id_b + " " + x.height);
            InorderTraversal(x.right);
        }
    }
}