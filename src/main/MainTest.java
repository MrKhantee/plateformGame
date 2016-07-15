package main;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class MainTest {


	public static void main(String[] args) throws Exception {

				System.out.println(InetAddress.getLocalHost().getHostAddress());
				String url = "https://rtsserveur-kevinbienvenu.c9users.io/";
				String urlParameters = "pseudo=roger2&password=123";
		
				System.out.println("Testing 1 - Send Http GET request");
				MainTest.sendGet(url);
				
				System.out.println("\nTesting 2 - Send Http POST request");
				MainTest.sendPost(url, urlParameters);
		
//		Socket MyClient;
//	    try {
//	           MyClient = new Socket("www.testserver-kevinbienvenu.c9users.io/", 8080);
//	    }
//	    catch (IOException e) {
//	        System.out.println(e);
//	    }
	}

	// HTTP GET request
	private static void sendGet(String url) throws Exception {

		URL obj = new URL(url);
		System.out.println(obj.getDefaultPort());
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		con.disconnect();
		//print result
		System.out.println(response.toString());

	}

	// HTTP POST request
	private static void sendPost(String url, String urlParameters) throws Exception {

		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

		System.out.println(con.toString());

		//add reuqest header
		con.setRequestMethod("POST");

		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		con.disconnect();

		//print result
		System.out.println(response.toString());

	}

}