package com.prb.erp.domain.member;

import com.chequer.axboot.core.vo.BaseVO;
import com.prb.erp.domain.member.child.MemberChild;
import com.prb.erp.domain.member.item.MemberItem;

import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class MemberManageRequestVO extends BaseVO {
	private MemberManage member;
    private MemberItem item;    
    private MemberChild child;
}