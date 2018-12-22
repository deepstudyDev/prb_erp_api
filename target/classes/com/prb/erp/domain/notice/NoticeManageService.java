package com.prb.erp.domain.notice;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.key.management.KeyManagementService;
 
@Service
public class NoticeManageService extends BaseService<NoticeManage, NoticeManage.NoticeManageId> {
    private NoticeManageRepository repository;
    

    @Inject private NoticeManageMapper noticeManageMapper;
    @Inject private KeyManagementService keyManagementService;
    
    @Inject
    public NoticeManageService(NoticeManageRepository repository) {
        super(repository);
        this.repository = repository;
    }

    //조회 - 전체 
    public List<NoticeManageVO> getNoticeListAll (RequestParams<NoticeManageVO> vo) {
    	return noticeManageMapper.getNoticeListAll(vo);
    }
    
    
    //조회 - 페이징
    public NoticeManagePagingVO getNoticeList(RequestParams<NoticeManageVO> vo , Pageable pageable) {
    	int pageNumber = pageable.getPageNumber();    	
    	vo.put("pageNumber" ,pageNumber);
    	vo.put("orgType", vo.getString("orgType"));

    	NoticeManagePagingVO area = new NoticeManagePagingVO();
    	area.setResult(noticeManageMapper.getNoticeList(vo));
    	
    	//현재페이지
    	area.setPageNo(pageNumber);

    	int totalCnt = noticeManageMapper.getNoticeListCount(vo);

    	area.setTotalCnt(totalCnt);
    	return area;
    }

    //조회 - 단건
    public NoticeManageVO getNotice(RequestParams<NoticeManageVO> vo) {
    	return noticeManageMapper.getNotice(vo);
    }

    @Transactional
    public NoticeManage saveNotice(NoticeManage notice) throws Exception {

		if(null==notice.getNoticeCd()){
			//상품코드생성
			notice.setNoticeCd(keyManagementService.getItemCode("NOTICE","NT",5));
			/*
			//신규일경우, temp file 존재시 키값 변경
	        update(qCommonFile).set(qCommonFile.targetId , notice.getNoticeCd())
	        	.where(qCommonFile.targetId.eq(notice.getTempFileCd())
	        			.and(qCommonFile.targetType.eq("NOTICE"))
	        			.and(qCommonFile.tempYn.eq("Y"))).execute();		 */
		}
		save(notice);		
		return notice;
    }
    
    
}


