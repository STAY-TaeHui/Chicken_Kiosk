import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Kiosk {

    private final int MAIN_CODE = 101;
    private final int SIDE_CODE = 201;
    private final int BEVERAGE_CODE = 301;

    int here_togo = 0;
    private Scanner sc;
    private Map<Integer, Food> FoodsCheck;
    private List<Food> Cart;
    private Manager manager;
    private String Temp;
    private FileReader fr;
    private BufferedReader br;
    private String Foods[];
    private int count;

    Kiosk() {
        sc = new Scanner(System.in);
        manager = new Manager();
        Temp = "Food.txt";
        Foods = new String[3];
        FoodsCheck = new HashMap<Integer, Food>();
        Cart = new ArrayList<>();
    }

    // 상품 확인
    void menuprint(int userChoice) {

        List<Integer> TempCart = new ArrayList<Integer>();

        String num = "" + userChoice;
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

        System.out.println("                   [관리자모드 0]");
        System.out.println("******************************");
        System.out.println("******************************");
        System.out.println("***                        ***");
        System.out.println("***      CHICKEN KIOSK     ***");
        System.out.println("***    1. 매장      2. 포장  ***");
        System.out.println("***                        ***");
        System.out.println("******************************");
        System.out.println("******************************");

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
        case HERE:
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

    void categoryChoice(int here_togo) {
        final int MAIN = 1;
        final int SIDE = 2;
        final int BEVERAGE = 3;
        final int BACK = 0;
        count = 1;
        FoodsCheck.clear();
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

        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        while (true) {

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
                clearScreen();

                System.out.println("*****   메인메뉴   *****");

                menuprint(MAIN_CODE);
                break;
            }
            case SIDE: {
                clearScreen();

                System.out.println("*****   사이드   *****");

                menuprint(SIDE_CODE);
                break;
            }
            case BEVERAGE: {
                clearScreen();

                System.out.println("*****   음료   *****");

                menuprint(BEVERAGE_CODE);
                break;

            }
            }
        }

    }

    void managerLogin() {

    }

    void payment(int here_togo) {

    }

    void receipt() {

    }

    void cart_Add(int Key) {
        int num=1;
        int price=0;
        
        String keyname = FoodsCheck.get(Key).getName(); //Key를 이용해 해당 key의 value의 name을 받아옴.
        
        if(!Cart.isEmpty()) {//Cart가 비어있지 않을때
            Iterator <Food> it = Cart.iterator();
            while(it.hasNext()) {
                Food now =it.next();
                if(now.getName().equals(keyname)){//현재 name와 파라미터 key의 name을 비교
                    now.setNum();
                    now.setPrice();
                    break ;
                }
              
            }
//               Cart.add(FoodsCheck.get(Key));// Cart에 동일한 Food가 없으면 add
            
        }
        else {//Cart가 비어있을때
            Cart.add(FoodsCheck.get(Key));

        }
       
        cart_Check();

    }

    void cart_Check() {

        int count = 1;
        int totalprice = 0;
        int cart_menu = 0;
        clearScreen();

        System.out.println("**************************************");
        System.out.println("장바구니를 확인하세요!!");
        if (!Cart.isEmpty()) {
            Iterator<Food> it = Cart.iterator();
            while (it.hasNext()) {
                Food now = it.next();
                System.out.println(count + ".  " + "메뉴 : " + now.getName() + "\t가격 : " + now.getPrice());
                count++;
                totalprice += Integer.parseInt(now.getPrice());
            }
            System.out.println();
            System.out.println("\t\t총 구매 금액 : " + totalprice);
            System.out.println();

            loop: while (true) {
                System.out.println("1. 결제하기\t 2. 장바구니 상품 삭제\t 3. 계속 쇼핑하기");
                try {
                    cart_menu = sc.nextInt();
                    if (!(cart_menu >= 1 && cart_menu <= 3)) {
                        throw new InputMismatchException();
                    } else {
                        switch (cart_menu) {
                        case 1: {
                            clearScreen();
                            payment(here_togo);
                            break loop;
                        }
                        case 2: {
                            cart_Delete();
                            break loop;
                        }
                        case 3: {
                            clearScreen();
                            break loop;
                        }
                        }
                    }
                } catch (InputMismatchException e) {
                    // TODO: handle exception
                    sc = new Scanner(System.in);
                    System.out.println("정확한 번호를 입력해주세요...");
                }

//            
            }
        } else
            System.out.println("장바구니가 비어있습니다... 상품을 골라주세요");

    }

    void cart_Delete() {
        int delete_num = 0;

        while (true) {
            try {
                System.out.println("삭제할 상품의 번호를 입력해주세요.");
                delete_num = sc.nextInt();
                if (delete_num > 0 && delete_num <= Cart.size()) {
                    Cart.remove(delete_num - 1);
                    break;
                } else {
                    throw new InputMismatchException();
                }

            } catch (InputMismatchException e) {
                // TODO: handle exception
                sc = new Scanner(System.in);
                System.out.println("정확한 번호를 입력해주세요...");

            }
        }
        cart_Check();
    }

}
