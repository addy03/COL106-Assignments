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

//        avl_obj x = new avl_obj(5, 10);

        BestFit x = new BestFit();
        x.AddBin(20,20);
        x.AddObject(20,10);
        x.AddObject(15,12);
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


//        x.AddBin(9, 40);
//        x.AddBin(11, 10);
//        x.AddBin(10, 10);
//        x.AddBin(15,12);
//        x.AddBin(17,13);
//        x.AddBin(23,10);
//        x.AddObject(5,35);
//        x.AddObject(6,13);
//        x.AddObject(10,12);
//        x.DeleteObject(10);
//        x.DeleteObject(6);

        x.bin.InorderTraversal(x.bin.root);
        System.out.println();
        x.rem.InorderTraversal(x.rem.root);
        System.out.println();
        x.obj.InorderTraversal(x.obj.root);
    }
}
