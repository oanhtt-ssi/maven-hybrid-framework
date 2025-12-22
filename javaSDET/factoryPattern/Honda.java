package factoryPattern;

public class Honda extends CarFactory {
    @Override
    public void viewCar() {
        System.out.println("View Honda car");
    }

    @Override
    public void driveCar() {
        System.out.println("Drive Honda car");
    }

    @Override
    public void buyCar() {
        System.out.println("Buy Honda car");
    }
}
