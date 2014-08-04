package idv.popcorny.builder;

import java.util.Collection;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Created by popcorny on 2014/8/4.
 */
public class CollectionBuilder<T> {
    private Collection<T> value;

    private CollectionBuilder(Collection<T> value) {
        this.value = value;
    }

    public Collection<T> get() {
        return value;
    }

    public static <T> CollectionBuilder<T> of(Collection<T> value) {
        return new CollectionBuilder<>(value);
    }

    public static <T> CollectionBuilder<T> of(
            Collection<T>  value,
            Consumer<Collection<T>> initFunc)
    {
        return new CollectionBuilder<>(value)
                .init(initFunc);
    }

    public CollectionBuilder<T> init(Consumer<Collection<T>> initFunc) {
        initFunc.accept(value);
        return this;
    }

    public CollectionBuilder<T> add(T t) {
        value.add(t);
        return this;
    }

    public CollectionBuilder<T> add(T t, BiConsumer<T, Builder<T>> initFunc) {
        value.add(t);
        initFunc.accept(t, Builder.of(t));
        return this;
    }
}
