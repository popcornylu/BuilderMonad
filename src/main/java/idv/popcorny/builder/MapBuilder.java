package idv.popcorny.builder;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by popcorny on 2014/8/4.
 */
public class MapBuilder<K,V> {
    private Map<K, V> value;

    private MapBuilder(Map<K, V> value) {
        this.value = value;
    }

    public Map<K, V> get() {
        return value;
    }

    public static <K, V> MapBuilder<K, V> of(Map<K, V> value) {
        return new MapBuilder<>(value);
    }

    public static <K, V> MapBuilder<K, V> of(
            Map<K, V>  value,
            Consumer<Map<K, V>> initFunc)
    {
        return new MapBuilder<>(value)
                .init(initFunc);
    }

    public MapBuilder<K, V> init(Consumer<Map<K, V>> initFunc) {
        initFunc.accept(value);
        return this;
    }

    public MapBuilder<K, V> put(K k, V v) {
        value.put(k, v);
        return this;
    }

    public MapBuilder<K, V> put(K k, V v, BiConsumer<V, Builder<V>> initFunc) {
        value.put(k, v);
        initFunc.accept(v, Builder.of(v));
        return this;
    }
}
