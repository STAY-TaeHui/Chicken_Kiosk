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


    private final int MAIN_CODE = 101;
    private final int SIDE_CODE = 201;
    private final int BEVERAGE_CODE = 301;
    
    private Scanner sc;
    private Map<Integer, Food> FoodsCheck;
    private List<Map<Integer, Food>> Cart;
    private String Temp;
    private FileReader fr;
    private BufferedReader br;
    private String Foods[];
    private int count;
    


    Kiosk() {
        sc = new Scanner(System.in);
        Temp = "Food.txt";
        Foods = new String[3];
        count = 1;
        FoodsCheck = new HashMap<Integer, Food>();

        Cart = new ArrayList<Map<Integer, Food>>();
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

        Cart = new ArrayList<>();
 
        

    }



    // 상품 확인
    void menuprint(int userChoice) {
        List<Integer> TempCart = new ArrayList<Integer>();

        String num = ""+userChoice;
        int C = 1;
        System.out.println("번호\t상품명\t가격");
        for (int i = 1; i <= FoodsCheck.size(); i++) {
            if (FoodsCheck.get(i).getCategory().equals(num)) {
                System.out.printf("%s.\t%s\t%s원\n", C, FoodsCheck.get(i).getName(), FoodsCheck.get(i).getPrice());
                C++;
                TempCart.add(i);
            }
        }
        System.out.println("상품을 선택해주세요   Ex) 2 ");
        int Choice = sc.nextInt();
        sc.nextLine();
        cart_Add(TempCart.get(Choice - 1));
        
        
    }

    void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    public void menuChoice() {
        final int HERE = 1;
        final int TOGO = 2;
        final int MANAGERLOGIN = 0;
        while (true) {
            System.out.println("                   [관리자모드 0]");
            System.out.println("******************************");
            System.out.println("******************************");
            System.out.println("***                        ***");
            System.out.println("***      CHICKEN KIOSK     ***");
            System.out.println("***    1. 매장      2. 포장   ***");
            System.out.println("***                        ***");
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
                break;

            }
            }

        }
    }

    void categoryChoice(int here_togo) {
        final int MAIN = 1;
        final int SIDE = 2;
        final int BEVERAGE = 3;
        final int BACK = 0;
        
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
            
        }finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

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

            menuprint(MAIN_CODE);
        }
        case SIDE: {
            System.out.println("*****   사이드   *****");

            menuprint(SIDE_CODE);
        }
        case BEVERAGE: {
            System.out.println("*****   음료   *****");

            menuprint(BEVERAGE_CODE);
            break;
        
        }
        }

    }

    void managerLogin() {

    }

    void payment() {

    }

    void receipt() {

    }


    void cart_Add(int Key) {
        System.out.println(Key);
    }

    void cart_Check() {

    }

    void cart_Delete() {

    }

}
