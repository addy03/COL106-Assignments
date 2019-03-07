public class avl_obj
{
    node_object root;

    public avl_obj(int id, int size)
    {
        root = new node_object(id, size, 0);
    }

    public node_object Search(int s)
    {
        // Time complexity is O(h);
        node_object x = root;
        while (x != null)
        {
            if(x.id_o == s)
            {
                break;
            }
            else if(x.id_o > s)
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
    public void update_height(node_object mid, node_object l, node_object r)
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
    public void node_rotate(node_object x)
    {
        node_object par = x.parent;
        node_object y,z,t1,t2,t3,t4;
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
            if(z == x.right)
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

    // Function to add a new node to the AVL tree.
    public void AddNode(int id, int cap)
    {
        node_object y, x = null;
        y = root;
        while (y != null)
        {
            x = y;
            if(y.id_o == cap)
            {
                break;
            }
            if(cap > y.id_o)
            {
                y = y.right;
            }
            else
            {
                y = y.left;
            }
        }

        if(x.id_o == cap)
        {
            System.out.println("Number already exists.");
        }
        else
        {
            node_object a = new node_object(id, cap,0);
            a.parent = x;
            if(x.id_o < id)
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

            System.out.println(x.id_o + " " + x.height);
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

                System.out.println(x.id_o + " " + x.height);
            }

            node_object rot = a;
            System.out.println(rot.id_o + " " + rot.height);
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
                System.out.println(diff);
            }

            if(rot != null)
            {
                System.out.println(rot.id_o + " " + rot.height);
                node_rotate(rot);
            }
            System.out.println();
        }
    }

//    public node_object DeleteNode(int s)
//    {
//        node_object x = Search(s);
//        if(x.left == null && x.right == null)
//        {
//            // If both children are null;
//            if(x == x.parent.left)
//            {
//                x.parent.left = null;
//            }
//            else
//            {
//                x.parent.right = null;
//            }
//        }
//        else if(x.left == null || x.right == null)
//        {
//            // If only one child is null;
//            node_object a;
//            if(x.left == null)
//            {
//                a = x.right;
//            }
//            else
//            {
//                a = x.left;
//            }
//            if(x == x.parent.left)
//            {
//                x.parent.left = a;
//            }
//            else
//            {
//                x.parent.right = a;
//            }
//        }
//        else
//        {
//            node_object a = x.left;
//            while (a != null)
//            {
//                a = a.right;
//            }
//            int val = a.id_o;
//            DeleteNode(a.id_o);
//            x.id_o = val;
//        }
//        return x;
//    }

    public void InorderTraversal(node_object x)
    {
        if(x == null)
        {

        }
        else
        {
            InorderTraversal(x.left);
            System.out.println(x.id_o + " " + x.height);
            InorderTraversal(x.right);
        }
    }
}