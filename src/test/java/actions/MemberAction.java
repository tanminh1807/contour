package actions;

import _base.BaseApiAction;
import _support.context.UserContext;
import _support.pdo.PMemberPDO;
import _support.pdo.QAddMemberPDO;
import core.apiclients.PortalApi;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

public class MemberAction extends BaseApiAction {

    public MemberAction(UserContext userContext) {
        super(userContext);
    }

    public PMemberPDO getMemberById(Integer userId) {
        Response getMemberResponse = getClient(PortalApi.class).GET("/members");
        Assert.assertEquals(getMemberResponse.statusCode(), 200);
        return getMemberResponse.getBody().jsonPath().getObject(String.format("find{it.id == %d}", userId), PMemberPDO.class);
    }

    public PMemberPDO searchMemberQueryingById(Integer userId) {
        Response searchMemberResponse = getClient(PortalApi.class).GET(String.format("/members?queryString=%d", userId));
        Assert.assertEquals(searchMemberResponse.statusCode(), 200);
        return searchMemberResponse.getBody().jsonPath().getObject(String.format("find{it.id == %d}", userId), PMemberPDO.class);
    }

    public void assertMemberInfo(Integer userId, QAddMemberPDO expectedMemberInfo) {
        SoftAssert softAssert = new SoftAssert();
        Response viewMemberResponse = getClient(PortalApi.class).GET(String.format("/members/%d", userId));
        Assert.assertEquals(viewMemberResponse.statusCode(), 200);
        PMemberPDO actualMemberInfo = viewMemberResponse.getBody().jsonPath().getObject("", PMemberPDO.class);
        softAssert.assertEquals(actualMemberInfo.firstName, expectedMemberInfo.firstName, String.format("\nExpected First Name: [%s] but found [%s]", expectedMemberInfo.firstName, actualMemberInfo.firstName));
        softAssert.assertEquals(actualMemberInfo.lastName, expectedMemberInfo.lastName, String.format("\nExpected Last Name: [%s] but found [%s]", expectedMemberInfo.lastName, actualMemberInfo.lastName));
        softAssert.assertEquals(actualMemberInfo.title, expectedMemberInfo.title, String.format("\nExpected Job Title: [%s] but found [%s]", expectedMemberInfo.title, actualMemberInfo.title));
        softAssert.assertEquals(actualMemberInfo.company, expectedMemberInfo.company, String.format("\nExpected Company: [%s] but found [%s]", expectedMemberInfo.company, actualMemberInfo.company));
        softAssert.assertEquals(actualMemberInfo.phoneNumber, expectedMemberInfo.phoneNumber, String.format("\nExpected Phone Number: [%s] but found [%s]", expectedMemberInfo.phoneNumber, actualMemberInfo.phoneNumber));
        softAssert.assertEquals(actualMemberInfo.website, expectedMemberInfo.website, String.format("\nExpected Website: [%s] but found [%s]", expectedMemberInfo.website, actualMemberInfo.website));
        softAssert.assertEquals(actualMemberInfo.email, expectedMemberInfo.email, String.format("\nExpected Email: [%s] but found [%s]", expectedMemberInfo.email, actualMemberInfo.email));
        softAssert.assertAll();
    }

    public Map<Integer, QAddMemberPDO> addNewMember(QAddMemberPDO newMember) {
        Response addMemberResponse = getClient(PortalApi.class).POST("/members", newMember.toJsonString());
        Assert.assertEquals(addMemberResponse.statusCode(), 200);
        Integer userId = addMemberResponse.getBody().htmlPath().getInt("");
        Map<Integer, QAddMemberPDO> memberMap = new HashMap<>();
        memberMap.put(userId, newMember);
        return memberMap;
    }
}