public class BestFit
{
    avl_bin bin;
    avl_rem_bin rem;
    avl_obj obj;

    public BestFit()
    {
        bin = null;
        rem = null;
        obj = null;
    }

    public void AddBin(int id, int c)
    {
        if(bin == null && rem == null)
        {
            bin = new avl_bin(id, c);
            rem = new avl_rem_bin(id, c);
        }
        else
        {
            bin.AddNode(id, c);
            node_bin x = new node_bin(id,c, 0);
            rem.AddNode(x);
        }
    }

    public int AddObject(int id,int s)                     // Check for this.
    {
        node_bin max = rem.root;
        while(max.right != null)
        {
            max = max.right;
        }

        if(max.rem_capacity >= s)
        {
            obj.AddNode(id, s);
            node_bin x = rem.DeleteNode(max);
            x.rem_capacity -= s;
            rem.AddNode(x);
            node_bin x2 = bin.Search(x.id_b);
            x2.rem_capacity -= s;                                       // How to print error here!!
        }else
        {
            System.out.println("Not enough space");
        }

        return max.id_b;
    }

    public void DeleteObject(int id)
    {
        try
        {
            // Update object AVL
            node_object x = obj.DeleteNode(id);
            int p = x.par_bin_id;
            // Update bin AVL
            node_bin p_bin = bin.Search(p);
            p_bin.rem_capacity += x.size_o;
            // Update rem_cap AVL
            node_bin p_rem_bin = rem.Search(p);
            node_bin del = rem.DeleteNode(p_rem_bin);
            del.rem_capacity += x.size_o;
            rem.AddNode(del);
        }
        catch(NullPointerException e)
        {
            System.out.println("Object not found");
        }
    }

    public void PrintBin(int id)
    {
        node_bin x = bin.Search(id);
        try
        {

        }
        catch(NullPointerException e)
        {
            System.out.println("Invalid ID");
        }
    }
}
