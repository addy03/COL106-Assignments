public class avl_rem_bin2
{
    node_bin2 root;

    public avl_rem_bin2(int id, int cap)
    {
        root = new node_bin2(cap, id);
    }

    public node_bin2 Search(int s)
    {
        // Time complexity is O(h);
        node_bin2 x = root;
        while (x != null)
        {
            if(x.rem_capacity == s)
            {
                break;
            }
            else if(x.rem_capacity > s)
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

    // Function to update the height of any node.
    public void update_height(node_bin2 mid, node_bin2 l, node_bin2 r)
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

    // Function to rebalance the AVL tree.
    public void node_rotate(node_bin2 x)
    {
        node_bin2 par = x.parent;
        node_bin2 y,z,t1,t2,t3,t4;
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
                //Defining for the parent
                if (par != null)
                {
                    if(par.left == x)
                    {
                        par.left = y;
                    }
                    else
                    {
                        par.right = y;
                    }
                }
                else
                {
                    root = y;
                }

                //Updating for y
                y.parent = par;
                y.right = x;
                y.left = z;

                //Updating for z.
                z.parent = y; // For z left and right are already correct.

                //Updating for x.
                x.left = t2; // For x right is already correct.
                x.parent = y;

                if(t2 != null)
                {
                    t2.parent = x;
                }

                update_height(x, t1, t2);
                update_height(y, x, z);
            }
            else
            {
                //Defining for the parent
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
                else
                {
                    root = z;
                }

                // Updating for z.
                z.left = y;
                z.right = x;
                z.parent = par;

                // Updating for x.
                x.left = t4;
                x.parent = z;

                //Updating for y.
                y.right = t3;
                y.parent = z;

                if(t3 != null)
                {
                    t3.parent = y;
                }

                if(t4 != null)
                {
                    t4.parent = x;
                }

                update_height(y, t2, t3);
                update_height(x, t4, t1);
                update_height(z, x, y);
            }
        }
        else
        {
            // Updating for parent
            if(z == y.right)
            {
                if (par != null)
                {
                    if(par.left == x)
                    {
                        par.left = y;
                    }
                    else
                    {
                        par.right = y;
                    }
                }
                else
                {
                    root = y;
                }

                // Updating for y.
                y.parent = par;
                y.left = x;
                y.right = z;

                // Updating for x.
                x.right = t2;
                x.parent = y;

                // Updating for z.
                z.parent = y;

                if(t2 != null)
                {
                    t2.parent = x;
                }

                update_height(x, t1, t2);
                update_height(y, z, x);

            }
            else
            {
                // Updating for parent
                if (par != null)
                {
                    if(par.left == x)
                    {
                        par.left = z;
                    }
                    else
                    {
                        par.right = z;
                    }
                }
                else
                {
                    root = z;
                }

                // Updating for z.
                z.left = x;
                z.right = y;
                z.parent = par;

                // Updating for x.
                x.right = t3;
                x.parent = z;

                // Updating for y.
                y.left = t4;
                y.parent = z;

                if(t4 != null)
                {
                    t4.parent = y;
                }

                if(t3 != null)
                {
                    t3.parent = x;
                }

                update_height(y, t4, t2);
                update_height(x, t3, t1);
                update_height(z, x, y);
            }
        }

        if (par != null)
        {
            while(par != null)
            {
                update_height(par, par.left, par.right);
                par = par.parent;
            }
        }

    }

    // Function to add a new bin node to the AVL tree.
    public void AddNode(int id, int cap)
    {
        node_bin2 y, x = null;
        y = root;
        if(root != null)
        {
            while (y != null)
            {
                x = y;
                if(y.rem_capacity == cap)
                {
                    break;
                }
                if(cap > y.rem_capacity)
                {
                    y = y.right;
                }
                else
                {
                    y = y.left;
                }
            }

            if(x.rem_capacity == cap)
            {
                x.id_bin.add(x.id_bin.size(), id);
            }
            else
            {
                node_bin2 a = new node_bin2(cap, id);
                a.parent = x;
                if(x.rem_capacity < cap)
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

                node_bin2 rot = a;
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
                            if(rot.left == null && rot.right != null)
                            {
                                diff = rot.right.height + 1;
                            }
                            else if(rot.left != null && rot.right == null)
                            {
                                diff = rot.left.height + 1;
                            }
                        }
                    }
                }

                if(rot != null)
                {
                    node_rotate(rot);
                }
            }
        }
        else
        {
            root = new node_bin2(cap, id);
        }
    }

    public node_bin2 DeleteNode(int cap)
    {
        node_bin2 x = Search(cap);

        if(!(x.left != null && x.right != null))
        {
            if(x.left == null && x.right == null)
            {
                // If both children are null;
                if(x != root)
                {
                    if(x == x.parent.left)
                    {
                        x.parent.left = null;
                    }
                    else
                    {
                        x.parent.right = null;
                    }
                }
                else
                {
                    root = null;
                }
            }
            else
            {
                // If only one child is null;
                node_bin2 a;
                if(x.left == null)
                {
                    a = x.right;
                }
                else
                {
                    a = x.left;
                }
                a.parent = x.parent;

                if(x != root)
                {
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
                    root = a;
                }
            }
            node_bin2 rot = x;
            while(rot != null)
            {
                rot = rot.parent;

                int diff = 0;
                if(rot != null)
                {
                    update_height(rot, rot.left, rot.right);
                    if (rot.left != null && rot.right != null)
                    {
                        diff = Math.abs(rot.right.height - rot.left.height);
                    }
                    else
                    {
                        if(rot.left == null && rot.right != null)
                        {
                            diff = rot.right.height + 1;
                        }
                        else if(rot.left != null && rot.right == null)
                        {
                            diff = rot.left.height + 1;
                        }
                    }
                }
                if(diff > 1)
                {
                    node_rotate(rot);
                }
            }
        }
        else
        {
            node_bin2 a = x.left;
            while (a.right != null)
            {
                a = a.right;
            }
            a = DeleteNode(a.rem_capacity);

            // Updating a.
            a.left = x.left;
            a.right = x.right;
            a.parent = x.parent;
            a.height = x.height;

            if (x.left != null)
            {
                x.left.parent = a;
            }

            if(x.right != null)
            {
                x.right.parent = a;
            }

            if(x.parent != null)
            {
                if(x.parent.left == x)
                {
                    x.parent.left = a;
                }
                else
                {
                    x.parent.right = a;
                }
            }else
            {
                root = a;
            }
        }

        return x;
    }

    public void InorderTraversal(node_bin2 x)
    {
        if(x == null)
        {

        }
        else
        {
            InorderTraversal(x.left);
            System.out.print(x.rem_capacity + " " + x + " " + x.height + " " + x.id_bin.size() + "  ");
            if(x.parent != null)
            {
                System.out.print(" " + x.parent.rem_capacity + " " + x.parent);
            }
            else
            {
                System.out.print("  " + " " + x.parent + "_____________");
            }
            if(x.left != null)
            {
                System.out.print(" " + x.left.rem_capacity + " " + x.left);
            }
            else
            {
                System.out.print("  " + " " + x.left + "_____________");
            }
            if(x.right != null)
            {
                System.out.print(" " + x.right.rem_capacity + " " + x.right);
            }
            else
            {
                System.out.print("  "  + " " + x.right + "_____________");
            }
            System.out.println();

            InorderTraversal(x.right);
        }
    }
}
