package cn.redandelion.seeha.core.util;

import java.util.UUID;

public class GetUuidCodeUtil {
    public static String getUUID16(){
        int random = (int) (Math.random()*9+1);
        String valueOf = String.valueOf(random);
        //生成uuid的hashCode值
        int hashCode = UUID.randomUUID().toString().hashCode();
        //可能为负数
        if(hashCode<0){
            hashCode = -hashCode;
        }
        String uuidValue = valueOf + String.format("%015d", hashCode);
        return uuidValue;
    }
}
