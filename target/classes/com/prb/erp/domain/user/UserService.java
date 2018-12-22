package com.prb.erp.domain.user;

import java.time.Clock;
import java.time.Instant;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;
import javax.transaction.Transactional;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.SHAPasswordEncoder;
import com.prb.erp.domain.BaseService;
import com.prb.erp.domain.user.auth.UserAuth;
import com.prb.erp.domain.user.auth.UserAuthService;
import com.prb.erp.domain.user.role.UserRole;
import com.prb.erp.domain.user.role.UserRoleService;
import com.prb.erp.utils.SmsSendUtils;
import com.querydsl.core.BooleanBuilder;


@Service
public class UserService extends BaseService<User, String> {

    private UserRepository repository;

    @Inject private UserRoleService userRoleService;
    @Inject private SHAPasswordEncoder bCryptPasswordEncoder;
    @Inject private UserMapper userMapper;
    @Inject private UserAuthService userAuthService;
    
    @Inject
    public UserService(UserRepository repository) {
        super(repository);
        this.repository = repository;
    }
    
    //패스워드초기화
    @Transactional
    public void resetPs(User user) throws Exception {
    	if(null!=user){
    		resetPs(user.getUserCd(),user.getHpNo());
    	}
	}

    @Transactional
    public void resetPs(String userCd , String hpNo) throws Exception {
    	String generateNum = generateNumber(6) + "";
	    String password = bCryptPasswordEncoder.encode(generateNum);
        update(qUser).set(qUser.userPs, password).set(qUser.userPs2, generateNum).where(qUser.userCd.eq(userCd)).execute();

        SmsSendUtils.sendPwMsg(hpNo , userCd, generateNum);
	}
    
    //사용자저장
    @Transactional
    public void saveUser(User user) throws Exception {    	
    	if(null!=user){    	    
    		
            delete(qUserRole).where(qUserRole.userCd.eq(user.getUserCd())).execute();
            delete(qUserAuth).where(qUserAuth.userCd.eq(user.getUserCd())).execute();
            
    	    //신규저장
	        if (isEmpty(user.getUserPs())) {
	        	//String generateNum = generateNumber(6) + "";
	        	String generateNum = user.getHpNo().replace("-","");
	    	    String password = bCryptPasswordEncoder.encode(generateNum);
	        	user.setUserPs(password);
	        	user.setUserPs2(generateNum);
	        	
	            SmsSendUtils.sendPwMsg(user.getHpNo() , user.getUserCd(), generateNum);
	        }
	        

			//결정가능자(ex:팀장)
			if(user.getRankCd().equals("310")){
				user.setDecisionYn("Y");
			}else{
				user.setDecisionYn("N");				
			}		
			
    	    save(user);    
    	    
    	    List<UserAuth> authList = user.getAuthList();
            userAuthService.save(authList);
    	    
            UserRole userRole = new UserRole();
            userRole.setUserCd(user.getUserCd());
            userRole.setRoleCd("ASP_ACCESS");
            userRoleService.save(userRole);
    	}
	}
    
    //교사
    @Transactional
    public void saveTcher(User user, String mode, String smsFlag) throws Exception {    	
		//교사권한 셋팅
	    String grpAuthCd = "";	    
	    String menuGrpCd = "SYSTEM_ADMIN_GROUP";
	    //String userType = "20";
    	if(null!=user){    	    
    		user.setGrpAuthCd(grpAuthCd);
    		user.setMenuGrpCd(menuGrpCd);		
    		//user.setUserType(userType);
    	    //신규저장
	        if (mode.equals("NEW")) {
	        	
	        	//초기비밀번호 휴대폰번호 (교사)
	        	String generateNum = user.getHpNo().replace("-","");
	    	    String password = bCryptPasswordEncoder.encode(generateNum);
	        	user.setUserPs(password);
	        	user.setUserPs2(generateNum);
	        	
	        	if(smsFlag.equals("Y") && isNotEmpty(user.getHpNo())){
		            //SmsSendUtils.sendPwMsg(user.getHpNo() , user.getUserCd(), generateNum);
	        	}
	            
	            UserRole aspAccess = new UserRole();
	            aspAccess.setUserCd(user.getUserCd());
	            aspAccess.setRoleCd("ASP_ACCESS");
	            userRoleService.save(aspAccess);
	        }
	        
    	    save(user);    	
    	}
	}
    
    
    

