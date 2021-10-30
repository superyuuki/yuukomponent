package com.superyuuki.yuukomponent.ideal.reduced;

import com.superyuuki.yuukomponent.ideal.CompStats;
import com.superyuuki.yuukomponent.ideal.EmptyObject;
import com.superyuuki.yuukomponent.ideal.StatObject;

import java.util.Map;
import java.util.function.Consumer;

public class Trial1NonInvertedComponent implements ReducedComponent {

    private final CompStats stats;
    private final Map<String, ReducedComponent> componentMap;
    private final ReducedBehavior behavior;

    public Trial1NonInvertedComponent(CompStats stats, Map<String, ReducedComponent> componentMap, MistakeLoader loader) {
        this.stats = stats;
        this.componentMap = componentMap;

        Consumer<StatObject> statObjectConsumer = this::stats;

        Stat stat = () -> {

            StatObject object = new EmptyObject();

            statObjectConsumer.accept(object);

            return 0;
        };

        this.behavior = loader.reduxed(stat);
    }

    @Override
    public void handle(Object event) {
        behavior.handle(event);

        for (ReducedComponent component : componentMap.values()) {
            component.handle(event);
        }
    }

    @Override
    public void stats(StatObject initialStats) {
        stats.modify(initialStats);

        componentMap.forEach((s, statComponent) -> {
            statComponent.stats(initialStats);
        });
    }





    interface MistakeLoader {
        ReducedBehavior reduxed(Stat stat);
    }

    interface Stat {
        int load();
    }

    public static class ShitLoader implements MistakeLoader {

        @Override
        public ReducedBehavior reduxed(Stat stat) {
            return new ShitBehavior(stat);
        }
    }

    public static class ShitBehavior implements ReducedBehavior {

        private final Stat someValue;

        public ShitBehavior(Stat someValue) {
            this.someValue = someValue;
        }

        @Override
        public void handle(Object event) {
            event.toString(); //interact

            someValue.load(); //interact
        }
    }

}
