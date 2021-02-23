import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chicken_Kiosk_Main {

    
   

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Kiosk k = new Kiosk();
        
         
        k.menuChoice();
        k.categoryChoice(0);
        k.managerLogin();
        
    }



}
