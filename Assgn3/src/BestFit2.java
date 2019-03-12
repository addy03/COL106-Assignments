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

        int bin_id_par = max.id_bin.get(0);

        for(int i=1; i<max.id_bin.size(); i++)
        {
            if(max.id_bin.get(i) > bin_id_par)
            {
                bin_id_par = max.id_bin.get(i);
            }
        }
        node_bin x1 = bin.Search(bin_id_par);

        if(max.rem_capacity >= s)
        {
            if(obj == null)
            {
                obj = new avl_obj(id, s, bin_id_par);
            }
            else
            {
                obj.AddNode(id, s, bin_id_par);
            }

            x1.objects.add(x1.objects.size(), id);
            x1.obj_size.add(x1.obj_size.size(), s);
            node_bin2 x = rem.DeleteNode(x1.rem_capacity);

            x1.rem_capacity -= s;

            if(x.id_bin.size() > 1)
            {
                for(int i=0; i<x.id_bin.size(); i++)
                {
                    if(x.id_bin.get(i) != x1.id_b)
                    {
                        rem.AddNode(x.id_bin.get(i), x.rem_capacity); //x.id_bin.remove(i);
                    }
                }
            }
            rem.AddNode(x1.id_b, x1.rem_capacity);

        }else
        {
            System.out.println("Not enough space");
        }

        return x1.id_b;
    }

    public int DeleteObject(int id)
    {
        //Update object AVL
        node_object x = obj.DeleteNode(id);
        int p = x.par_bin_id;

        // Search the node in both bin trees
        node_bin p_bin = bin.Search(p);
        node_bin2 p_rem_bin = rem.DeleteNode(p_bin.rem_capacity);

        p_bin.rem_capacity += x.size_o;

        for(int i=0; i < p_bin.objects.size(); i++)
        {
            if(p_bin.objects.get(i) == id)
            {
                p_bin.objects.remove(i);
                p_bin.obj_size.remove(i);
                break;
            }
        }

        if(p_rem_bin.id_bin.size() > 1)
        {
            for(int i=0; i < p_rem_bin.id_bin.size(); i++)
            {
                if(p_rem_bin.id_bin.get(i) != p_bin.id_b)
                {
                    rem.AddNode(p_rem_bin.id_bin.get(i), p_rem_bin.rem_capacity); //p_rem_bin.id_bin.remove(i);
                }
            }
        }

        rem.AddNode(p_bin.id_b, p_bin.rem_capacity);
        return p;
    }

    public void PrintBin(int id)
    {
        node_bin x = bin.Search(id);
        try
        {
            for(int i=0;i<x.objects.size();i++){
                System.out.println(x.objects.get(i) + " " + x.obj_size.get(i));
            }
        }
        catch(NullPointerException e)
        {
            System.out.println("Invalid ID");
        }
    }
}
