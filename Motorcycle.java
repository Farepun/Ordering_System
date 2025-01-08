public  class Motorcycle extends Vehicle {

    private String brand;
    private String model;

    public Motorcycle() {
        super();
        brand = " ";
        model = " ";
    }
    
    public Motorcycle(String brand, String model, int year, boolean isAvailable, int price, String imagePath, int Days) {
        super();
        this.brand = brand;
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public String toString(){
        return  "Brand: " + brand + " Model: " + model + super.toString();
    }
    

    
}
