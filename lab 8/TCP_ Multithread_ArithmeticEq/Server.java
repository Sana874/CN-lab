import java.io.*;
import java.net.*;
import java.util.*;

class Server
{
	public static void main(String[] args)
	{
		try
		{
			ServerSocket s = new ServerSocket(8009);
			while(true)
			{
				Socket client = s.accept();
				ClientHandler1 clientSock = new ClientHandler1(client);
				new Thread(clientSock).start();
			}
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}