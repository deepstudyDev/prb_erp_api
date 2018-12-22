package com.prb.erp.controllers;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.user.User;
import com.prb.erp.domain.user.UserPagingVO;
import com.prb.erp.domain.user.UserService;
import com.prb.erp.domain.user.UserVO;
import com.prb.erp.utils.UserLogUtil;

/*
 * 사용자관리
 */
@Controller
@RequestMapping(value = "/api/v1/users")
public class UserController extends BaseController {

    @Inject
    private UserService userService;

    //조회 jpa
    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public User get(RequestParams<User> requestParams) {
    	UserLogUtil.saveUserLog("UserController","사용자관리","GET");
        User user = userService.get(requestParams);
        return user;
    }
    
    //조회 마이바티스
    @RequestMapping(value ="getUser", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public UserVO getUser(RequestParams<UserVO> vo) {
    	UserLogUtil.saveUserLog("UserController","사용자관리","GET");        
        return userService.getUser(vo);
    }

    //조회 마이바티스
    @RequestMapping(value ="getUserList", method = RequestMethod.GET, produces = APPLICATION_JSON)
    public UserPagingVO getUserList(RequestParams<UserVO> vo, Pageable pageable) {
    	UserLogUtil.saveUserLog("UserController","사용자관리","GET");        
        return userService.getUserList(vo , pageable);
    }

    //저장
    @RequestMapping(method = RequestMethod.PUT, produces = APPLICATION_JSON)
    public ApiResponse save(@Valid @RequestBody User users) throws Exception {
    	UserLogUtil.saveUserLog("UserController","사용자관리","PUT");
        userService.saveUser(users);
        return ok();
    } 

    //패스워드초기하
    @RequestMapping(value = "resetPs" ,method = RequestMethod.PUT, produces = APPLICATION_JSON)
    public ApiResponse resetPs(@RequestBody User users) throws Exception {
    	UserLogUtil.saveUserLog("UserController","사용자관리(패스워드초기화)","PUT");
        userService.resetPs(users);
        return ok();
    } 
}
