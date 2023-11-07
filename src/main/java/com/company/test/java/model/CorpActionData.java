package com.company.test.java.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode(of = {"grpId", "rank", "custodianCode"}, callSuper = false)
@ToString(of = {"grpId", "rank", "custodianCode"})
public class CorpActionData {
    private String grpId;
    private Integer rank;
    private String custodianCode;
    private String sfId;
    private String accountNumber;
    private String isMultiPrime;
}
