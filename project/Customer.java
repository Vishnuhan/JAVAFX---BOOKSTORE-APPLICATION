/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
@author muhammad @ harpreet
 */

package coe528.project;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
public class Customer{

    static void add(Customer customer){
    }
    
    private SimpleStringProperty userName;
    private SimpleStringProperty passWord;
    private SimpleStringProperty status;
    private SimpleIntegerProperty points;

public Customer(){
    this.userName = new SimpleStringProperty("");
    this.passWord = new SimpleStringProperty("");
    this.points = new SimpleIntegerProperty(0);
    this.status = new SimpleStringProperty("Silver");
}

public Customer(String userName, String passWord){
    this.userName = new SimpleStringProperty(userName);
    this.passWord = new SimpleStringProperty(passWord);
    this.points = new SimpleIntegerProperty(0);
    this.status = new SimpleStringProperty("Silver");    
}

public Customer(String userName, String passWord, int points){
    this.userName = new SimpleStringProperty(userName);

    this.passWord = new SimpleStringProperty(passWord);
    this.points = new SimpleIntegerProperty(points);
    
    if (points > 999){
        this.status = new SimpleStringProperty("Gold");
    } else {
        this.status = new SimpleStringProperty("Silver");
    }  
}

public String getUserName(){
    return this.userName.get();
}

public String getPassWord(){
    return this.passWord.get();
}

public int getPoints(){
    return this.points.get();
}

public void setPoints(int points){
    this.points = new SimpleIntegerProperty(points);
    if (points > 999){
        this.status = new SimpleStringProperty("Gold");
    } else {
        this.status = new SimpleStringProperty("Silver");
    }
}

public String getStatus(){
        return this.status.get();
    }
}