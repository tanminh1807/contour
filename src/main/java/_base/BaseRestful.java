package _base;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static io.restassured.http.Method.GET;
import static io.restassured.http.Method.POST;

public class BaseRestful {

    private final String host;

    public BaseRestful(String host) {
        this.host = host;
    }

    protected Response post(Headers headers, String endPoint, Object payLoad) {
        List<Header> headerList = headers.asList();
        Map<String, String> specHeaders = new HashMap<>();
        for (Header h : headerList) {
            specHeaders.putIfAbsent(h.getName(), h.getValue());
        }
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        specBuilder.setBaseUri(host).setBasePath(endPoint).addHeaders(specHeaders).setBody(payLoad);
        if (endPoint.contains("?")) {
            String[] paramString = endPoint.split("\\?")[1].split("&");
            Map<String, Object> paramMap = Arrays.stream(paramString).map(p -> p.split("=")).collect(Collectors.toMap(p -> p[0].trim(), p -> p[1].trim()));
            specBuilder.addQueryParams(paramMap);
        }
        return given().spec(specBuilder.build()).request(POST);
    }

    protected Response get(Headers headers, String endPoint) {
        List<Header> headerList = headers.asList();
        Map<String, String> specHeaders = new HashMap<>();
        for (Header h : headerList) {
            specHeaders.putIfAbsent(h.getName(), h.getValue());
        }
        RequestSpecBuilder specBuilder = new RequestSpecBuilder();
        specBuilder.setBaseUri(host).setBasePath(endPoint).addHeaders(specHeaders);
        if (endPoint.contains("?")) {
            String[] paramString = endPoint.split("\\?")[1].split("&");
            Map<String, Object> paramMap = Arrays.stream(paramString).map(p -> p.split("=")).collect(Collectors.toMap(p -> p[0].trim(), p -> p[1].trim()));
            specBuilder.addQueryParams(paramMap);
        }
        return given().spec(specBuilder.build()).request(GET);
    }
}