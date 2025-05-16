import java.util.*;
import java.io.*;
import java.net.*;

class EchoClient
{
	public static void main(String[] args)
	{
		try
		{
			Socket client = new Socket("localhost",8009);
			PrintWriter out = new PrintWriter(client.getOutputStream(),true);
			BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			Scanner sc = new Scanner(System.in);
			String line;
			while(true)
			{
				line = sc.nextLine();
				out.println(line);
				System.out.println("Server reply: " + in.readLine());
				if (line.trim().equalsIgnoreCase("bye"))
				{
					out.println("Client said bye");
					break;
				}
			}
			client.close();
			sc.close();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}