import java.util.*;
import java.io.*;
import java.net.*;

class udpBaseClient
{
	public static void main(String[] args)
	{
		try
		{
			DatagramSocket ds = new DatagramSocket();
			InetAddress ip = InetAddress.getLocalHost();
			byte[] receive = null;
			byte[] buf =null;
			Scanner sc = new Scanner(System.in);
			while(true)
			{
				String inp = sc.nextLine();
				buf = inp.getBytes();
				DatagramPacket Dpsend = new DatagramPacket(buf,buf.length,ip,1234);
				ds.send(Dpsend);
				if(inp.equalsIgnoreCase("bye")) {break;}
				receive = new byte[65535];
				DatagramPacket Dpreceive = new DatagramPacket(receive,receive.length);
				ds.receive(Dpreceive);
				System.out.println("Server reply: " + data(receive));
				receive = new byte[65535];
			}
		}		
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	public static StringBuilder data(byte[] a)
	{
		int i = 0;
		StringBuilder ret = new StringBuilder();
		if (a == null)
			{ return null; }
		while(a[i]!= 0)
 		{
			ret.append((char) a[i]);
			i++;
		}
		return ret;
	}
}

				
				