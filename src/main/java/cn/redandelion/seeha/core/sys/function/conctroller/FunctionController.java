package cn.redandelion.seeha.core.sys.function.conctroller;

import cn.redandelion.seeha.core.sys.basic.controller.BaseController;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.function.dto.Function;
import cn.redandelion.seeha.core.sys.function.service.IFunctionService;
import cn.redandelion.seeha.core.user.dto.User;
import cn.redandelion.seeha.core.user.service.IUserService;
import cn.redandelion.seeha.core.util.ResponseData;
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
                                     @RequestParam(defaultValue = DEFAULT_PAGE_SIZE )int pagesize,
                                     HttpServletRequest request){
        IRequest iRequest = (IRequest) context.getBean("iRequestHelper");
        return new ResponseData(service.select(iRequest,dto,page,pagesize));
    }
    @RequestMapping("rolemenu")
    @ResponseBody
    public ResponseData roleFunctionMenu(HttpServletRequest request){
        IRequest iRequest = (IRequest) context.getBean("iRequestHelper");
//      todo
        Long[] a = new Long[]{10001L, 10002L};
        iRequest.setRoleId(10001L);
        iRequest.setUserId(10001L);
//      更新到全局
        context.getAutowireCapableBeanFactory().applyBeanPropertyValues(iRequest,"iRequestHelper");
        iRequest.setAllRoleId(a);
        return new ResponseData(service.selectRoleFunctions(iRequest));
    }

}
