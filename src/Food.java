
public class Food {
    private String name;
    private String price; //정가
    private int addprice; // 총 액
    private int num; // 수량
    private String category;

    public Food(String name, String price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.addprice = Integer.parseInt(price);
        this.num = 1;
    }


    public int getAddprice() {
        return addprice;
    }

    public void setAddprice(int price) {
        this.addprice += price;
    }


    
//-------- getter setter ----------

    public int getNum() {
        return num;
    }


    public void setNum(int num) {
        this.num += num;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price2) {
        this.price = price2;
    }

//    public void doublePrice() {
//        this.price = ""+(Integer.parseInt(this.price)+Integer.parseInt(this.price));
//    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return this.name + "," + this.price + "," + this.category;
    }

}
