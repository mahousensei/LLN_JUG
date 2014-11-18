package internet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class SimpleEchoClient {
	
	public static void main(String[] args) {
		String hostName = "localhost";
		int portNumber = 88;

		try (
		    Socket socket = new Socket(hostName, portNumber);
		    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
		    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		) {
			out.println("Hello");
			String resp = in.readLine();
			System.out.println("received " + resp);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
}
