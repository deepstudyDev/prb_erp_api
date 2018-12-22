package com.prb.erp.domain.tcher.inf.salesHappy;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.froebel.HPB201TVVO;
import com.prb.erp.utils.FroebelIFUtils;
 
@Service
public class TcherInfSalesHappyService extends BaseService<TcherInfSalesHappy, TcherInfSalesHappy.TcherInfSalesHappyId> {
    private TcherInfSalesHappyRepository repository;
    

    @Inject private TcherInfSalesHappyMapper tcherInfSalesHappyMapper;
    
    @Inject
    public TcherInfSalesHappyService(TcherInfSalesHappyRepository repository) {
        super(repository);
        this.repository = repository;
    }

    //전체
    public List<HPB201TVVO> getTcherSalesHappyList(RequestParams<TcherInfSalesHappyVO> vo) {
    	String tcherCd = vo.getString("tcherCd","");
    	return FroebelIFUtils.getHPB201TV_HAPPY(tcherCd);
    	
    	//return tcherInfSalesHappyMapper.getTcherSalesHappyList(vo);
    }
    
    
    @Transactional
    public void deleteIfData() throws Exception {
        delete(qTcherInfSalesHappy).where(qTcherInfSalesHappy.ifYn.eq("Y")).execute();
    }
}


