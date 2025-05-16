import java.util.*;
import java.io.*;
import java.net.*;

class udpBaseClient2
{
	public static void main(String[] args)
	{
		try
		{
			DatagramSocket ds = new DatagramSocket();
			Scanner sc = new Scanner(System.in);
			InetAddress ip = InetAddress.getLocalHost();
			byte[] buf = null;
			while(true)
			{
				String inp = sc.nextLine();
				buf = inp.getBytes();
				DatagramPacket Dpsend = new DatagramPacket(buf, buf.length,ip,1234);
				ds.send(Dpsend);
				if(inp.equalsIgnoreCase("bye"))
				{
					break;
				}
			}	
			sc.close();
			ds.close();		
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}
		