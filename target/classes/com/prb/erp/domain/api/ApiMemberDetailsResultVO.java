package com.prb.erp.domain.api;

import java.util.List;

import com.chequer.axboot.core.utils.ModelMapperUtils;
import lombok.Data;
import lombok.NoArgsConstructor;
 
@Data
@NoArgsConstructor
public class ApiMemberDetailsResultVO {

	private ApiMemberHeaderVO header;
	private List<ApiMemberChildVO> child;
	private List<ApiBrotherVO> brothers;
	private String resultCode;
	private String resultMsg;


	public static ApiMemberDetailsResultVO of(Object object) {
		return ModelMapperUtils.map(object, ApiMemberDetailsResultVO.class);
	}
}