import java.util.*;
import java.io.*;
import java.net.*;

public class ClientHandler1 implements Runnable
{
	private Socket socket;
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
			out.println("HELLOS WELCOME TO MY CALC");
			out.println("TYPE BYE TO EXIT");
			out.println("Send: ADD/SUB/MUL/DIV num1 num2");
			String str;
			while ((str = in.readLine())!=null)
			{
				if (str.trim().equalsIgnoreCase("bye"))
				{
					out.println("Goodbye!");
					break;
				}
				String[] tokens = str.trim().split("\\s+");
				if (tokens.length < 1)
					{
						out.println("Invalid input");
						continue;
					}
				String command = tokens[0].toUpperCase();
				switch(command)
				{
					case "ADD":
					case "SUB":
					case "MUL":
					case "DIV":
					if (tokens.length != 3)
						{
							out.println("invalid");
							break;
						}
					try
					{
						double num1 = Double.parseDouble(tokens[1]);
						double num2 = Double.parseDouble(tokens[2]);
						double result = 0;
						switch (command)
						{
							case "ADD" : result = num1 + num2; break;
							case "SUB" : result = num1 - num2; break;
							case "MUL" : result = num1 * num2; break;
							case "DIV" : 
								if(num2 == 0)
									{ out.println("Invalid division"); continue;}
								result = num1 / num2;
								break;
						}
						out.println("results" + result);
					}
					catch(NumberFormatException e)
					{
						out.println("error");
					}
					break;
					default: 
					out.println("oh no wrong");
					
				}
			}
			socket.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}	

