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

    public void node_rotate(node_bin x, int id)
    {
        node_bin par = x.parent;
        node_bin y,z,t1,t2,t3,t4;
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

        t3 = z.left;
        t4 = z.right;

        if(y == x.left)
        {
            if(z == y.left)
            {
                t2.parent = x;
                x.left = t2;

            }
        }
        else
        {
            if(z == x.right)
            {

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

            x.height += 1;
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
            }
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
            System.out.println(x.id_b + "  " + x.height);
            InorderTraversal(x.right);
        }
    }
}
