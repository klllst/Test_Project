package com.test_project.TestApp.DTO;

import java.math.BigDecimal;

public class ReportDTO {
    public final Long id;
    public final String productName;
    public final String MCname;
    public final BigDecimal startCost;
    public final BigDecimal endCost;

    public ReportDTO(Long id,String productName, String MCname, BigDecimal startCost, BigDecimal endCost){
        this.id=id;
        this.productName=productName;
        this.MCname=MCname;
        this.startCost=startCost;
        this.endCost=endCost;
    }
}