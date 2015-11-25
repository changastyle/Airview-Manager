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
    /*ObservableList<model.Ubiquiti> data = FXCollections.observableArrayList();
    data.*/
   /* static ArrayList<model.Ubiquiti> model = new ArrayList<Ubiquiti>();*/
    
   /* 
    public static void main(String[] args) 
    {
        for(Ubiquiti ubiquiti : daos.UbiquitisDAO.findAll())
        {
            //model.add(ubiquiti);
            System.out.println(ubiquiti);
        }
        launch(args);
    }*/
 
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
            
            Channel canal = NETWORK.SSH.sshCMD(ubiquiti.getDireccionIP(), 22 , ubiquiti.getUsuario(), ubiquiti.getPassword());

            try
            {   
                InputStream inputStream = canal.getInputStream();
                OutputStream output = canal.getOutputStream();
                
                /*String cadena = "";
                while(true)
                {
                    char caracter = (char) inputStream.read();
                    cadena += String.valueOf(caracter);
                    
                    if(caracter == '#' || caracter == '$')
                    {
                        System.out.println( cadena );
                        break;
                    }
                }*/

                /*((ChannelExec) canal).setCommand("ls");*/
                canal.connect();
                
                byte[] tmp=new byte[1024];
                while(true)
                {
                    System.out.println("input stream: " + inputStream);
                  while(inputStream.available() > 0)
                  {
                    int i=inputStream.read(tmp, 0, 1024);
                    if(i<0)break;
                    System.out.print(new String(tmp, 0, i));
                  }
                  if(canal.isClosed())
                  {
                    if(inputStream.available()>0) continue; 
                    System.out.println("exit-status: "+canal.getExitStatus());
                    break;
                  }
                }
                canal.disconnect();
                
              }
              catch(Exception e)
              {
                System.out.println(e);
              }
                /*while(true)
                {
                    char caracter = (char) inputStream.read();
                    cadena += String.valueOf(caracter);
                    
                    if(caracter == '#' || caracter == '$')
                    {
                        System.out.println( cadena );
                        break;
                    }
                }*/
                //InputStream input = canal.getInputStream();
                //canal.setOutputStream(System.out);
                /*
                canal.setOutputStream(System.out);
                String comando = "airview start";*/
             /*catch (IOException ex)
            {
                ex.printStackTrace();
                System.out.println("ERROR: SSH2");
            }*/
            
            

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