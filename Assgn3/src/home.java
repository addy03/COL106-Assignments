import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class home
{
    public static void main(String args[])
    {
        BestFit new_fit = new BestFit();
        try
        {
            Scanner input = new Scanner(new File("new_small.txt"));
            String answer;

            while(input.hasNext())
            {
                answer = input.nextLine();
                String[] splited = answer.split(" ");
                int a = Integer.parseInt(splited[0]);
                if(splited.length == 3)
                {
                    System.out.println(a + " " + splited[1] + " " + splited[2]);
                }
                else
                {
                    System.out.println(a + " " + splited[1]);
                }
                System.out.println();
                if (a == 1) {
                    new_fit.AddBin(Integer.parseInt(splited[1]), Integer.parseInt(splited[2]));
                } else if (a == 2) {
                    new_fit.AddObject(Integer.parseInt(splited[1]), Integer.parseInt(splited[2]));
                } else if (a == 3) {
                    new_fit.DeleteObject(Integer.parseInt(splited[1]));
                } else if (a == 4) {
                    new_fit.PrintBin(Integer.parseInt(splited[1]));
                }

                new_fit.bin.InorderTraversal(new_fit.bin.root);
                System.out.println();
                new_fit.rem.InorderTraversal(new_fit.rem.root);
                System.out.println();
                if(new_fit.obj != null)
                {
                    new_fit.obj.InorderTraversal(new_fit.obj.root);
                    System.out.println();
                }
                System.out.println("_____________________________________");
            }

        }
        catch(FileNotFoundException e)
        {
            System.out.println("Program Error.");
        }
////        avl_bin code = new avl_bin(5, 10);
////        code.AddNode(1,5);
////        code.AddNode(2,3);
////        code.AddNode(10,3);
////        code.AddNode(9,3);
////        code.AddNode(4,3);
////        code.AddNode(3,3);
//////        code.AddNode(8, 70);
////        code.AddNode(13,5);
//////        code.AddNode(13,5);
////        code.AddNode(11, 12);
////        code.AddNode(15,10);
////        code.AddNode(17, 45);
////        code.InorderTraversal(code.root);
//
////        avl_obj x = new avl_obj(5, 10);
//
//        BestFit x = new BestFit();
//        x.AddBin(20,20);
//        x.AddObject(20,10);
//        x.AddObject(15,12);
////        x.AddBin(15,10);
////        x.AddBin(7,10);
////        x.AddBin(3,10);
////        x.AddBin(1,10);
////        x.AddBin(17, 8);
////        x.AddBin(2,80);
////        x.AddBin(7,12);
////        x.AddBin(11,14);
////        x.AddBin(1,12);
////        x.AddBin(18,10);
//
//
////        x.AddBin(9, 40);
////        x.AddBin(11, 10);
////        x.AddBin(10, 10);
////        x.AddBin(15,12);
////        x.AddBin(17,13);
////        x.AddBin(23,10);
////        x.AddObject(5,35);
////        x.AddObject(6,13);
////        x.AddObject(10,12);
////        x.DeleteObject(10);
////        x.DeleteObject(6);
//

    }
}
