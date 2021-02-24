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
    private Buyer buyer;
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
        buyer = new Buyer();
        Temp = "Food.txt";
        Foods = new String[3];
        FoodsCheck = new HashMap<Integer, Food>();
        Cart = new ArrayList<>();
    }

// ---------- 상품 확인 -------------
    void menuprint(int userChoice) {

        List<Integer> TempCart = new ArrayList<Integer>();

        String num = "" + userChoice;
        int C = 1;
        System.out.println("*** \t번호\t상품명\t\t가격\t ***");
        for (int i = 1; i <= FoodsCheck.size(); i++) {
            if (FoodsCheck.get(i).getCategory().equals(num)) {
                System.out.printf("*** \t %s\t%s\t\t%s원\t ***\n", C, FoodsCheck.get(i).getName(), FoodsCheck.get(i).getPrice());
                C++;
                TempCart.add(i);
            }
        }
        System.out.println("상품을 선택해주세요   Ex) 2 ");
        int Choice = sc.nextInt();
        sc.nextLine();
        cart_Add(TempCart.get(Choice - 1));

    }

 // ---------- 줄 올리기 -------------
    void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

 // ---------- 메뉴 선택 -------------
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
     // ---------- 관리자 -------------
        case MANAGERLOGIN: {
            while(true) {
                if (!manager.isLogin()) {
                    clearScreen();
                    System.out.println("※ 로그인을 하여야 사용 가능합니다 ※");
                    managerLogin();
                } else if (manager.isLogin()) {
                    int choice = 0;
                    while (true) {
                        System.out.println("");
                        System.out.println("******************************************************");
                        System.out.println("***                 Manager Menu                   ***");
                        System.out.println("***                                                ***");
                        System.out.println("*** 1. 총 매출 확인  2. 전체 영수증 출력  3. 음식 가격 변경 ***");
                        System.out.println("*** 4. 음식 추가 하기  5. 음식 삭제 하기        0.로그아웃 ***");
                        
                        while (true) {
                            try {
                                choice = sc.nextInt();
                                sc.nextLine();
                                if (choice != 1 && choice != 2 && choice != 3 && choice != 0
                                        && choice != 4 && choice != 5) {
                                    throw new InputMismatchException();
                                } else
                                    break;

                            } catch (InputMismatchException e) {
                                // TODO: handle exception
                                sc = new Scanner(System.in);
                                System.out.println("올바른 숫자를 입력해주세요.");
                            }
                        }

                     // ---------- 총 매출 확인 -------------    
                        if (choice == 1) {
                            manager.totalSales();

                            
                     // ---------- 전체 영수증 출력 -------------       
                        } else if (choice == 2) {
                            manager.receipt_Checkt();
                                
                    // ---------- 음식 가격 변경 -------------
                        } else if (choice == 3) {
                            String changeName = null;
                            String changePrice = null;
                            clearScreen();
                            System.out.println("*******************************************");
                            System.out.println("***             음식 가격 변경             ***");
                            System.out.println("*** 변경 할 음식의 이름을 입력해주세요 ex) 피자 ***");
                            System.out.print(">");
                            changeName = sc.nextLine();
                            System.out.println("변경 할 금액을 입력해주세요.");
                            System.out.print(">");
                            changePrice = sc.nextLine();

                            manager.priceChange(changeName, changePrice);
                            break;
                     // ---------- 음식 추가 하기 -------------
                        } else if (choice == 4) {
                            clearScreen();
                            manager.addFood();
                            break;
                     // ---------- 음식 삭제 하기 -------------
                        } else if (choice == 5) {
                            clearScreen();
                            manager.deleteFood();
                            break;
                     // ---------- 로 그 아 웃 -------------
                        } else if (choice == 0) {
                            clearScreen();
                            System.out.println("**********로 그 아 웃 **********");
                            manager.setLogin(false);
                            menuChoice();
                        }
                    }
                }
            }
        }
    }
}

 // ---------- 카테고리 선택 -------------
    void categoryChoice() {
        final int MAIN = 1;
        final int SIDE = 2;
        final int BEVERAGE = 3;
        final int BACK = 0;
        count = 1;
        FoodsCheck.clear();
     // ---------- Food.txt 로드 -------------
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
    //--------------------------------------------
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
                 // ---------- 메인 메뉴 -------------
                    clearScreen();
                    System.out.println("********************************************");
                    System.out.println("***               메 인 메 뉴              ***");
                    menuprint(MAIN_CODE);
                    break;
                }
                case SIDE: {
                 // ---------- 사이드 메뉴 -------------
                    clearScreen();
                    System.out.println("********************************************");
                    System.out.println("***             사 이 드 메 뉴              ***");
                    menuprint(SIDE_CODE);
                    break;
                }
                case BEVERAGE: {
                 // ----------- 음료 --------------
                    clearScreen();
                    System.out.println("********************************************");
                    System.out.println("***                 음  료                ***");
                    menuprint(BEVERAGE_CODE);
                    break;

                }
                
            }
        }
    }
 // ---------- 관리자 로그인 -------------
    public void managerLogin() {
        // 정규표현식
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
// ---------- 결제 하기 -------------    
    public void payment(int totalPrice) {
        System.out.println("결제 하시겠습니까 ?");
        System.out.println("1. 네   2. 아니요");
        int choice = 0;
        while(true) {
            try {
                choice = sc.nextInt();
                sc.nextLine();
                if(choice != 1 && choice != 2) {
                    throw new InputMismatchException();
                }else{
                    break;
                }
            } catch (InputMismatchException e) {
                sc = new Scanner(System.in);
                System.out.println("올바른 숫자를 입력해주세요.");
            } 
        }
        
        if(choice == 1) {
            System.out.println("결제가 완료되었습니다.");
            System.out.println("\\t\\t총 결제 금액 : " + totalPrice);
        }else if(choice == 2) {
            System.out.println("취소하였습니다.");
            return;
        }

    }
 // ---------- 영수증 -------------    
    void receipt() {

    }

 // ---------- 장바구니 추가 -------------
    void cart_Add(int Key) {
        int num = 1;
        int price = 0;

        String keyname = FoodsCheck.get(Key).getName(); // Key를 이용해 해당 key의 value의 name을 받아옴.

        if (!Cart.isEmpty()) {// Cart가 비어있지 않을때
            Iterator<Food> it = Cart.iterator();
            while (it.hasNext()) {
                Food now = it.next();
                if (now.getName().equals(keyname)) {// 현재 name와 파라미터 key의 name을 비교
                    now.setNum();
                    now.doublePrice();
                    break;
                }

            }
         //-- Cart.add(FoodsCheck.get(Key));// Cart에 동일한 Food가 없으면 add --

        } else {// Cart가 비어있을때
            Cart.add(FoodsCheck.get(Key));

        }

        cart_Check();

    }

 // ---------- 장바구니 확인 -------------
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
                    sc = new Scanner(System.in);
                    System.out.println("정확한 번호를 입력해주세요...");
                } 
            }
        } else
            System.out.println("장바구니가 비어있습니다... 상품을 골라주세요");

    }
    
 // ---------- 장바구니 삭제 -------------
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
