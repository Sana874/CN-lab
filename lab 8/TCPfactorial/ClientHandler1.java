import java.util.*;
import java.io.*;
import java.net.*;

public class ClientHandler1 implements Runnable
{
	private final Socket socket;
	public ClientHandler1(Socket socket)
	{
		this.socket = socket;
	}
	public void run()
	{
		try
		{
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			BufferedReader in = new BufferedReader( new InputStreamReader(socket.getInputStream()));
			String str;
			while((str = in.readLine()) != null)
			{
				if (str.trim().equalsIgnoreCase("bye"))
				{
					out.println("bye"); break;
				}
				String[] tokens = str.trim().split("\\s+");
				try {double num = Double.parseDouble(tokens[0]);
				double result =1;
				for(int i=1 ; i<=num ; i++)
				{
					result = result * i ;
				}		
				out.println("result: " + result);}
				catch(NumberFormatException e) { out.println("ERROR");}
			}
		}			
		catch(IOException e)
		{
			e.printStackTrace();
		}	
		finally {
    			try {
        			socket.close();
    				} 
			catch (IOException e)
				{
        				e.printStackTrace();
    				}
			}

	}
}



