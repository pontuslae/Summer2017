import Connection.Connector;
import External.Singleton;

public class CheckConnection {
	static class checkConnection extends Thread {

		public void run() {
			Singleton.debugPrint("Checking for server connection");

			Connector connector = Main.getConnector();
			try {
				connector.connect();
			} catch (Exception ex) {
				Singleton.debugPrint("An Exception was thrown when connecting to the socket", ex);
				ConnectingLayout.failedStatus = true;
				return;
			}

			// Hangs the client while not connected.
			while (!connector.isConnected());
		}

	}
}
