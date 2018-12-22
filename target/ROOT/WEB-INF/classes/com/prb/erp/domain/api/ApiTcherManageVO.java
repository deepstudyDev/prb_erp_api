package com.prb.erp.domain.api;

import com.chequer.axboot.core.vo.BaseVO;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiTcherManageVO extends BaseVO {
	private String tcherCd;		
	private String tcherNm;	
	private String orgCd;	
	private String orgNm;	

    private int memberCount;
    private int freeMemberCount;
    private int chargeMemberCount;

    private int newMemberCount;
    private int transPrevCount;
    private int transCount;

    private int goods1Count;
    private int goods2Count;
    private int goods3Count;
    private int goods4Count;
    private int goods5Count;
}