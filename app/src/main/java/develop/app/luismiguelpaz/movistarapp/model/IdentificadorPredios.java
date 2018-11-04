package develop.app.luismiguelpaz.movistarapp.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class IdentificadorPredios {

	private static HashMap<String, String> dictionary = new HashMap<>();
	private static IdentificadorPredios identificador;

	private IdentificadorPredios() {
		dictionary = new HashMap<>();
		try {
			BufferedReader in  = new BufferedReader(new FileReader(new File("./resources/dictionary.txt")));
			String line = in.readLine();
			while (line!=null) {
				StringTokenizer skt = new StringTokenizer(line);
				dictionary.put(skt.nextToken(), skt.nextToken());
				line = in.readLine();
			}
			in.close();
		} catch (Exception e) {
		}
	}

	public static String getPredio(String direccion){
		if (identificador == null){
			identificador = new IdentificadorPredios();
		}
		return IdentificarPredio(direccion);
	}

	public static String IdentificarPredio(String direccion) {

		StringTokenizer skt = new StringTokenizer(direccion);		
		while(skt.hasMoreTokens()) {
			String part = skt.nextToken().toUpperCase();
			if (dictionary.containsKey(part)) {
				return dictionary.get(part);
			}
		}
		return "Casa";
	}
	
}
