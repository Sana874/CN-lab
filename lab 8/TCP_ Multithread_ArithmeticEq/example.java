#server.java

import java.util.*;
import java.net.*;
import java.io.*;

class Server{
	public static void main(String[] args){
		try
		{
			ServerSocket s = new ServerSocket(8009);
			s.setReuseAddress(true);
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

------------------------------------------------------------------------------------------------------------------------------------------------------------
#Client.java

import java.util.*;
import java.io.*;
import java.net.*;

class Client
{
	public static void main(String[] args)
	{
		try
		{
			Socket socket = new Socket("localhost",8009);
			PrintWriter out = new PrintWriter(socket.getOutputStream());
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			Scanner sc = new Scanner(System.in);
			for(int i=0;i<3;i++)
			{
				System.out.println(in.readLine());
			}
			String line = null;
			while(!(line=sc.nextLine()).equalsIgnoreCase("bye"))
			{
				out.println(line);
				System.out.println("Server Reply: " + in.readLine());

			}
			out.println("bye");	
			sc.close();
			socket.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}

------------------------------------------------------------------------------------------------------------------------------------------------------------

#ClientHandler1

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
		BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		out.println("Hello my love");
		out.println("Enter in the format: add/sub/mul/div num1 num2");
		out.println("Enjoy! ");
		String str;
		while((str = in.readLine()) != null)
		{
			if (str.trim().equalsIgnoreCase("bye"))
			{
				out.println("byee");
				break;
			}
			String[] tokens = str.trim().split("\\s+");
			double result = 0;
			String command = tokens[0];
			switch(command)
			{
				case "ADD":
				case "SUB":
				case "DIV":
				case "MUL":
				if(tokens.length<3)
				{
					out.println("Invalid bro"); break;
				}
				try
				{
					double num1 = Double.parseDouble(tokens[1]);
					double num2 = Double.parseDouble(tokens[2]);
					switch(command)
					{
						case "ADD": result = num1 + num2;break;
						case "SUB": result = num1 - num2;break;
						case "MUL": result = num1 * num2;break;
						case "DIV": 
							if(num2 == 0)
							{
								out.println("Invalid Deno");
								continue;
							}
							result = num1 / num2;					
							break;
					}
				}
				catch(NumberFormatException e)
				{
					out.println("Invalid");
				}
			break;
			default:
			out.println("Invalid");
			}
		}
	}
	catch(IOException e)
	{
		e.printStackTrace();
	}
	}
}
			
















