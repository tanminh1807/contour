package steps;

import _base.BaseStep;
import _support.pdo.MemberMap;
import _support.pdo.PMemberPDO;
import _support.pdo.QAddMemberPDO;
import actions.MemberAction;
import io.cucumber.java8.En;
import org.testng.asserts.SoftAssert;

import java.util.Map;

public class ApiProfileManagementSteps extends BaseStep implements En {
    public ApiProfileManagementSteps() {
        When("adding {int} new random members to Profile Manager", (Integer membersNumber) -> {
            MemberMap mapOfMembers = new MemberMap();
            for (int i = 0; i < membersNumber; i++) {
                QAddMemberPDO newMember = new QAddMemberPDO().randomMember();
                mapOfMembers.memberMap.putAll(use(MemberAction.class).addNewMember(newMember));
            }
            save(mapOfMembers);
        });

        Then("new members should be displayed in Home Page", () -> {
            SoftAssert softAssert = new SoftAssert();
            MemberMap mapOfMembers = load(MemberMap.class);
            for (Integer userId : mapOfMembers.memberMap.keySet()) {
                PMemberPDO actualMemberProfile = use(MemberAction.class).getMemberById(userId);
                softAssert.assertNotNull(actualMemberProfile, String.format("\nCan't find new added member in Home Page | Id: %d\n", userId));
            }
            softAssert.assertAll();
        });

        And("new members should be queryable in Search Member Page", () -> {
            SoftAssert softAssert = new SoftAssert();
            MemberMap mapOfMembers = load(MemberMap.class);
            for (Integer userId : mapOfMembers.memberMap.keySet()) {
                PMemberPDO actualMemberProfile = use(MemberAction.class).searchMemberQueryingById(userId);
                softAssert.assertNotNull(actualMemberProfile, String.format("\nCan't find new added member in Search Member Page | Id: %d\n", userId));
            }
            softAssert.assertAll();
        });

        And("verify information of new members is correct in View Member Page", () -> {
            MemberMap mapOfMembers = load(MemberMap.class);
            for (Map.Entry<Integer, QAddMemberPDO> pair : mapOfMembers.memberMap.entrySet())
                use(MemberAction.class).assertMemberInfo(pair.getKey(), pair.getValue());
        });
    }
}