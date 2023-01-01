package _support.pdo;

import _base.BasePDO;

import java.util.HashMap;
import java.util.Map;

public class MemberMap extends BasePDO {

    public Map<Integer, QAddMemberPDO> memberMap;

    public MemberMap() {
        memberMap = new HashMap<>();
    }
}