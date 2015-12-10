package NETWORK;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SSH
{
    public static Channel sshExec(String direccionIP ,int puerto, String usuario,String password)
    {
        JSch jsch=new JSch();
        Channel channel = null;
        Session session;
        int timeout = 3000;
        try
        {
            session = jsch.getSession(usuario, direccionIP, puerto);
            session.setPassword(password);
            
            //SACANDO EL FINGERPRINT:
            java.util.Properties config = new java.util.Properties(); 
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();  
            
            channel = session.openChannel("exec");
            
            /*channel.setInputStream(System.in);
            channel.setOutputStream(System.out);
            channel.connect(timeout);*/

        } 
        catch (JSchException e)
        {
            e.printStackTrace();
            System.out.println("ERROR: SSH.sshCMD(" + direccionIP + ")");
        }
        return channel;
    }
    public static Channel sshShell(String direccionIP ,int puerto, String usuario,String password)
    {
        JSch jsch=new JSch();
        Channel channel = null;
        Session session;
        int timeout = 3000;
        try
        {
            session = jsch.getSession(usuario, direccionIP, puerto);
            session.setPassword(password);
            
            //SACANDO EL FINGERPRINT:
            java.util.Properties config = new java.util.Properties(); 
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();  
            
            channel = session.openChannel("shell");
            
        } 
        catch (JSchException e)
        {
            e.printStackTrace();
            System.out.println("ERROR: SSH.sshCMD(" + direccionIP + ")");
        }
        return channel;
    }
    public static void enviarComando(Channel canal, String comando)
    {
        //System.out.println(leer(inputStream));
        try
        {
            InputStream input = canal.getInputStream();
            OutputStream output = canal.getOutputStream();
            
            ((ChannelExec)canal).setCommand(comando);
            ((ChannelExec)canal).setErrStream(System.err);
                
            canal.connect(1500);
                
            System.out.println(leer(input));
            
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static String leer(InputStream input) throws IOException
    {
        String salida = "";
        
        System.out.println("input.available():" + input.available());
        while(input.available() > 0)
        {
            int i = input.read();
            //System.out.println("i:" + i);
            if(i<0)
            {
                break;
            }
            else
            {
                char c = (char) i;
               //System.out.println(""+ c);
                salida += "" + c; 
            }
        }

        return salida;
    }
}
