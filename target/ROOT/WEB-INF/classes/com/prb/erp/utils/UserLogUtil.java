package com.prb.erp.utils;

import com.chequer.axboot.core.context.AppContextManager;
import com.prb.erp.domain.user.log.UserLogService;

public class UserLogUtil {
	public static void saveUserLog(String programCode,String programName, String programAction) {
		//로그 중지
		//getService().saveUserLog(programCode,programName,programAction);
    }
    
    public static UserLogService getService() {
        return AppContextManager.getBean(UserLogService.class);
    }  
}
