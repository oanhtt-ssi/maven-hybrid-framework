package keywords;

public class Networking {
    //Phạm vi truy cập - Access modifier

    // Sử dụng trong phạm vi class này
    private String switchProduct = "";

    //Sử dụng thông qua kế thừa
    protected String hubProduct = "";

    // Sử dụng trong cùng 1 package
    String firewall = "";

    //Dùng trong toàn bộ hệ thống/framwork
    public String routerProduct = "";

    //Hàm khởi tạo
    public Networking(String productName){

    }
}
