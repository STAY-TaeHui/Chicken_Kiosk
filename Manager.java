package kr.or.kiosk;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Manager {
    private String id;
    private String password ;
    private boolean login;
    private String path = "C:\\Temp\\receipt.txt";
	private FileReader fr = null;
	private BufferedReader br = null;
	private File receipt = new File(path);
    //총 매출
    void totalSales() {
		if(login==true) {
		try {
			//receipt.createNewFile();
			fr = new FileReader(receipt);
			br = new BufferedReader(fr);
			
			if(receipt.exists()) {
				String line = null;
				int sum = 0;
				while((line = br.readLine()) != null) {
					String[] line2 = line.split(" ");
					//for(int i=0; i<line2.length; i++) {
						int resprice = Integer.parseInt(line2[1]);
						System.out.println(resprice);
						//System.out.println(line2[i]);
						sum += resprice;
						
					//}
				}
				System.out.println("Today total> "+sum);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				fr.close();
				br.close();
			} catch (Exception e2) {
				e2.getStackTrace();
			}
			}
		}
    }
    
    //영수증..?무엇..?
    void receipt_Checkt() {
    	if(login==true) {
    	try {
			br = new BufferedReader(new FileReader(path));
			while(true) {
				if(receipt.exists()) {
					String line = br.readLine();
					if(line==null) break;
					System.out.println(line);
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}finally {
			try {
				br.close();
				//fr.close();
			} catch (Exception e2) {
				e2.getStackTrace();
			}
			}
		}
    }
    //가격 변경
    void priceChange(String name, String price) {
        
    }
}
