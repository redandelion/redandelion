package cn.redandelion.seeha.core.sys.function.conctroller;

import cn.redandelion.seeha.core.sys.basic.controller.BaseController;
import cn.redandelion.seeha.core.sys.function.service.IResourceService;
import cn.redandelion.seeha.core.util.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("sys/")
public class ResourceController extends BaseController{
    @Autowired
    private IResourceService resourceService;

    @RequestMapping("query")
    @ResponseBody
    public ResponseData queryResources(){

        return null;
    }

}
