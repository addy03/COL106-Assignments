public class home
{
    public static void main(String args[])
    {
//        avl_bin code = new avl_bin(5, 10);
//        code.AddNode(1,5);
//        code.AddNode(2,3);
//        code.AddNode(10,3);
//        code.AddNode(9,3);
//        code.AddNode(4,3);
//        code.AddNode(3,3);
////        code.AddNode(8, 70);
//        code.AddNode(13,5);
////        code.AddNode(13,5);
//        code.AddNode(11, 12);
//        code.AddNode(15,10);
//        code.AddNode(17, 45);
//        code.InorderTraversal(code.root);

//        avl_obj code = new avl_obj(5, 10);
//        code.AddNode(1,5);
//        code.AddNode(2,3);
//        code.AddNode(10,3);
//        code.AddNode(9,3);
//        code.AddNode(4,3);
//        code.AddNode(3,3);
////        code.AddNode(8, 70);
//        code.AddNode(13,5);
////        code.AddNode(13,5);
//        code.AddNode(11, 12);
//        code.AddNode(15,10);
//        code.AddNode(17, 45);
//        code.DeleteNode(5);
//        code.DeleteNode(2);
//        code.DeleteNode(1);

//        code.InorderTraversal(code.root);

        BestFit x = new BestFit();
        x.AddBin(1,10);
        x.AddBin(2,20);
        x.AddBin(3,10);
        x.bin.InorderTraversal(x.bin.root);
        System.out.println();
        x.rem.InorderTraversal(x.rem.root);
    }
}
