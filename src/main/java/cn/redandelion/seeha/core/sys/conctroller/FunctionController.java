package cn.redandelion.seeha.core.sys.conctroller;

import cn.redandelion.seeha.core.sys.basic.controller.BaseController;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.dto.Function;
import cn.redandelion.seeha.core.sys.service.IFunctionService;
import cn.redandelion.seeha.core.util.ResponseData;
import cn.redandelion.seeha.core.util.SysConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/sys")
public class FunctionController extends BaseController{
    @Autowired
    private IFunctionService service;
    @Autowired
    private ApplicationContext context;
    @RequestMapping("/list")
    @ResponseBody
    public ResponseData functionMenu(Function dto,
                                     @RequestParam(defaultValue = DEFAULT_PAGE )int page,
                                     @RequestParam(defaultValue = DEFAULT_PAGE_SIZE )int pageSize,
                                     HttpServletRequest request){
        IRequest iRequest = (IRequest) context.getBean("iRequestHelper");
        return new ResponseData(service.select(iRequest,dto,page,pageSize));
    }

}
