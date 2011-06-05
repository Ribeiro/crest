package org.codegist.crest.annotate;

import org.codegist.crest.HttpRequest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * If specified at method parameter level, indicates to inject it into the request entity body.
 * <p>If specified at interface or method level, indicates to inject a default parameter into the request entity body for all requests.
 * @see org.codegist.crest.HttpRequest.Builder#addFormParam(String, Object)
 * @author laurent.gilles@codegist.org
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD, ElementType.PARAMETER})
@Param(HttpRequest.DEST_MATRIX)
public @interface MatrixParam {

    /**
     * Indicates the parameter name to use
     * @return parameter name
     */
    String value();

    /**
     * Indicates the parameter default value to use.
     * <p>At method parameter level, this value is used if the parameter is null
     * <p>At interface/method levels, this value is used to specifie the value of the parameter to add for each request
     * @return parameter default value
     */
    String defaultValue() default "";

}