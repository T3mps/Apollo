package com.temprovich.inferno.signal;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Signal<T> implements Iterable<SignalListener<T>> {

    private List<SignalListener<T>> listeners = new CopyOnWriteArrayList<SignalListener<T>>();
    
    public void register(SignalListener<T> listener) {
        if (listeners.contains(listener)) return;
        listeners.add(listener);
    }
    
    public void unregister(SignalListener<T> listener) {
        listeners.remove(listener);
    }
    
    public void dispatch(T data) {
        for (SignalListener<T> listener : listeners) listener.receive(data);
    }

    @Override
    public Iterator<SignalListener<T>> iterator() {
        return listeners.iterator();
    }
}