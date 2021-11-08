import com.superyuuki.yuukomponent.core.addon.internal.PathAddonLoaderSpi;

module com.superyuuki.yuukomponent.core {
    uses com.superyuuki.yuukomponent.api.addon.internal.AddonLoaderSpi;

    requires com.superyuuki.yuukomponent;
    requires com.google.gson;
    requires com.google.common;

    exports com.superyuuki.yuukomponent.core.addon;
    exports com.superyuuki.yuukomponent.core.event;
    exports com.superyuuki.yuukomponent.core.addon.internal;

    provides com.superyuuki.yuukomponent.api.addon.internal.AddonLoaderSpi with PathAddonLoaderSpi;

}