package com.prb.erp.domain.tcher.inf.tcher;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
 
@Service
public class TcherInfTcherService extends BaseService<TcherInfTcher, TcherInfTcher.TcherInfTcherId> {
    private TcherInfTcherRepository repository;
    
    @Inject private TcherInfTcherMapper tcherInfTcherMapper;
    
    @Inject
    public TcherInfTcherService(TcherInfTcherRepository repository) {
        super(repository);
        this.repository = repository;
    }

    //전체
    public List<TcherInfTcherVO> getTcherInfoList(RequestParams<TcherInfTcherVO> vo) {
    	return tcherInfTcherMapper.getTcherInfoList(vo);
    }
    
    @Transactional
    public void deleteIfData() throws Exception {
        delete(qTcherInfTcher).where(qTcherInfTcher.ifYn.eq("Y")).execute();
    }
}


