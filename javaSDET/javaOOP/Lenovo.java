package javaOOP;

public class Lenovo extends Computer implements IComputer{
    public static void main(String[] args) {

        Lenovo thinkPadX1Carbon = new Lenovo();
        thinkPadX1Carbon.setName("Lenovo Think Pad X1 Carbon");
        thinkPadX1Carbon.setChip("Intel Core i9");
        thinkPadX1Carbon.setRam("8GB");
        thinkPadX1Carbon.setSsd("256GB");
        thinkPadX1Carbon.setColor("");
        thinkPadX1Carbon.setResolution("");
        System.out.println(thinkPadX1Carbon.getName());
        System.out.println(thinkPadX1Carbon.getRam());
        System.out.println(thinkPadX1Carbon.getSsd());

        Lenovo ideaPad = new Lenovo();
        ideaPad.setName("Lenovo IdeaPad");
        ideaPad.setRam("16GB");
        ideaPad.setSsd("512GB");
        System.out.println(ideaPad.getName());
        System.out.println(ideaPad.getRam());
        System.out.println(ideaPad.getSsd());

    }
    @Override
    public void setColor(String colorName){
        System.out.println(colorName);
    }

    @Override
    public void setResolution(String resolution){

    }
}
