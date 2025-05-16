import java.util.*;
import java.io.*;
import java.net.*;

class udpBaseServer
{
	public static void main(String[] args) throws IOException
	{
		DatagramSocket ds = new DatagramSocket(1234);
		byte[] receive = new byte[65535];
		DatagramPacket DpReceive = null;
		while(true)
		{
			DpReceive = new DatagramPacket(receive,receive.length);
			ds.receive(DpReceive);
			String clientMsg = data(receive).toString().trim();
			System.out.println("Client: " + clientMsg);
			if (clientMsg.equals("bye"))
				{break;}
			System.out.println("Processing command...");
			String response = processCommand(clientMsg);
			byte[] send = response.getBytes();
			DatagramPacket DpSend = new DatagramPacket(send,send.length,DpReceive.getAddress(),DpReceive.getPort());
			ds.send(DpSend);
			receive = new byte[65535];
		}
		ds.close();
	}
	public static StringBuilder data(byte[] a)
	{
		if (a == null)
			{ return null;}
		int i=0;
		StringBuilder ret = new StringBuilder();
		while(i<a.length && a[i] != 0)
		{
			ret.append((char) a[i]);
			i++;
		}
		return ret;
	}
	public static String processCommand(String msg) {
    String[] tokens = msg.trim().split("\\s+");

    if (tokens.length != 3) {
        return "Error: Expected format <OPERATION> <num1> <num2>";
    }

    String command = tokens[0].toUpperCase();
    try {
        double num1 = Double.parseDouble(tokens[1]);
        double num2 = Double.parseDouble(tokens[2]);
        double result;

        switch (command) {
            case "ADD":
                result = num1 + num2;
                break;
            case "SUB":
                result = num1 - num2;
                break;
            case "MUL":
                result = num1 * num2;
                break;
            case "DIV":
                if (num2 == 0) {
                    return "Error: Cannot divide by zero";
                }
                result = num1 / num2;
                break;
            default:
                return "Error: Invalid operation";
        }

        return "Result: " + result;

    } catch (NumberFormatException e) {
        return "Error: Please enter valid numbers";
    } catch (Exception e) {
        return "Error: Unexpected server error";
    }
}
	
}
			