    //계약자(부모)저장
    @Transactional
    public void saveMember(User user, String mode) throws Exception {    	
	    String grpAuthCd = "";
	    String menuGrpCd = "SYSTEM_ADMIN_GROUP";
	    String userType = "30";	//자녀	
    	if(null!=user){    	    	
    		user.setGrpAuthCd(grpAuthCd);
    		user.setMenuGrpCd(menuGrpCd);		
    		user.setUserType(userType);
			user.setUserStatus("");		
    	    //신규저장
	        if (mode.equals("NEW")) {
	        	String generateNum = generateNumber(6) + "";
	        	
	    	    String password = bCryptPasswordEncoder.encode(generateNum);
	        	user.setUserPs(password);
	        	user.setUserPs2(generateNum);
	        }
    	    save(user);    	
    	    
    	}
	}

    //자녀저장
    @Transactional
    public void saveChildren(User user, String mode) throws Exception {    	
	    String grpAuthCd = "";
	    String menuGrpCd = "SYSTEM_ADMIN_GROUP";
	    String userType = "40";	//자녀	
    	if(null!=user){    	    	
    		user.setGrpAuthCd(grpAuthCd);
    		user.setMenuGrpCd(menuGrpCd);		
    		user.setUserType(userType);
			user.setUserStatus("");
			
    	    //신규저장
	        if (mode.equals("NEW")) {
	        	String generateNum = generateNumber(6) + "";
	    	    String password = bCryptPasswordEncoder.encode(generateNum);
	        	user.setUserPs(password);
	        	user.setUserPs2(generateNum);
	        }
    	    save(user);    	
    	    
    	}
	}

    public User get(RequestParams requestParams) {

        String userCd = requestParams.getString("userCd" , "");
        BooleanBuilder builder = new BooleanBuilder();
        
        if (isNotEmpty(userCd)) {
            builder.and(qUser.userCd.eq(userCd));           	
        }
        
        User user = select().from(qUser).where(builder).fetchOne();

        List<UserAuth> userAuthList = userAuthService.findByUserCd(userCd);
        user.setAuthList(userAuthList);
        
        return user;
    }
    
    public UserVO getOne(RequestParams requestParams) {

        String userCd = requestParams.getString("userCd" , "");
        String userPs = requestParams.getString("userPs" , "");
        
	    String password = bCryptPasswordEncoder.encode(userPs);
	    requestParams.put("userPs" , password);	   
    	UserVO user = userMapper.getLoginUser(requestParams);
    	
    	if(null != user){
    		if(!user.getUserPs().equals(password)){
    			user = null;
    		}
    	}
    	return user;
    }
    
    public UserVO getLoginUser(String userCd) {
    	RequestParams<UserVO> requestParams = new RequestParams();
    	requestParams.put("userCd" , userCd);
    	UserVO user = userMapper.getLoginUser(requestParams);
    	return user;
    }
    
    //조회2 마이바티스
    public UserPagingVO getUserList(RequestParams<UserVO> vo, Pageable pageable) {
    	
    	int pageNumber = pageable.getPageNumber();    	
    	vo.put("pageNumber" ,pageNumber);
    	
    	UserPagingVO user = new UserPagingVO();
    	user.setResult(userMapper.getUserList(vo));   

    	
    	//현재페이지    	
    	user.setPageNo(pageNumber);    
    	  
    	int totalCnt = userMapper.getUserListCount(vo);    	
    	user.setTotalCnt(totalCnt);
    	return user;
    }
    
    public UserVO getUser(RequestParams<UserVO> vo) {
    	UserVO user = userMapper.getUser(vo);
    	List<UserAuth> userAuthList = userAuthService.findByUserCd(user.getUserCd());
        user.setAuthList(userAuthList);
    	return user;
    }
    
    //난수 생성 - 패스워드초기화
    public static int generateNumber(int length) {
        String numStr = "1";
        String plusNumStr = "1";
     
        for (int i = 0; i < length; i++) {
            numStr += "0";
     
            if (i != length - 1) {
                plusNumStr += "0";
            }
        }     
        Random random = new Random();
        int result = random.nextInt(Integer.parseInt(numStr)) + Integer.parseInt(plusNumStr);
     
        if (result > Integer.parseInt(numStr)) {
            result = result - Integer.parseInt(plusNumStr);
        }     
        return result;
    }
    //자녀저장
    @Transactional
    public void setLoginDate(String userCd) {    	
    	update(qUser).set(qUser.lastLoginDate, Instant.now(Clock.systemUTC())).where(qUser.userCd.eq(userCd)).execute();
    }
}
