/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/
package avion_acc;

import avion_accov.Avion;
import java.awt.AWTException;
import java.sql.Connection;
import java.util.HashMap;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * @author ne3mat_slim
 */
public class new_avion extends Application {
    
    static HashMap<String,String> CompListinfo;
   
    static Connection conn ;
    @Override
    public void start(Stage primaryStage) throws AWTException {
        
        
        //==============declare variables and components
        CompListinfo=new HashMap<>();
       
        BorderPane root = new BorderPane();
        VBox vbox1=new VBox();
        BorderPane border1=new BorderPane();
        GridPane grid1=new GridPane();
        grid1.setHgap(10);
        grid1.setVgap(10);
        HBox hbox1=new HBox();
        ImageView imgview1=new ImageView();
        ImageView imgview2=new ImageView();
        //Label lb1=new Label("company name");
        //Label lb2=new Label("address");
       // Label lb3=new Label("phone");
        //lb1.setId("titles");
        //lb2.setId("titles");
        //lb3.setId("titles");
        GridPane grid2=new GridPane();
        grid2.setHgap(10);
        grid2.setVgap(10);
        Label nolbl=new Label("Numero Avion");
        Label nomlbl=new Label("Nom Avion");
        Label depbl=new Label("Depart");
        Label arrlbl=new Label("Arrivee");
        Label xlbl=new Label("Position X");
        Label ylbl=new Label("Position Y");
        Label zlbl=new Label("Position Z");
        
        Label error=new Label("");
        error.setId("error");
        nolbl.setId("usr");
        nomlbl.setId("usr");
        depbl.setId("usr");
        arrlbl.setId("usr");
        xlbl.setId("usr");
        ylbl.setId("usr");
        zlbl.setId("usr");
        
        TextField notxt=new TextField();
        TextField nomtxt =new TextField();
        TextField deptxt =new TextField();
        TextField arrtxt =new TextField();
        TextField xtxt =new TextField();
        TextField ytxt =new TextField();
        TextField ztxt =new TextField();
        
        notxt.setPrefWidth(200);
        nomtxt.setPrefWidth(200);
        deptxt.setPrefWidth(200);
        arrtxt.setPrefWidth(200);
        xtxt.setPrefWidth(200);
        ytxt.setPrefWidth(200);
        ztxt.setPrefWidth(200);
        
        Button loginbtn=new Button("Login");
        loginbtn.setId("rich-blue");
        loginbtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                 Avion a = new Avion(Integer.parseInt(notxt.getText()),
                nomtxt.getText(),deptxt.getText(),arrtxt.getText(),
                Integer.parseInt(xtxt.getText()),Integer.parseInt(ytxt.getText()),
                        Integer.parseInt(ztxt.getText()));
       

 new Thread(() -> {
     try{
         a.start();
        
     }
     catch(Exception e){}
        }).start();
            }
        });
        //==============end declare variables and components
        //================assign items to parents
        root.setCenter(vbox1);
        vbox1.getChildren().add(border1);
        vbox1.getChildren().add(grid1);
        
        border1.setLeft(hbox1);
        border1.setRight(imgview1);
        hbox1.getChildren().add(imgview2);
        hbox1.getChildren().add(grid2);
        
        grid1.add(nolbl, 5, 3);
        grid1.add(nomlbl, 5, 4);
        grid1.add(depbl, 5, 5);
        grid1.add(arrlbl, 5, 6);
        grid1.add(xlbl, 5, 7);
        grid1.add(ylbl, 5, 8);
        grid1.add(zlbl, 5, 9);
        
        grid1.add(notxt, 6, 3);
        grid1.add(nomtxt, 6, 4);
         grid1.add(deptxt, 6, 5);
          grid1.add(arrtxt, 6, 6);
           grid1.add(xtxt, 6, 7);
            grid1.add(ytxt, 6, 8);
             grid1.add(ztxt, 6, 9);
             
        grid1.add(loginbtn, 5, 10);
        grid1.add(error, 6, 10);
        //================end assign items to parents
       
        
        
        Scene scene = new Scene(root, 500, 350);
        String cssURL = this.getClass().getResource("/css/avion.css").toExternalForm();
        scene.getStylesheets().add(cssURL);
        primaryStage.setMaximized(false);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Avion");
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        launch(args);
    }
    public boolean checkUserName(String usertext,String passtext){
        Boolean ResBool=true;
        return ResBool;
    }
}
