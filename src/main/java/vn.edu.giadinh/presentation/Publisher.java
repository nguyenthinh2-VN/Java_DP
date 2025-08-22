package vn.edu.giadinh.presentation;

import java.util.ArrayList;
import java.util.List;

/**
 * Publisher class for Observer pattern
 * Manages subscribers and notifies them of changes
 */
public class Publisher {
    private List<Subscriber> subscribers;

    public Publisher() {
        this.subscribers = new ArrayList<>();
    }

    /**
     * Add a subscriber to the list
     * @param subscriber The subscriber to add
     */
    public void addSubscriber(Subscriber subscriber) {
        if (subscriber != null && !subscribers.contains(subscriber)) {
            subscribers.add(subscriber);
        }
    }

    /**
     * Remove a subscriber from the list
     * @param subscriber The subscriber to remove
     */
    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    /**
     * Notify all subscribers of a change
     */
    public void notifySubscribers() {
        for (Subscriber subscriber : subscribers) {
            subscriber.update();
        }
    }

    /**
     * Get the number of subscribers
     * @return Number of subscribers
     */
    public int getSubscriberCount() {
        return subscribers.size();
    }
}
