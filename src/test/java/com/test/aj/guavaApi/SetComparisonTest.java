package com.test.aj.guavaApi;

import com.company.test.java.model.CorpActionData;
import com.google.common.collect.Sets;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class SetComparisonTest {
    @Test
    public void compareHashSet() {
        CorpActionData ca1 = new CorpActionData();
        ca1.setRank(1);
        ca1.setSfId("Random_id");
        ca1.setGrpId("9U82");
        ca1.setCustodianCode("1234");

        CorpActionData ca2 = new CorpActionData();
        ca2.setRank(1);
        ca2.setGrpId("9U82");
        ca2.setCustodianCode("1234");

        Set<CorpActionData> caInDB = Sets.newHashSet();
        caInDB.add(ca1);

        Set<CorpActionData> caInSf = Sets.newHashSet();
        caInSf.add(ca2);

        System.out.println(String.format("Under Comparison, %s, %s", caInDB, caInSf));
        Set<CorpActionData> toBeInserted = Sets.difference(caInSf, caInDB);
        Assert.assertTrue(toBeInserted.size() == 0);

        Set<CorpActionData> toBeDeleted = Sets.difference(caInDB, caInSf);
        Assert.assertTrue(toBeDeleted.size() == 0);
    }
}
