package javaObject;

public class Honda extends Car{
    String hondaCivic;

    public static void main(String[] args) {
        Honda honda = new Honda();
        honda.driveCar();

        Honda city = new Honda();
    }

    public void viewHondaCivic(){

    }

    //Non-abstract class không chứa abstract method
    //Abstract method - hàm không chứa phần thân
    //Sử dụng để cho các lớp con kế thừa phải implement lại
    //Thể hiện cho tính chất trừu tượng (Abstraction)
    //public abstract void driveHondaCivic();

    public final void driveHondaCivic(){

    }

    @Override
    public void viewCar() {

    }
}
