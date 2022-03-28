import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Order {
    
    // private LocalDateTime localDateTime;
    private String orderTimeStamp;
    private Item item;

    public Order(Item item) {
        this.orderTimeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
        this.item           = item;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getOrderTimeStamp() {
        return this.orderTimeStamp;
    }

    public String generateString() {
        return this.getOrderTimeStamp()+"#"+item.getName()+"#"+item.getCategory()+"#"+item.getPrice();
    }

    @Override
    public String toString() {
        return "Order [item=" + item + ", orderTimeStamp=" + orderTimeStamp + "]";
    }
    

}
