package _support.pdo;

import _base.BasePDO;
import _support.enums.JOB_TITLE;
import helper.RandomHelper;

public class QAddMemberPDO extends BasePDO {

    public String firstName;
    public String lastName;
    public String title;
    public String company;
    public String phoneNumber;
    public String website;
    public String email;

    public QAddMemberPDO randomMember() {
        firstName = RandomHelper.randomAlphaNumericString(5);
        lastName = RandomHelper.randomAlphaNumericString(5);
        title = JOB_TITLE.getRandomTitle().name;
        company = RandomHelper.randomAlphaNumericString(10);
        String vnCountryCode = "+84";
        phoneNumber = vnCountryCode + RandomHelper.randomNumber(9);
        website = RandomHelper.randomWebsite();
        email = RandomHelper.randomEmailWithFullName(firstName, lastName);
        return this;
    }
}