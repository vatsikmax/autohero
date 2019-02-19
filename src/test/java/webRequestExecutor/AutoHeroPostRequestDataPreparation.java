package webRequestExecutor;

import org.apache.http.client.utils.URIBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class AutoHeroPostRequestDataPreparation {
    private final String scheme = "https";
    private final String host = "www.autohero.com";
    private final String pathV2 = "de/api/v1/search-template/classified/findAds/v2";
    private final String pathSearch = "de/search/";
    private final String sort = "sort";
    private final String yearMin = "yearMin";
    private final String priceDesc = "PRICE_DESC";
    private final String year = "2015";
    private final String referer = "Referer";
    private final String contentType = "Content-Type";
    private final String jsonTypeValue = "application/json";
    private final String userDir = "user.dir";
    private final String bodyFileName = "/src/test/java/data/PostRequestBody";

    public PostRequestData prepareAutoHeroPostRequestData() throws IOException, URISyntaxException {

        URI uri = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(pathV2)
                .build();

        URI referrerUri = new URIBuilder()
                .setScheme(scheme)
                .setHost(host)
                .setPath(pathSearch)
                .setParameter(sort, priceDesc)
                .setParameter(yearMin, year)
                .build();

        String body = new String(Files.readAllBytes(Paths.get(System.getProperty(userDir)
                + bodyFileName)));
        HashMap<String, String> headers = new HashMap<>();
        headers.put(contentType, jsonTypeValue);
        headers.put(referer, referrerUri.toString());
        PostRequestData postRequestAutoHeroData = new PostRequestData(uri.toString(), body, headers);
        return postRequestAutoHeroData;
    }
}
