package com.prb.erp.domain.member.brother;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.prb.erp.domain.BaseService;
 
@Service
public class MemberBrotherService extends BaseService<MemberBrother, MemberBrother.MemberBrotherId> {
    private MemberBrotherRepository repository;
    
    @Inject
    public MemberBrotherService(MemberBrotherRepository repository) {
        super(repository);
        this.repository = repository;
    }
    
    //저장
    @Transactional
    public void saveDetail(MemberBrother detail) throws Exception {
	    save(detail); 
	}
}