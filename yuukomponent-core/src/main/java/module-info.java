import com.superyuuki.yuukomponent.api.addon.internal.spi.AddonLoaderSpi;
import com.superyuuki.yuukomponent.core.addon.internal.PathAddonLoaderSpi;

module com.superyuuki.yuukomponent.core {
    uses AddonLoaderSpi;

    requires com.superyuuki.yuukomponent;
    requires com.google.gson;
    requires com.google.common;

    exports com.superyuuki.yuukomponent.core.addon;
    exports com.superyuuki.yuukomponent.core.event;
    exports com.superyuuki.yuukomponent.core.addon.internal;
    exports com.superyuuki.yuukomponent.core.config;
    exports com.superyuuki.yuukomponent.core.component;

    provides AddonLoaderSpi with PathAddonLoaderSpi;

}