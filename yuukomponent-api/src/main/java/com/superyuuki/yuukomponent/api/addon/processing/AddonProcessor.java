package com.superyuuki.yuukomponent.api.addon.processing;

import com.google.gson.Gson;
import com.superyuuki.yuukomponent.api.addon.AddonDescription;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.FileObject;
import javax.tools.StandardLocation;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Arcane workaround to load addons using annotations.
 *
 * Credit to velocity
 */
@SupportedAnnotationTypes({"com.superyuuki.yuukomponent.api.feature.AddonDescription"})
public class AddonProcessor extends AbstractProcessor {

    private ProcessingEnvironment environment;
    private String pluginClassFound;
    private boolean warnedAboutMultiplePlugins;

    @Override
    public synchronized void init(ProcessingEnvironment processingEnv) {
        this.environment = processingEnv;
    }

    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {

        if (roundEnv.processingOver()) {
            return false;
        }

        for (Element element : roundEnv.getElementsAnnotatedWith(AddonDescription.class)) {
            if (element.getKind() != ElementKind.CLASS) {
                environment.getMessager().printMessage(
                        Diagnostic.Kind.ERROR,
                        "Only classes can use the annotation " + AddonDescription.class.getCanonicalName()
                );

                return false;
            }

            Name qualifiedName = ((TypeElement) element).getQualifiedName();

            if (Objects.equals(pluginClassFound, qualifiedName.toString())) {
                if (!warnedAboutMultiplePlugins) {

                    environment.getMessager().printMessage(
                            Diagnostic.Kind.WARNING,
                            "Found multiple plugins: We only support a single entry point. Using: " + pluginClassFound
                    );

                    warnedAboutMultiplePlugins = true;
                }
                return false;
            }

            AddonDescription plugin = element.getAnnotation(AddonDescription.class);



            try {
                FileObject object = environment.getFiler()
                        .createResource(StandardLocation.CLASS_OUTPUT, "", "yuukomponent-addon.json");
                try (Writer writer = new BufferedWriter(object.openWriter())) {
                    new Gson().toJson(new SerializedDescription(
                            plugin.displayName(),
                            plugin.version(),
                            plugin.description(),
                            plugin.author(),
                            List.of(plugin.exports()),
                            List.of(plugin.depends()),
                            qualifiedName.toString()), writer);
                }
                pluginClassFound = qualifiedName.toString();
            } catch (IOException e) {
                environment.getMessager()
                        .printMessage(Diagnostic.Kind.ERROR, "Unable to generate plugin file");
            }
        }

        return false;
    }
}
