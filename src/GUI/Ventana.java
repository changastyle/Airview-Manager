import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javax.jws.Oneway;
import model.Ubiquiti;
 
public class Ventana extends Application
{
 
    private TableView<model.Ubiquiti> table = new TableView<model.Ubiquiti>();
    ObservableList<model.Ubiquiti> data = FXCollections.observableArrayList();
    static ArrayList<model.Ubiquiti> model = new ArrayList<Ubiquiti>();
    

    public static void main(String[] args) 
    {
        for(Ubiquiti ubiquiti : daos.UbiquitisDAO.findAll())
        {
            //model.add(ubiquiti);
            System.out.println(ubiquiti);
        }
        launch(args);
    }
 
    @Override
    public void start(Stage stage)
    {
        Scene scene = new Scene(new Group());
        stage.setTitle("Airview Manager");
        stage.setWidth(650);
        stage.setHeight(600);
 
        final Label label = new Label("Seleccionar un Ubiquiti:");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
        
        final ObservableList<Ubiquiti> ubiquitiCollection = FXCollections.observableArrayList();
        for(Ubiquiti ubiquiti : daos.UbiquitisDAO.findAll())
        {
            ubiquitiCollection.add(ubiquiti);
        }
        
        TableColumn primeraColumna = new TableColumn("nombreUbiquiti");
        primeraColumna.setMinWidth(150);
        primeraColumna.setCellValueFactory(new PropertyValueFactory<model.Ubiquiti, String>("nombreUbiquiti"));
        
        TableColumn segundaColumna = new TableColumn("direccionIP");
        segundaColumna.setMinWidth(150);
        segundaColumna.setCellValueFactory(new PropertyValueFactory<model.Ubiquiti, String>("direccionIP"));
        
        TableColumn terceraColumna = new TableColumn("usuario");
        terceraColumna.setMinWidth(150);
        terceraColumna.setCellValueFactory(new PropertyValueFactory<model.Ubiquiti, String>("usuario"));
        
        TableColumn cuartaColumna = new TableColumn("password");
        cuartaColumna.setMinWidth(150);
        cuartaColumna.setCellValueFactory(new PropertyValueFactory<model.Ubiquiti, String>("password"));
 
        table.setItems(ubiquitiCollection);
        table.getColumns().addAll(primeraColumna, segundaColumna, terceraColumna,cuartaColumna);
 
        Button button1 = new Button();
        button1.setText("Airview");
        button1.setOnAction((ActionEvent event) -> 
        {
            Ubiquiti ubiquiti = table.getSelectionModel().getSelectedItem();  
            System.out.println("Abriendo: " + ubiquiti.getNombreUbiquiti() );
            
            Channel canal = NETWORK.SSH.sshExec(ubiquiti.getDireccionIP(), 22 , ubiquiti.getUsuario(), ubiquiti.getPassword());
            NETWORK.SSH.enviarComando(canal, "/bin/sh /sbin/airview service");
            System.out.println("canal: " + canal);
            
            CMD.Consola.executar("java -jar airview.jar ubnt://"+ubiquiti.getDireccionIP()+":18888");
            
        });
        
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table, button1);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
                
        stage.setScene(scene);
        stage.show();
    }
}