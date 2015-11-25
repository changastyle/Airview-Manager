package CMD;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Consola 
{
	public static ArrayList<String> executar(String comando)
	{
            ArrayList<String> salida = new ArrayList<String>();
		
            String lineaConsola  = null;
	    String preComando = "cmd /c ";
	    comando = preComando + comando;
	    
	    try
	    {
	        Process proceso = Runtime.getRuntime().exec(comando);

	        InputStreamReader isr = new InputStreamReader(proceso.getInputStream());
	        BufferedReader br = new BufferedReader(isr);

	        if(	( lineaConsola = br.readLine())   != null)
	        {
	            //System.out.println("Comando ejecutado Correctamente");
	        	salida.add(lineaConsola);
	        	
	            while (	( lineaConsola = br.readLine())   != null)
	            {
	            	salida.add(lineaConsola);
	            } 
	        }
	        else
	        {
	            System.out.println("No se a producido ninguna salida");
	        }
	    }
	    catch (IOException e) 
	    {
	            System.out.println("Excepci�n: ");
	            e.printStackTrace();
	    }
	    return salida;
	}
}
