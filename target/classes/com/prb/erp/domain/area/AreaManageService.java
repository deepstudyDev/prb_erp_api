package com.prb.erp.domain.area;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.key.management.KeyManagementService;
 
@Service
public class AreaManageService extends BaseService<AreaManage, AreaManage.AreaManageId> {
    private AreaManageRepository repository;
    

    @Inject private AreaManageMapper areaManageMapper;
    @Inject private KeyManagementService keyManagementService;
    
    @Inject
    public AreaManageService(AreaManageRepository repository) {
        super(repository);
        this.repository = repository;
    }

    //조회 - 전체 
    public List<AreaManageVO> getAreaListAll (RequestParams<AreaManageVO> vo) {
    	return areaManageMapper.getAreaListAll(vo);
    }
    
    
    //조회 - 페이징
    public AreaManagePagingVO getAreaList(RequestParams<AreaManageVO> vo , Pageable pageable) {
    	int pageNumber = pageable.getPageNumber();    	
    	vo.put("pageNumber" ,pageNumber);
    	
    	AreaManagePagingVO area = new AreaManagePagingVO();
    	area.setResult(areaManageMapper.getAreaList(vo));    
    	
    	
    	//현재페이지    	
    	area.setPageNo(pageNumber);    
    	  
    	int totalCnt = areaManageMapper.getAreaListCount(vo);    	
    	area.setTotalCnt(totalCnt);
    	return area;
    }

    //조회 - 단건
    public AreaManageVO getArea(RequestParams<AreaManageVO> vo) {
    	return areaManageMapper.getArea(vo);
    }

    @Transactional
    public AreaManage saveArea(AreaManage area) throws Exception {

		if(null==area.getAreaCd()){
			//상품코드생성
			area.setAreaCd(keyManagementService.getItemCode("AREA","AREA",5));
		}
		save(area);
		
		return area;
    }
}


