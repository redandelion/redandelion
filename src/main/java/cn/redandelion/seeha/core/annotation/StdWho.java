package cn.redandelion.seeha.core.annotation;


import java.lang.annotation.*;

@Inherited
@Target({ElementType.PARAMETER,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StdWho {
    boolean who() default true;
}
