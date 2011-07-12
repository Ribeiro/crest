package org.codegist.crest.annotate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * If specified at method parameter level, indicates to inject it into the io entity body.
 * <p>If specified at interface or method level, indicates to inject a default parameter into the io entity body for all requests.
 * @see org.codegist.crest.io.http.HttpRequest.Builder#addFormParam(String, Object)
 * @author laurent.gilles@codegist.org
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD, ElementType.PARAMETER})
@Param("MATRIX")
public @interface MatrixParam {

    /**
     * Indicates the parameter name to use
     * @return parameter name
     */
    String value();

    /**
     * Indicates the parameter default value to use.
     * <p>At method parameter level, this value is used if the parameter is null
     * <p>At interface/method levels, this value is used to specifie the value of the parameter to add for each io
     * @return parameter default value
     */
    String defaultValue() default "";
}
