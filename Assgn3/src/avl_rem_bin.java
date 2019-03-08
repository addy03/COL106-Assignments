public class avl_rem_bin
{
    node_bin root;

    public avl_rem_bin(int id, int cap)
    {
        root = new node_bin(id, cap, 0);
    }

    public node_bin Search(int s, int id, node_bin start)
    {
        // Time complexity is O(h);
        node_bin x = start;
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

        if(x != null)
        {
            while (x.id_b != id && x != null)
            {
                x = Search(s, id, x.left);
            }
        }
        return x;
    }

    // Function to update the height of any node.
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

    // Function to rebalance the AVL tree.
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
    public void AddNode(node_bin a)
    {

        int cap = a.rem_capacity;
        node_bin y, x = null;
        y = root;
        if(root != null)
        {
            //Finding the node where the new node will be added.
            while (y != null)
            {
                x = y;

                if(cap > y.rem_capacity)
                {
                    y = y.right;
                }
                else
                {
                    y = y.left;
                }
            }

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

            node_bin rot = a;
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
        else
        {
            root = a;
        }
    }

    public node_bin DeleteNode(node_bin x)
    {
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
                node_bin a;
                if(x.left == null)
                {
                    a = x.right;
                }
                else
                {
                    a = x.left;
                }
                a.parent = x.parent;
                if(x == x.parent.left)
                {
                    x.parent.left = a;
                }
                else
                {
                    x.parent.right = a;
                }
            }
            node_bin rot = x;
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
            node_bin a = x.left;
            while (a.right != null)
            {
                a = a.right;
            }
            a = DeleteNode(a);

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

    public void InorderTraversal(node_bin x)
    {
        if(x == null)
        {

        }
        else
        {
            InorderTraversal(x.left);
            System.out.print(x.id_b + " " + x + " " + x.height + " " + x.rem_capacity + "  ");
            if(x.parent != null)
            {
                System.out.print(" " + x.parent.id_b + " " + x.parent);
            }
            else
            {
                System.out.print("  " + " " + x.parent + "_____________");
            }
            if(x.left != null)
            {
                System.out.print(" " + x.left.id_b + " " + x.left);
            }
            else
            {
                System.out.print("  " + " " + x.left + "_____________");
            }
            if(x.right != null)
            {
                System.out.print(" " + x.right.id_b + " " + x.right);
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
