import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;


public class Kiosk {
    Scanner sc; // 스캐너
    Manager manager;
    Map<Integer, Food> FoodsCheck; // 상품확인
    List<Food> Cart; // 담긴상품
    String id;
    String password;
    String Temp;
    FileReader fr;
    BufferedReader br;

    Kiosk() {
        sc = new Scanner(System.in);
        manager = new Manager();
        Temp = "Food.txt";
        FoodsCheck = new HashMap<Integer, Food>();
        Cart = new ArrayList<Food>();
        
    }

    void clearScreen() {
        for(int i=0; i<200; i++) {
            System.out.println();
        }
        
    }
    // 메뉴선택(스캐너, 상품확인)
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

    // 관리자 로그인
    // 정규표현식 아이디 확인
    public void managerLogin() {
        String PATTERN_ID = "^[a-zA-Z][0-9]+{5,19}$";
        String PATTERN_PASSWORD = "^[a-zA-Z0-9][!,@,#,$,%,^,&,*,?,_,~]+{9,15}$";

        while (true) {
            System.out.println("ID와 PW를 입력하세요");
            System.out.println("ID -> ");
            id = sc.nextLine();
            while(!Pattern.matches(PATTERN_ID, id) && !(manager.getId().equals(id))) {
                System.out.println("잘못입력 하셨습니다");
                System.out.println("ID -> ");
                id = sc.nextLine();
            }
            
            System.out.println("PW -> ");
            password = sc.nextLine();
           
            while(!Pattern.matches(PATTERN_PASSWORD, password) && !(manager.getPassword().equals(password))) {
                System.out.println("PW를 잘못입력 하셨습니다");
                System.out.println("PW -> ");
                password = sc.nextLine();
                
            }

            manager.setLogin(true);
       System.out.println("로그인이 성공!!");
       break;
        }

    }


    // 결제하기
    void payment() {

    }

    // 영수증
    void receipt() {

    }

    // 장바구니 담기(담긴상품)
    void cart_Add() {

    }

    // 장바구니 확인
    void cart_Check() {

    }

    // 장바구니 삭제
    void cart_Delete(int number) {

    }

}
