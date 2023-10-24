package com.example.demo;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Normalizer;

import java.io.IOException;
public class HelloApplication extends Application {
    public static boolean validate(TextField Book_ID,TextField Book_Name,TextField Author_name,TextField Category,TextField Price,TextField Quantity){
        if(Book_ID.getText().isEmpty() || Book_Name.getText().isEmpty() || Author_name.getText().isEmpty() || Category.getText().isEmpty() || Price.getText().isEmpty() || Quantity.getText().isEmpty())
        {
            return false;
        }
        return true;
    }
    @Override
    public void start(Stage stage) throws IOException {
        BorderPane bp=new BorderPane();
        GridPane Start=new GridPane();
        Label LIBRARY_login=new Label("Pocket Library login");
        Line line=new Line(50,50,300,50);
        line.setStyle("-fx-font-size: 20pt;");
        LIBRARY_login.setStyle("-fx-font-size: 20pt;");
        Label USERNAME_LABEL=new Label("USERNAME: ");
        USERNAME_LABEL.setStyle("-fx-font-size: 20pt;");
        Label PASSWORD_LABEL=new Label("PASSWORD: ");
        PASSWORD_LABEL.setStyle("-fx-font-size: 20pt;");
        TextField USERNAME=new TextField();
        USERNAME.setStyle("-fx-font-size: 15pt;");
        PasswordField PASSWORD=new PasswordField();
        PASSWORD.setStyle("-fx-font-size: 15pt;");
        RadioButton ADMIN=new RadioButton("ADMIN");
        ADMIN.setStyle("-fx-font-size: 15pt;");
        RadioButton STUDENT=new RadioButton("STUDENT");
        STUDENT.setStyle("-fx-font-size: 15pt;");
        RadioButton Faculty=new RadioButton("FACULTY");
        Faculty.setStyle("-fx-font-size: 15pt;");
        Button SUBMIT=new Button("SUBMIT");
        SUBMIT.setStyle("-fx-font-size: 15pt;");
        Button EXIT=new Button("EXIT");
        EXIT.setStyle("-fx-font-size: 15pt;");
        Start.setVgap(10);
        Start.add(LIBRARY_login,0,0,1,1);
        Start.add(line,0,1,1,1);
        Start.add(USERNAME_LABEL,0,2,1,1);
        Start.add(USERNAME,1,2,1,1);
        Start.add(PASSWORD_LABEL,0,3,1,1);
        Start.add(PASSWORD,1,3,1,1);
        ADMIN.setSelected(true);
        ToggleGroup t=new ToggleGroup();
        Faculty.setToggleGroup(t);
        ADMIN.setToggleGroup(t);
        Start.add(ADMIN,0,4,1,1);
        STUDENT.setToggleGroup(t);
        Start.add(STUDENT,1,4,1,1);
        Start.add(Faculty,2,4,1,1);
        Start.add(SUBMIT,0,5,1,1);
        Start.add(EXIT,1,5,1,1);
        Start.setAlignment(Pos.CENTER);
        EXIT.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        Image im2=new Image(new FileInputStream("src/lib3.png"));
        BackgroundImage bgImage=new BackgroundImage(im2,BackgroundRepeat.NO_REPEAT,BackgroundRepeat.NO_REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
        Background bg=new Background(bgImage);
        bp.setCenter(Start);
        Image im3=new Image(new FileInputStream("src/icon.jpg"));
        bp.setBackground(bg);
        Scene scene=new Scene(bp);
        SUBMIT.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(ADMIN.isSelected()){
                    try{
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","Karthik@03");
                        Statement stmt=con.createStatement();
                        ResultSet rs=stmt.executeQuery("select * from login");
                        while(rs.next())
                        {
                            if((USERNAME.getCharacters().toString().equals(rs.getString(1)))&&(PASSWORD.getCharacters().toString().equals(rs.getString(2)))){
                                Button Change_username_and_password=new Button("Change UserID/Password");
                                Change_username_and_password.setStyle("-fx-font-size: 15pt;");
                                Button manage_books=new Button("     Manage books     ");
                                manage_books.setStyle("-fx-font-size: 15pt;");
                                Button manage_students=new Button("    Manage students   ");
                                manage_students.setStyle("-fx-font-size: 15pt;");
                                Button manage_faculty=new Button("    Manage Faculty    ");
                                manage_faculty.setStyle("-fx-font-size: 15pt;");
                                Button Premium_content=new Button("    Premium Content   ");
                                Premium_content.setStyle("-fx-font-size: 15pt;");
                                Button Fines=new Button("         Fines        ");
                                Fines.setStyle("-fx-font-size: 15pt;");
                                Button View=new Button("         View         ");
                                View.setStyle("-fx-font-size: 15pt;");
                                Button logout=new Button("        Logout        ");
                                logout.setStyle("-fx-font-size: 15pt;");
                                Button back=new Button("        Back        ");
                                back.setStyle("-fx-font-size: 15pt;");
                                GridPane list_n=new GridPane();
                                list_n.setVgap(10);
                                list_n.setHgap(10);
                                bp.getChildren().clear();
                                Change_username_and_password.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        GridPane Change_usernamendpass=new GridPane();
                                        Label old_username=new Label("Old Username: ");
                                        Label new_username=new Label("New Username: ");
                                        Label old_pass=new Label("Old Password: ");
                                        Label new_pass=new Label("New Password: ");
                                        old_pass.setStyle("-fx-font-size: 20pt;");
                                        old_username.setStyle("-fx-font-size: 20pt;");
                                        new_pass.setStyle("-fx-font-size: 20pt;");
                                        new_username.setStyle("-fx-font-size: 20pt;");
                                        TextField old_username_tf=new TextField("Enter the old username");
                                        TextField new_username_tf=new TextField("Enter the new username");
                                        TextField old_pass_tf=new TextField("Enter the old password");
                                        TextField new_pass_tf=new TextField("Enter the new password");
                                        old_pass_tf.setStyle("-fx-font-size: 15pt;");
                                        old_username_tf.setStyle("-fx-font-size: 15pt;");
                                        new_pass_tf.setStyle("-fx-font-size: 15pt;");
                                        new_username_tf.setStyle("-fx-font-size: 15pt;");
                                        Button submit=new Button("Submit");
                                        submit.setStyle("-fx-font-size: 15pt;");
                                        Button back=new Button("Back");
                                        back.setStyle("-fx-font-size: 15pt;");
                                        submit.setOnAction(new EventHandler<ActionEvent>(){
                                            @Override
                                            public void handle(ActionEvent actionEvent){
                                                try{
                                                    Class.forName("com.mysql.cj.jdbc.Driver");
                                                    Connection abcd= DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","Karthik@03");
                                                    String check_cred="select * from login";
                                                    String drop="truncate table login";
                                                    Statement check_S=con.createStatement();
                                                    ResultSet res=check_S.executeQuery(check_cred);
                                                    String usernameand_password ="insert into login values (?,?)";
                                                    Statement drop_S=con.createStatement();
                                                    PreparedStatement pst=abcd.prepareStatement(usernameand_password);
                                                    String new_pass=new_pass_tf.getCharacters().toString();
                                                    String new_username=new_username_tf.getCharacters().toString();
                                                    String old_pass=old_pass_tf.getCharacters().toString();
                                                    String old_username=old_username_tf.getCharacters().toString();
                                                    while(res.next()){
                                                        if(res.getString(1).equals(old_username)&&res.getString(2).equals(old_pass)){
                                                            pst.setString(1,new_username);
                                                            pst.setString(2,new_pass);
                                                            drop_S.executeQuery(drop);
                                                            pst.executeUpdate();
                                                            System.out.println("success");
                                                        }
                                                        else{
                                                            System.exit(0);
                                                        }
                                                    }

                                                }
                                                catch(Exception e){

                                                }
                                            }
                                        });
                                        back.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent actionEvent) {
                                                bp.getChildren().clear();
                                                bp.setCenter(list_n);
                                                stage.show();
                                            }
                                        });
                                        Change_usernamendpass.setHgap(10);
                                        Change_usernamendpass.setVgap(10);
                                        Change_usernamendpass.add(old_username,0,0);
                                        Change_usernamendpass.add(old_username_tf,1,0);
                                        Change_usernamendpass.add(old_pass,0,1);
                                        Change_usernamendpass.add(old_pass_tf,1,1);
                                        Change_usernamendpass.add(new_username,0,2);
                                        Change_usernamendpass.add(new_username_tf,1,2);
                                        Change_usernamendpass.add(new_pass,0,3);
                                        Change_usernamendpass.add(new_pass_tf,1,3);
                                        Change_usernamendpass.add(submit,0,4);
                                        Change_usernamendpass.add(back,1,4);
                                        Change_usernamendpass.setAlignment(Pos.CENTER);
                                        bp.getChildren().clear();
                                        bp.setCenter(Change_usernamendpass);
                                        stage.show();
                                    }
                                });
                                manage_books.setOnAction(new EventHandler<ActionEvent>() {
                                    public void handle(ActionEvent actionEvent) {
                                        VBox m_books_grid=new VBox();
                                        Button add_new_books=new Button("Add new books");
                                        add_new_books.setStyle("-fx-font-size: 15pt;");
                                        Button delete_books=new Button("Delete books");
                                        delete_books.setStyle("-fx-font-size: 15pt;");
                                        Button Update_books=new Button("Update books");
                                        Update_books.setStyle("-fx-font-size: 15pt;");
                                        add_new_books.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent actionEvent) {
                                                GridPane Add_Books_GRID=new GridPane();
                                                Button back=new Button("Back");
                                                back.setStyle("-fx-font-size: 15pt;");
                                                Label Book_id_label=new Label("Book ID: ");
                                                Book_id_label.setStyle("-fx-font-size: 20pt;");
                                                Label Book_name_label=new Label("Book Name: ");
                                                Book_name_label.setStyle("-fx-font-size: 20pt;");
                                                Label Author_name_label=new Label("Author Name: ");
                                                Author_name_label.setStyle("-fx-font-size: 20pt;");
                                                Label Category_label=new Label("Category: ");
                                                Category_label.setStyle("-fx-font-size: 20pt;");
                                                Label Price_label =new Label("Price: ");
                                                Price_label.setStyle("-fx-font-size: 20pt;");
                                                Label Quantity_label=new Label("Quantity: ");
                                                Quantity_label.setStyle("-fx-font-size: 20pt;");
                                                TextField Book_id_textfield=new TextField();
                                                Book_id_textfield.setStyle("-fx-font-size: 15pt;");
                                                TextField Book_name_textfield=new TextField();
                                                Book_name_textfield.setStyle("-fx-font-size: 15pt;");
                                                TextField Author_name_textfield=new TextField();
                                                Author_name_textfield.setStyle("-fx-font-size: 15pt;");
                                                TextField Category_textfield=new TextField();
                                                Category_textfield.setStyle("-fx-font-size: 15pt;");
                                                TextField Price_textfield=new TextField();
                                                Price_textfield.setStyle("-fx-font-size: 15pt;");
                                                TextField Quantity_textfield=new TextField();
                                                Quantity_textfield.setStyle("-fx-font-size: 15pt;");
                                                Button Submit_books=new Button("SUBMIT");
                                                Submit_books.setStyle("-fx-font-size: 15pt;");
                                                Submit_books.setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent actionEvent) {

                                                        if(validate(Book_id_textfield,Book_name_textfield,Author_name_textfield,Category_textfield,Price_textfield,Quantity_textfield)){
                                                            try {
                                                                Class.forName("com.mysql.cj.jdbc.Driver");
                                                                Connection abc= DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","Karthik@03");
                                                                String query_for_books="insert into books values (?,?,?,?,?,?)";
                                                                PreparedStatement pst = abc.prepareStatement(query_for_books);
                                                                pst.setInt(1,Integer.parseInt(Book_id_textfield.getCharacters().toString()));
                                                                pst.setString(2,Book_name_textfield.getCharacters().toString());
                                                                pst.setString(3, Author_name_textfield.getCharacters().toString());
                                                                pst.setString(4,Category_textfield.getCharacters().toString());
                                                                pst.setInt(5,Integer.parseInt(Price_textfield.getCharacters().toString()));
                                                                pst.setInt(6,Integer.parseInt(Quantity_textfield.getCharacters().toString()));
                                                                pst.executeUpdate();
                                                                Label l=new Label("Successfully added!!");
                                                                l.setTextFill(new Color(0,1,0,1));
                                                                l.setStyle("-fx-font-size: 28pt;");
                                                                VBox g=new VBox();
                                                                g.getChildren().addAll(l);
                                                                g.setAlignment(Pos.CENTER);
                                                                Scene temp_scene=new Scene(g,400,200);
                                                                Stage temp_stage=new Stage();
                                                                temp_stage.setScene(temp_scene);
                                                                temp_stage.show();
                                                                con.close();
                                                                bp.getChildren().clear();
                                                                bp.setCenter(m_books_grid);
                                                                stage.setScene(scene);
                                                                stage.show();
                                                                pst.close();
                                                                abc.close();
                                                                bp.getChildren().clear();
                                                                bp.setCenter(m_books_grid);
                                                                stage.setScene(scene);
                                                                stage.show();
                                                            } catch (SQLException e) {
                                                                System.out.println("An sql error(129)");
                                                            }
                                                            catch(ClassNotFoundException e2){
                                                                System.out.println("some other case(133)");
                                                            }
                                                        }
                                                        else
                                                        {
                                                            Label l=new Label("PLease enter all values!");
                                                            l.setStyle("-fx-font-size: 15pt;");
                                                            l.setTextFill(new Color(1,0,0,1));
                                                            Add_Books_GRID.add(l,0,7,1,1);
                                                        }
                                                    }
                                                });
                                                back.setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent actionEvent) {
                                                        bp.getChildren().clear();
                                                        bp.setCenter(m_books_grid);
                                                        stage.show();
                                                    }
                                                });
                                                Add_Books_GRID.setHgap(10);
                                                Add_Books_GRID.setVgap(10);
                                                Add_Books_GRID.add(Book_id_label,0,0);
                                                Add_Books_GRID.add(Book_id_textfield,1,0);
                                                Add_Books_GRID.add(Book_name_label,0,1);
                                                Add_Books_GRID.add(Book_name_textfield,1,1);
                                                Add_Books_GRID.add(Author_name_label,0,2);
                                                Add_Books_GRID.add(Author_name_textfield,1,2);
                                                Add_Books_GRID.add(Category_label,0,3);
                                                Add_Books_GRID.add(Category_textfield,1,3);
                                                Add_Books_GRID.add(Price_label,0,4);
                                                Add_Books_GRID.add(Price_textfield,1,4);
                                                Add_Books_GRID.add(Quantity_label,0,5);
                                                Add_Books_GRID.add(Quantity_textfield,1,5);
                                                Add_Books_GRID.add(Submit_books,0,6);
                                                Add_Books_GRID.add(back,1,6);
                                                Add_Books_GRID.setAlignment(Pos.CENTER);
                                                bp.getChildren().clear();
                                                bp.setCenter(Add_Books_GRID);
                                                stage.show();
                                            }
                                        });
                                        Update_books.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent actionEvent) {
                                                Label Book_id_label=new Label("Book ID: ");
                                                Book_id_label.setStyle("-fx-font-size: 20pt;");
                                                Label Book_name_label=new Label("Book Name: ");
                                                Book_name_label.setStyle("-fx-font-size: 20pt;");
                                                Label Quantity_label=new Label("Quantity: ");
                                                Quantity_label.setStyle("-fx-font-size: 20pt;");
                                                TextField Book_id_textfield=new TextField();
                                                Book_id_textfield.setStyle("-fx-font-size: 15pt;");
                                                TextField Book_name_textfield=new TextField();
                                                Book_name_textfield.setStyle("-fx-font-size: 15pt;");
                                                TextField Quantity_textfield=new TextField();
                                                Quantity_textfield.setStyle("-fx-font-size: 15pt;");
                                                Button Submit=new Button("Submit");
                                                Submit.setStyle("-fx-font-size: 15pt;");
                                                Button back=new Button("Back");
                                                back.setStyle("-fx-font-size: 15pt;");
                                                back.setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent actionEvent) {
                                                        bp.getChildren().clear();
                                                        bp.setCenter(m_books_grid);
                                                        stage.show();
                                                    }
                                                });
                                                Submit.setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent actionEvent) {
                                                        try{
                                                            Class.forName("com.mysql.cj.jdbc.Driver");
                                                            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","Karthik@03");
                                                            Statement stmt=con.createStatement();
                                                            ResultSet rs=stmt.executeQuery("select * from books");
                                                            while(rs.next()){
                                                                if(Book_id_textfield.getCharacters().toString().equals(rs.getString(1))||Book_name_textfield.getCharacters().toString().equals((rs.getString(2)).toLowerCase().trim())){
                                                                    if(Book_id_textfield.getCharacters().toString().isEmpty()){
                                                                        String query_for_del_books_BookName="update books set quantity=? where book_name=?;";
                                                                        PreparedStatement ps=con.prepareStatement(query_for_del_books_BookName);
                                                                        ps.setInt(1,Integer.parseInt(Quantity_textfield.getCharacters().toString()));
                                                                        ps.setString(2,Book_name_textfield.getCharacters().toString());
                                                                        ps.executeUpdate();
                                                                        ps.close();
                                                                    }
                                                                    else if(Book_name_textfield.getCharacters().toString().isEmpty()){
                                                                        String query_for_del_books_Bookid="update books set quantity=? where book_id=?;";
                                                                        PreparedStatement ps=con.prepareStatement(query_for_del_books_Bookid);
                                                                        ps.setInt(1,Integer.parseInt(Quantity_textfield.getCharacters().toString()));
                                                                        ps.setInt(2,Integer.parseInt(Book_id_textfield.getCharacters().toString()));
                                                                        ps.executeUpdate();
                                                                        ps.close();
                                                                    }
                                                                    Label l=new Label("Successfully Updated!!");
                                                                    l.setTextFill(new Color(0,1,0,1));
                                                                    l.setStyle("-fx-font-size: 28pt;");
                                                                    VBox g=new VBox();
                                                                    g.getChildren().addAll(l);
                                                                    g.setAlignment(Pos.CENTER);
                                                                    Scene temp_scene=new Scene(g,400,200);
                                                                    Stage temp_stage=new Stage();
                                                                    temp_stage.setScene(temp_scene);
                                                                    temp_stage.show();
                                                                    con.close();
                                                                    bp.getChildren().clear();
                                                                    bp.setCenter(m_books_grid);
                                                                    stage.setScene(scene);
                                                                    stage.show();
                                                                }
                                                            }
                                                        }
                                                        catch (Exception e){
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                });
                                                GridPane update_grid=new GridPane();
                                                update_grid.setHgap(10);
                                                update_grid.setVgap(10);
                                                update_grid.add(Book_id_label,0,0);
                                                update_grid.add(Book_id_textfield,1,0);
                                                update_grid.add(Book_name_label,0,1);
                                                update_grid.add(Book_name_textfield,1,1);
                                                update_grid.add(Quantity_label,0,2);
                                                update_grid.add(Quantity_textfield,1,2);
                                                update_grid.add(Submit,0,3);
                                                update_grid.add(back,1,3);
                                                update_grid.setAlignment(Pos.CENTER);
                                                bp.getChildren().clear();
                                                bp.setCenter(update_grid);
                                                stage.show();
                                            }
                                        });
                                        delete_books.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent actionEvent) {
                                                Label Book_id_label=new Label("Book ID: ");
                                                Book_id_label.setStyle("-fx-font-size: 20pt;");
                                                Label Book_name_label=new Label("Book Name: ");
                                                Book_name_label.setStyle("-fx-font-size: 20pt;");
                                                TextField Book_id_textfield=new TextField();
                                                Book_id_textfield.setStyle("-fx-font-size: 15pt;");
                                                TextField Book_name_textfield=new TextField();
                                                Book_name_textfield.setStyle("-fx-font-size: 15pt;");
                                                Button Submit=new Button("Submit");
                                                Submit.setStyle("-fx-font-size: 15pt;");
                                                Button back=new Button("Back");
                                                back.setStyle("-fx-font-size: 15pt;");
                                                back.setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent actionEvent) {
                                                        bp.getChildren().clear();
                                                        bp.setCenter(m_books_grid);
                                                        stage.show();
                                                    }
                                                });
                                                Submit.setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent actionEvent) {
                                                        try{
                                                            Class.forName("com.mysql.cj.jdbc.Driver");
                                                            Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","Karthik@03");
                                                            Statement stmt=con.createStatement();
                                                            ResultSet rs=stmt.executeQuery("select * from books");
                                                            while(rs.next()){
                                                                if(Book_id_textfield.getCharacters().toString().equals(rs.getString(1))||Book_name_textfield.getCharacters().toString().equals((rs.getString(2)).toLowerCase().trim())){
                                                                    if(Book_id_textfield.getCharacters().toString().isEmpty()){
                                                                        String query_for_del_books_BookName="update books set quantity=0 where book_name=?;";
                                                                        PreparedStatement ps=con.prepareStatement(query_for_del_books_BookName);
                                                                        ps.setString(1,Book_name_textfield.getCharacters().toString());
                                                                        ps.executeUpdate();
                                                                        ps.close();
                                                                    }
                                                                    else if(Book_name_textfield.getCharacters().toString().isEmpty()){
                                                                        String query_for_del_books_Bookid="update books set quantity=0 where book_id=?;";
                                                                        PreparedStatement ps=con.prepareStatement(query_for_del_books_Bookid);
                                                                        ps.setInt(1,Integer.parseInt(Book_id_textfield.getCharacters().toString()));
                                                                        ps.executeUpdate();
                                                                        ps.close();
                                                                    }
                                                                    Label l=new Label("Successfully deleted!!");
                                                                    l.setTextFill(new Color(0,1,0,1));
                                                                    l.setStyle("-fx-font-size: 28pt;");
                                                                    VBox g=new VBox();
                                                                    g.getChildren().addAll(l);
                                                                    g.setAlignment(Pos.CENTER);
                                                                    Scene temp_scene=new Scene(g,400,200);
                                                                    Stage temp_stage=new Stage();
                                                                    temp_stage.setScene(temp_scene);
                                                                    temp_stage.show();
                                                                    con.close();
                                                                    bp.getChildren().clear();
                                                                    bp.setCenter(m_books_grid);
                                                                    stage.setScene(scene);
                                                                    stage.show();
                                                                    con.close();
                                                                    bp.getChildren().clear();
                                                                    bp.setCenter(m_books_grid);
                                                                    stage.setScene(scene);
                                                                    stage.show();
                                                                }
                                                            }
                                                        }
                                                        catch (Exception e){
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                });
                                                GridPane del_grid=new GridPane();
                                                del_grid.setHgap(10);
                                                del_grid.setVgap(10);
                                                del_grid.add(Book_id_label,0,0);
                                                del_grid.add(Book_id_textfield,1,0);
                                                del_grid.add(Book_name_label,0,1);
                                                del_grid.add(Book_name_textfield,1,1);
                                                del_grid.add(Submit,0,2);
                                                del_grid.add(back,1,2);
                                                del_grid.setAlignment(Pos.CENTER);
                                                bp.getChildren().clear();
                                                bp.setCenter(del_grid);
                                                stage.show();
                                            }
                                        });
                                        Button back=new Button("Back");
                                        back.setStyle("-fx-font-size: 15pt;");
                                        back.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent actionEvent) {
                                                bp.getChildren().clear();
                                                bp.setCenter(list_n);
                                                stage.show();
                                            }
                                        });
                                        m_books_grid.getChildren().add(add_new_books);
                                        m_books_grid.getChildren().add(Update_books);
                                        m_books_grid.getChildren().add(delete_books);
                                        m_books_grid.getChildren().add(back);
                                        m_books_grid.setAlignment(Pos.CENTER);
                                        bp.getChildren().clear();
                                        bp.setCenter(m_books_grid);
                                        stage.show();
                                    }
                                });
                                manage_students.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        VBox manage_students_box=new VBox();
                                        Button Add_students=new Button("Add new Students");
                                        Button Delete_students=new Button("Delete Students");
                                        Button Update_students=new Button("Update Students");
                                        Button back=new Button("Back");
                                        Add_students.setStyle("-fx-font-size: 15pt;");
                                        Delete_students.setStyle("-fx-font-size: 15pt;");
                                        Update_students.setStyle("-fx-font-size: 15pt;");
                                        back.setStyle("-fx-font-size: 15pt;");
                                        manage_students_box.getChildren().add(Add_students);
                                        manage_students_box.getChildren().add(Delete_students);
                                        manage_students_box.getChildren().add(Update_students);
                                        manage_students_box.getChildren().add(back);
                                        manage_students_box.setAlignment(Pos.CENTER);
                                        Add_students.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent actionEvent) {
                                                String choices[]={"YES","NO"};
                                                Label Student_fname=new Label("Student First Name: ");
                                                Student_fname.setStyle("-fx-font-size: 20pt;");
                                                Label Student_lname=new Label("Student Last Name: ");
                                                Student_lname.setStyle("-fx-font-size: 20pt;");
                                                Label Premium_User=new Label("Premium User: ");
                                                Premium_User.setStyle("-fx-font-size: 20pt;");
                                                Label Contact_no=new Label("Contact Number: ");
                                                Contact_no.setStyle("-fx-font-size: 20pt;");
                                                Label Sid=new Label("Student ID: ");
                                                Sid.setStyle("-fx-font-size: 20pt;");
                                                TextField Student_fname_tf=new TextField();
                                                Student_fname_tf.setStyle("-fx-font-size: 15pt;");
                                                TextField Student_lname_tf=new TextField();
                                                Student_lname_tf.setStyle("-fx-font-size: 15pt;");
                                                ChoiceBox Premium_User_cb=new ChoiceBox(FXCollections.observableArrayList(choices));
                                                Premium_User_cb.setValue("--select--");
                                                Premium_User_cb.setStyle("-fx-font-size: 15pt;");
                                                TextField Contact_no_tf=new TextField();
                                                Contact_no_tf.setStyle("-fx-font-size: 15pt;");
                                                TextField Sid_tf=new TextField();
                                                Sid_tf.setStyle("-fx-font-size: 15pt;");
                                                Button Submit=new Button("Submit");
                                                Submit.setStyle("-fx-font-size: 15pt;");
                                                Button back=new Button("Back");
                                                back.setStyle("-fx-font-size: 15pt;");
                                                back.setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent actionEvent) {
                                                        bp.getChildren().clear();
                                                        bp.setCenter(manage_students_box);
                                                        stage.show();
                                                    }
                                                });
                                                Submit.setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent actionEvent) {
                                                        try{
                                                            Class.forName("com.mysql.cj.jdbc.Driver");
                                                            Connection abc= DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","Karthik@03");
                                                            String add_stud_command="insert into student_list values(?,?,?,?,?,?,?)";
                                                            PreparedStatement pst=abc.prepareStatement(add_stud_command);
                                                            pst.setString(1,Student_fname_tf.getCharacters().toString());
                                                            pst.setString(2,Student_lname_tf.getCharacters().toString());
                                                            pst.setInt(3,Integer.parseInt(Sid_tf.getCharacters().toString()));
                                                            pst.setInt(4,Integer.parseInt(Contact_no_tf.getCharacters().toString()));
                                                            pst.setString(5,Premium_User_cb.getValue().toString());
                                                            pst.setInt(6,0);
                                                            pst.setInt(7,0);
                                                            pst.executeUpdate();
                                                            pst.close();
                                                            Label l=new Label("Successfully added!!");
                                                            l.setTextFill(new Color(0,1,0,1));
                                                            l.setStyle("-fx-font-size: 28pt;");
                                                            VBox g=new VBox();
                                                            g.getChildren().addAll(l);
                                                            g.setAlignment(Pos.CENTER);
                                                            Scene temp_scene=new Scene(g,400,200);
                                                            Stage temp_stage=new Stage();
                                                            temp_stage.setScene(temp_scene);
                                                            temp_stage.show();
                                                            abc.close();
                                                            bp.getChildren().clear();
                                                            bp.setCenter(manage_students_box);
                                                            stage.show();
                                                        }
                                                        catch(Exception e){

                                                        }
                                                    }
                                                });
                                                GridPane students_grid=new GridPane();
                                                students_grid.add(Student_fname,0,0);
                                                students_grid.add(Student_fname_tf,1,0);
                                                students_grid.add(Student_lname,0,1);
                                                students_grid.add(Student_lname_tf,1,1);
                                                students_grid.add(Premium_User,0,2);
                                                students_grid.add(Premium_User_cb,1,2);
                                                students_grid.add(Sid,0,3);
                                                students_grid.add(Sid_tf,1,3);
                                                students_grid.add(Contact_no,0,4);
                                                students_grid.add(Contact_no_tf,1,4);
                                                students_grid.add(Submit,0,7);
                                                students_grid.add(back,1,7);
                                                students_grid.setAlignment(Pos.CENTER);
                                                bp.getChildren().clear();
                                                bp.setCenter(students_grid);
                                                stage.show();
                                            }
                                        });
                                        Delete_students.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent actionEvent) {

                                            }
                                        });
                                        Update_students.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent actionEvent) {

                                            }
                                        });
                                        back.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent actionEvent) {
                                                bp.getChildren().clear();
                                                bp.setCenter(list_n);
                                                stage.show();
                                            }
                                        });
                                        bp.getChildren().clear();
                                        bp.setCenter(manage_students_box);
                                        stage.show();
                                    }
                                });
                                manage_faculty.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        VBox manage_faculty_box=new VBox();
                                        Button Add_faculty=new Button("Add new Faculty");
                                        Button Delete_faculty=new Button("Delete Faculty");
                                        Button Update_faculty=new Button("Update Faculty");
                                        Button back=new Button("Back");
                                        Add_faculty.setStyle("-fx-font-size: 15pt;");
                                        Delete_faculty.setStyle("-fx-font-size: 15pt;");
                                        Update_faculty.setStyle("-fx-font-size: 15pt;");
                                        back.setStyle("-fx-font-size: 15pt;");
                                        manage_faculty_box.getChildren().add(Add_faculty);
                                        manage_faculty_box.getChildren().add(Delete_faculty);
                                        manage_faculty_box.getChildren().add(Update_faculty);
                                        manage_faculty_box.getChildren().add(back);
                                        manage_faculty_box.setAlignment(Pos.CENTER);
                                        Add_faculty.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent actionEvent) {
                                                String choices[]={"YES","NO"};
                                                Label Faculty_fname=new Label("Faculty First Name: ");
                                                Faculty_fname.setStyle("-fx-font-size: 20pt;");
                                                Label Faculty_lname=new Label("Faculty Last Name: ");
                                                Faculty_lname.setStyle("-fx-font-size: 20pt;");
                                                Label Premium_User=new Label("Premium User: ");
                                                Premium_User.setStyle("-fx-font-size: 20pt;");
                                                Label Contact_no=new Label("Contact Number: ");
                                                Contact_no.setStyle("-fx-font-size: 20pt;");
                                                Label Fid=new Label("Faculty ID: ");
                                                Fid.setStyle("-fx-font-size: 20pt;");
                                                TextField Faculty_fname_tf=new TextField();
                                                Faculty_fname_tf.setStyle("-fx-font-size: 15pt;");
                                                TextField Faculty_lname_tf=new TextField();
                                                Faculty_lname_tf.setStyle("-fx-font-size: 15pt;");
                                                ChoiceBox Premium_User_cb=new ChoiceBox(FXCollections.observableArrayList(choices));
                                                Premium_User_cb.setValue("--select--");
                                                Premium_User_cb.setStyle("-fx-font-size: 15pt;");
                                                TextField Contact_no_tf=new TextField();
                                                Contact_no_tf.setStyle("-fx-font-size: 15pt;");
                                                TextField Sid_tf=new TextField();
                                                Sid_tf.setStyle("-fx-font-size: 15pt;");
                                                Button Submit=new Button("Submit");
                                                Submit.setStyle("-fx-font-size: 15pt;");
                                                Button back=new Button("Back");
                                                back.setStyle("-fx-font-size: 15pt;");
                                                back.setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent actionEvent) {
                                                        bp.getChildren().clear();
                                                        bp.setCenter(manage_faculty_box);
                                                        stage.show();
                                                    }
                                                });
                                                Submit.setOnAction(new EventHandler<ActionEvent>() {
                                                    @Override
                                                    public void handle(ActionEvent actionEvent) {
                                                        try{
                                                            Class.forName("com.mysql.cj.jdbc.Driver");
                                                            Connection abc= DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","Karthik@03");
                                                            String add_stud_command="insert into faculty_list values(?,?,?,?,?,?,?)";
                                                            PreparedStatement pst=abc.prepareStatement(add_stud_command);
                                                            pst.setString(1,Faculty_fname_tf.getCharacters().toString());
                                                            pst.setString(2,Faculty_lname_tf.getCharacters().toString());
                                                            pst.setInt(3,Integer.parseInt(Sid_tf.getCharacters().toString()));
                                                            pst.setInt(4,Integer.parseInt(Contact_no_tf.getCharacters().toString()));
                                                            pst.setString(5,Premium_User_cb.getValue().toString());
                                                            pst.setInt(6,0);
                                                            pst.setInt(7,0);
                                                            pst.executeUpdate();
                                                            pst.close();
                                                            Label l=new Label("Successfully added!!");
                                                            l.setTextFill(new Color(0,1,0,1));
                                                            l.setStyle("-fx-font-size: 28pt;");
                                                            VBox g=new VBox();
                                                            g.getChildren().addAll(l);
                                                            g.setAlignment(Pos.CENTER);
                                                            Scene temp_scene=new Scene(g,400,200);
                                                            Stage temp_stage=new Stage();
                                                            temp_stage.setScene(temp_scene);
                                                            temp_stage.show();
                                                            abc.close();
                                                            bp.getChildren().clear();
                                                            bp.setCenter(manage_faculty_box);
                                                            stage.show();
                                                        }
                                                        catch(Exception e){

                                                        }
                                                    }
                                                });
                                                GridPane faculty_grid=new GridPane();
                                                faculty_grid.add(Faculty_fname,0,0);
                                                faculty_grid.add(Faculty_fname_tf,1,0);
                                                faculty_grid.add(Faculty_lname,0,1);
                                                faculty_grid.add(Faculty_lname_tf,1,1);
                                                faculty_grid.add(Premium_User,0,2);
                                                faculty_grid.add(Premium_User_cb,1,2);
                                                faculty_grid.add(Fid,0,3);
                                                faculty_grid.add(Sid_tf,1,3);
                                                faculty_grid.add(Contact_no,0,4);
                                                faculty_grid.add(Contact_no_tf,1,4);
                                                faculty_grid.add(Submit,0,7);
                                                faculty_grid.add(back,1,7);
                                                faculty_grid.setAlignment(Pos.CENTER);
                                                bp.getChildren().clear();
                                                bp.setCenter(faculty_grid);
                                                stage.show();
                                            }
                                        });
                                        Delete_faculty.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent actionEvent) {

                                            }
                                        });
                                        Update_faculty.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent actionEvent) {

                                            }
                                        });
                                        back.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent actionEvent) {
                                                bp.getChildren().clear();
                                                bp.setCenter(list_n);
                                                stage.show();
                                            }
                                        });
                                        bp.getChildren().clear();
                                        bp.setCenter(manage_faculty_box);
                                        stage.show();
                                    }
                                });
                                Premium_content.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {

                                    }
                                });
                                Fines.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {

                                    }
                                });
                                View.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {

                                    }
                                });
                                logout.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {

                                    }
                                });
                                back.setOnAction(new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent actionEvent) {
                                        bp.getChildren().clear();
                                        bp.setCenter(Start);
                                        stage.show();
                                    }
                                });
                                list_n.add(manage_books,0,0);
                                list_n.add(manage_students,1,0);
                                list_n.add(manage_faculty,2,0);
                                list_n.add(Premium_content,0,1);
                                list_n.add(View,1,1);
                                list_n.add(Fines,2,1);
                                list_n.add(Change_username_and_password,0,2);
                                list_n.add(logout,1,2);
                                list_n.add(back,2,2);
                                list_n.setAlignment(Pos.CENTER);
                                bp.setCenter(list_n);
                                bp.setBackground(bg);
                            }
                            else{
                                Label l=new Label("Enter correct values!!");
                                l.setStyle("-fx-font-size: 15pt;");
                                l.setTextFill(new Color(1,0,0,1));
                                Start.add(l,0,6,1,1);
                            }
                        }
                        con.close();
                    }catch(Exception e){ System.out.println(e);}
                }
                else{
                    try{
                        Class.forName("com.mysql.jdbc.Driver");
                        Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Library","root","Karthik@03");
                        Statement stmt=con.createStatement();
                        ResultSet rs=stmt.executeQuery("select * from Student_login");
                        while(rs.next()){
                            if((USERNAME.getCharacters().toString().equals(rs.getString(1)))&&(PASSWORD.getCharacters().toString().equals(rs.getString(2)))){
                                VBox v=new VBox();
                                v.getChildren().addAll();
                                Scene scene2=new Scene(v);
                                stage.setScene(scene2);
                                stage.show();
                            }
                            else{
                                Label l=new Label("Enter correct values!!");
                                Start.getChildren().addAll(l);
                            }
                        }

                        con.close();
                    }catch(Exception e){ System.out.println(e);}
                }
            }
        });
        stage.setScene(scene);
        stage.getIcons().add(im3);
        stage.setTitle("PocketLibrary");
        stage.setMaximized(true);
        stage.show();
    }
    public static void main(String[] args){
        launch();
    }
}
