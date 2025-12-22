package factoryPattern;

public class Huyndai extends CarFactory {
    @Override
    public void viewCar() {
        System.out.println("View Huyndai car");
    }

    @Override
    public void driveCar() {
        System.out.println("Drive Huyndai car");
    }

    @Override
    public void buyCar() {
        System.out.println("Buy Huyndai car");
    }
}
