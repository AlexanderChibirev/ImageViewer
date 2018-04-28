package com.example.omega.imageviewer;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;


/**
 * Created by Alexander Chibirev on 4/28/2018.
 */
public class NetworkTests {//for example
    @Test
    public void correctReturnBodyListResources() throws IOException, InterruptedException {
        MockWebServer server = new MockWebServer();

        server.enqueue(new MockResponse().setBody("Test"));
        server.start();

        HttpUrl baseUrl = server.url("/api/hey");
        String request = sendGetRequest(new OkHttpClient(), baseUrl);
        Assert.assertEquals("Test", request);
    }

    private String sendGetRequest(OkHttpClient okHttpClient, HttpUrl base) throws IOException {
        RequestBody body = RequestBody.create(MediaType.parse("text/plain"), "hi there");

        okhttp3.Request request = new okhttp3.Request.Builder()
                .post(body)
                .url(base)
                .build();
        Response response = okHttpClient.newCall(request).execute();
        return response.body().string();
    }
}
