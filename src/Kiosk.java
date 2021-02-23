import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Kiosk {

    private Scanner sc;
    private Map<Integer, Foods> FoodsCheck;
    private List<Map<Integer, Foods>> Cart;
    private int discount;

    Kiosk() {
        sc = new Scanner(System.in);
        FoodsCheck = new HashMap<Integer, Foods>();
        Cart = new ArrayList<Map<Integer, Foods>>();
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
                categoryChoice(here_togo);
                break;
            }
            case TOGO: {
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
        clearScreen();

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
            return;
        }
        case MAIN: {
//                menuPrint(101)
        }
        case SIDE: {
//            menuPrint(201)
        }
        case BEVERAGE: {
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
