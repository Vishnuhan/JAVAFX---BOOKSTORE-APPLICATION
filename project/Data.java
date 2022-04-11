/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coe528.project;

/**
 *
 * @author vishnu @ harpreet
 */
import java.io.*;
import static java.time.Clock.system;
import java.util.Scanner;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Data {
    private String bookName;
    private int price;
    private String username;
    private String password;
    private int points; 
    private ObservableList<Book> books = FXCollections.observableArrayList();
    private ObservableList<Customer> customers = FXCollections.observableArrayList();
    private static Data instance; // singleton design pattern
    
    
    public static Data getInstance(){ 
        if (instance == null){
            instance = new Data();
        }
        return instance;
    }

    public void loadBooks() throws IOException{
      try{
            
            File book = new File("books.txt");     
            if (!book.exists()) {
                 book.createNewFile();
                 
            }
            else{
                
                BufferedReader reader = new BufferedReader(new FileReader(book));
                
                String line = reader.readLine();
                while (line != null) {
                    
                    
                    String info[] = line.split(", ");
                    this.bookName = info[0];
                    this.price = Integer.parseInt(info[1]);
                    
                    books.add(new Book(this.bookName, this.price));

                     
                    line = reader.readLine();
                }
                reader.close();
           }   
        } catch (Exception e){
            System.out.println(e);
        } 
    }
    
    public void loadCustomers() throws IOException{
        try{
            File customer = new File("customers.txt");
            if(!customer.exists()){
                customer.createNewFile();
            }
            else{
                BufferedReader reader = new BufferedReader(new FileReader(customer));

                String line = reader.readLine();
                while (line != null) {
                    
                    String info[] = line.split(", ");
                    username = info[0];
                    password = info[1];
                    points = Integer.parseInt(info[2]);

                    customers.add(new Customer(username, password, points));
                    // read next line
                    line = reader.readLine();
                }
                reader.close();
            }
        } catch (Exception e){
            System.out.println("Invalid");
        } 
    }
    
    public ObservableList<Book> getBook(){ 
        return books;
    }
    
    public ObservableList<Customer> getCustomers(){
        return customers;
    }
    
    public void addBook(String title, int price) {
        books.add(new Book(title, price));
    }
    
    public void deleteBook(Book book) throws IOException{
        books.remove(book);
    }    
    
    public void addCustomer(String username, String password) throws IOException{
        customers.add(new Customer(username, password));
    }
    
    public void deleteCustomer(Customer customer) throws IOException{
        customers.remove(customer);
    }   
    
    public void closeProgram(){
        try { 
            File customerFile = new File("customers.txt");
            if (!customerFile.exists()) {
                customerFile.createNewFile();
            }
            else{
                PrintWriter cleanFile = new PrintWriter("customers.txt");
                cleanFile.print("");
                cleanFile.close();
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(customerFile, true)));
                int sizeOfCustomerArray = customers.size();
                for (int i=0; i<sizeOfCustomerArray; i++){
                out.println(customers.get(i).getUserName() + ", " + customers.get(i).getPassWord() + ", " + customers.get(i).getPoints());
                }
                out.flush();
                out.close();
            }
        }
        catch (IOException e) {  
          System.out.println(e);
        }
        
        try { 
            File booksFile = new File("books.txt");
            if (!booksFile.exists()) {
                booksFile.createNewFile();
            }
            else{
                PrintWriter cleanFile = new PrintWriter("books.txt");
                cleanFile.print("");
                cleanFile.close();
                PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(booksFile, true)));
                int sizeOfBooksArray = books.size();
                for (int i=0; i<sizeOfBooksArray; i++){
                out.println(books.get(i).getBookName() + ", " + books.get(i).getPrice());
                }
                out.flush();
                out.close();
            }
        }
        catch (IOException e) {  
          System.out.println(e);
        }
    
    }

    public void buyBook(Customer customer, int totalprice){
        int bookpoints = totalprice * 10;  // 10 points for every dollar spent
        int totalpoints = bookpoints + customer.getPoints();  // new points + already existing points = total points
        
        customer.setPoints(totalpoints);
    }
     
    public int buyBookPoints(Customer customer, int totalPrice){
        int availablePoints = (int) customer.getPoints(); 
        int remainingPoints = availablePoints;
        int usablePoints = 0;
        int usedPoints = 0;
        int remainingPrice = totalPrice;
        int paidCash = 0;
        
        // Book can only be brought with points if points are 100 or more
        if (availablePoints > 99){
            if (availablePoints/100 == totalPrice){
                // points are exactly same as books price
                usedPoints = remainingPoints;
                paidCash = 0;
                remainingPoints = remainingPoints - usedPoints;
                
            } else if (availablePoints/100 > totalPrice){
                // if more points are avaible than the books price
                paidCash = 0;
                remainingPoints = availablePoints - (totalPrice*100);
                
            } else if (availablePoints/100 < totalPrice){
                // if less points are available than booksprice
                // find out how many points can be used for purchase
                //System.out.println("Avaible points " + availablePoints);
                usablePoints = availablePoints - (remainingPoints%100);
                //System.out.println("Usable points " + usablePoints);
                usedPoints = usablePoints;
                //System.out.println("Used points " + usedPoints);
                remainingPoints = remainingPoints - usablePoints;
                //System.out.println("Remaining points " + remainingPoints);
                remainingPrice = remainingPrice - (usablePoints/100);
                //System.out.println("Remaining price " + remainingPrice);
                paidCash = remainingPrice;
                //System.out.println("Paid price " + paidCash);
                remainingPoints = remainingPoints + (paidCash*10);
                //System.out.println("Final points " + remainingPoints);
            }
            
        } else {
                // when less than 100 points are avaible
                usedPoints = 0;
                paidCash = remainingPrice;
                remainingPoints = remainingPoints + (paidCash*10);
                
        }
       
        // update the remaining points
        customer.setPoints(remainingPoints);
        return paidCash;
    }
    
    boolean validate(String user,String pass){
        int index = customers.size();
        int i;
        for(i=0;i<index;i++){
            if(customers.get(i).getUserName().equals(user) && customers.get(i).getPassWord().equals(pass)){
                return true;
            }
        }
        return false;
    }
    
    boolean userExists(String user){
        int index = customers.size();
        int i;
        for(i=0;i<index;i++){
            if(customers.get(i).getUserName().equals(user)){
                return true;
            }
        }
        return false;
    }
    
    boolean bookExists(String name){
        int index = books.size();
        int i;
        for(i=0;i<index;i++){
            if(books.get(i).getBookName().equals(name)){
                return true;
            }
        }
        return false;
    }
    
    Customer obtainUser(String user){
        int index = customers.size();
        int i;
        for(i=0;i<index;i++){
            if(customers.get(i).getUserName().equals(user)){
                return customers.get(i);
            }
        }
        return null;
    }
}