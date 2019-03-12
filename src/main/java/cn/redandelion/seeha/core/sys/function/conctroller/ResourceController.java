package cn.redandelion.seeha.core.sys.function.conctroller;

import cn.redandelion.seeha.core.sys.basic.controller.BaseController;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.ServiceRequest;
import cn.redandelion.seeha.core.sys.function.dto.Resource;
import cn.redandelion.seeha.core.sys.function.service.IResourceService;
import cn.redandelion.seeha.core.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("sys/resource")
public class ResourceController extends BaseController{
    @Autowired
    private IResourceService resourceService;
    @Autowired
    private ApplicationContext context;
    @RequestMapping("query")
    @ResponseBody
    public ResponseData queryResource(HttpServletRequest request, Resource resource,
                                      @RequestParam(defaultValue = DEFAULT_PAGE) int page,
                                      @RequestParam(defaultValue = DEFAULT_PAGE_SIZE) int pagesize) {
        IRequest requestContext = (IRequest) context.getBean(ServiceRequest.class);
        return new ResponseData(resourceService.select(requestContext, resource, page, pagesize));
    }
    @PostMapping(value = "/submit")
    public ResponseData submitResource(HttpServletRequest request, @RequestBody List<Resource> resources,
                                       BindingResult result) throws Exception {
//        getValidator().validate(resources, result);
        if (result.hasErrors()) {
            ResponseData responseData = new ResponseData(false);
            responseData.setMessage("保存失败！");
            return responseData;
        }
        IRequest requestContext = (IRequest) context.getBean(ServiceRequest.class);
        return new ResponseData(resourceService.batchUpdate(requestContext, resources));
    }

    @PostMapping(value = "/remove")
    public ResponseData removeResource(HttpServletRequest request, @RequestBody List<Resource> resources)
            throws Exception {
        resourceService.batchDelete(resources);
        return new ResponseData();
    }

}
