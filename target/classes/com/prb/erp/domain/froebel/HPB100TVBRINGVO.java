package com.prb.erp.domain.froebel;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class HPB100TVBRINGVO {
	//영입정보						
    private String JOIN_DATE;
    private String BRING_PERSON_NUMB;	
    private String PERSON_NAME;    
    private String HR_TREE_CODE;
    private String CLASS_CODE;
    private String WORK_STATE;
    private String BRING_PERSON_NUMB_NAME;
    private String CLASS_NAME;
    private String WORK_NAME;
}