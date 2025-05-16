import java.util.*;
import java.io.*;
import java.net.*;

class udpBaseServer2
{
	public static void main(String[] args)
	{
		try
		{
			DatagramSocket ds = new DatagramSocket(1234);
			byte[] receive = new byte[65535];
			DatagramPacket Dpreceive;
			while(true)
			{
				Dpreceive = new DatagramPacket(receive,receive.length);
				ds.receive(Dpreceive);
				System.out.println("Client: " + data(receive));
				if (data(receive).toString().equalsIgnoreCase("bye")) 
					{
						break;
					}
				receive = new byte[65535];
			}
			ds.close();
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

				
					