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
//        x.AddBin(15,10);
//        x.AddBin(7,10);
//        x.AddBin(3,10);
//        x.AddBin(1,10);
//        x.AddBin(17, 8);
//        x.AddBin(2,80);
//        x.AddBin(7,12);
//        x.AddBin(11,14);
//        x.AddBin(1,12);
//        x.AddBin(18,10);


        x.AddBin(9, 40);
        x.AddBin(11, 10);
        x.AddBin(10, 10);
        x.AddObject(5,30);
        x.AddObject(6,10);
        x.AddBin(15,0);
        x.AddObject(4,10);
        x.AddObject(8,8);
//        System.out.println(x.rem.root.right.right.id_b);
//        x.DeleteObject(5);
        x.bin.InorderTraversal(x.bin.root);
        System.out.println();
        x.rem.InorderTraversal(x.rem.root);
//        System.out.println();
//        x.obj.InorderTraversal(x.obj.root);
    }
}
