public class node
{
    String st;
    byte[] bit = new byte[2];
    node next = null;

    public node(String st, byte[] bit)
    {
        this.st = st;
        this.bit = bit;
    }
}
