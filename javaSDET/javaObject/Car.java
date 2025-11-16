package javaObject;

public abstract class Car {
    //Abstract Method
    //Bắt buộc phải override
    public abstract void viewCar();

    //Non-Abstract Method
    //Cho lớp con (sub-class) sử dụng trực tiếp
    //Cho phép override lại
    public void driveCar(){

    }
}
