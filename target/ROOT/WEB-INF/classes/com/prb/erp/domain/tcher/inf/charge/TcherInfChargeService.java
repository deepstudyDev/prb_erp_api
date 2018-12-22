package com.prb.erp.domain.tcher.inf.charge;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
 
@Service
public class TcherInfChargeService extends BaseService<TcherInfCharge, TcherInfCharge.TcherInfChargeId> {
    private TcherInfChargeRepository repository;
    
    @Inject private TcherInfChargeMapper tcherInfChargeMapper;
    
    @Inject
    public TcherInfChargeService(TcherInfChargeRepository repository) {
        super(repository);
        this.repository = repository;
    }

    //전체
    public List<TcherInfChargeVO> getTcherInfChargeList(RequestParams<TcherInfChargeVO> vo) {
    	return tcherInfChargeMapper.getTcherInfChargeList(vo);
    }
    
    @Transactional
    public void deleteIfData() throws Exception {
        delete(qTcherInfCharge).where(qTcherInfCharge.ifYn.eq("Y")).execute();
    }
}


