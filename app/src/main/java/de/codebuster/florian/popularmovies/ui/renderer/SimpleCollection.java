package de.codebuster.florian.popularmovies.ui.renderer;

import android.os.Parcelable;

import com.pedrogomez.renderers.AdapteeCollection;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public abstract class SimpleCollection<T> implements AdapteeCollection<T>, Parcelable {

    protected List<T> list;

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public void add(T object) {
        list.add(object);
    }

    @Override
    public void remove(T object) {
        list.remove(object);
    }

    @Override
    public void addAll(Collection<T> collection) {
        this.list.addAll(collection);
    }

    @Override
    public void removeAll(Collection<T> collection) {
        this.list.removeAll(collection);
    }

    public void clear() {
        list.clear();
    }

    public List<T> getAsList() {
        return (List<T>) ((LinkedList<T>) list).clone();
    }
}
