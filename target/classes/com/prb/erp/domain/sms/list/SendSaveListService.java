package com.prb.erp.domain.sms.list;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.utils.SessionUtils;

@Service
public class SendSaveListService extends BaseService<SendSaveList, SendSaveList.SendSaveListId> {
    private SendSaveListRepository repository;
    
    @Inject private SendSaveListMapper sendSaveListMapper;
    @Inject
    public SendSaveListService(SendSaveListRepository repository) {
        super(repository);
        this.repository = repository;
    }

    public SendSaveListPagingVO getSendSaveList(RequestParams<SendSaveList> vo, Pageable pageable) {

    	String userCd = SessionUtils.getCurrentLoginUserCd();	
    	vo.put("userCd" ,userCd);
    	
    	int pageNumber = pageable.getPageNumber();    	
    	vo.put("pageNumber" ,pageNumber);
    	
    	SendSaveListPagingVO master = new SendSaveListPagingVO();
    	master.setResult(sendSaveListMapper.getSendSaveList(vo));   
    	
    	
    	//현재페이지    	
    	master.setPageNo(pageNumber);    
    	  
    	int totalCnt = sendSaveListMapper.getSendSaveListCount(vo);    	
    	master.setTotalCnt(totalCnt);
    	return master;
    }


    @Transactional
    public void saveSendMsg(SendSaveList vo){
    	String userCd = SessionUtils.getCurrentLoginUserCd();
    	vo.setUserCd(userCd);
    	
    	if(null==vo.getSaveSeq()){
			Long saveSeq = select().select(qSendSaveList.saveSeq.max()).distinct()
					.from(qSendSaveList)
					.where(qSendSaveList.userCd.eq(userCd)).fetchOne();
			
			if(null==saveSeq){
				saveSeq = new Long(1);
			}else{
				saveSeq = saveSeq + new Long(1);
			}	
			vo.setSaveSeq(saveSeq);			
		}    	
    	save(vo);
    }
    @Transactional
    public void deleteMsg(SendSaveList vo){
    	String userCd = SessionUtils.getCurrentLoginUserCd();
    	delete(qSendSaveList).where(qSendSaveList.userCd.eq(userCd).and(qSendSaveList.saveSeq.eq(vo.getSaveSeq()))).execute();
    }
}