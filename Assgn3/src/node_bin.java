import java.util.List;

public class node_bin
{
    int id_b;
    int capacity;
    int rem_capacity;
    int height;
    List<node_object> objects = null;
    node_bin parent = null;
    node_bin left = null;
    node_bin right = null;
    public node_bin(int id, int c, int height)
    {
        id_b = id;
        capacity = c;
        rem_capacity = c;
        this.height = height;
    }

    public int get_id()
    {
        return id_b;
    }
}