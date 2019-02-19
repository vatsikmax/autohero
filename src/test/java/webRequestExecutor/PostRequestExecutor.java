package webRequestExecutor;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import java.io.IOException;
import java.util.Map;

public class PostRequestExecutor {
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    public HttpResponse executePostRequest(PostRequestData data)
            throws IOException {
        HttpPost request = new HttpPost(data.getUri());
        StringEntity params =new StringEntity(data.getBody());
        for (Map.Entry<String, String> header : data.getHeaders().entrySet()){
            request.addHeader(header.getKey(), header.getValue());
        }
        request.setEntity(params);
        HttpResponse response = httpClient.execute(request);
        return response;
    }
}
