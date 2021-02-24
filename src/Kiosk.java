import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Kiosk {

    private final int MAIN_CODE = 101;
    private final int SIDE_CODE = 201;
    private final int BEVERAGE_CODE = 301;
    private Manager manager;

    private int here_togo = 0;
    private Scanner sc;
    private Map<Integer, Food> FoodsCheck;
    private List<Food> Cart;
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
        while(true) {
        try {
            int Choice = sc.nextInt();
            sc.nextLine();
            cart_Add(TempCart.get(Choice - 1));         //상품선택시 장바구니에 넣어주는 메소드 호출
            break;
        } catch (IndexOutOfBoundsException e) {
            // TODO: handle exception
            System.out.println("ERROR : " + e.getMessage());
            sc = new Scanner(System.in);
            System.out.println("올바른 숫자를 입력해주세요.");
        }
        catch(InputMismatchException e1) {
            System.out.println("ERROR : " + e1.getMessage());
            sc = new Scanner(System.in);
            System.out.println("올바른 숫자를 입력해주세요.");
        }
        }
        

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
                sc.nextLine();

                if (here_togo != HERE && here_togo != TOGO && here_togo != MANAGERLOGIN) {
                    throw new InputMismatchException();
                } else
                    break;

            } catch (InputMismatchException e) {
                // TODO: handle exception
                System.out.println("ERROR : " + e.getMessage());
                sc = new Scanner(System.in);
                System.out.println("올바른 숫자를 입력해주세요.");
            }
        }

        switch (here_togo) {
        case HERE:
        case TOGO: {
            clearScreen();
            categoryChoice();
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
                            System.out.println("ERROR : " + e.getMessage());
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

    // 카테고리 선택
    void categoryChoice() {
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
            e1.printStackTrace();

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
                    System.out.println("ERROR : " + e.getMessage());
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
            while (!Pattern.matches(PATTERN_PASSWORD, password) && !(manager.getPassword().equals(password))) {
                System.out.println("PW를 잘못입력 하셨습니다");
                System.out.print("PW : ");
                password = sc.nextLine();

            }

            manager.setLogin(true);
            System.out.println("로그인이 성공!!");
            return;
        }
    }

    void payment(int totalprice) {

    }

    void receipt() {

    }

    void cart_Add(int Key) {

        String keyname = FoodsCheck.get(Key).getName(); // Key를 이용해 해당 key의 value의 name을 받아옴.

        if (!Cart.isEmpty()) {// Cart가 비어있지 않을때
            for(int i=0; i<Cart.size(); i++) {      //Cart크기만큼 for문을 돌면서 Cart에 동일한 Food가 있는지 확인한다.
                Food now = Cart.get(i);             //현재 넣을 Food
                if(now.getName().equals(keyname)) { //Cart에 같은 이름의 Food가 있다면
                    now.setNum();                   //setNum() >> 수량 +1;
                    now.setAddprice();              //setAddprice() >> 기존 price에 더하기 price를 addprice에 넣어주어 해당 메뉴의 총 가격을 정해준다.
                    break;
                }
                else if(i==Cart.size()-1) {
                    Cart.add(FoodsCheck.get(Key));// Cart에 동일한 Food가 없으면 add
                    break;
                }
            }
        } else {// Cart가 비어있을때
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
                System.out.println(count + ".  " + "메뉴 : " + now.getName() + "\t가격 : " + now.getAddprice() + 
                                    "\t수량 : " + now.getNum());
                count++;
                totalprice += now.getAddprice();
            }
            System.out.println();
            System.out.println("\t\t\t 총 구매 금액 : " + totalprice);
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
                            payment(totalprice);
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
                    System.out.println("ERROR : " + e.getMessage());
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

    // 음식 리스트 getter setter
    public Map<Integer, Food> getFoodsCheck() {
        return FoodsCheck;
    }

    public void setFoodsCheck(Map<Integer, Food> foodsCheck) {
        FoodsCheck = foodsCheck;
    }

}
