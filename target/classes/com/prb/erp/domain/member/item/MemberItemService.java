package com.prb.erp.domain.member.item;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.prb.erp.domain.BaseService;
 
@Service
public class MemberItemService extends BaseService<MemberItem, MemberItem.MemberItemId> {
    private MemberItemRepository repository;
    
    @Inject
    public MemberItemService(MemberItemRepository repository) {
        super(repository);
        this.repository = repository;
    }
    
    //저장
    @Transactional
    public void saveDetail(MemberItem detail) throws Exception {
	    save(detail); 
	}
}