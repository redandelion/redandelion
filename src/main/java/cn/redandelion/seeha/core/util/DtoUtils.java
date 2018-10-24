package cn.redandelion.seeha.core.util;

import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;

import java.util.Date;

public class DtoUtils {
//    public static void setUpdateInfo(BaseDTO baseDTO, IRequest requestCtx){
//        Date date = new Date();
//        Long userId = requestCtx.getUserId();
//
//        //baseDTO.setObjectVersionNumber(baseDTO.getObjectVersionNumber()+1);
//        baseDTO.setLastUpdatedBy(userId);
//        baseDTO.setLastUpdateDate(date);
//        baseDTO.setLastUpdateLogin(userId);
//    }


    public static void setUpdateInfo(BaseDto baseDTO){
        Date date = new Date();
        Long userId = 100L;
        baseDTO.setLastUpdatedBy(userId);
        baseDTO.setLastUpdateDate(date);
        baseDTO.setLastUpdateLogin(userId);
        baseDTO.setCreatedBy(userId);
        baseDTO.setCreationDate(date);
//        baseDTO.setLastUpdatedBy(-1L);
//        baseDTO.setLastUpdateDate(new Date());
//        baseDTO.setLastUpdateLogin(-1L);
        //版本号+1==> by jiajun.yang
        //baseDTO.setObjectVersionNumber(baseDTO.getObjectVersionNumber()+1);

    }
}
