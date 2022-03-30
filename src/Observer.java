import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.time.LocalDate;

public class Observer {

    public static ObservableList<Order> observeOrder = FXCollections.observableArrayList();

    public static ObservableList<Item> getObservableItemList() throws IOException{

        ObservableList<Item> observeItem = FXCollections.observableArrayList();
        
        BufferedReader reader = new BufferedReader(new FileReader("storage/inventory/batch0001.txt"));

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

    public static void setObservableOrderList(Order order) {

        if(!observeOrder.contains(order)){
            observeOrder.add(order);
            System.out.println("Added");
        }

    }

    public static void updateObservableOrderList() throws IOException{
        
        String filename = "storage/orders/"+LocalDate.now()+".txt";

        try {
            File file = new File(filename);
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fw       = new FileWriter(file,true);
            BufferedWriter bw   = new BufferedWriter(fw);
            PrintWriter pw      = new PrintWriter(bw);

            for(Order order : observeOrder) {
                pw.println(order.generateString());
            }
            pw.close();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        } 
        
    }

    public static ObservableList<Item> getObservableOrderList() throws IOException{

        String filename = "storage/orders/"+LocalDate.now()+".txt";

        ObservableList<Item> observeOrders = FXCollections.observableArrayList();
        
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        String singleLine;

        try {
            while( (singleLine = reader.readLine()) != null ) {
                String [] parses = singleLine.split("#");
                Item item = new Item(parses[1], parses[2], Double.parseDouble(parses[3]));
                observeOrders.add(item);
            }
        } catch(IOException ioe){
            ioe.printStackTrace();
        } finally {
            if(reader.readLine() == null) {
                reader.close();
            }
        }
        
        return observeOrders;

    }



    // // old code - overwrite problem
    // public static void updateObservableOrderList() throws IOException{
        
    //     String filename = "storage/orders/"+LocalDate.now()+".txt";
    //     BufferedWriter writer = new BufferedWriter(new FileWriter(filename));

    //     try {
    //         for(Order order : observeOrder) {
    //             writer.write(order.generateString()+"\n");
    //         }
    //     } catch(IOException ioe) {
    //         ioe.printStackTrace();
    //     } finally {
    //         writer.close();
    //     }
        
    // }

}
