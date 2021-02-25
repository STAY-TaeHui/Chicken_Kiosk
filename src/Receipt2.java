import java.text.SimpleDateFormat;
import java.util.List;
import java.util.*;
import java.io.*;

public class Receipt2 {

    private File saveTemp;
    private PrintWriter save; // >> printf , println
    
    private FileReader load;
    private String transactionDate;
    private String transactionTime;
    private String Foodname;
    private int Foodprice;
    private int totalPrice;
    private int Foodnum;
    private int ordernum;
    private SimpleDateFormat simpledate;
    private Date date;
    
    public Receipt2() {
        // TODO Auto-generated constructor stub
        simpledate = new SimpleDateFormat("YYYY/MM/DD HH:mm:ss");
    }
    void totalSalse() {
    }

    void receiptWrite(List<Food> Cart, int totalPrice, int ordernum) { //Cart와 totalPrice를 받아와 사용
        this.ordernum++;
        date = new Date();
        try {
            save = new PrintWriter(this.ordernum+".txt");
         // -----------------  영수증 윗단  ---------------------
            save.printf("%s\n", simpledate.format(date));
            save.printf("========================================\n");
            save.printf("             주문번호 : "+ordernum+"            \n");
            save.printf("========================================\n");
            save.printf("                  [영수증]                \n");
            save.printf("-----------------------------------------\n");
            save.printf("주문일시   " + simpledate.format(date) + "\n");
            save.printf("=======================================\n");
            save.printf("상품명               수량              가격\n");
            //while을 통해 Cart정보 추출 >> 영수증 중간
            Iterator<Food> it = Cart.iterator();
            while(it.hasNext()) {
                Food now_food = it.next();
                save.printf("메뉴 : %10s  수량 : %10d   가격 : %10d\n",now_food.getName(),now_food.getNum(),now_food.getAddprice());
            }
            
         // -----------------  영수증 하단  ---------------------
            save.printf("총 가격 : %15d", totalPrice);     //영수증 하단
            
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {
            save.close();
        }
    }       
    void setFoodinfo(String Foodname, int Foodprice, int totalPrice, int Foodnum, int ordernum) {
        this.Foodname=Foodname;
        this.Foodprice = Foodprice;
        this.totalPrice = totalPrice;
        this.Foodnum = Foodnum;
        this.ordernum = ordernum;
    }

}
