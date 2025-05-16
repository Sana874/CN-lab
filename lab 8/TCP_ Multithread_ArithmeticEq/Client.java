import java.util.*;
import java.net.*;
import java.io.*;

class Client
{
	public static void main(String[] args)
	{
		try
		{
			Socket socket = new Socket("localhost" , 8009);
			PrintWriter out = new PrintWriter(socket.getOutputStream() , true);
			BufferedReader in = new BufferedReader( new InputStreamReader (socket.getInputStream()));
			Scanner sc  = new Scanner(System.in);
			for(int i=0;i<3;i++)
				{
					System.out.println("Server reply: " + in.readLine());
				}
			String line = null;
			while(!(line = sc.nextLine()).equalsIgnoreCase("bye"))
			{
				out.println(line);
				System.out.println("Server reply: "+ in.readLine());
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