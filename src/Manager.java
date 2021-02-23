
public class Manager {
    private String id; // 아이디
    private String password ; // 비밀번호
    private boolean login; // 로그인 여부
    
    public Manager() {
        this.id = "admin123";
        this.password = "a123456789!";
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isLogin() {
        return login;
    }

    public void setLogin(boolean login) {
        this.login = login;
    }

    // 총 매출 확인
    void totalSales() {
        
    }
    // 영수증 데이터 전체 확인
    void receipt_Checkt() {
        
    }
    // 음식 가격 변경
    void priceChange(String name, String price) {
        
    }
}
