package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable {

	public static void main(String args[]) throws IOException {
		Server server = new Server(8888);
		server.run();
		System.out.println("Server is running");
	}

	private int port;
	private ServerSocket serverSocket;
	private boolean running = false;

	public Server(int port) {
		this.port = port;

		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void start() {

		new Thread(this).start();
	}

	@Override
	public void run() {

		running = true;
		while (running) {

			try {
				Socket socket = serverSocket.accept();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		shutDown();

	}

	private void intSocket(Socket socket) {
		Connection connection = new Connection(socket);
		new Thread(connection).start();
	}

	public void shutDown() {
		running = false;

		try {
			serverSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
