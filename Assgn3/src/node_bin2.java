import java.util.ArrayList;
import java.util.List;

public class node_bin2
{
    int key;
    List<Integer> id_bin = new ArrayList<>();
    int no_id = 0;
    node_bin2 parent = null;
    node_bin2 left = null;
    node_bin2 right = null;
    int height;

    public node_bin2(int key, int id)
    {
        height = 0;
        this.key = key;
        id_bin.add(0, id);
        no_id = 1;
    }
}
