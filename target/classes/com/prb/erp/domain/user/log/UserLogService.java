package com.prb.erp.domain.user.log;

import java.util.List;

import javax.inject.Inject;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.chequer.axboot.core.config.AXBootContextConfig;
import com.chequer.axboot.core.parameter.RequestParams;
import com.chequer.axboot.core.utils.MDCUtil;
import com.chequer.axboot.core.utils.PhaseUtils;
import com.prb.erp.domain.BaseService;
import com.prb.erp.utils.SessionUtils;
import com.querydsl.core.BooleanBuilder;


@Service
public class UserLogService extends BaseService<UserLog, Long> {

    private UserLogRepository repository;

    @Inject
    private AXBootContextConfig axBootContextConfig;

    @Inject
    public UserLogService(UserLogRepository repository) {
        super(repository);
        this.repository = repository;
    }
    
    @Transactional
    public void deleteAllLogs() {
        Query query = em.createNativeQuery("DELETE FROM TB_ERP_USERLOG");
        query.executeUpdate();
    }

    public void deleteLog(Long id) {
        delete(id);
    }


    @Transactional
    public void saveUserLog(String programCode,String programName, String programAction) {
        String userCd = SessionUtils.getCurrentLoginUserCd();
        
        UserLog userLog = new UserLog();
        userLog.setPhase(PhaseUtils.phase());
        userLog.setSystem("8PM");
        userLog.setUserCd(userCd);
        
        userLog.setProgramCode(programCode);
        userLog.setProgramName(programName);
        userLog.setProgramAction(programAction);
        
        userLog.setLoggerName("");
        userLog.setServerName(axBootContextConfig.getServerName());
        userLog.setHostName(getHostName());
        userLog.setPath(MDCUtil.get(MDCUtil.REQUEST_URI_MDC));
        userLog.setHeaderMap(MDCUtil.get(MDCUtil.HEADER_MAP_MDC));
        userLog.setParameterMap(MDCUtil.get(MDCUtil.PARAMETER_BODY_MDC));
        userLog.setUserInfo(MDCUtil.get(MDCUtil.USER_INFO_MDC));
        save(userLog);
    }

    public String getHostName() {

        HttpServletRequest req = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest();
        String ip = req.getHeader("X-FORWARDED-FOR");
        if (ip == null)
            ip = req.getRemoteAddr();
        
        try {
            return ip;
        } catch (Exception e) {
        }
        return null;
    }
    
    public List<UserLog> get(RequestParams<UserLog> requestParams) {        

        String userCd = requestParams.getString("userCd" , "");
        BooleanBuilder builder = new BooleanBuilder();        

        if (isNotEmpty(userCd)) {
            builder.and(qUserLog.userCd.eq(userCd));           	
        }
        
        List<UserLog> list = select().from(qUserLog).where(builder).orderBy(qUserLog.errorDatetime.asc()).fetch();
        return list;
    }
}

