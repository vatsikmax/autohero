import autoHeroJson.JsonNodesFields;
import com.google.common.collect.Ordering;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.json.JSONObject;
import webRequestExecutor.AutoHeroPostRequestDataPreparation;
import webRequestExecutor.PostRequestData;
import webRequestExecutor.PostRequestExecutor;
import java.io.*;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.apache.http.entity.mime.MIME.UTF8_CHARSET;

public class ApiTests {
    private static JSONArray carsInfo;

    @BeforeClass
    public static void getTestingData() throws IOException, URISyntaxException {


        PostRequestExecutor post = new PostRequestExecutor();
        PostRequestData data = new AutoHeroPostRequestDataPreparation().prepareAutoHeroPostRequestData();
        HttpResponse response = post.executePostRequest(data);
        String responseJSON = EntityUtils.toString(response.getEntity(), UTF8_CHARSET);
        JSONObject jsonObject = new JSONObject(responseJSON);
        carsInfo =jsonObject.getJSONObject(JsonNodesFields.RESPONSE.toString())
                .getJSONObject(JsonNodesFields.HITS.toString())
                .getJSONArray(JsonNodesFields.HITS.toString());
    }

    @Test
    public void verifyCarsSortedDescending() {
        List<Double> carPricesList = new ArrayList<>();
        for (int i = 0; i < carsInfo.length(); i++) {
            JSONObject carInfo = carsInfo.getJSONObject(i).getJSONObject(JsonNodesFields.SOURCE.toString());
            carPricesList.add(carInfo.getJSONObject(JsonNodesFields.OFFER_PRICE.toString())
                    .getDouble(JsonNodesFields.AMOUNT_MINOR_UNITS.toString()));
        }
        boolean areCarsSortedByPriceDescending = Ordering.natural().reverse().isOrdered(carPricesList);
        Assert.assertTrue("Cars are not sorted by price descending",areCarsSortedByPriceDescending);
    }

    @Test
    public void verifyCarsFilteredByYear(){
        List<Integer> registrationYearList = new ArrayList<>();
        for (int i = 0; i < carsInfo.length(); i++) {
            JSONObject carInfo = carsInfo.getJSONObject(i).getJSONObject(JsonNodesFields.SOURCE.toString());
            registrationYearList.add(carInfo.getInt(JsonNodesFields.REGISTRATION_YEAR.toString()));
        }
        int expectedYear = 2015;
        boolean areCarsFilteredByYear = registrationYearList.stream().allMatch(year -> year>=expectedYear);
        Assert.assertTrue("Cars are not filtered by year 2015", areCarsFilteredByYear);
    }
}
