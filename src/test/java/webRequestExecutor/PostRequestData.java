package webRequestExecutor;

import java.util.HashMap;

public class PostRequestData {
    private String uri;

    private String body;

    private HashMap<String, String> headers;

    public String getUri() {
        return uri;
    }

    public String getBody() {
        return body;
    }

    public HashMap<String, String> getHeaders() {
        return headers;
    }


    public PostRequestData(String uri, String body, HashMap<String, String> headers){
        this.uri = uri;
        this.body = body;
        this.headers = headers;
    }
}
