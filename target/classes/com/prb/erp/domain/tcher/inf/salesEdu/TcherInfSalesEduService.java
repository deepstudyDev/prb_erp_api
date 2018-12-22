package com.prb.erp.domain.tcher.inf.salesEdu;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.froebel.HPB201TVVO;
import com.prb.erp.utils.FroebelIFUtils;
 
@Service
public class TcherInfSalesEduService extends BaseService<TcherInfSalesEdu, TcherInfSalesEdu.TcherInfSalesEduId> {
    private TcherInfSalesEduRepository repository;
    
    @Inject private TcherInfSalesEduMapper tcherInfSalesEduMapper;
    
    @Inject
    public TcherInfSalesEduService(TcherInfSalesEduRepository repository) {
        super(repository);
        this.repository = repository;
    }

    //전체
    public List<HPB201TVVO> getTcherSalesEduList(RequestParams<TcherInfSalesEduVO> vo) {

    	String tcherCd = vo.getString("tcherCd","");
    	return FroebelIFUtils.getHPB201TV_EDU(tcherCd);
    	//return tcherInfSalesEduMapper.getTcherSalesEduList(vo);
    }
    
    
    @Transactional
    public void deleteIfData() throws Exception {
        delete(qTcherInfSalesEdu).where(qTcherInfSalesEdu.ifYn.eq("Y")).execute();
    }
}


