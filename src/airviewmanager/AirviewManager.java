package airviewmanager;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import javax.swing.JOptionPane;
import model.Ubiquiti;

public class AirviewManager
{
    public static void main(String[] args) throws JSchException
    {
        for(Ubiquiti ubiquiti : daos.UbiquitisDAO.findAll())
        {
            System.out.println(ubiquiti);
        }
        
        
        /*JSch jsch=new JSch();
        
        String host = null;
        String usuario = null;
        String ip = null;
        if(args.length > 0)
        {
            host= args[0];
        }
        else
        {
            host=JOptionPane.showInputDialog("Enter username@hostname","ubnt"+"@192.168.5.145"); 
            String split[] = host.split("@");
            usuario = split[0] ;
            ip= split[1];
            
            System.out.println("host: " + usuario.trim() + " -> " + ip.trim() );
            
            Session session=jsch.getSession(usuario, ip, 22);
            
            String passwd = JOptionPane.showInputDialog("Contraseña:","ubnt");
            session.setPassword(passwd);
            
            java.util.Properties config = new java.util.Properties(); 
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect(30000);   // making a connection with timeout.

            
            Channel channel=session.openChannel("shell");
            
            channel.setInputStream(System.in);
            channel.setOutputStream(System.out);
            channel.connect(3*1000);
            
        }*/
    }
    
}
