package com.example.urlShortner.Common;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.net.MalformedURLException;
import java.net.URL;

@AllArgsConstructor
public class UrlUtil {


    public static String getBaseURL(String url) throws MalformedURLException {
        URL reqURL= new URL(url);
        String protocol = reqURL.getProtocol();
        String host = reqURL.getHost();
        int port = reqURL.getPort();

        if(port==-1)
            return String.format("%s://%s/",protocol,host);
        else
            return String.format("%s://%s:%d/",protocol,host,port);
    }
}
