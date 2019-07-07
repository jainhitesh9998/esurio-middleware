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
    public static HttpResponse doPost(String URL, String authorization, String data, String type) throws IOException{
        ByteArrayContent fromString = ByteArrayContent.fromString("application/json", data);
        HttpRequest postRequest = RequestHelper.requestFactory.buildPostRequest(new GenericUrl(URL), fromString);
        HttpHeaders authHeader = new HttpHeaders();
        postRequest.setHeaders(new HttpHeaders().setAuthorization(authorization));
        return postRequest.execute();
    } 
    
    /**
     *
     * @param URL
     * @param authorization
     * @return
     * @throws java.io.IOException
     */
    public static HttpResponse doGet(String URL,String authorization) throws IOException{
        HttpRequest getRequest = RequestHelper.requestFactory.buildGetRequest(new GenericUrl(URL));
        getRequest.setHeaders(new HttpHeaders().setAuthorization(authorization));
        return getRequest.execute();
    }
    
    
    
    
}
