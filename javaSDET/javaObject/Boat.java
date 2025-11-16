package javaObject;

public interface Boat {
    //Nếu như không define từ khóa abstract thì trình biên dịch
    //vẫn tự hiểu tất cả đều là abstract method
    public void clickToElement();
    public abstract void clickToCheckbox();

    //public void selectDropdown(){}
}
