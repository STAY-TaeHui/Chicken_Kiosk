import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {
    private String id;
    private String password;
    private boolean login;

    private String Temp;
    private Map<Integer, Food> FoodsCheck;
    private FileWriter fw;
    private BufferedWriter bw;
    private FileReader fr;
    private BufferedReader br;
    private String Foods[];
    private int count;

    public Manager() {
        this.id = "admin1234";
        this.password = "a123456!";
        this.login = false;
        this.count = 1;
        this.Foods = new String[3];
        this.Temp = "Food.txt";
        this.FoodsCheck = new HashMap<Integer, Food>();
    }

    public void totalSales() {

    }

    public void receipt_Checkt() {

    }

    public void priceChange(String name, String price) {
        FoodCheck();
        Boolean foodCheck = false;
        
        for (int i = 1; i <= FoodsCheck.size(); i++) {
            if (FoodsCheck.get(i).getName().equals(name)) {
                FoodsCheck.get(i).setPrice(price);
                System.out.printf(" [%s]의 가격이 [%s원]으로 변경 되었습니다.\n", name, price);
                foodCheck = true;
            }
        }
        if(!foodCheck) {
            System.out.println("해당음식이 존재하지 않습니다.");
            return;
        }
        try {
            fw = new FileWriter(Temp);
            bw = new BufferedWriter(fw);

            String toData = null;
            for (int i = 1; i <= FoodsCheck.size(); i++) {
                toData = FoodsCheck.get(i).toString();
                bw.write(toData + "\n");

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
