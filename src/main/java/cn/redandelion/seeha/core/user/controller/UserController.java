package cn.redandelion.seeha.core.user.controller;

import cn.redandelion.seeha.core.sys.basic.dto.Code;
import cn.redandelion.seeha.core.user.dto.User;
import cn.redandelion.seeha.core.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService service;
    @RequestMapping(value = "index")
    public String index2(ModelMap map) {
        map.put("title", "freemarker hello word  ====");
        // 开头不要加上/，linux下面会出错
        return "index";
    }
    @RequestMapping(value = {"resource","resource/{resourceId}"})
    public String resource(ModelMap map, @PathVariable(required = false) String resourceId) {
        map.put("resourceId", resourceId);
        return "sys/sys_resource";
    }

    @RequestMapping(value = {"resource/edit","resource/edit/{functionId}","resource/edit/{functionId}/{resourceId}"})
    public String resourceEdit(ModelMap map, @PathVariable(required = false) String resourceId,
                               @PathVariable(required = false) String functionId) {
        map.put("title", "freemarker hello word  ====");
        map.put("resourceId", resourceId);
        map.put("functionId", functionId);
        return "sys/sys_resource_edit";
    }

    @RequestMapping(value = "/code")
    @ResponseBody
    public List<Code> supplierCode(HttpServletResponse response ) throws IOException {
        List<Code> codeList = new ArrayList<>();
        List<User>  users = service.selectAll();
        if (users.size()>0){
            users.forEach(x->{
                Code code = new Code();
                code.setValue(x.getUserId());
                code.setMeaning(x.getUserName());
                codeList.add(code);
            });
        }
        return codeList;
    }
}

