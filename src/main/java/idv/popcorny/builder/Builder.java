package idv.popcorny.builder;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by popcorny on 2014/8/3.
 */
public class Builder<T> {
    private T value;

    private Builder(T value) {
        this.value = value;
    }

    public T get() {
        return value;
    }

    public static <T> Builder<T> of(T value) {
        return new Builder<>(value);
    }

    public static <T> Builder<T> of(T value, Consumer<T> initFunc) {
        return new Builder<>(value)
                .init(initFunc);
    }

    public Builder<T> init(Consumer<T> initFunc) {
        initFunc.accept(value);
        return this;
    }

    public <V> Builder<T> set(BiConsumer<T, V> setter, V v) {
        setter.accept(value, v);
        return this;
    }

    public <V> Builder<T> set(BiConsumer<T, V> setter, V v, BiConsumer<V, Builder<V>> initFunc) {
        setter.accept(value, v);
        initFunc.accept(v, Builder.of(v));
        return this;
    }

    public <V> Builder<T> setCollection(
            BiConsumer<T, Collection<V>> setter,
            Collection<V> v,
            Consumer<CollectionBuilder<V>> initFunc)
    {
        setter.accept(value, v);
        initFunc.accept(CollectionBuilder.of(v));
        return this;
    }

    public <V> Builder<T> setList(
            BiConsumer<T, List<V>> setter,
            List<V> v,
            BiConsumer<List<V>, CollectionBuilder<V>> initFunc)
    {
        setter.accept(value, v);
        initFunc.accept(v, CollectionBuilder.of(v));
        return this;
    }

    public <K, V> Builder<T> setMap(
            BiConsumer<T, Map<K, V>> setter,
            Map<K, V> v,
            BiConsumer<Map<K, V>, MapBuilder<K, V>> initFunc)
    {
        setter.accept(value, v);
        initFunc.accept(v, MapBuilder.of(v));
        return this;
    }
}
