public class Vehicle{
    private int year;
    private boolean isAvailable;
    private int price;
    private String imagePath;
    private int Days;

    public Vehicle(){
        year = 0;
        isAvailable = true;
        price = 0;
        imagePath = " ";
        Days = 0;
    }

    public Vehicle(int year, boolean isAvailable, int price, String imagePath, int Days) {
        this.year = year;
        this.isAvailable = isAvailable;
        this.price = price;
        this.imagePath = imagePath;
        this.Days = Days;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public int getPrice() {
        return price;
    }
    public void setPrice(int price) {
        this.price = price;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    public int getDays() {
        return Days;
    }

    public void setDays(int days) {
        this.Days = days;
    }

    @Override
    public String toString() {
        return ", year=" + year + ", isAvailable=" + isAvailable
                + ", price=" + price + ", Days=" + Days + "]";
    }

    
}
