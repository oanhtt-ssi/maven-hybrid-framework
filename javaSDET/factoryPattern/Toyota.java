package factoryPattern;

public class Toyota extends CarFactory {
    @Override
    public void viewCar() {
        System.out.println("View Toyota car");
    }

    @Override
    public void driveCar() {
        System.out.println("Drive Toyota car");
    }

    @Override
    public void buyCar() {
        System.out.println("Buy Toyota car");
    }
}
