package com.prb.erp.domain.tmsg;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.prb.erp.domain.BaseService;
 
@Service
public class TmsgKncdService extends BaseService<TmsgKncd, TmsgKncd.TmsgKncdId> {
    private TmsgKncdRepository repository;
    
    
    @Inject
    public TmsgKncdService(TmsgKncdRepository repository) {
        super(repository);
        this.repository = repository;
    }
}


