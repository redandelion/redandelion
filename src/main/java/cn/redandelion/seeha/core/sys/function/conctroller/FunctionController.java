package cn.redandelion.seeha.core.sys.function.conctroller;

import cn.redandelion.seeha.core.sys.basic.controller.BaseController;
import cn.redandelion.seeha.core.sys.basic.dto.Code;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.function.dto.Function;
import cn.redandelion.seeha.core.sys.function.dto.FunctionResource;
import cn.redandelion.seeha.core.sys.function.dto.Resource;
import cn.redandelion.seeha.core.sys.function.service.IFunctionResourceService;
import cn.redandelion.seeha.core.sys.function.service.IFunctionService;
import cn.redandelion.seeha.core.sys.function.service.IResourceService;
import cn.redandelion.seeha.core.user.dto.User;
import cn.redandelion.seeha.core.user.service.IUserService;
import cn.redandelion.seeha.core.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/sys")
public class FunctionController extends BaseController{
    @Autowired
    private IFunctionService service;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private IResourceService resourceService;
    @Autowired
    private IFunctionResourceService functionResourceService;
    @RequestMapping("query")
    @ResponseBody
    public ResponseData queryResource(HttpServletRequest request, Function function,
                                      @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestContext = (IRequest) context.getBean("iRequestHelper");
        List<Function> functions = service.select(requestContext, function, page, pagesize);
        functions.stream().forEach(x-> {
//            设置父功能名称
            Function functionTemp = new Function();
            functionTemp.setFunctionId(x.getParentFunctionId());
            functionTemp = service.selectByPrimaryKey(requestContext, functionTemp);
            if (functionTemp != null) {
                x.setParentFunctionName(functionTemp.getFunctionName());
            }
//            设置资源名称
            Resource resource = new Resource();
            resource.setResourceId(x.getResourceId());
            resource = resourceService.selectByPrimaryKey(requestContext, resource);
            if (resource!=null){
                x.setResourceName(resource.getName());
            }
        });
        return new ResponseData(functions);
    }
    @PostMapping(value = "/submit")
    public ResponseData submitResource(HttpServletRequest request, @RequestBody List<Function> functions,
                                       BindingResult result) throws Exception {
//        getValidator().validate(resources, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage("保存失败！");
            return responseData;
        }
        IRequest requestContext = (IRequest) context.getBean("iRequestHelper");
        service.saveFunctionAndResource(requestContext, functions);
        return new ResponseData(functions);
    }
    @PostMapping(value = "/remove")
    public ResponseData removeResource(HttpServletRequest request, @RequestBody List<Function> functions)
            throws Exception {

        service.deleteFunctionAndResource(functions);

        return new ResponseData();
    }


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
//      更新到全局todo
        context.getAutowireCapableBeanFactory().applyBeanPropertyValues(iRequest,"iRequestHelper");
        iRequest.setAllRoleId(a);
        return new ResponseData(service.selectRoleFunctions(iRequest));
    }

    @RequestMapping(value = "/functionParent/code")
    @ResponseBody
    public List<Code> supplierCode(HttpServletResponse response ) throws IOException {
        List<Code> codeList = new ArrayList<>();
        Example example = new Example(Function.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIsNull("resourceId");
        List<Function> functions = service.selectByExample(example);
        if (functions.size()>0){
            functions.forEach(x->{
                Code code = new Code();
                code.setValue(x.getFunctionId());
                code.setMeaning(x.getFunctionName());
                codeList.add(code);
            });
        }
        return codeList;
    }
}
