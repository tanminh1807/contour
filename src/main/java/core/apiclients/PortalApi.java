package core.apiclients;

import _base.BaseRestful;
import helper.PropertyHelper;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.List;

public class PortalApi extends BaseRestful {


    public PortalApi() {
        super(PropertyHelper.getEnvValue("API_HOST"));
    }

    public Response POST(String endPoint, Object payLoad) {
        return post(getHeaders(), endPoint, payLoad);
    }

    public Response GET(String endPoint) {
        return get(getHeaders(), endPoint);
    }

    private Headers getHeaders() {
        List<Header> headers = new ArrayList<>();
        headers.add(new Header("content-type", "application/json"));
        return new Headers(headers);
    }
}