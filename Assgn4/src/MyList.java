public class MyList
{
    node first;

    public MyList()
    {
        first = null;
    }

    public void AddNode(String st, byte[] bit)
    {
        node x = new node(st, bit);
        if(first == null)
        {
            first = x;
        }
        else
        {
            x.next = first;
            first = x;
        }
    }
}
