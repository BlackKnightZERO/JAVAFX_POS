import java.io.IOException;

import javafx.collections.ObservableList;

public class Computed {

    private static ObservableList<Item> orderList;
    private static ObservableList<Item> itemList;

    public static String getTotalIncome(){
        String result = "$";
        double total = 0;
        try {
            orderList = Observer.getObservableOrderList();
            
            for(Item item : orderList) {
                total += item.getPrice();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result+total;
    }

    public static String getTotalOrders(){
        String result = " ";
        try {
            orderList = Observer.getObservableOrderList();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return result+orderList.size();
    }

    public static String getTotalItems(){
        String result = " ";
        try {
            itemList = Observer.getObservableItemList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result+itemList.size();
    }
}
