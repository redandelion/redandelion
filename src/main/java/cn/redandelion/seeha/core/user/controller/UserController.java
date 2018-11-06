package cn.redandelion.seeha.core.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "index")
    public String index2(ModelMap map) {
        map.put("title", "freemarker hello word  ====");
        return "index"; // 开头不要加上/，linux下面会出错
    }
    @RequestMapping(value = "resource")
    public String resource(ModelMap map) {
        map.put("title", "freemarker hello word  ====");
        return "sys_resource"; // 开头不要加上/，linux下面会出错
    }
}

