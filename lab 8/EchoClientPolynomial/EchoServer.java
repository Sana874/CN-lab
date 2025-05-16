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
				PrintWriter out = new PrintWriter(client.getOutputStream(),true);
				BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
				String str;
				while((str=in.readLine())!=null)
				{
					if (str.trim().equalsIgnoreCase("bye"))
					{ 
						out.println("goodbye"); 
						break;
					}
					out.println("echo: " + calc(str));
				}
				client.close();
			}
		} 
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static String calc(String a)
	{	
		try
		{
			String[] parts = a.trim().split(";");
			String[] tokens = parts[0].trim().split("\\s+");
			double x = Double.parseDouble(parts[1].trim());
			double result = 0 ;
			int degree = tokens.length - 1;
			for(int i = 0 ; i<tokens.length;i++)
			{
				double coeff = Double.parseDouble(tokens[i]);
				result += coeff * Math.pow(x,degree - i);
			}
			return String.valueOf(result);	
		}
		catch(NumberFormatException | ArrayIndexOutOfBoundsException e)
		{
			return "Error Format";
		}
		
	}
}