import java.util.*;
import java.io.*;
import java.net.*;

class udpBaseClient
{
	public static void main(String[] args) throws IOException
	{
		DatagramSocket ds = new DatagramSocket();
		Scanner sc = new Scanner(System.in);
		byte buf[];
		InetAddress ip = InetAddress.getLocalHost();
		while(true)
		{
			String inp = sc.nextLine();
			buf = inp.getBytes();
			DatagramPacket DpSend = new DatagramPacket(buf,buf.length,ip,1234);
			ds.send(DpSend);
			if (inp.equals("bye")){break;}
		}
		sc.close();
		ds.close();
	}
}