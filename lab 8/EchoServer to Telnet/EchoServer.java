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
			Socket incoming = s.accept();
			PrintWriter out = new PrintWriter( new OutputStreamWriter(incoming.getOutputStream()));
			BufferedReader in = new BufferedReader(new InputStreamReader(incoming.getInputStream()));
			out.println("Helo");
			out.println("write BYE to exit");
			out.flush();
			while(true)
			{
				String str = in.readLine();
				out.println("Echo " + str);
				out.flush();
				if(str.trim().equals("BYE"))
				{
					break;
				}
			} 
			incoming.close();
		}
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
}}