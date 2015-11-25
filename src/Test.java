import static NETWORK.SSH.enviarComando;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test
{
   
    public static void main(String args[]) throws IOException, JSchException
    {
        /*Channel canal = NETWORK.SSH.sshExec("192.168.5.145", 22 , "ubnt", "ubnt");

        System.out.println("canal: " + canal);
        
        NETWORK.SSH.enviarComando(canal, "ps");
        NETWORK.SSH.enviarComando(canal, "airview");
        */
        Channel canal = NETWORK.SSH.sshShell("192.168.5.145", 22 , "ubnt", "ubnt");
        
        
        InputStream in = canal.getInputStream();
        OutputStream output = canal.getOutputStream();
        System.out.println("IN: " + in + " | OUT: " +output);
        
        canal.connect();
        
        System.out.println(NETWORK.SSH.leer(canal.getInputStream()));
        /*
        try
            {
                InputStream inputStream = canal.getInputStream();
                OutputStream output = canal.getOutputStream();
                
                ((ChannelExec)canal).setCommand("ps");
                ((ChannelExec)canal).setErrStream(System.err);
                
                canal.connect(1500);
                
                System.out.println(leer(inputStream));
                //((ChannelExec)canal).setCommand("airview start");
                //((ChannelExec)canal).setErrStream(System.err);
                //enviarComando(canal, "ps");
                

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
                /*
                output.flush();
                ((ChannelExec) canal).setCommand("ls");
                

                canal.connect(3000);
                String cadena = "";
                while(true)
                {
                    char caracter = (char) inputStream.read();
                    cadena += String.valueOf(caracter);
                    
                    if(caracter == '#' || caracter == '$')
                    {
                        System.out.println( cadena );
                        break;
                    }
                }
                byte[] b = {'h', 'e', 'l', 'l', 'o'};
                output.write(b);
                
                //canal.sendSignal("ls");
                
                while(true)
                {
                    char caracter = (char) inputStream.read();
                    cadena += String.valueOf(caracter);
                    
                    if(caracter == '#' || caracter == '$')
                    {
                        System.out.println( cadena );
                        break;
                    }
                }
                //InputStream input = canal.getInputStream();
                //canal.setOutputStream(System.out);
                /*
                canal.setOutputStream(System.out);
                String comando = "airview start";
 
            } 
            catch (JSchException ex)
            {
                ex.printStackTrace();
                System.out.println("ERROR: SSH");
            } catch (IOException ex)
            {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) 
        {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        } */
    }

   
}
