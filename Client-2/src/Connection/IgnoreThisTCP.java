package Connection;

import java.io.*;
import java.net.*;

class IgnoreThisTCP {
	public static void main(String argv[]) throws Exception {
		ServerSocket welcomeSocket = new ServerSocket(9005);
		System.out.println("Listening...");
		Socket connectionSocket = welcomeSocket.accept();
		System.out.println("Connected");
	}
}