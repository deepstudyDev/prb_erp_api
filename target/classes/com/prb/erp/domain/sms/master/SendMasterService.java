package com.prb.erp.domain.sms.master;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.key.management.KeyManagementService;
import com.prb.erp.domain.sms.detail.SendDetail;
import com.prb.erp.domain.sms.detail.SendDetailService;
import com.prb.erp.domain.user.UserService;
import com.prb.erp.utils.SessionUtils;
import com.prb.erp.utils.SmsSendUtils;

@Service
public class SendMasterService extends BaseService<SendMaster, SendMaster.SendMasterId> {
    private SendMasterRepository sendMasterRepository;

	@Inject
	private SendDetailService detailService;

    @Inject private KeyManagementService keyManagementService;  
    @Inject private SendMasterMapper sendMasterMapper;
    
    @Inject private UserService userService; 
    @Inject
    public SendMasterService(SendMasterRepository sendMasterRepository) {
        super(sendMasterRepository);
        this.sendMasterRepository = sendMasterRepository;
    }

    //조회 마이바티스
    public SendMasterPagingVO getSendList(RequestParams<SendMasterVO> vo, Pageable pageable) {
    	int pageNumber = pageable.getPageNumber();    	
    	vo.put("pageNumber" ,pageNumber);
    	
    	SendMasterPagingVO master = new SendMasterPagingVO();
    	master.setResult(sendMasterMapper.getSendList(vo));   
    	
    	
    	//현재페이지    	
    	master.setPageNo(pageNumber);    
    	  
    	int totalCnt = sendMasterMapper.getSendListCount(vo);    	
    	master.setTotalCnt(totalCnt);
    	return master;
    }
    


    //조회 - sms 전송 대상 유저조회
    public SendSmsUserPagingVO getSmsUserList(RequestParams<SendSmsUserListVO> vo, Pageable pageable) {
    	int pageNumber = pageable.getPageNumber();    	
    	vo.put("pageNumber" ,pageNumber);
    	
    	SendSmsUserPagingVO master = new SendSmsUserPagingVO();
    	master.setResult(sendMasterMapper.getSmsUserList(vo));   
    	
    	
    	//현재페이지    	
    	master.setPageNo(pageNumber);    
    	  
    	int totalCnt = sendMasterMapper.getSmsUserListCount(vo);    	
    	master.setTotalCnt(totalCnt);
    	return master;
    }
    
    
    @Transactional
    public void saveSend(SendRequestVO requestVO){    	
    	SendMaster master = requestVO.getMaster();
    	List<SendDetail> details = requestVO.getDetail();
    	
    	if (null!=master) {  
    		String reserveTime = "";
    		//즉시발송 - 현재시간
    		if(master.getSendType().equals("10")){
	            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            Calendar c1 = Calendar.getInstance();
	            String strToday = sdf.format(c1.getTime());
	            master.setSendDt(strToday);
    		}else{
    			
    			
    			try{
        			String oldstring = master.getSendDt();
        			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(oldstring);
        			
        			Calendar cal = Calendar.getInstance();
        			cal.setTime(date);
        			cal.add(Calendar.HOUR_OF_DAY, -9);

        			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        			reserveTime = format.format(cal.getTime());
        			
    			}catch(Exception e){
    				
    			}
    			//reserveTime=
    		}
	            
    		String sendCode = "";    		
    		
    		if(isEmpty(master.getSendCode())){
				sendCode = keyManagementService.getItemCode("SMS","SMS",5);
    			master.setSendCode(sendCode);				
			}else{
				sendCode = master.getSendCode();
			}

	    	String userCd = SessionUtils.getCurrentLoginUserCd();		 
	    	master.setSenderUserCd(userCd);
	    		    	
        	if (isNotEmpty(details)) {
        		Long seq = new Long(1);
        		for (SendDetail detail : details) {
        			
        			detail.setSendCode(sendCode);
        			detail.setSendSeq(seq);        			
        			seq = seq + 1;        			

        			SendResultVO vo = SmsSendUtils.sendMsg(master.getSenderNo(), detail.getHpNo(), master.getSendMessage(),reserveTime);
        			 
        			detail.setSuccessYn("Y");
                	//성공
        			/*
                	if(vo.getResult().equals("OK")){
                		detail.setSuccessYn("Y");
                	}else{ 
                		detail.setSuccessYn("N");
                		successYn = "N";
                	}*/
                	//detail.setMessage(vo.getMsgContents());
                	detailService.save(detail);
        		}
        	}     
        	save(master);
    	}
    }
}