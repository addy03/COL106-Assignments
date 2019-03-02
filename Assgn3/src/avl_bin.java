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
        // Returns the node with value that was searched or returns the leaf node if the value was not found;
        node_bin x = root;
        while (x.left != null || x.right != null)
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

    public void node_rotate(node_bin x)
    {
        
    }

    public void AddNode(int id, int cap)
    {
        //For AVL Tree;
        // Time complexity is O(h);
        // New node is always added to the leaf node;
        node_bin x = Search(id);

        if(x.get_id() == id)
        {
            System.out.println("Number already exists.");
        }
        else
        {
            node_bin a = new node_bin(id, cap,0);
            a.parent = x;
            if(x.get_id() < id)
            {
                x.right = a;
            }
            else
            {
                x.left = a;
            }
        }

        x.height += 1;
        while(x == root)
        {
            x = x.parent;
            int max = x.left.height;
            if (x.right.height > x.left.height)
            {
                max = x.right.height;
            }
            x.height = max + 1;
        }
    }


}
