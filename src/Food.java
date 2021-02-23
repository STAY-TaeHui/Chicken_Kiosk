
public class Food {
    String name; // 이름
    String price; // 가격
    String category; // 카테고리
    
    @Override
    public String toString() {
        return " 메뉴 : " + name + "  price : " + price + "원";
    }
}
