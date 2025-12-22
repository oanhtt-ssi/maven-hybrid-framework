package factoryPattern;

public class EndUser {
    public static void main(String[] args) {
        CarFactory car;

        car = getCar("Honda");
        car.viewCar();
        car.driveCar();
        car.buyCar();

        car = getCar("Huyndai");
        car.viewCar();
        car.driveCar();
        car.buyCar();

        car = getCar("Toyota");
        car.viewCar();
        car.driveCar();
        car.buyCar();

        car = getCar("Vinfast");
        car.viewCar();
        car.driveCar();
        car.buyCar();

    }

    public static CarFactory getCar(String carName){
        CarFactory carFactory = null;

        switch (carName){
            case "Honda":
                carFactory = new Honda();
                break;
            case "Huyndai":
                carFactory = new Huyndai();
                break;
            case "Toyota":
                carFactory = new Toyota();
                break;
            case "Vinfast":
                carFactory = new Vinfast();
                break;
        }
        return carFactory;
    }
}
