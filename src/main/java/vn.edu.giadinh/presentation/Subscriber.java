package vn.edu.giadinh.presentation;

/**
 * Subscriber interface for Observer pattern
 * Defines the contract for objects that want to be notified of changes
 */
public interface Subscriber {
    /**
     * Called when the publisher notifies subscribers of a change
     */
    void update();
}
