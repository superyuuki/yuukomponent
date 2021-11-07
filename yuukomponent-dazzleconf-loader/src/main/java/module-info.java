import com.superyuuki.yuukomponent.dazzleconf.loader.DazzleConfigurationLoader;

module com.superyuuki.yuukomponent.dazzleconf.loader {

    requires com.superyuuki.yuukomponent;
    requires com.superyuuki.yuukomponent.core;
    requires space.arim.dazzleconf;
    requires space.arim.dazzleconf.ext.snakeyaml;

    exports com.superyuuki.yuukomponent.dazzleconf.loader;

}