

public class Food{
    private String name;
    private String price;
    private String category;
    
    public Food(String name, String price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }
    
    
}
