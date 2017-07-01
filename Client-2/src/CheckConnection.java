import Connection.Connector;
import Connection.GateServer;
import Connection.Server;
import External.Singleton;

public class CheckConnection {
	static class checkConnection extends Thread {

		public void run() {
			Singleton.debugPrint("Checking for server connection");

			GateServer gs = new GateServer();

			try {
				gs.connect();
				Server newServer = gs.transfer();
				Main.setConnector(newServer);
			} catch (Exception ex) {
				Singleton.debugPrint("An Exception was thrown when connecting to the socket", ex);
				ConnectLayout.failedStatus = true;
			}
		}
	}
}
