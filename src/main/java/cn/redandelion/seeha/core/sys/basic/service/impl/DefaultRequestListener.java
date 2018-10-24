package cn.redandelion.seeha.core.sys.basic.service.impl;

import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.IRequestListener;

import javax.servlet.http.HttpServletRequest;

public class DefaultRequestListener implements IRequestListener{
    @Override
    public IRequest newInstance() {
        return new ServiceRequest();
    }

    @Override
    public void afterInitialize(HttpServletRequest httpServletRequest, IRequest request) {

    }
}
