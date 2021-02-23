import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
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

    void menuprint(int userChoice) {
        String num = ""+userChoice;
        System.out.println("번호\t상품명\t가격");
        for (int i = 1; i <= FoodsCheck.size(); i++) {
            if (FoodsCheck.get(i).getCategory().equals(num)) {
                System.out.printf("%s.\t%s\t%s원\n", i, FoodsCheck.get(i).getName(), FoodsCheck.get(i).getPrice());
            }
        }
    }

    void clearScreen() {
        for (int i = 0; i < 200; i++) {
            System.out.println();
        }
    }

    public void menuChoice() {
        final int HERE = 1;
        final int TOGO = 2;
        final int MANAGERLOGIN = 0;
        while (true) {
            System.out.println("                     관리자모드 0");
            System.out.println("******************************");
            System.out.println("******************************");
            System.out.println("***                        ***");
            System.out.println("***      CHICKEN KIOSK     ***");
            System.out.println("***    1. 매장      2. 포장   ***");
            System.out.println("***                       ***");
            System.out.println("******************************");
            System.out.println("******************************");

            int here_togo = 0;

            while (true) {
                try {
                    here_togo = sc.nextInt();
                    if (here_togo != HERE && here_togo != TOGO) {
                        throw new InputMismatchException();
                    } else
                        break;

                } catch (InputMismatchException e) {
                    // TODO: handle exception
                    sc = new Scanner(System.in);
                    System.out.println("1번과 2번 중 하나만 선택해 주세요.");
                }
            }

            switch (here_togo) {
            case HERE: {
                clearScreen();
                categoryChoice(here_togo);
                break;
            }
            case TOGO: {
                clearScreen();
                categoryChoice(here_togo);
                break;
            }
            case MANAGERLOGIN: {

            }
            }

        }
    }

    void categoryChoice(int here_togo) {
        final int MAIN = 1;
        final int SIDE = 2;
        final int BEVERAGE = 3;
        final int BACK = 0;

        System.out.println("***               CATEGORY              ***");
        System.out.println("***                                     ***");
        System.out.println("***   1. 대표메뉴    2. 사이드메뉴   3. 음료   ***");

        int menu = 0;
        while (true) {
            try {
                menu = sc.nextInt();
                if (menu != BACK && menu != MAIN && menu != SIDE && menu != BEVERAGE) {
                    throw new InputMismatchException();
                } else
                    break;
            } catch (InputMismatchException e) {
                // TODO: handle exception
                sc = new Scanner(System.in);
                System.out.println("1번, 2번, 3번 중 하나만 선택해 주세요.");
            }
        }

        switch (menu) {
        case BACK: {
            clearScreen();
            return;
        }
        case MAIN: {
            System.out.println("*****   메인메뉴   *****");
//                menuPrint(101)
        }
        case SIDE: {
            System.out.println("*****   사이드   *****");

//            menuPrint(201)
        }
        case BEVERAGE: {
            System.out.println("*****   음료   *****");

//            menuPrint(301)
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
