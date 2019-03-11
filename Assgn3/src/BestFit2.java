public class BestFit2
{
    avl_bin bin;
    avl_rem_bin2 rem;
    avl_obj obj;

    public BestFit2()
    {
        bin = null;
        rem = null;
        obj = null;
    }

    public void AddBin(int id, int c)
    {
        if(bin == null && rem == null)
        {
            rem = new avl_rem_bin2(id, c);
            bin = new avl_bin(id, c);
        }
        else
        {
            node_bin x1 = new node_bin(id,c, 0);
            if(bin.Search(id) == null)
            {
                rem.AddNode(id, c);
            }
            bin.AddNode(x1);

        }
    }

    public int AddObject(int id, int s)                     // Check for this.
    {
        node_bin2 max = rem.root;
        while(max.right != null)
        {
            max = max.right;
        }

        node_bin x1 = bin.Search(max.id_bin.get(0));
        if(max.key >= s)
        {
            if(obj == null)
            {
                obj = new avl_obj(id, s, max);
            }
            else
            {
                obj.AddNode(id, s, max);
            }

            x1.rem_capacity -= s;
            x1.objects.add(id);
//            System.out.println("__________________________________________");
//            System.out.println(rem.root.id_b);
//            rem.InorderTraversal(rem.root);
//            System.out.println();
            node_bin2 x = rem.DeleteNode(max.key, max.id_bin.get(0));
//            System.out.println(rem.root.id_b);
//            rem.InorderTraversal(rem.root);
//            System.out.println();

            rem.AddNode(x1.id_b, x1.rem_capacity);
            // How to print error here!!
        }else
        {
            System.out.println("Not enough space");
        }

        return x1.id_b;
    }

    public void DeleteObject(int id)
    {
//        try
//        {
        //Update object AVL
        node_object x = obj.DeleteNode(id);
        int p = x.par_bin_id;
        // Search the node in both bin trees
        node_bin p_bin = bin.Search(p);
        node_bin2 p_rem_bin = rem.Search(p_bin.rem_capacity);
        System.out.println(p_bin.id_b + "  __________" + p_bin.rem_capacity);
//        System.out.println(p_rem_bin.id_b + "  __________");
        node_bin2 del;
        for(int i=0; i < p_rem_bin.id_bin.size(); i++)
        {
            if(p_rem_bin.id_bin.get(i) == p_bin.id_b)
            {
                del = rem.DeleteNode(p_rem_bin.key, p_bin.id_b);
                break;
            }
        }

        for(int i=0; i < p_bin.objects.size(); i++)
        {
            if(p_bin.objects.get(i) == id)
            {
                p_bin.objects.remove(i);
                break;
            }
        }
        // Update bin AVL
//            p_bin.objects.remove(id);
        p_bin.rem_capacity += x.size_o;
        // Update rem_cap AVL
//            System.out.println(p_bin.rem_capacity);
//            p_rem_bin.objects.remove(id);
        rem.AddNode(p_bin.id_b, p_bin.rem_capacity);
//        }
//        catch(NullPointerException e)
//        {
//            System.out.println("Object not found");
//        }
    }

    public void PrintBin(int id)
    {
        node_bin x = bin.Search(id);
        try
        {
            for(int i=0;i<x.objects.size();i++){
                System.out.println(x.objects.get(i));
            }
        }
        catch(NullPointerException e)
        {
            System.out.println("Invalid ID");
        }
    }
}
