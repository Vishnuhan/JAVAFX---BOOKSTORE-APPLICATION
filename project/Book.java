/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package coe528.project;
import javafx.scene.control.CheckBox;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
 *
 * @author vishnu @ harpreet
 */
public class Book{

    static void add(Book book){    
    }
    
    private SimpleStringProperty bookName;
    private SimpleIntegerProperty price;
    private CheckBox select;
    
    public Book(){
        this.bookName = new SimpleStringProperty("");
        this.price = new SimpleIntegerProperty(0);
        this.select = new CheckBox();
    }
    
    public Book(String bookName, int price){
        this.bookName = new SimpleStringProperty(bookName);
        
        this.price = new SimpleIntegerProperty(price);
        this.select = new CheckBox();
    }
    
    public String getBookName(){
        return this.bookName.get();
    }
    
    public int getPrice(){
        return this.price.get();
    }
    public CheckBox getSelect(){
        return select;
    }
    public void setSelect(CheckBox select){
        this.select = select;
    }
}
