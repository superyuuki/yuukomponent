import com.superyuuki.yuukomponent.api.addon.internal.AddonLoaderSpi;
import com.superyuuki.yuukomponent.api.addon.processing.AddonProcessor;

module com.superyuuki.yuukomponent {

    requires com.google.gson;
    requires java.compiler;
    requires space.arim.omnibus;

    exports com.superyuuki.yuukomponent.api.blueprint;
    exports com.superyuuki.yuukomponent.api.component;
    exports com.superyuuki.yuukomponent.api.config;
    exports com.superyuuki.yuukomponent.api.inbuilt.persistent;
    exports com.superyuuki.yuukomponent.api.addon;
    exports com.superyuuki.yuukomponent.api.slot;
    exports com.superyuuki.yuukomponent.api.structure;
    exports com.superyuuki.yuukomponent.api.config.behavior;
    exports com.superyuuki.yuukomponent.api.config.error;
    exports com.superyuuki.yuukomponent.api.config.behavior.error;
    exports com.superyuuki.yuukomponent.api.event;
    exports com.superyuuki.yuukomponent.api;
    exports com.superyuuki.yuukomponent.api.addon.internal;
    exports com.superyuuki.yuukomponent.api.addon.error;
    exports com.superyuuki.yuukomponent.api.addon.processing;
    exports com.superyuuki.yuukomponent.api.exception;
    exports com.superyuuki.yuukomponent.api.component.error;
    exports com.superyuuki.yuukomponent.api.behavior;

    uses AddonLoaderSpi;

    provides javax.annotation.processing.Processor with AddonProcessor;

}