import java.util.*;
import java.io.*;
import java.net.*;

class EchoServer
{
	public static void main(String[] args)
	{
	try
	{
		ServerSocket s = new ServerSocket(8008);
		while(true)
		{
			Socket client = s.accept();
			PrintWriter out = new PrintWriter(client.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String str;
			while((str = in.readLine()) != null)
			{	
				if(str.trim().equalsIgnoreCase("bye"))
				{ out.println("good bye");break;	}
				String reversed = "";
				for(int i=str.length()-1; i >=0 ;i--)
				{
					reversed += str.charAt(i);
				}
				out.println("Reversed string: " + reversed);
			}
			client.close();
			in.close();
			out.close();
		}
		
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
}
}
				