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
        }

    }
}
