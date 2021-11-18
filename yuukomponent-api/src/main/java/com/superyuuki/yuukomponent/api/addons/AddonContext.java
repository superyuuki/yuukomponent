package com.superyuuki.yuukomponent.api.addons;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface AddonContext {

    /**
     * the name to associate with the plugin
     *
     * @return the plugin name
     */
    String name();

    String version();

    //TODO Implement semver checking
    String platformVersion() default "not-implemented";
    String yuuKomponentVersion() default "not-implemented";

    Class<? extends Addon>[] dependencies() default {};

}
