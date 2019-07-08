/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infy.esurio.middleware.requestshelper;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.infy.esurio.middleware.authentication.Authentication;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author developer
 */
public class RequestHelper {
    private static String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImF1dGgiOiJST0xFX0FETUlOLFJPTEVfVVNFUiIsImV4cCI6MTU2MjY4MjUyOH0.3d78yeeK9NDS5qqvT5nJ8st_YsZaBcSd4MXm7SErIGKnHV6hA8ykrYuhmxbvyne1xIcQoaekTIuK593za5Sung";
    private static final HttpRequestFactory requestFactory = new NetHttpTransport().createRequestFactory();
    
    /**
     *
     * @param URL
     * @param authorization
     * @param data
     * @param type
     * @return
     * @throws IOException
     */
    
    public static void RefreshToken() throws IOException{
        
           Authentication authentication = new Authentication();
           RequestHelper.token = authentication.getToken();

    } 

    public static HttpResponse doPost(String URL, String data, String type) throws IOException {
        if( RequestHelper.token == null || RequestHelper.token.isEmpty()){
            RequestHelper.RefreshToken();
        }
        ByteArrayContent fromString = ByteArrayContent.fromString("application/json", data);
        HttpRequest postRequest = RequestHelper.requestFactory.buildPostRequest(new GenericUrl(URL), fromString);
        HttpHeaders authHeader = new HttpHeaders();
        postRequest.setHeaders(new HttpHeaders().setAuthorization("Bearer " + RequestHelper.token));
        return postRequest.execute();
    } 
    
    /**
     *
     * @param URL
     * @param authorization
     * @return
     * @throws java.io.IOException
     */
    public static HttpResponse doGet(String URL) throws IOException{
        System.out.println(RequestHelper.token);
        if( RequestHelper.token == null || RequestHelper.token.isEmpty()){
            RequestHelper.RefreshToken();
        }
        HttpRequest getRequest = RequestHelper.requestFactory.buildGetRequest(new GenericUrl(URL));
        getRequest.setHeaders(new HttpHeaders().setAuthorization("Bearer " + RequestHelper.token));
        HttpResponse response = getRequest.execute();
        return response;
    }
    
    
    
    
}
