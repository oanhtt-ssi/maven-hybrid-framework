package keywords;

public class Dell extends Networking implements IComputer{
    String ssd; //Global
    public Dell(String ssd){
        //Trong hàm khởi tạo của lớp con phải gọi qua hàm khởi tạo của lớp cha = super
        super("Router");

        System.out.println(ssd);
        System.out.println(this.ssd);
    }
}
