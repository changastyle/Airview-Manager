import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.Ubiquiti;
 
public class Ventana extends Application 
{
 
    private TableView<model.Ubiquiti> table = new TableView<model.Ubiquiti>();
    /*ObservableList<model.Ubiquiti> data = FXCollections.observableArrayList();
    data.*/
    static ArrayList<model.Ubiquiti> model = new ArrayList<Ubiquiti>();
    
    
    public static void main(String[] args) 
    {
        for(Ubiquiti ubiquiti : daos.UbiquitisDAO.findAll())
        {
            model.add(ubiquiti);
            System.out.println(ubiquiti);
        }
        launch(args);
    }
 
    @Override
    public void start(Stage stage)
    {
        Scene scene = new Scene(new Group());
        stage.setTitle("Airview Manager");
        stage.setWidth(500);
        stage.setHeight(500);
 
        final Label label = new Label("Seleccionar un Ubiquiti:");
        label.setFont(new Font("Arial", 20));
 
        table.setEditable(true);
 
        TableColumn columnaNombre = new TableColumn("Nombre");
        TableColumn columnaUsuario = new TableColumn("Usuario");
        TableColumn columnaPassword = new TableColumn("Password");
        TableColumn columnaDireccionIP = new TableColumn("DireccionIP");
        
        //columnaNombre.setCellValueFactory(new PropertyValueFactory<model.Ubiquiti, String>("nombreUbiquiti"));
         ObservableList<model.Ubiquiti> olist = FXCollections.observableArrayList(model);
        table.setSelectionModel(olist);
        
        table.getColumns().addAll(columnaNombre, columnaUsuario, columnaPassword,columnaDireccionIP);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(10, 0, 0, 10));
        vbox.getChildren().addAll(label, table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
}