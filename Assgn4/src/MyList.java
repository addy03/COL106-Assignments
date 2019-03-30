public class MyList
{
    node first;
    int size;

    public MyList()
    {
        first = null;
        size = 0;
    }

    public void AddNode(String st, byte[] bit)
    {
        size = size+1;
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

    public void print_list()
    {
        node x = first;
        while(x != null)
        {
            System.out.println(x.st + " ");
            x = x.next;
        }
        System.out.println();
    }
}
