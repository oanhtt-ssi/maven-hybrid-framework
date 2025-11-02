package javaOOP;

public abstract class Computer {
    private String ram;
    private String ssd;
    private String name;
    private String chip;

    public abstract void setColor(String colorName);

    public String getSsd() {
        return ssd;
    }

    public void setSsd(String ssd) {
        this.ssd = ssd;
    }


    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected void setChip(String chipName){
        this.chip = chipName;
    }
}
