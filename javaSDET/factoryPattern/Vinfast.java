package factoryPattern;

public class Vinfast extends CarFactory {
    @Override
    public void viewCar() {
        System.out.println("View Vinfast car");
    }

    @Override
    public void driveCar() {
        System.out.println("Drive Vinfast car");
    }

    @Override
    public void buyCar() {
        System.out.println("Buy Vinfast car");
    }
}
