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

        if(max.rem_capacity >= s)
        {
            if(obj == null)
            {
                obj = new avl_obj(id, s, max);
            }
            else
            {
                obj.AddNode(id, s, max);
            }

            x1.objects.add(x1.objects.size(), id);
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

    public void DeleteObject(int id)
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
