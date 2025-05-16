import java.util.*;
import java.io.*;
import java.net.*;

class EchoServer
{
	public static void main(String[] args)
	{		
		try
		{
			ServerSocket s = new ServerSocket(8009);
			while(true)
			{
				Socket client = s.accept();
				System.out.println("Address: " + client.getInetAddress().getHostAddress());
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				PrintWriter out = new PrintWriter(client.getOutputStream(),true);
				String str;
				while((str = in.readLine()) != null)
				{
					if(str.trim().equalsIgnoreCase("bye"))
					{ out.println("Goodbye"); break;}
					out.println("Echo " + str.toUpperCase());
				}
			client.close();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
			
					