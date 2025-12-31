package at.nager.date.helpers;

import at.nager.date.models.HolidaySpecification;

import java.util.List;

/**
 * Collection Helper
 * <p>
 * Provides utility methods for working with collections.
 * Translates C# extension methods to Java static methods.
 * </p>
 */
class CollectionHelper {

    /**
     * Private constructor to prevent instantiation
     */
    private CollectionHelper() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Add an item to a list if it is not null
     * <p>
     * Equivalent to C# ListExtension.AddIfNotNull
     * </p>
     *
     * @param list the list to add to
     * @param item the item to add (may be null)
     */
    static void addIfNotNull(List<HolidaySpecification> list, HolidaySpecification item) {
        if (item != null) {
            list.add(item);
        }
    }

    /**
     * Add an array of items to a list if the array is not null and not empty
     * <p>
     * Equivalent to C# ListExtension.AddRangeIfNotNull
     * </p>
     *
     * @param list the list to add to
     * @param items the array of items to add (may be null or empty)
     */
    static void addRangeIfNotNull(List<HolidaySpecification> list, HolidaySpecification[] items) {
        if (items == null || items.length == 0) {
            return;
        }

        for (HolidaySpecification item : items) {
            list.add(item);
        }
    }
}
