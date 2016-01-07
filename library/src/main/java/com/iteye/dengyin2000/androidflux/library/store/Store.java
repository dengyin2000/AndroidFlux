package com.iteye.dengyin2000.androidflux.library.store;

import com.iteye.dengyin2000.androidflux.library.action.Action;
import com.iteye.dengyin2000.androidflux.library.dispatcher.Dispatcher;

/**
 * Created by lgvalle on 02/08/15.
 */
public abstract class Store {

    protected Dispatcher dispatcher;

    protected Store(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public abstract void onEventAsync(Action action);


}
