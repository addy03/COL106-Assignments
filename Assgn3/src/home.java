public class home
{
    public static void main(String args[])
    {
        avl_bin code = new avl_bin(5, 10);
        code.AddNode(1,5);
        code.AddNode(2,3);
        code.AddNode(10,3);
        code.AddNode(9,3);
//        code.AddNode(4,3);
        code.InorderTraversal(code.root);
    }
}
