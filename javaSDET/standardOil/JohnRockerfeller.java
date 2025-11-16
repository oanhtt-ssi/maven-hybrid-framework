package standardOil;

public class JohnRockerfeller {

    //Thuộc tính
    public String companyName = "Standard Oil Company";
    public String compayProduct = "E95 Oil";

    String companyPolicy = "Rockerfeller Salary Tax Cash";

    protected String rockerFrendship = "Abraham Lincoln President";

    private String keySecret = "ROCK-2025-***";


    //Phương thức
    public void viewCompany(){
        System.out.println(companyName);
    }

    void  viewPolicy(){
        System.out.println(companyPolicy);
    }

    protected void viewFriendship(){
        System.out.println(rockerFrendship);
    }

    private void viewKeySecret(){
        System.out.println(keySecret);
    }

    public static void main(String[] args) {
        JohnRockerfeller john = new JohnRockerfeller();
        john.viewCompany();
        john.viewPolicy();
        john.viewFriendship();
        john.viewKeySecret();
    }
}
