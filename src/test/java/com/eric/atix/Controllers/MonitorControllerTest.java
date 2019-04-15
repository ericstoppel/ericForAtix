package com.eric.atix.Controllers;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import org.junit.Test;

import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class MonitorControllerTest {
    //CORRER LA API PRIMERO

    @Test
    public void sendMessageNotAcceptable() {
        Client restClient = Client.create();

        WebResource webResource = restClient.resource("http://localhost:8080/messages?value=-1");

        ClientResponse resp = webResource.accept(MediaType.TEXT_PLAIN)
                .post(ClientResponse.class);

        assert resp.getStatus() == 400;
    }

    @Test
    public void sendMessagOK() {
        Client restClient = Client.create();

        WebResource webResource = restClient.resource("http://localhost:8080/messages?value=1");

        ClientResponse resp = webResource.accept(MediaType.TEXT_PLAIN)
                .post(ClientResponse.class);

        assert resp.getStatus() == 200;
    }

}