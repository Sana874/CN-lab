import java.util.*;
import java.io.*;
import java.net.*;

class udpBaseServer
{
	public static void main(String[] args)
	{
		try
		{
			DatagramSocket ds = new DatagramSocket(1234);
			byte[] receive = new byte[65535];
			DatagramPacket Dpreceive;
			byte buf[];
			while(true)
			{
				Dpreceive = new DatagramPacket(receive,receive.length);
				ds.receive(Dpreceive);
				String clientMsg = data(receive).toString().trim();
				System.out.println("client: " + clientMsg);
				if (clientMsg.equalsIgnoreCase("Bye")) 
				{
					break;
				}
				String result = calc(clientMsg);
				buf = result.getBytes();
				DatagramPacket Dpsend = new DatagramPacket(buf,buf.length,Dpreceive.getAddress(), Dpreceive.getPort());
				ds.send(Dpsend);
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
		if(a == null) return null;
		int i =0;
		StringBuilder ret = new StringBuilder();
		while(a[i] != 0)
		{
			ret.append((char) a[i]);
			i++;
		}
		return ret;
	}
	public static String calc(String msg)
	{
		String[] tokens = msg.trim().split("\\s+");
		try
		{
			double num1 = Double.parseDouble(tokens[1]);	
			double num2 = Double.parseDouble(tokens[2]);
			double result = 0;
			String command = tokens[0];
			switch(command)
			{
				case "ADD" : result = num1+num2;break;
				case "SUB" : result = num1-num2;break;
				case "MUL" : result = num1*num2;break;
				case "DIV" : result = num1/num2;break;
				default: 
				return "Incorrect option";
			}
			return String.valueOf(result);
		}
		catch(NumberFormatException e)
		{
			return "Invalid number format. Use: CMD num1 num2 (e.g. ADD 3 4)";
		}
	}
}		