package com.superyuuki.yuukomponent.api.addon;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface AddonDescription {

    String displayName();
    String version() default "UNKNOWN";
    String description() default "Generic addon description";
    String author() default "UNKNOWN";

    /**
     * Defines a contract of features this addon MUST provide. Failure to provide included features is shitcode.
     * @return collection of features to export
     */
    Class<? extends Feature>[] exports() default {};

    /**
     * Defines a list of features this addon requires to function.
     * @return collection of features to try and load first.
     */
    Class<? extends Feature>[] depends() default {};

}
