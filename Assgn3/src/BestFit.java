public class BestFit
{
    avl_bin bin = null;
    avl_rem_bin rem= null;
    avl_obj obj = null;

    public void AddBin(int id, int c)
    {
        if(bin == null)
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

    public int AddObject(int id,int s)
    {
        node_bin max = rem.root;
        while(max.right != null)
        {
            max = max.right;
        }

        if(max.rem_capacity >= s)
        {

        }else
        {
            System.out.println("Not enough space");
        }
    }

    public void DeleteObject(int id)
    {
        try
        {
            node_object x = obj.DeleteNode(id);
            int p = x.par_bin_id;
            node_bin p_bin = bin.Search(p);
            p_bin.rem_capacity += x.size_o;
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
