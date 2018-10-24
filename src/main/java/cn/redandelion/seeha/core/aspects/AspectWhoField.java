package cn.redandelion.seeha.core.aspects;

import cn.redandelion.seeha.core.annotation.Condition;
import cn.redandelion.seeha.core.annotation.StdWho;
import cn.redandelion.seeha.core.sys.basic.dto.BaseDto;
import cn.redandelion.seeha.core.sys.basic.dto.IRequest;
import cn.redandelion.seeha.core.sys.basic.service.impl.ServiceRequest;
import cn.redandelion.seeha.core.util.DtoUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Date;

@Aspect
@Component
public class AspectWhoField {
    private Logger logger = LoggerFactory.getLogger(AspectWhoField.class);
    @Autowired
    private ApplicationContext context;
    @Pointcut("execution(* cn.redandelion.seeha..service..*(..))")
    public void annotationPoinCut(){}

    @Before("annotationPoinCut()")
    public void before(JoinPoint joinPoint)  {

        MethodSignature signature = (MethodSignature)joinPoint.getSignature();
        Method method = signature.getMethod();
        // 1.获取参数的类型
        Boolean flag = true;
        method.setAccessible(true);
        Class<?>[] types = method.getParameterTypes();
//        StdWho[] annotation = method.getDeclaredAnnotationsByType(StdWho.class);
        Parameter[] parameters = method.getParameters();
        // 2.Object[] args = joinPoint.getArgs();获取AOP切面的参数类型
        // 3.按照类型的种类遍历，是否为BaseDto 的子类或派生类
        StdWho stdWho = method.getDeclaredAnnotation(StdWho.class);
        if (stdWho ==null){
            flag = false;
        }
        for (int i=0; i< types.length ; ++i){
            if(stdWho != null ){
                logger.trace("enable StdWho for argument {}, type: {}", i, types[i].getName());
            }
          //  logger.info(types[i].getName());

            Object[] args = joinPoint.getArgs();
                //logger.info((String) Class.forName(types[i].getName()).newInstance());
                if( args[i]  instanceof BaseDto){
//                  4.是子类则，自动填充Who字段
                    logger.info("dto 子类");
//                  强制转为BaseDto基类
                    BaseDto baseDTO = (BaseDto) args[i];
                    autoSetWhoProperty(baseDTO,flag);
                }
        }
//        Boolean excludeStatus =condition.exclude();
       //  DtoUtils.setUpdateInfo(new BaseDto());
    }
    private void autoSetWhoProperty(BaseDto baseDTO,Boolean whoSupport){
        ServiceRequest iRequest = context.getBean(ServiceRequest.class);
        Date date = new Date();
        if(whoSupport){
            // 为ture 则是更新，或者插入
            baseDTO.setCreatedBy(iRequest.getUserId());
            baseDTO.setCreationDate(date);
            baseDTO.setLastUpdateLogin(iRequest.getUserId());
            baseDTO.setLastUpdatedBy(iRequest.getUserId());
            baseDTO.setLastUpdateDate(date);
        }
// 否则就是select，delete

    }

}
