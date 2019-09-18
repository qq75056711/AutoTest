package com.apitest01.api;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;

import java.net.URI;

/**
 * @author xiaomi
 */
public class HttpDelete extends HttpEntityEnclosingRequestBase {

    public static final String METHOD_NAME = "DELETE";

    @Override
    public String getMethod() {
        return METHOD_NAME;
    }

    public HttpDelete (final String uri){
        super();
        super.setURI(URI.create(uri));
    }

    public HttpDelete (final URI uri){
        super();
        super.setURI(uri);
    }

    public HttpDelete (){
        super();
    }
}
