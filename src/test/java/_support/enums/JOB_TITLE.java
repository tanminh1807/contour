package _support.enums;

import helper.RandomHelper;

public enum JOB_TITLE {
    QA("QA Engineer"),
    BE("BE Developer"),
    FE("FE Developer"),
    BA("Business Analyst"),
    PO("Product Owner");

    public String name;

    JOB_TITLE(String name) {
        this.name = name;
    }

    public static JOB_TITLE getRandomTitle() {
        return JOB_TITLE.values()[RandomHelper.randomNumberInRange(0, JOB_TITLE.values().length - 1)];
    }
}