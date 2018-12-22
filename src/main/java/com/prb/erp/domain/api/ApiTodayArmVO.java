package com.prb.erp.domain.api;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiTodayArmVO {    
    private int todayVisitEndCount;
    private int todayTransApprovalCount;
    private int todayTransRequestCount;

    private int goods1EndCount;
    private int goods2EndCount;
    private int goods3EndCount;
    private int goods4EndCount;
    private int goods5EndCount;
}