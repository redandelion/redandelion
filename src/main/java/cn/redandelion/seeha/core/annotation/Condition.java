package cn.redandelion.seeha.core.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * where condition configuration.
 *
 * @author jessen
 * @since 2015-05-30 19:07
 */
@Target({ ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Condition {

    /**
     * don't use this field as condition.
     *
     * default false
     */
    boolean exclude() default false;
}
