package TCP;

import java.net.*;
import java.io.*;

public class Server {
	public static void main(String[] args) throws Exception {
		int contador = 1;
		System.out.println("Server is running...................");
		ServerSocket socketServer = new ServerSocket(7878);
		while (true) {
			new RevThread(socketServer.accept(), contador).start();
			System.out.println(contador + " client connected");
			contador++;
		}
	}
}

class RevThread extends Thread {
	Socket socketClient = null;
	int contador;

	public RevThread(Socket socket, int count) {
		socketClient = socket;
		contador = count;
	}

	public void run() {
		try {
			while (true) {
				System.out.println("receiving from client " + contador);
				DataInputStream entrada = new DataInputStream(socketClient.getInputStream());
				String fraseRecebida = entrada.readUTF();
				System.out.println("processing data of Client " + contador);
				StringBuffer bufferInvertida = new StringBuffer();
				bufferInvertida = bufferInvertida.append(fraseRecebida);
				bufferInvertida = bufferInvertida.reverse();
				String stringInvertida = new String(bufferInvertida);
				System.out.println("sending to client " + contador);
				DataOutputStream saida = new DataOutputStream(socketClient.getOutputStream());
				saida.writeUTF(stringInvertida);
			}
		} catch (IOException e) {
			System.out.println(e);
		}
	}
}
