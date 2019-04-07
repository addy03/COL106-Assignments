public class node
{
    String s;
    node parent;
    MyList child;

    public node(String s)
    {
        this.s = s;
        parent = null;
        child = new MyList();
    }
}
