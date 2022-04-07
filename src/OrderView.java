public class OrderView {

    private String time;
    private String name;
    private String category;
    private double price;
    
    public OrderView(String time, String name, String category, double price) {
        this.time = time;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    

}
