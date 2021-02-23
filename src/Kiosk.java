import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Kiosk {
    Scanner sc;
    Map<Integer, Food> FoodsCheck;
    List<Food> Cart;
    String Temp;
    FileReader fr;
    BufferedReader br;
    String[] Foods;
    int count;

    Kiosk() {
        sc = new Scanner(System.in);
        Temp = "Food.txt";
        Foods = new String[3];
        count = 1;
        FoodsCheck = new HashMap<Integer, Food>();
        Cart = new ArrayList<Food>();
        try {
            fr = new FileReader(Temp);
            br = new BufferedReader(fr);
            String Data = null;
            while ((Data = br.readLine()) != null) {
                Foods = Data.split(",");
                FoodsCheck.put(count, new Food(Foods[0], Foods[1], Foods[2]));
                count++;
            }

        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        }finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        

    }

    ArrayList menuChoice() {

        return null;
    }

    void menuprint(int userChoice) {
        String num = ""+userChoice;
        System.out.println("번호\t상품명\t가격");
        for (int i = 1; i <= FoodsCheck.size(); i++) {
            if (FoodsCheck.get(i).getCategory().equals(num)) {
                System.out.printf("%s.\t%s\t%s원\n", i, FoodsCheck.get(i).getName(), FoodsCheck.get(i).getPrice());
            }
        }
    }

    void managerLogin() {

    }

    void payment() {

    }

    void receipt() {

    }

    void cart_Add() {
        
    }

    void cart_Check() {

    }

    void cart_Delete() {

    }

}
