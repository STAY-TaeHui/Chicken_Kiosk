import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Receipt {

    File saveTemp;
    FileInputStream save;
    FileOutputStream load;
    SimpleDateFormat sdf;
    Date today;
    File file;
    String Foodname;
    int Foodprice;
    int totalPrice;
    int Foodnum;
    int ordernum;
    FileWriter fw;
    BufferedWriter bw;

    void setFoodinfo(String Foodname, int Foodprice, int totalPrice, int Foodnum, int ordernum) {
        this.Foodname = Foodname;
        this.Foodprice = Foodprice;
        this.totalPrice = totalPrice;
        this.Foodnum = Foodnum;
        this.ordernum = ordernum;
    }

    // 영수증 출력
    public void receiptFrontPrint() {
        // 주문번호
        // 날짜, 시간
        // 왼쪽 음식
        // 오른쪽 가격
        // (수량)
        // 총액
        // 왼쪽 음식, 오른쪽 가격, (수량), 총액(수량 총액은 food에서 불러오기)

        // String foods = "";
        today = new Date();
        sdf = new SimpleDateFormat("yyyy년 MM월 dd일 E요일 HH:mm:ss");
        file = new File("receipt.txt");

        try {
            fw = new FileWriter(file, true);
            bw = new BufferedWriter(fw);
            bw.write("========================================\n");
            bw.write("             주문번호 : " + ordernum + "            \n");
            bw.write("========================================\n");
            bw.write("                  [영수증]                \n");
            bw.write("-----------------------------------------\n");
            bw.write("주문일시   " + sdf.format(today) + "\n");
            bw.write("=======================================\n");

            bw.write("상품명               수량              가격\n");
//         bw.write(f.getName() +    f.getNum() +   f.getPrice()+"\n");
//         bw.write("----------------------------------------\n");
//         
//         bw.write("총 결제금액" +         f.getAddprice());
//         bw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void receiptMiddlePrint() {

        try {
            bw.write(this.Foodname + "\t" + this.Foodnum + "\t" + this.Foodprice + "\n");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void receiptTailPrint() {
        try {
            bw.write("----------------------------------------\n");
            bw.write("총 결제금액" + this.totalPrice);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            try {
                bw.close();
                fw.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}