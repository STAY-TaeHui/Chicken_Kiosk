import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class Receipt {

    private SimpleDateFormat format;
    private int receiprNum;
    private FileWriter fw;
    private BufferedWriter bw;
    private Date date;
    private String TotalSalse;
    
    public Receipt() {
        format = new SimpleDateFormat("yyyy-MM-dd a HH시mm분ss초");
        date = new Date();
        receiprNum = 1;
        TotalSalse = "TotalSalse.txt";
    }
 
 // ---------- 총 매출 -------------        
    private void totalSalse(int totalPrice) {
        try {
            fw = new FileWriter(TotalSalse,true);
            bw = new BufferedWriter(fw);
            String data = format.format(date) +","+ totalPrice;
            bw.write("\n"+data); 
            bw.flush();
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception e2) {
            }
        }
    }

// ---------- 영수증 -------------    
    public void receiptWrite(List<Food> cart,int discount, int totalPrice) {
        System.out.println("영수증이 출력되었습니다.");
        totalSalse(totalPrice); // 총 매출 확인
        String hereTogo = "매장";
        if(discount > 0 ) {
            hereTogo = "포장";
        }
        try {
            fw = new FileWriter(receiprNum + "Receipt.txt");
            bw = new BufferedWriter(fw);
            
            bw.write("=============영 수 증==============\n");
            bw.write("= (치킨사조)                       =\n");
            bw.write("= 02-3486-9600                   =\n");
            bw.write("= 서울특별시 서초구 강남대로 459       =\n");
            bw.write("=                                =\n");
            bw.write("=              "+ hereTogo +"\t\t\t\t =\n");
            bw.write("=         [ 주문번호 : "+ receiprNum + " ]\t\t =\n");
            bw.write("=--------------------------------=\n");
            bw.write("=\t번호\t\t상품명\t가격\t\t수량\t =\n");
            // ----------------장바구니 확인-------------------------
            int count = 1;
            int totalprice = 0;
            String data = null;
            Iterator<Food> it = cart.iterator();
            while (it.hasNext()) {
            Food now = it.next();
            data = "=  \t " + count + "\t\t" + now.getName() + "\t" + now.getAddprice() + "\t " + now.getNum() + "\t =\n";
            bw.write(data);
           // bw.write("=  \t " + count + "\t\t" + now.getName() + " ");
           // bw.write("\t" + now.getAddprice() + "\t " + now.getNum() + "\t =\n");
            count++;
            totalprice += now.getAddprice();
            }
            // ---------------------------------------------------
            if(discount > 0 ) {
                bw.write("=               포장 할인 : " + discount + "\t =\n");
            }else {
                bw.write("=                                =\n");  
            }
            bw.write("=                                =\n");
            bw.write("=             ￦ 합 계 :  " + totalprice + " \t =\n");
            bw.write("=--------------------------------=\n");
            bw.write("=                                =\n");
            bw.write("=   " + format.format(date) + "\t =\n");
            bw.write("==================================\n");
            
            receiprNum++;
            bw.flush();
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception e2) {
            }
        }
    }

 // ---------- 관리자 영수증 -------------        
    public void addreceiptWrite(List<Food> cart,int discount, int totalPrice) {
        String hereTogo = "매장";
        if(discount > 0) {
            hereTogo = "포장";
        }
        try {
            fw = new FileWriter("AddReceipt.txt",true);
            bw = new BufferedWriter(fw);
            
            bw.write("   " + format.format(date) + "\n");
            bw.write("              "+ hereTogo +"\n");
            bw.write("----------------------------------\n");
            bw.write(" 번호\t상품명\t가격\t수량\t\n");
            // ----------------장바구니 확인-------------------------
            int count = 1;
            int totalprice = 0;
            Iterator<Food> it = cart.iterator();
            while (it.hasNext()) {
            Food now = it.next();
            
            bw.write("   " + count + "\t" + now.getName() + " ");
            bw.write("\t" + now.getAddprice() + "\t " + now.getNum() + "\t\n");
            count++;
            totalprice += now.getAddprice();
            }
            // ---------------------------------------------------
            if(discount > 0 ) {
                bw.write("               포장 할인 : " + discount + "\t \n");
            }else {
                bw.write("                                \n");  
            }
            bw.write("                                \n");
            bw.write("             ￦ 합 계 :  " +  totalprice + " \t\n");
            bw.write("==================================\n");
            
            bw.flush();
        } catch (Exception e) {
            // TODO: handle exception
        }finally {
            try {
                bw.close();
                fw.close();
            } catch (Exception e2) {
            }
        }
    }    
}
