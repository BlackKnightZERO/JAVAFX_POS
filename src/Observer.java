import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.time.LocalDate;

public class Observer {

    private static String inventoryDirectory = "storage/inventory/batch0001.txt";
    private static String orderTodayDirectory = "storage/orders/"+LocalDate.now()+".txt";

    public static Order currentOrder;

    public static ObservableList<Item> getObservableItemList() throws IOException{

        ObservableList<Item> observeItem = FXCollections.observableArrayList();
        
        BufferedReader reader = new BufferedReader(new FileReader(inventoryDirectory));

        String singleLine;

        try {
            while( (singleLine = reader.readLine()) != null ) {
                String [] parses = singleLine.split("#");
                Item item = new Item(parses[0], parses[1], Double.parseDouble(parses[2]));
                observeItem.add(item);
            }
        } catch(IOException ioe){
            ioe.printStackTrace();
        } finally {
            if(reader.readLine() == null) {
                reader.close();
            }
        }
        
        return observeItem;

    }

    public static ObservableList<Admin> getObservableAdminList() throws IOException{

        ObservableList<Admin> observeAdmin = FXCollections.observableArrayList();
        
        BufferedReader reader = new BufferedReader(new FileReader("storage/users/admin.txt"));

        String singleLine;

        try {
            while( (singleLine = reader.readLine()) != null ) {
                String [] parses = singleLine.split("#");
                Admin admin = new Admin(parses[0], parses[1], parses[2]);
                observeAdmin.add(admin);
            }
        } catch(IOException ioe){
            ioe.printStackTrace();
        } finally {
            if(reader.readLine() == null) {
                reader.close();
            }
        }
        
        return observeAdmin;

    }

    public static void setCurrentOrder(Order order) {

        currentOrder = order;
        System.out.println("Order placed");
        System.out.println("Current Order: "+currentOrder.generateString());

    }

    public static void updateOrderList() throws IOException{
        
        String filename = orderTodayDirectory;

        try {

            File file = new File(filename);

            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter fw       = new FileWriter(file,true);
            BufferedWriter bw   = new BufferedWriter(fw);
            PrintWriter pw      = new PrintWriter(bw);

            pw.println(currentOrder.generateString());    

            pw.close();

            System.out.println("Order List Updated");

        } catch(IOException ioe) {
            ioe.printStackTrace();
        } 
        
    }

    public static ObservableList<Item> getObservableOrderList() throws IOException{

        String filename = orderTodayDirectory;

        ObservableList<Item> observeOrders = FXCollections.observableArrayList();

        String singleLine;

        try {

            File file = new File(filename);
            if(!file.exists()){
                file.createNewFile();
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            
            while( (singleLine = reader.readLine()) != null ) {
                String [] parses = singleLine.split("#");
                Item item = new Item(parses[1], parses[2], Double.parseDouble(parses[3]));
                observeOrders.add(item);
            }

            if(reader.readLine() == null) {
                reader.close();
            }

        } catch(IOException ioe){
            ioe.printStackTrace();
        } 
        
        return observeOrders;

    }

    public static ObservableList<OrderView> getObservableOrderViewList() throws IOException{

        String filename = orderTodayDirectory;

        ObservableList<OrderView> observeOrdersView = FXCollections.observableArrayList();

        String singleLine;

        try {

            File file = new File(filename);
            if(!file.exists()){
                file.createNewFile();
            }
            
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            
            while( (singleLine = reader.readLine()) != null ) {
                String [] parses = singleLine.split("#");
                OrderView orderView = new OrderView(parses[0], parses[1], parses[2], Double.parseDouble(parses[3]));
                observeOrdersView.add(orderView);
            }

            if(reader.readLine() == null) {
                reader.close();
            }

        } catch(IOException ioe){
            ioe.printStackTrace();
        } 
        
        return observeOrdersView;

    }

    public static void addItemToInventory(Item item) throws IOException{
        
        String filename = inventoryDirectory;

        try {

            File file = new File(filename);

            if(!file.exists()){
                file.createNewFile();
            }

            FileWriter fw       = new FileWriter(file,true);
            BufferedWriter bw   = new BufferedWriter(fw);
            PrintWriter pw      = new PrintWriter(bw);

            pw.println(item.generateString());    

            pw.close();

            System.out.println("Inventory List Updated");

        } catch(IOException ioe) {
            ioe.printStackTrace();
        } 
        
    }

    public static void deleteItemFromInventory(int index) throws IOException{

        String filename = inventoryDirectory;

        ObservableList<Item> inventoryList = getObservableItemList();

        try {

            File file = new File(filename);

            if ( file.exists() ) {
                inventoryList.remove(index);

                BufferedWriter writer = new BufferedWriter(new FileWriter(inventoryDirectory));

                for(Item item : inventoryList) {
                    writer.write(item.generateString()+"\n");
                }

                writer.close();

                System.out.println("Inventory List Updated");

            } else {
                throw new IOException("File Not Found!");
            }   

        } catch(IOException ioe) {
            ioe.getMessage();
            ioe.printStackTrace();
        } catch(Exception ex) {
            ex.printStackTrace();
        }

    }

}
