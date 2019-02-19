package webRequestExecutor;

import common.CommonStrings;
import org.apache.http.client.utils.URIBuilder;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class AutoHeroPostRequestDataPreparation {
    private final String sort = "sort";
    private final String yearMin = "yearMin";
    private final String priceDesc = "PRICE_DESC";
    private final String referer = "Referer";
    private final String contentType = "Content-Type";
    private final String jsonTypeValue = "application/json";
    private final String bodyFileName = "/src/test/java/data/PostRequestBody";

    public PostRequestDataModel prepareAutoHeroPostRequestData() throws IOException, URISyntaxException {

        URI uri = new URIBuilder()
                .setScheme(CommonStrings.scheme)
                .setHost(CommonStrings.host)
                .setPath(CommonStrings.pathV2)
                .build();

        URI referrerUri = new URIBuilder()
                .setScheme(CommonStrings.scheme)
                .setHost(CommonStrings.host)
                .setPath(CommonStrings.pathSearch)
                .setParameter(sort, priceDesc)
                .setParameter(yearMin, CommonStrings.year)
                .build();

        String body = new String(Files.readAllBytes(Paths.get(System.getProperty(CommonStrings.userDir)
                + bodyFileName)));
        HashMap<String, String> headers = new HashMap<>();
        headers.put(contentType, jsonTypeValue);
        headers.put(referer, referrerUri.toString());
        PostRequestDataModel postRequestAutoHeroData = new PostRequestDataModel(uri.toString(), body, headers);
        return postRequestAutoHeroData;
    }
}
