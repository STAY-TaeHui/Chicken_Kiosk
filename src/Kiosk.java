import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Kiosk {

    private final int MAIN_CODE = 101;
    private final int SIDE_CODE = 201;
    private final int BEVERAGE_CODE = 301;
    private Manager manager;

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
        manager = new Manager();
        Temp = "Food.txt";
        Foods = new String[3];
        FoodsCheck = new HashMap<Integer, Food>();
        Cart = new ArrayList<Map<Integer, Food>>();

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

    // 줄 올리기
    void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    // 메뉴선택
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
            System.out.println("***    1. 매장      2. 포장  ***");
            System.out.println("***                        ***");
            System.out.println("******************************");
            System.out.println("******************************");

            int here_togo = 0;

            while (true) {
                try {
                    here_togo = sc.nextInt();
                    sc.nextLine();

                    if (here_togo != HERE && here_togo != TOGO && here_togo != MANAGERLOGIN) {
                        throw new InputMismatchException();
                    } else
                        break;

                } catch (InputMismatchException e) {
                    // TODO: handle exception
                    sc = new Scanner(System.in);
                    System.out.println("올바른 숫자를 입력해주세요.");
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
                if (!manager.isLogin()) {
                    System.out.println("※ 로그인을 하여야 사용 가능합니다 ※");
                    managerLogin();
                    
                } else if (manager.isLogin()) {
                    int choice = 0;
                    while (true) {
                        System.out.println("******************************************************");
                        System.out.println("***                 Manager Menu                   ***");
                        System.out.println("***                                       0.로그아웃 ***");
                        System.out.println("*** 1. 총 매출 확인  2. 전체 영수증 출력  3. 음식 가격 변경 ***");
                        while (true) {
                            try {
                                choice = sc.nextInt();
                                sc.nextLine();
                                if (choice != 1 && choice != 2 && choice != 3 && choice != 0) {
                                    throw new InputMismatchException();
                                } else
                                    break;

                            } catch (InputMismatchException e) {
                                // TODO: handle exception
                                sc = new Scanner(System.in);
                                System.out.println("올바른 숫자를 입력해주세요.");
                            }
                        }

                        if (choice == 1) {

                        } else if (choice == 2) {

                            // 음식 가격 변경
                        } else if (choice == 3) {
                            String changeName = null;
                            String changePrice = null;
                            System.out.println("***   음식 가격 변경  ***");
                            System.out.println("변경 할 음식의 이름을 입력해주세요  ex) 피자 ");
                            System.out.print(">");
                            changeName = sc.nextLine();
                            System.out.println("변경 할 금액을 입력해주세요.");
                            System.out.print(">");
                            changePrice = sc.nextLine();

                            manager.priceChange(changeName, changePrice);
                            break;
                        } else if (choice == 0) {
                            System.out.println("**로그아웃**");
                            manager.setLogin(false);
                            break;
                        }
                    }

                }

            }
            }

        }
    }

    // 카테고리 선택
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

        System.out.println("***               CATEGORY              ***");
        System.out.println("***                                     ***");
        System.out.println("***   1. 대표메뉴    2. 사이드메뉴   3. 음료  ***");

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
                System.out.println("올바른 숫자를 입력해주세요.");
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
            break;
        }
        case SIDE: {
            System.out.println("*****   사이드   *****");

            menuprint(SIDE_CODE);
            break;
        }
        case BEVERAGE: {
            System.out.println("*****   음료   *****");

            menuprint(BEVERAGE_CODE);
            break;

        }
        }

    }

    public void managerLogin() {
        String PATTERN_ID = "^[a-zA-Z][0-9]+{5,19}$";
        String PATTERN_PASSWORD = "^[a-zA-Z0-9][!,@,#,$,%,^,&,*,?,_,~]+{9,15}$";

        while (true) {
            System.out.print("ID : ");
            String id = sc.nextLine();
            while (!Pattern.matches(PATTERN_ID, id) && !(manager.getId().equals(id))) {
                System.out.println("잘못입력 하셨습니다");
                System.out.print("ID : ");
                id = sc.nextLine();
            }

            System.out.print("PW : ");
            String password = sc.nextLine();
            if (password.equals("0")) {
                break;
            }
            while (!Pattern.matches(PATTERN_PASSWORD, password) && !(manager.getPassword().equals(password))) {
                System.out.println("PW를 잘못입력 하셨습니다");
                System.out.print("PW : ");
                password = sc.nextLine();

            }

            manager.setLogin(true);
            System.out.println("로그인이 성공!!");
            break;
        }
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

    // 음식 리스트 getter setter
    public Map<Integer, Food> getFoodsCheck() {
        return FoodsCheck;
    }

    public void setFoodsCheck(Map<Integer, Food> foodsCheck) {
        FoodsCheck = foodsCheck;
    }

}
