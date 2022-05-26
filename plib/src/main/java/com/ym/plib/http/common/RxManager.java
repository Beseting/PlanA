package com.ym.plib.http.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Jabez on 2018/3/7.
 */

public class RxManager {
    private static RxManager rxManager;
    private Map<String, CompositeDisposable> map;

    private RxManager() {
        map = new HashMap<>();
    }

    public static RxManager getInstance() {
        if (rxManager == null) {
            rxManager = new RxManager();
        }
        return rxManager;
    }

    public void add(String key, Disposable disposable) {
        Set<String> keySet = map.keySet();
        if (keySet.contains(key)) {
            CompositeDisposable compositeDisposable = map.get(key);
            compositeDisposable.add(disposable);
        } else {
            CompositeDisposable compositeDisposable = new CompositeDisposable();
            compositeDisposable.add(disposable);
            map.put(key, compositeDisposable);
        }
    }

    public void cancel(String key) {
        Set<String> keySet = map.keySet();
        if (keySet.contains(key)) {
            CompositeDisposable compositeDisposable = map.get(key);
            compositeDisposable.clear();
            map.remove(key);
        }
    }

    public void cancelAll() {
        for(String key:map.keySet()){
            CompositeDisposable compositeDisposable = map.get(key);
            compositeDisposable.clear();
            map.remove(key);
        }
    }
}