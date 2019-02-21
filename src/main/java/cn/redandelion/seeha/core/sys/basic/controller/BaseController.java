package cn.redandelion.seeha.core.sys.basic.controller;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    protected static final String DEFAULT_PAGE = "1";
    protected static final String DEFAULT_PAGE_SIZE = "10";
}
