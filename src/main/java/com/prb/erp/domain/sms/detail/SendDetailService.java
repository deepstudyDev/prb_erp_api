package com.prb.erp.domain.sms.detail;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.prb.erp.domain.BaseService;

@Service
public class SendDetailService extends BaseService<SendDetail, SendDetail.SendDetailId> {
    private SendDetailRepository sendDetailRepository;
	
	
    @Inject
    public SendDetailService(SendDetailRepository sendDetailRepository) {
        super(sendDetailRepository);
        this.sendDetailRepository = sendDetailRepository;
    }
} 