package TCP;

import java.io.*;
import java.net.*;

public class Client {
	public static void main(String[] args) throws Exception {
		Socket socketClient = new Socket("127.0.0.1", 7878);
		if (socketClient.isConnected()) {
			System.out.println("Conectado ao server....");
		}
		while (true) {
			System.out.println("Digite sua frase para ser invertida:");
			DataInputStream entrada = new DataInputStream(System.in);
			String frase = entrada.readLine();
			DataOutputStream saida = new DataOutputStream(socketClient.getOutputStream());
			saida.writeUTF(frase);

			DataInputStream entradaDoServer = new DataInputStream(socketClient.getInputStream());
			String fraseInvertida = entradaDoServer.readUTF();
			System.out.println("Frase invertida:\t" + fraseInvertida);
		}
	}
}
