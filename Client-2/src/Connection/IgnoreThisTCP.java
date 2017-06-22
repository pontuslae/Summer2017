package Connection;

import External.Singleton;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

public class IgnoreThisTCP extends Thread {

	ServerSocket serverSocket;

	public static IgnoreThisTCP th;

	public static IgnoreThisTCP getInstance() {
		return th;
	}

	public void run() {
		if (th == null) {
			Singleton.debugPrint("itt: th == null");
			makeServerSocket();
		} else if (th.serverSocket == null){
			Singleton.debugPrint("itt: th.serverSocket == null");
			makeServerSocket();

			Singleton.debugPrint("Starting Mocking server.");
			accept();

		} else {
			Singleton.debugPrint("itt: else");
			this.serverSocket = th.serverSocket;
		}
		th = this;
	}

	private void makeServerSocket(){
		try {
			this.serverSocket = new ServerSocket(9005);
		} catch (IOException ex) {
			Singleton.debugPrint("Exception was thrown during mocking", ex);
		}
	}

	private void accept(){
		try {
			this.serverSocket.accept();
			Singleton.debugPrint("Connected to Mocking server.");
		} catch (IOException ex) {
			Singleton.debugPrint("An IOException was thrown when mocking a server and accepting.");
		}
	}

	public IgnoreThisTCP() {
	}

	public void mock(String receive, int send){
		try {
			Singleton.debugPrint("itt: Blocking for accept");
			Socket socket = serverSocket.accept();
			Singleton.debugPrint("itt: Accepted");

			BufferedReader inFromClient =
					new BufferedReader(new InputStreamReader(socket.getInputStream()));
			DataOutputStream outToClient = new DataOutputStream(socket.getOutputStream());
			String rl = inFromClient.readLine();

			Singleton.debugPrint("Received: " + rl + " | Expected: " + receive);

			outToClient.write(send);
		} catch (IOException ex) {
			Singleton.debugPrint("Exception was thrown during mocking", ex);
		}
	}

	public static void main(String argv[]) throws Exception {
		System.out.println("Listening...");
		new ServerSocket(9005).accept();
		System.out.println("Connected");
	}
}