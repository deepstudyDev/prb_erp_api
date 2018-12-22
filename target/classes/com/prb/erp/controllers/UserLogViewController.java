package com.prb.erp.controllers;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.chequer.axboot.core.api.response.ApiResponse;
import com.chequer.axboot.core.api.response.Responses;
import com.chequer.axboot.core.controllers.BaseController;
import com.chequer.axboot.core.parameter.RequestParams;
import com.prb.erp.domain.user.log.UserLog;
import com.prb.erp.domain.user.log.UserLogService;

@Controller
@RequestMapping(value = "/api/v1/userLogs")
public class UserLogViewController extends BaseController {

    @Inject
    private UserLogService userLogService;
    
    @RequestMapping(method = RequestMethod.GET, produces = APPLICATION_JSON)
    public Responses.ListResponse list(RequestParams<UserLog> requestParams) {
        List<UserLog> logs = userLogService.get(requestParams);
        return Responses.ListResponse.of(logs);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = APPLICATION_JSON)
    public ApiResponse delete(@PathVariable(value = "id") Long id) {
    	userLogService.delete(id);
        return ok();
    } 
    
    @RequestMapping(value = "/events/all", method = RequestMethod.DELETE, produces = APPLICATION_JSON)
    public ApiResponse deleteAll() {
    	userLogService.deleteAllLogs();
        return ok();
    }
}
