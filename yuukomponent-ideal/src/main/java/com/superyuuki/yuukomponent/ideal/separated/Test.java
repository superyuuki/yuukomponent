package com.superyuuki.yuukomponent.ideal.separated;

import com.superyuuki.yuukomponent.ideal.CompStats;
import com.superyuuki.yuukomponent.ideal.EmptyObject;
import com.superyuuki.yuukomponent.ideal.StatObject;
import com.superyuuki.yuukomponent.ideal.reduced.ReducedBehavior;
import com.superyuuki.yuukomponent.ideal.temp.BehaviorCalculator;
import com.superyuuki.yuukomponent.ideal.temp.StatCalculator;

import java.util.Collection;
import java.util.Map;
import java.util.function.Supplier;

public class Test {

    void lol() {
        CompStats stats1 = stats -> {

        };

        CompStats stats2 = stats -> {

        };

        InsertLaterMap map = new InsertLaterMap(Map.of(
                "Hello",
                new SMapTree(stats2, new InsertLaterMap(Map.of()))
        ));

        StatCalculator calculator = new SMapTree(
                stats1,
                map
        );

        Supplier<StatObject> objectSupplier = () -> {
            StatObject object = new EmptyObject();

            calculator.stats(object);

            return object;
        };

        ReducedBehavior shit1 = new ShitBehavior(objectSupplier);

        new BMapTree()
    }

    class ShitBehavior implements ReducedBehavior {
        private final Supplier<StatObject> supplier;

        public ShitBehavior(Supplier<StatObject> supplier) {
            this.supplier = supplier;
        }

        @Override
        public void handle(Object event) {
            System.out.println("behavior called!");
            //supplier.get().value("test");
        }
    }

    class BMapTree implements BehaviorCalculator {

        private final ReducedBehavior behavior;
        private final BehaviorMap map;

        public BMapTree(ReducedBehavior behavior, BehaviorMap map) {
            this.behavior = behavior;
            this.map = map;
        }

        @Override
        public void handle(Object event) {
            behavior.handle(event);

            for (BehaviorCalculator calculator : map.asBehaviors()) {
                calculator.handle(event);
            }
        }
    }

    class SMapTree implements StatCalculator {

        private final CompStats stats;
        private final StatMap map;

        public SMapTree(CompStats stats, StatMap map) {
            this.stats = stats;
            this.map = map;
        }

        @Override
        public void stats(StatObject initialStats) {
            stats.modify(initialStats);

            for (StatCalculator calc : map.asStats()) {
                calc.stats(initialStats);
            }
        }
    }

    interface StructureMap extends StatMap, BehaviorMap, KeyedStructure {

    }

    interface KeyedStructure {
        void evict(String key);
    }

    interface StatMap {
        Collection<StatCalculator> asStats();
    }

    interface BehaviorMap {
        Collection<BehaviorCalculator> asBehaviors();
    }

    class MappedMap implements StatMap {

        private final Map<String, StatCalculator> calculatorMap;

        MappedMap(Map<String, StatCalculator> calculatorMap) {
            this.calculatorMap = calculatorMap;
        }

        @Override
        public Collection<StatCalculator> asStats() {
            return calculatorMap.values();
        }
    }

    class SuperMap implements StructureMap {

        private final Map<String, StatCalculator> statCalculatorMap;
        private final Map<String, BehaviorCalculator> behaviorCalculatorMap;

        SuperMap(Map<String, StatCalculator> statCalculatorMap, Map<String, BehaviorCalculator> behaviorCalculatorMap) {
            this.statCalculatorMap = statCalculatorMap;
            this.behaviorCalculatorMap = behaviorCalculatorMap;
        }

        @Override
        public Collection<StatCalculator> asStats() {
            return statCalculatorMap.values();
        }

        @Override
        public Collection<BehaviorCalculator> asBehaviors() {
            return behaviorCalculatorMap.values();
        }

        @Override
        public void evict(String key) {
            statCalculatorMap.remove(key);
            behaviorCalculatorMap.remove(key);
        }
    }

    class InsertLaterMap implements StructureMap {
        private final Map<String, StatCalculator> statCalculatorMap;
        private Map<String, BehaviorCalculator> insertLater;

        public InsertLaterMap(Map<String, StatCalculator> statCalculatorMap) {
            this.statCalculatorMap = statCalculatorMap;
        }

        void insert(Map<String, BehaviorCalculator> insertLater) {
            if (this.insertLater == null) {
                this.insertLater = insertLater;
            }
        }

        @Override
        public void evict(String key) {
            statCalculatorMap.remove(key);
            insertLater.remove(key);
        }

        @Override
        public Collection<StatCalculator> asStats() {
            return statCalculatorMap.values();
        }

        @Override
        public Collection<BehaviorCalculator> asBehaviors() {
            return insertLater.values();
        }
    }


}
