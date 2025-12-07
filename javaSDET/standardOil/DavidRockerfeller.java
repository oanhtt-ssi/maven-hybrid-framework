package standardOil;

public class DavidRockerfeller extends JohnRockerfeller{
    public static void main(String[] args) {
        DavidRockerfeller david = new DavidRockerfeller();
        david.viewCompanyName();
        david.viewPolicy();
        david.viewFriendship();
        //david.viewKeySecret();

    }

    public void viewCompanyName(){
        viewCompany();
        viewFriendship();
    }
}
