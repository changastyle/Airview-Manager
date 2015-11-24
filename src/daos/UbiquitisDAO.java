package daos;

import model.Ubiquiti;

public class UbiquitisDAO
{
    public static java.util.ArrayList<model.Ubiquiti> findAll()
    {
        java.util.ArrayList<model.Ubiquiti> arr = new java.util.ArrayList<model.Ubiquiti>();
        for(Object o : JSON.JSONWS.solamenteConvertirDatosJSONaArrayJava(JSON.JSONWS.getDataFromLocalFile("ubiquitis.json"), model.Ubiquiti.class))
        {
            Ubiquiti ubiquiti = (Ubiquiti) o ;
            arr.add(ubiquiti);
        }
        return arr;
    }
}
