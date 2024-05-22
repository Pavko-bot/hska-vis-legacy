package hska.iwi.eShopMaster;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {

    private String host;
    private String endpoint;
    private String params;

    public HttpClient(String host, String endpoint, String params) {
        this.host = host;
        this.endpoint = endpoint;
        this.params = params;
    }

    public String get() throws IOException {
        URL url = new URL("http://" + host + ":8080/" + endpoint + "?" + params);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        return getResponse(connection);
    }

    public String post(String payload) throws IOException {
        URL url = new URL("http://" + host + ":8080/" + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        OutputStream os = null;
        try {
            os = connection.getOutputStream();
            byte[] input = payload.getBytes("utf-8");
            os.write(input, 0, input.length);
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return getResponse(connection);
    }

    public String delete() throws IOException {
        URL url = new URL("http://" + host + ":8080/" + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("DELETE");

        return getResponse(connection);
    }

    private String getResponse(HttpURLConnection connection) throws IOException {
        int status = connection.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        in.close();
        connection.disconnect();

        if (status == 200) {
            return content.toString();
        } else {
            throw new IOException("Request failed with status: " + status);
        }
    }
}