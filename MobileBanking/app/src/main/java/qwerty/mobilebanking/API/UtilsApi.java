package qwerty.mobilebanking.API;

/**
 * Created by 10 on 1/10/2018.
 */

public class UtilsApi {
    public static final String base_url = "http://10.0.2.2:8000/api/";
    public static ApiModel getAPIService(){
        return ApiClient.getClient(base_url).create(ApiModel.class);
    }
}
