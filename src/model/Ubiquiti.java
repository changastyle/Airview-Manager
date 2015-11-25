package model;

import javafx.beans.property.SimpleStringProperty;

public class Ubiquiti
{
    private String direccionIP;
    private String nombreUbiquiti;
    private String usuario;
    private String password;

    public Ubiquiti()
    {
        this.direccionIP = "";
        this.nombreUbiquiti =  "";
        this.usuario = "";
        this.password = "";
    }

    public Ubiquiti(String direccionIP, String nombreUbiquiti, String usuario, String password)
    {
        this.direccionIP = direccionIP;
        this.nombreUbiquiti = nombreUbiquiti;
        this.usuario = usuario;
        this.password = password;
    }

    //<editor-fold desc="GYS:">
    public String getDireccionIP()
    {
        return direccionIP;
    }

    public void setDireccionIP(String direccionIP)
    {
        this.direccionIP = direccionIP;
    }

    public String getNombreUbiquiti()
    {
        return nombreUbiquiti;
    }

    public void setNombreUbiquiti(String nombreUbiquiti)
    {
        this.nombreUbiquiti = nombreUbiquiti;
    }

    public String getUsuario()
    {
        return usuario;
    }

    public void setUsuario(String usuario)
    {
        this.usuario = usuario;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    //</editor-fold>

    @Override
    public String toString()
    {
        return "Ubiquiti{" + "direccionIP=" + direccionIP + ", nombreUbiquiti=" + nombreUbiquiti + ", usuario=" + usuario + ", password=" + password + '}';
    }
}
