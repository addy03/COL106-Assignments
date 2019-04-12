public class MyList
{
    listNode first;

    public MyList()
    {
        first = null;
    }

    public void AddNode(node st)
    {
        listNode x = new listNode(st);
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
        listNode x = first;
        while(x != null)
        {
            System.out.println(x.elem.s + " ");
            x = x.next;
        }
        System.out.println();
    }
}
