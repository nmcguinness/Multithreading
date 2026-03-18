package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Utility helper methods for working with lists.
 */
public class ListHelper {

    /**
     * Filters a list using the supplied predicate.
     * <p>
     * Every item that matches the predicate is copied into a new result list.
     * The original list is not modified.
     *
     * @param items the source list to filter
     * @param filter the rule used to decide whether an item should be included
     * @param <T> the element type stored in the list
     * @return a new list containing only items that match the predicate
     * @throws IllegalArgumentException if {@code items} or {@code filter} is {@code null}
     */
    public static <T> List<T> filterBy(List<T> items, Predicate<T> filter) {
        if (items == null)
            throw new IllegalArgumentException("items cannot be null");

        if (filter == null)
            throw new IllegalArgumentException("filter cannot be null");

        List<T> results = new ArrayList<>();

        for (T item : items) {
            if (filter.test(item))
                results.add(item);
        }

        return results;
    }
}
