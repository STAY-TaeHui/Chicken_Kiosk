import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Manager {
    private String id;
    private String password;
    private boolean login;

    private Scanner sc;
    private String Temp;
    private Map<Integer, Food> FoodsCheck;
    private FileWriter fw;
    private BufferedWriter bw;
    private FileReader fr;
    private BufferedReader br;
    private String Foods[];
    private int count;
    private String TotalSalse;
    private String AddReceipt;
    private File receipt;

    
    public Manager() {
        this.id = "admin1234";
        this.password = "a123456!";
        this.login = false;
        this.count = 1;
        this.Foods = new String[3];
        this.Temp = "Food.txt";
        this.FoodsCheck = new HashMap<Integer, Food>();
        this.sc = new Scanner(System.in);
        this.TotalSalse = "TotalSalse.txt";
        this.AddReceipt = "AddReceipt.txt";

        
    }

//-------------- 총 매출 확인 --------------------//    
    public void totalSales() {
        this.receipt = new File(TotalSalse);
        if (login == true) {
            try {
                // receipt.createNewFile();
                fr = new FileReader(TotalSalse);
                br = new BufferedReader(fr);

                if (receipt.exists()) {
                    String line = null;
                    int sum = 0;
                    String[] line2 = new String[1];
                    while ((line = br.readLine()) != null) {
                        line2 = line.split(",");
                        int resprice = Integer.parseInt(line2[1]);
                        sum += resprice;
                    }
                    System.out.println("******************************************************");
                    System.out.println("***               Todat TotalSales                 ***");
                    System.out.println("***                   "  +  sum    + "원\t\t\t   ***");
                
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fr.close();
                    br.close();
                } catch (Exception e2) {
                    e2.getStackTrace();
                }
            }
        }
    }

//-------------- 전체 영수증 출력 --------------------//    
    public void receipt_Checkt() {
        this.receipt = new File(AddReceipt);
        if (login == true) {
            try {
                fr = new FileReader(AddReceipt);
                br = new BufferedReader(fr);
                String data = null;
                while ((data = br.readLine()) != null) {
                    System.out.println(data);
                }   
            } catch (Exception e) {
                System.out.println("출력할 영수증이 없습니다.");
            } finally {
                try {
                    br.close();
                    // fr.close();
                } catch (Exception e2) {
                    e2.getStackTrace();
                }
            }
        }
    }

//-------------- 음식 가격 변경 --------------------//    
    public void priceChange(String name, String price) {
        FoodCheck();
        Boolean foodCheck = false;

        for (int i = 1; i <= FoodsCheck.size(); i++) {
            if (FoodsCheck.get(i).getName().equals(name)) {
                FoodsCheck.get(i).setPrice(price);
                clearScreen();
                System.out.printf(" [%s]의 가격이 [%s원]으로 변경 되었습니다.\n", name, price);
                foodCheck = true;
            }
        }
        if (!foodCheck) {
            clearScreen();
            System.out.println("해당음식이 존재하지 않습니다.");
            return;
        }
        try {
            fw = new FileWriter(Temp);
            bw = new BufferedWriter(fw);

            String toData = null;
            for (int i = 1; i <= FoodsCheck.size(); i++) {
                toData = FoodsCheck.get(i).toString();
                if (i == 1) {
                    bw.write(toData);
                } else {
                    bw.write("\n" + toData);
                }
            }
            bw.flush();

        } catch (Exception e1) {
            System.out.println(e1.getMessage());
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

// -------------- 음식 추가 --------------------//
    public void addFood() {
        int choice = 0;
        String categoryName = null;
        String category = null;
        System.out.println("******************************************************");
        System.out.println("***                 MenuAddList                    ***");
        System.out.println("***                                                ***");
        System.out.println("***    1. 메인메뉴 추가하기       2. 사이드메뉴 추가하기    ***");
        System.out.println("***    3. 음료 추가하기                       0.취소   ***");

        while (true) {
            try {
                choice = sc.nextInt();
                sc.nextLine();
                if (choice != 0 && choice != 1 && choice != 2 && choice != 3) {
                    throw new InputMismatchException();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                sc = new Scanner(System.in);
                System.out.println("올바른 숫자를 입력해주세요.");
            }
        }

        clearScreen();
        if (choice == 1) {
            category = "101";
            categoryName = "메인 메뉴";
            System.out.println("******************************************************");
            System.out.println("***                 메인 메뉴 추가                    ***");
        } else if (choice == 2) {
            category = "201";
            categoryName = "사이드 메뉴";
            System.out.println("******************************************************");
            System.out.println("***                 사이드메뉴 추가                    ***");
        } else if (choice == 3) {
            category = "301";
            categoryName = "음료 메뉴";
            System.out.println("******************************************************");
            System.out.println("***                   음료 추가                      ***");
        } else if (choice == 0) {
            System.out.println("취소하였습니다.");
            return;
        }
        System.out.println("***            추가할 음식의 이름을 입력해주세요          ***");
        System.out.print(">");
        String addName = sc.nextLine();
        System.out.printf("                 [%s] 의 가격을 입력해주세요\n", addName);
        System.out.print(">");
        String addPrice = sc.nextLine();
        clearScreen();
        System.out.printf("[%s]에 [%s] 추가하였습니다.\n", categoryName, addName);

        try {
            fw = new FileWriter(Temp, true);
            bw = new BufferedWriter(fw);

            String data = addName + "," + addPrice + "," + category;
            bw.write("\n" + data);
            bw.flush();

        } catch (Exception e) {

        } finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception e2) {

            }
        }

    }

// -------------- 음식 삭제 --------------------//
    public void deleteFood() {
        FoodCheck();
        String category = null;

        int choice = 0;
        System.out.println("*******************************************");
        System.out.println("*** 삭제하고 싶은 음식이 있는 메뉴를 선택해주세요 ***");
        System.out.println("***                              0.취소  ***");
        System.out.println("***   1. 메인메뉴    2. 사이드메뉴   3. 음료  ***");
        while (true) {
            try {
                choice = sc.nextInt();
                sc.nextLine();
                if (choice != 0 && choice != 1 && choice != 2 && choice != 3) {
                    throw new InputMismatchException();
                } else {
                    break;
                }
            } catch (InputMismatchException e) {
                sc = new Scanner(System.in);
                System.out.println("올바른 숫자를 입력해주세요.");
            }
        }
        if (choice == 1) {
            category = "101";
            System.out.println("                                  [메인 메뉴]");
        } else if (choice == 2) {
            category = "201";
            System.out.println("                                  [사이드 메뉴]");
        } else if (choice == 3) {
            category = "301";
            System.out.println("                                        [음료]");
        } else if (choice == 0) {
            System.out.println("취소하였습니다.");
            return;
        }
        List<Integer> DeleteTemp = new ArrayList<Integer>();
        int C = 1;
        System.out.println("********************************************");
        System.out.println("***           Food_Delete_List           ***");
        System.out.println("*** \t번호\t상품명\t\t가격\t ***");
        for (int i = 1; i <= FoodsCheck.size(); i++) {
            if (FoodsCheck.get(i).getCategory().equals(category)) {
                System.out.printf("*** \t %s\t%s\t\t%s원\t ***\n", C, FoodsCheck.get(i).getName(),
                        FoodsCheck.get(i).getPrice());
                C++;
                DeleteTemp.add(i);
            }
        }
        System.out.println("삭제할 상품의 번호를 입력해주세요   Ex) 3");
        choice = sc.nextInt();
        sc.nextLine();
        System.out.printf("[%s] 삭제하였습니다.\n", FoodsCheck.get(DeleteTemp.get(choice - 1)).getName());
        FoodsCheck.remove(DeleteTemp.get(choice - 1));
        try {
            fw = new FileWriter(Temp);
            bw = new BufferedWriter(fw);

            String data = null;
            for (int i = 1; i <= FoodsCheck.size() + 1; i++) {
                if (!FoodsCheck.containsKey(i)) {
                    continue;
                } else {
                    data = FoodsCheck.get(i).toString();
                    bw.write(data + "\n");
                }
            }
            bw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception e2) {

            }
        }
    }

// -----------------------------------------------------------------------
    // Food.txt 로드
    private void FoodCheck() {
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
            System.out.println(e1.getMessage());
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // ---------- 줄 올리기 -------------
    void clearScreen() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    // -------- getter setter ----------
    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public Map<Integer, Food> getFoodsCheck() {
        return FoodsCheck;
    }

}
