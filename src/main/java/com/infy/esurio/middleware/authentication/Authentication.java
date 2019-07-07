/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infy.esurio.middleware.connection;

/**
 *
 * @author developer
 */
import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jooq.tools.json.JSONObject;

public class Connection {

    private String userName = "admin";
    private String password = "admin";
    private String token;
    private String API_URL = "http://192.168.1.206:8080/api/authenticate";

    public void authenticate() throws MalformedURLException, IOException {
        JSONObject login = new JSONObject();
        login.put("username", userName);
        login.put("password", password);
        HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
        System.out.println(login);
        HttpRequest request = requestFactory.buildPostRequest(new GenericUrl(API_URL), ByteArrayContent.fromString("application/json", login.toString()));
        String rawResponse = request.execute().parseAsString();
        System.out.println(rawResponse);
    }

    public static void main(String[] args) {
        Connection connection = new Connection();
        try {
            connection.authenticate();
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
