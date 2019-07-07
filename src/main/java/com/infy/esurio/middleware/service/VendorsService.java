/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.infy.esurio.middleware.service;

import com.google.api.client.http.HttpResponse;
import com.infy.esurio.middleware.DTO.VendorsDTO;
import com.infy.esurio.middleware.authentication.Authentication;
import com.infy.esurio.middleware.requestshelper.RequestHelper;
import static com.infy.esurio.middleware.requestshelper.RequestHelper.doGet;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;

/**
 *
 * @author developer
 */
public class VendorsService {

    public final String vendorURL = "http://192.168.1.206:8080/api/vendors";

    public List<VendorsDTO> getAllVendors(String authorization) throws IOException {
        HttpResponse response = doGet(vendorURL, authorization);
        if (response.getStatusCode() == 200) {
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = response.parseAsString();
            VendorsDTO[] vendorsDTOs = mapper.readValue(jsonString, VendorsDTO[].class);
            for (VendorsDTO vendorsDTO : vendorsDTOs) {
                System.err.println(vendorsDTO.toString());
            }

            System.out.println(vendorsDTOs[0].toString());
            
            return  Arrays.asList(vendorsDTOs);
        }
        return new ArrayList<>();
    }
    public static void main(String[] args) throws IOException {
        Authentication connection = new Authentication();
        connection.authenticate();

        VendorsService vendorsService = new VendorsService();
        try {
            System.out.println(" Token " + connection.getToken());
            vendorsService.getAllVendors("Bearer " + connection.getToken());
        } catch (IOException ex) {
            Logger.getLogger(VendorsService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
