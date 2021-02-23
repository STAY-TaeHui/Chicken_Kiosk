

public class Food{
    private String name;
    private String price;
    private int num;
    

    private String category;
    
    public Food(String name, String price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.num=1;
    }

    public int getNum() {
        return num;
    }

    public void setNum() {
        this.num +=1;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice() {
        this.price +=this.price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
    
    
}
