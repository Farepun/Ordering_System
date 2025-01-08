public class Customer {
    private String IC;
    private String name;
    private String address;
    private String Licience;
    private String PNumber;

    public Customer(){
        IC = "";
        name = "";
        address = "";
        Licience = "";
    }
    
    public Customer(String iC, String name, String address, String licience, String pNumber) {
        IC = iC;
        this.name = name;
        this.address = address;
        Licience = licience;
        PNumber = pNumber;
    }
    public String getIC() {
        return IC;
    }
    public void setIC(String iC) {
        IC = iC;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getLicience() {
        return Licience;
    }
    public void setLicience(String licience) {
        Licience = licience;
    }
    public String getPNumber() {
        return PNumber;
    }
    public void setPNumber(String pNumber) {
        PNumber = pNumber;
    }

    
}
