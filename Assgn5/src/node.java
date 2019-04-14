import java.util.*;

public class node
{
    String s;
    List<Integer> ind = new ArrayList<>();
    node parent;
    MyList child;

    public node(String s, int init)
    {
        this.s = s;
        parent = null;
        child = new MyList();
        ind.add(init);
    }
}
