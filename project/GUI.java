package coe528.project;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.geometry.Insets;
import java.lang.Integer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.CheckBox;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Font;
import javafx.stage.WindowEvent;

public class GUI extends Application {
   
    private Customer cUser;
    
         
    
    @Override
    public void start(Stage primaryStage) throws FileNotFoundException {
       

       //Tables for books and customers
       TableView<Book> bookTable = new TableView<Book>();//Admin
       bookTable.setItems(Data.getInstance().getBook());
       TableView<Book> bookTable2 = new TableView<Book>();//Customer
       bookTable2.setItems(Data.getInstance().getBook());
       
       TableView<Customer> customerTable = new TableView(); 
       customerTable.setItems(Data.getInstance().getCustomers());

        //Scene and Main Layout Initialization
        VBox loginMain = new VBox(10);
        Scene login = new Scene(loginMain,500,300);
        
        VBox AdminMenuMain = new VBox(20);
        Scene AdminMenu = new Scene(AdminMenuMain,500,300);
        
        VBox CBLMain = new VBox(10);
        Scene CustomerBookList = new Scene (CBLMain,500,500);
        
        VBox ABLMain = new VBox(10);
        Scene AdminBookList = new Scene (ABLMain,500,500);
        
        VBox CLMain = new VBox(10);
        Scene CustomerList = new Scene (CLMain,500,500);
        
        VBox CBuyMain = new VBox(10);
        Scene CBuy = new Scene(CBuyMain,500,300);
        
        //Logo Image
        Image logo = new Image(new FileInputStream("3DLogo.png"));
        ImageView logoView = new ImageView(logo);
        logoView.setFitHeight(100);
        logoView.setPreserveRatio(true);
        
        Label cWelcome = new Label("Welcome " +". You have " + " points. Your status is "); //Part of Customer Book List
        
        //Scene Table of Contents
        
        /*
        -login
        -admin menu
        -customer list
        -admin book list
        -customer buy screen
        -customer book list
        -stage setup
        */
        
        
        //Login:
        //nodes
        Label usernameLabel = new Label("Username:"); 
        Label passLabel = new Label("Password: "); 
        TextField usernameInput = new TextField();
        PasswordField passInput = new PasswordField();
        Label loginFail = new Label("");
        loginFail.setStyle("-fx-text-fill: red");
        
        Button loginBTN = new Button();
        loginBTN.setText("Login");
        loginBTN.setLayoutX(200);
        loginBTN.setLayoutY(20);
        loginBTN.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                if(usernameInput.getText().equals("") && passInput.getText().equals("") ){
                   loginFail.setText("Please enter username and password.");
                   }
                else if(usernameInput.getText().equals("admin") && passInput.getText().equals("admin") ){
                   loginFail.setText("");
                    primaryStage.setScene(AdminMenu);
                }
                else if(Data.getInstance().validate(usernameInput.getText(), passInput.getText())){
                    loginFail.setText("");
                    cUser = Data.getInstance().obtainUser(usernameInput.getText());
                    cWelcome.setText("Welcome " + cUser.getUserName() + ". You have " + cUser.getPoints() + " points. Your status is " + cUser.getStatus() + ".");
                    primaryStage.setScene(CustomerBookList);
                }
                else {loginFail.setText("Invalid username or password.");}
                
            }
        });
        //Login:
        //Layout
        HBox userRow = new HBox(10);
        userRow.getChildren().addAll(usernameLabel,usernameInput);
        userRow.setAlignment(Pos.CENTER);
        HBox passRow = new HBox(10);
        passRow.getChildren().addAll(passLabel,passInput);
        passRow.setAlignment(Pos.CENTER);
       
        loginMain.getChildren().addAll(logoView,userRow,passRow,loginFail,loginBTN);
        loginMain.setAlignment(Pos.CENTER);
        
        //Login:
        //Keystroke
        login.setOnKeyPressed(new EventHandler<KeyEvent>() {
        @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    
                    if(usernameInput.getText().equals("") && passInput.getText().equals("") ){
                   loginFail.setText("Please enter username and password.");
                   }
                else if(usernameInput.getText().equals("admin") && passInput.getText().equals("admin") ){
                   loginFail.setText("");
                    primaryStage.setScene(AdminMenu);
                }
                else if(Data.getInstance().validate(usernameInput.getText(), passInput.getText())){
                    loginFail.setText("");
                    cUser = Data.getInstance().obtainUser(usernameInput.getText());
                    cWelcome.setText("Welcome " + cUser.getUserName() + ". You have " + cUser.getPoints() + " points. Your status is " + cUser.getStatus() + ".");
                    primaryStage.setScene(CustomerBookList);
                }
                else {loginFail.setText("Invalid username or password.");}
            }
            }
        });
        
        
        //Admin Menu Nodes and Layout 
        
        Button booksBTN = new Button();
        booksBTN.setText("Books");
        booksBTN.setLayoutX(50);
        booksBTN.setLayoutY(10);
        booksBTN.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
               
                primaryStage.setScene(AdminBookList);
            }
        });
        Button customersBTN = new Button();
        customersBTN.setText("Customers");
        customersBTN.setLayoutX(50);
        customersBTN.setLayoutY(10);
        customersBTN.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                primaryStage.setScene(CustomerList);
            }
        });
        Button logoutBTN = new Button(); 
        logoutBTN.setText("Logout");
        logoutBTN.setLayoutX(50);
        logoutBTN.setLayoutY(10);
        logoutBTN.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                usernameInput.setText("");
                passInput.setText("");
                primaryStage.setScene(login);
            }
        });
        
        AdminMenuMain.getChildren().addAll(booksBTN,customersBTN,logoutBTN);
        AdminMenuMain.setAlignment(Pos.CENTER);
        
        //Admin Customer List
        
        //Admin CL:
        //Table Setup
        TableColumn userCol = new TableColumn("Username");
        userCol.setMinWidth(170);
        userCol.setCellValueFactory(
            new PropertyValueFactory<Customer,String>("userName"));
        TableColumn passCol = new TableColumn("Password");
        passCol.setMinWidth(170);
        passCol.setCellValueFactory(
            new PropertyValueFactory<Customer,String>("passWord"));
        TableColumn pointsCol = new TableColumn("Points");
        pointsCol.setMinWidth(138);
        pointsCol.setCellValueFactory(
            new PropertyValueFactory<Customer,Integer>("points"));
        
        customerTable.getColumns().addAll(userCol,passCol,pointsCol);
        
        TableViewSelectionModel<Customer> customerSelect = customerTable.getSelectionModel();
        customerSelect.setSelectionMode(SelectionMode.MULTIPLE);
        
        ObservableList<Integer> selectedCustomers = customerSelect.getSelectedIndices();
        
        //Admin CL:
        //Nodes and Layout setup
        Button clDel = new Button(); 
        clDel.setText("Delete");
        clDel.setLayoutX(50);
        clDel.setLayoutY(10);
        clDel.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                int i;
                int length;
                int index;
                
                length = selectedCustomers.size();
               
                
                for(i=length -1;i>=0;i--){
                    try{
                        index = selectedCustomers.get(i);
                        Data.getInstance().deleteCustomer(Data.getInstance().getCustomers().get(index));
                    }catch(IOException e){
                        
                    }
                   
                }
                customerSelect.clearSelection();
            }
        });
        Button backBTN = new Button(); 
        backBTN.setText("Back");
        backBTN.setLayoutX(50);
        backBTN.setLayoutY(10);
        backBTN.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(AdminMenu);
            }
        });
        
       
        TextField usernameInput2 = new TextField();
        usernameInput2.setPromptText("Username");
        TextField passInput2 = new TextField();
        passInput2.setPromptText("Password");
        
        Label clAddFail = new Label("");
        clAddFail.setStyle("-fx-text-fill: red");
        
        Button clAdd = new Button(); 
        clAdd.setText("Add");
        clAdd.setLayoutX(50);
        clAdd.setLayoutY(10);
        clAdd.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                
                
                if(usernameInput2.getText().equals("") || passInput2.getText().equals("") ){
                   
                }else{
                    
                    
                    try{
                        if(Data.getInstance().userExists(usernameInput2.getText())){
                            clAddFail.setText("Username is already taken.");
                        }else{
                            Data.getInstance().addCustomer(usernameInput2.getText(),passInput2.getText());
                            usernameInput2.setText("");
                            passInput2.setText("");
                            clAddFail.setText("");
                        }
                        
                    }
                    catch(IOException e){
                       System.out.println("Add Customer Error."); 
                    }
                    
                }  
            }
        });
        
        
        
        HBox addCustomerRow = new HBox(20);
        HBox clBTNs = new HBox(20);
        
        addCustomerRow.getChildren().addAll(usernameInput2,passInput2,clAdd);
        clBTNs.getChildren().addAll(clDel,backBTN);
        CLMain.setPadding(new Insets(10,10,10,10));
        CLMain.getChildren().addAll(customerTable,addCustomerRow,clAddFail,clBTNs);
        
        //Admin Book List
        
        //Admin Book List:
        //Table setup
        TableColumn bookNameCol = new TableColumn("Book");
        bookNameCol.setMinWidth(400);
        bookNameCol.setCellValueFactory(
            new PropertyValueFactory<Book,String>("bookName"));
        TableColumn bookPriceCol = new TableColumn("Price");
        bookPriceCol.setMinWidth(78);
        bookPriceCol.setCellValueFactory(
            new PropertyValueFactory<Book,Double>("price"));
        
        
        bookTable.getColumns().addAll(bookNameCol,bookPriceCol);
        
        TableViewSelectionModel<Book> bookSelect = bookTable.getSelectionModel();
        bookSelect.setSelectionMode(SelectionMode.MULTIPLE);
        
        ObservableList<Integer> selectedBooks = bookSelect.getSelectedIndices();
        
        //Admin Book List:
        //Node and Layout Setup
        Button blDel = new Button(); 
        blDel.setText("Delete");
        blDel.setLayoutX(50);
        blDel.setLayoutY(10);
        blDel.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                int i;
                int length;
                int index;
                
                length = selectedBooks.size();
               
                
                for(i=length -1;i>=0;i--){
                    try{
                        index = selectedBooks.get(i);
                        Data.getInstance().deleteBook(Data.getInstance().getBook().get(index));
                    }catch(IOException e){
                        System.out.println("Error with book deletion GUI.");
                    }
                   
                }
                bookSelect.clearSelection();
            }
        });
        Button backBTN2 = new Button(); 
        backBTN2.setText("Back");
        backBTN2.setLayoutX(50);
        backBTN2.setLayoutY(10);
        backBTN2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                primaryStage.setScene(AdminMenu);
            }
        });
        
        
       
        TextField bookNameInput = new TextField();
        bookNameInput.setPromptText("Name");
        TextField bookPriceInput = new TextField();
        bookPriceInput.setPromptText("Price");
        
        Label blAddFail = new Label("");
        blAddFail.setStyle("-fx-text-fill: red");
        
        Button blAdd = new Button(); 
        blAdd.setText("Add");
        blAdd.setLayoutX(50);
        blAdd.setLayoutY(10);
        blAdd.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                
                if(bookNameInput.getText().equals("") || bookPriceInput.getText().equals("") ){
                   
                }else{
                    
                    try{
                        
                            Data.getInstance().addBook(bookNameInput.getText(),Integer.parseInt(bookPriceInput.getText()));
                            bookNameInput.setText("");
                            bookPriceInput.setText("");
                            blAddFail.setText("");
                        
                    }
                    catch(NumberFormatException e){
                       blAddFail.setText("Price must be a number."); 
                    }
                    
                }
                
            }
        });
        HBox addBookRow = new HBox(20);
        HBox blBTNs = new HBox(20);
        
        addBookRow.getChildren().addAll(bookNameInput,bookPriceInput,blAdd);
        blBTNs.getChildren().addAll(blDel,backBTN2);
        ABLMain.setPadding(new Insets(10,10,10,10));
        ABLMain.getChildren().addAll(bookTable,addBookRow,blAddFail,blBTNs);
        
        //Customer Buy Sceen
        
        //Customer Buy Sceen:
        //Node setup
        
        Label costText = new Label("Total cost");
        costText.setFont(new Font("Arial",24));
        Label psText = new Label("points and status");
        psText.setFont(new Font("Arial",24));
        
        Button logoutBTN3 = new Button(); 
        logoutBTN3.setText("Logout");
        logoutBTN3.setLayoutX(50);
        logoutBTN3.setLayoutY(10);
        logoutBTN3.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                usernameInput.setText("");
                passInput.setText("");
                primaryStage.setScene(login);
            }
        });
        
        //Customer Buy Sceen:
        //Layout Setup
        
        CBuyMain.getChildren().addAll(costText,psText,logoutBTN3);
        CBuyMain.setPadding(new Insets(10,10,10,10));
        CBuyMain.setAlignment(Pos.CENTER);
        
        //Customer Book List
        
        
        //Customer Book List:
        //TableView Setup
        TableColumn bookNameColUser = new TableColumn("Book");
        bookNameColUser.setMinWidth(300);
        bookNameColUser.setCellValueFactory(
            new PropertyValueFactory<Book,String>("bookName"));
        TableColumn bookPriceColUser = new TableColumn("Price");
        bookPriceColUser.setMinWidth(100);
        bookPriceColUser.setCellValueFactory(
            new PropertyValueFactory<Book,Double>("price"));
        TableColumn bookSelectCol = new TableColumn("Select");
        bookSelectCol.setMinWidth(78);
        bookSelectCol.setCellValueFactory(
            new PropertyValueFactory<Book,CheckBox>("select"));
        
        bookTable2.getColumns().addAll(bookNameColUser,bookPriceColUser,bookSelectCol);
        
        //Customer Book List:
        //Node Setup
        
        
        cWelcome.setFont(new Font("Arial",16));
        
        Button buyBTN = new Button(); 
        buyBTN.setText("Buy");
        buyBTN.setLayoutX(50);
        buyBTN.setLayoutY(10);
        buyBTN.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                boolean selected = false;
                int index = Data.getInstance().getBook().size();
                int i;
                double sum=0;
                for(i=index-1;i>=0;i--){
                    
                    if(Data.getInstance().getBook().get(i).getSelect().isSelected()){
                        sum = sum + Data.getInstance().getBook().get(i).getPrice();
                        selected = true;
                        try{
                            
                            Data.getInstance().deleteBook(Data.getInstance().getBook().get(i));
                            
                        }catch(IOException e){}
                    }
  
                }
                
                if(selected){
                    
                    Data.getInstance().buyBook(cUser,(int)sum);
                    costText.setText("Total Cost: " + (int)sum);
                    psText.setText("Points: " + cUser.getPoints() + ", Status: " + cUser.getStatus());
                    primaryStage.setScene(CBuy);
                }
            }
        });
        
        Button buyPointsBTN = new Button(); 
        buyPointsBTN.setText("Redeem Points and Buy");
        buyPointsBTN.setLayoutX(50);
        buyPointsBTN.setLayoutY(10);
        buyPointsBTN.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                boolean selected = false;
                int index = Data.getInstance().getBook().size();
                int i;
                double sum=0;
                for(i=index-1;i>=0;i--){
                    
                    if(Data.getInstance().getBook().get(i).getSelect().isSelected()){
                        sum = sum + Data.getInstance().getBook().get(i).getPrice();
                        selected = true;
                        try{
                            
                            Data.getInstance().deleteBook(Data.getInstance().getBook().get(i));
                            
                        }catch(IOException e){}
                    }
  
                }
                
                if(selected){
                    
                    sum = Data.getInstance().buyBookPoints(cUser,(int)sum);
                    costText.setText("Total Cost: " + (int)sum);
                    psText.setText("Points: " + cUser.getPoints() + ", Status: " + cUser.getStatus());
                    primaryStage.setScene(CBuy);
                };
            }
        });
        
        Button logoutBTN2 = new Button(); 
        logoutBTN2.setText("Logout");
        logoutBTN2.setLayoutX(50);
        logoutBTN2.setLayoutY(10);
        logoutBTN2.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                int index = Data.getInstance().getBook().size();
                int i;
                for(i=0;i<index;i++){
                    Data.getInstance().getBook().get(i).getSelect().setSelected(false);
                }
                
                usernameInput.setText("");
                passInput.setText("");
                primaryStage.setScene(login);
            }
        });
        
        //Customer Book List:
        //Layout Setup
        
        HBox buyRow = new HBox(20);
        buyRow.getChildren().addAll(buyBTN,buyPointsBTN);
        
        CBLMain.setPadding(new Insets(10,10,10,10));
        CBLMain.getChildren().addAll(cWelcome,bookTable2,buyRow,logoutBTN2);
        
        
        
        
        
        //Stage Setup
        primaryStage.setTitle("BookStore App");
        primaryStage.setScene(login);
        primaryStage.show();
        primaryStage.getIcons().add(new Image("file:BookStore Icon.png"));
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent e) {
                Data.getInstance().closeProgram();
                
            }
         });
    
    }
    
    public static void main(String[] args) throws IOException {
       Data.getInstance().loadBooks();
       Data.getInstance().loadCustomers();
       launch(args);
    }
    

    
    
}
