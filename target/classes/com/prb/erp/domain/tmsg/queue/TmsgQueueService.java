package com.prb.erp.domain.tmsg.queue;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.prb.erp.domain.BaseService;
 
@Service
public class TmsgQueueService extends BaseService<TmsgQueue, TmsgQueue.TmsgQueueId> {
    private TmsgQueueRepository repository;
    
    @Inject
    public TmsgQueueService(TmsgQueueRepository repository) {
        super(repository);
        this.repository = repository;
    }
    
    
    @Transactional
    public void insertQueue(String tmsgSeq) throws Exception {
    	TmsgQueue q = new TmsgQueue();
    	q.setTmsgSeq(tmsgSeq);
    	q.setSendOrgCd("DC001");
    	q.setRecvOrgCd("DK001");
    	q.setTmsgKncd("KC101");
    	q.setTmsgSection("1");
    	//q.setTmsgType("1");
    	//q.setRegYmd("1");
    	
    	save(q);
	}
}


