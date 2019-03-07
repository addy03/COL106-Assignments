public class node_object
{
    int id_o;
    int size_o;
    int height;
    node_object parent = null;
    node_bin p = null;
    node_object left = null;
    node_object right = null;
    public node_object(int id_o, int size_o, int height)
    {
        this.id_o = id_o;
        this.size_o = size_o;
        this.height = height;
    }
}
