package com.example.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class CollectionUtils {
    private CollectionUtils() {
    }

    public static boolean isEmpty(Collection<?> collection) {
        return (collection == null || collection.isEmpty());
    }

    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

    public static boolean isNotEmpty(Collection<?> collection) {
        return !isEmpty(collection);
    }

    public static boolean isNotEmpty(Map<?, ?> map) {
        return !isEmpty(map);
    }

    public static <E> E first(List<E> list) {
        return firstOrDefault(list, null);
    }

    public static <E> E firstOrDefault(List<E> list, E defaultValue) {
        if (isEmpty(list)) {
            return defaultValue;
        }
        return list.get(0);
    }

    public static <E> E last(List<E> list) {
        return lastOrDefault(list, null);
    }

    public static <E> E lastOrDefault(List<E> list, E defaultValue) {
        if (isEmpty(list)) {
            return defaultValue;
        }
        return list.get(list.size() - 1);
    }

    public static <E> E first(Deque<E> deque) {
        return firstOrDefault(deque, null);
    }

    public static <E> E firstOrDefault(Deque<E> deque, E defaultValue) {
        if (isEmpty(deque)) {
            return defaultValue;
        }
        return deque.getFirst();
    }

    public static <E> E last(Deque<E> deque) {
        return lastOrDefault(deque, null);
    }

    public static <E> E lastOrDefault(Deque<E> deque, E defaultValue) {
        if (isEmpty(deque)) {
            return defaultValue;
        }
        return deque.getLast();
    }

    public static <E> E first(Queue<E> queue) {
        return firstOrDefault(queue, null);
    }

    public static <E> E firstOrDefault(Queue<E> queue, E defaultValue) {
        if (isEmpty(queue)) {
            return defaultValue;
        }
        return queue.peek();
    }

    public static <E> E random(List<E> list) {
        if (isEmpty(list)) {
            return null;
        }
        return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }

    public static <E> E random(Collection<E> c) {
        if (isEmpty(c)) {
            return null;
        }
        return random(new ArrayList<>(c));
    }

    public static <K, V> Map.Entry<K, V> random(Map<K, V> map) {
        if (isEmpty(map)) {
            return null;
        }
        return random(map.entrySet());
    }

    public static <K, V> K randomKey(Map<K, V> map) {
        if (isEmpty(map)) {
            return null;
        }
        return random(map.keySet());
    }

    public static <K, V> V randomValue(Map<K, V> map) {
        if (isEmpty(map)) {
            return null;
        }
        return random(map.values());
    }

    public static <E> void shuffle(List<E> list) {
        Collections.shuffle(list);
    }

    public static <E> void reverse(List<E> list) {
        Collections.reverse(list);
    }

    /**
     * Returns consecutive {@linkplain List#subList(int, int) sublists} of a list,
     * each of the same size (the final list may be smaller). For example,
     * partitioning a list containing {@code [a, b, c, d, e]} with a partition
     * size of 3 yields {@code [[a, b, c], [d, e]]} -- an outer list containing
     * two inner lists of three and two elements, all in the original order.
     *
     * <p>The outer list is unmodifiable, but reflects the latest state of the
     * source list. The inner lists are sublist views of the original list,
     * produced on demand using {@link List#subList(int, int)}, and are subject
     * to all the usual caveats about modification as explained in that API.
     * @param list the list to return consecutive sublists of
     * @param size the desired size of each sublist (the last may be smaller)
     * @return a list of consecutive sublists
     */
    public static <E> List<List<E>> partition(List<E> list, final int size) {
        if (isEmpty(list)) {
            return Collections.emptyList();
        }
        List<List<E>> result;
        int fromIndex = 0;
        int toIndex = size;
        int total = list.size();
        if (size >= total) {
            result = new ArrayList<>();
            result.add(list);
        } else {
            int part = total / size;
            result = new ArrayList<>(part);
            for (int i = 0; i < part; i++) {
                result.add(list.subList(fromIndex, toIndex));
                fromIndex = toIndex;
                toIndex += size;
            }
            if (total > fromIndex) {
                result.add(list.subList(fromIndex, total));
            }
        }
        return result;
    }


    public static <E> List<E> ofList(E e) {
        List<E> list = new ArrayList<>();
        list.add(e);
        return list;
    }

    public static <E> List<E> ofList(final E... array) {
        return ofCollection(array, ArrayList::new);
    }

    public static <E, C extends List<E>> C ofList(E[] array, Supplier<C> collectionFactory) {
        return ofCollection(array, collectionFactory);
    }

    public static <E, T> List<E> ofList(T[] array, Function<T, E> mapper) {
        return ofCollection(array, mapper, ArrayList::new);
    }

    public static <E, T, C extends List<E>> C ofList(T[] array, Function<T, E> mapper, Supplier<C> collectionFactory) {
        return ofCollection(array, mapper, collectionFactory);
    }

    public static <E> List<E> ofList(Collection<E> collection) {
        return new ArrayList<>(collection);
    }

    public static <E, T> List<E> ofList(Collection<T> collection, Function<T, E> mapper) {
        return ofCollection(collection, mapper, ArrayList::new);
    }

    public static <E, T, C extends List<E>> C ofList(Collection<T> collection, Function<T, E> mapper, Supplier<C> collectionFactory) {
        return ofCollection(collection, mapper, collectionFactory);
    }


    public static <E> Set<E> ofSet(E e) {
        Set<E> set = new HashSet<>();
        set.add(e);
        return set;
    }

    public static <E> Set<E> ofSet(final E... array) {
        return ofCollection(array, HashSet::new);
    }

    public static <E, C extends Set<E>> C ofSet(E[] array, Supplier<C> collectionFactory) {
        return ofCollection(array, collectionFactory);
    }

    public static <E, T> Set<E> ofSet(T[] array, Function<T, E> mapper) {
        return ofCollection(array, mapper, HashSet::new);
    }

    public static <E, T, C extends Set<E>> C ofSet(T[] array, Function<T, E> mapper, Supplier<C> collectionFactory) {
        return ofCollection(array, mapper, collectionFactory);
    }

    public static <E> Set<E> ofSet(Collection<E> collection) {
        return new HashSet<>(collection);
    }

    public static <E, T> Set<E> ofSet(Collection<T> collection, Function<T, E> mapper) {
        return ofCollection(collection, mapper, HashSet::new);
    }

    public static <E, T, C extends Set<E>> C ofSet(Collection<T> collection, Function<T, E> mapper, Supplier<C> collectionFactory) {
        return ofCollection(collection, mapper, collectionFactory);
    }


    public static <E, C extends Collection<E>> C ofCollection(E[] array, Supplier<C> collectionFactory) {
        C c = collectionFactory.get();
        if (array == null || array.length == 0) {
            return c;
        }
        Collections.addAll(c, array);
        return c;
    }

    public static <E, T, C extends Collection<E>> C ofCollection(T[] array, Function<T, E> mapper, Supplier<C> collectionFactory) {
        if (array == null || array.length == 0) {
            return collectionFactory.get();
        }
        return ofCollection(Arrays.stream(array), mapper, collectionFactory);
    }

    public static <E, T, C extends Collection<E>> C ofCollection(Collection<T> collection, Function<T, E> mapper, Supplier<C> collectionFactory) {
        if (isEmpty(collection)) {
            return collectionFactory.get();
        }
        return ofCollection(collection.stream(), mapper, collectionFactory);
    }

    public static <E, T, C extends Collection<E>> C ofCollection(Stream<T> stream, Function<T, E> mapper, Supplier<C> collectionFactory) {
        Objects.requireNonNull(mapper, "Mapper is required");
        if (stream == null) {
            return collectionFactory.get();
        }
        return stream.map(mapper).collect(Collectors.toCollection(collectionFactory));
    }


    public static <K, V, T> Map<K, V> ofMap(T[] array, Function<T, K> keyMapper, Function<T, V> valueMapper) {
        return ofMap(array, keyMapper, valueMapper, (oldValue, newValue) -> newValue);
    }

    public static <K, V, T> Map<K, V> ofMap(T[] array, Function<T, K> keyMapper, Function<T, V> valueMapper, BinaryOperator<V> mergeFunction) {
        return ofMap(array, keyMapper, valueMapper, mergeFunction, HashMap::new);
    }

    public static <K, V, T, M extends Map<K, V>> M ofMap(T[] array, Function<T, K> keyMapper, Function<T, V> valueMapper, BinaryOperator<V> mergeFunction, Supplier<M> mapFactory) {
        if (array == null || array.length == 0) {
            return mapFactory.get();
        }
        return ofMap(Arrays.stream(array), keyMapper, valueMapper, mergeFunction, mapFactory);
    }

    public static <K, V, T> Map<K, V> ofMap(Collection<T> collection, Function<T, K> keyMapper, Function<T, V> valueMapper) {
        return ofMap(collection, keyMapper, valueMapper, (oldValue, newValue) -> newValue);
    }

    public static <K, V, T> Map<K, V> ofMap(Collection<T> collection, Function<T, K> keyMapper, Function<T, V> valueMapper, BinaryOperator<V> mergeFunction) {
        return ofMap(collection, keyMapper, valueMapper, mergeFunction, HashMap::new);
    }

    public static <K, V, T, M extends Map<K, V>> M ofMap(Collection<T> collection, Function<T, K> keyMapper, Function<T, V> valueMapper, BinaryOperator<V> mergeFunction, Supplier<M> mapFactory) {
        if (isEmpty(collection)) {
            return mapFactory.get();
        }
        return ofMap(collection.stream(), keyMapper, valueMapper, mergeFunction, mapFactory);
    }

    public static <K, V, T, M extends Map<K, V>> M ofMap(Stream<T> stream, Function<T, K> keyMapper, Function<T, V> valueMapper, BinaryOperator<V> mergeFunction, Supplier<M> mapFactory) {
        Objects.requireNonNull(keyMapper, "Key mapper is required");
        Objects.requireNonNull(valueMapper, "Value mapper is required");
        if (stream == null) {
            return mapFactory.get();
        }
        return stream.collect(Collectors.toMap(keyMapper, valueMapper, mergeFunction, mapFactory));
    }


    public static <K, T> Map<K, List<T>> grouping(Collection<T> collection, Function<T, K> keyMapper) {
        return grouping(collection, keyMapper, HashMap::new, ArrayList::new);
    }

    public static <K, T, M extends Map<K, List<T>>> M grouping(Collection<T> collection, Function<T, K> keyMapper, Supplier<M> mapFactory) {
        return grouping(collection, keyMapper, mapFactory, ArrayList::new);
    }

    public static <K, T, M extends Map<K, C>, C extends List<T>> M grouping(Collection<T> collection, Function<T, K> keyMapper, Supplier<M> mapFactory, Supplier<C> collectionFactory) {
        Objects.requireNonNull(collection);
        return collection.stream().collect(
                Collectors.groupingBy(
                        keyMapper,
                        mapFactory,
                        Collectors.toCollection(collectionFactory)));
    }


    /**
     * Returns an empty {@link ArrayList},
     * if need an immutable list, please replace by <code>Collections.emptyList()</code>
     * @return an empty {@link ArrayList}
     * @see Collections#emptyList
     */
    public static <E> List<E> emptyList() {
        return new ArrayList<>();
    }

    /**
     * Returns an empty {@link HashSet},
     * if need an immutable set, please replace by <code>Collections.emptySet()</code>
     * @return an empty {@link HashSet}
     * @see Collections#emptySet
     */
    public static <E> Set<E> emptySet() {
        return new HashSet<>();
    }

    /**
     * Returns an empty {@link HashMap},
     * if need an immutable map, please replace by <code>Collections.emptyMap()</code>
     * @return an empty {@link HashMap}
     * @see Collections#emptyMap
     */
    public static <K, V> Map<K, V> emptyMap() {
        return new HashMap<>();
    }


    public static <E, C extends Collection<E>> C copyCollection(Collection<E> collection, Supplier<C> collectionFactory) {
        C newCollection = collectionFactory.get();
        if (collection == null) {
            return newCollection;
        }
        newCollection.addAll(collection);
        return newCollection;
    }

    public static <E> List<E> copyList(Collection<E> collection) {
        if (collection == null) {
            return emptyList();
        }
        return new ArrayList<>(collection);
    }

    public static <E> Set<E> copySet(Collection<E> collection) {
        if (collection == null) {
            return emptySet();
        }
        return new HashSet<>(collection);
    }

    public static <K, V> Map<K, V> copyMap(Map<K, V> map) {
        if (map == null) {
            return emptyMap();
        }
        return new HashMap<>(map);
    }

}
