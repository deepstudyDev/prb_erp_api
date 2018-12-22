package com.prb.erp.security;

import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.chequer.axboot.core.utils.DateTimeUtils;
import com.prb.erp.domain.user.SessionUser;
import com.prb.erp.domain.user.UserService;
import com.prb.erp.domain.user.UserVO;
import com.prb.erp.domain.user.auth.UserAuth;
import com.prb.erp.domain.user.auth.UserAuthService;
import com.prb.erp.domain.user.role.UserRole;
import com.prb.erp.domain.user.role.UserRoleService;

@Service
public class AXBootUserDetailsService implements UserDetailsService {

    @Inject
    private UserService userService;

    @Inject
    private UserRoleService userRoleService;
    
    @Inject
    private UserAuthService userAuthService;

 
    @Override
    public final SessionUser loadUserByUsername(String userCd) throws UsernameNotFoundException {
        UserVO user = userService.getLoginUser(userCd);

        if (user == null) {
            throw new UsernameNotFoundException("사용자 정보를 확인하세요.");
        }

        if (user.getUseYn().equals("N")) {
            throw new UsernameNotFoundException("존재하지 않는 사용자 입니다.");
        }

        if (null!=user.getUserStatus() && user.getUserStatus().equals("20")) {
            throw new UsernameNotFoundException("접근할 수 없는 사용자 입니다.");
        }

		//교사인데 최초로그인이면
		if ((user.getUserType().equals("12") || user.getUserType().equals("14")) && null == user.getLastLoginDate()){
            throw new UsernameNotFoundException("최초 로그인 하는 교사입니다. 패스워드 변경 후 진행 하세요.");
        }else{
        	userService.setLoginDate(userCd); 
        }

        List<UserRole> userRoleList = userRoleService.findByUserCd(userCd);
        List<UserAuth> userAuthList = userAuthService.findByUserCd(userCd);

        SessionUser sessionUser = new SessionUser();
        sessionUser.setUserCd(user.getUserCd());
        sessionUser.setUserType(user.getUserType());
        sessionUser.setDecisionYn(user.getDecisionYn());
        
        sessionUser.setUserNm(user.getUserNm());
        sessionUser.setUserPs(user.getUserPs());
        sessionUser.setMenuGrpCd(user.getMenuGrpCd());
        sessionUser.setHpNo(user.getHpNo());
        

        sessionUser.setAreaCd(user.getAreaCd());
        sessionUser.setAreaNm(user.getAreaNm());
        sessionUser.setOrgCd(user.getOrgCd());
        sessionUser.setOrgNm(user.getOrgNm());

        

        userRoleList.forEach(r -> sessionUser.addAuthority(r.getRoleCd()));
        userAuthList.forEach(a -> sessionUser.addAuthGroup(a.getGrpAuthCd()));
        
        
        String[] localeString = user.getLocale().split("_");

        Locale locale = new Locale(localeString[0], localeString[1]);

        final Calendar cal = Calendar.getInstance();
        final TimeZone timeZone = cal.getTimeZone();

        sessionUser.setTimeZone(timeZone.getID());
        sessionUser.setDateFormat(DateTimeUtils.dateFormatFromLocale(locale));
        sessionUser.setTimeFormat(DateTimeUtils.timeFormatFromLocale(locale));
        sessionUser.setLocale(locale);

        return sessionUser;
    }
}
