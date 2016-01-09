package com.iteye.dengyin2000.androidflux.library.store;

import com.iteye.dengyin2000.androidflux.library.action.StoreAction;
import com.iteye.dengyin2000.androidflux.library.dispatcher.Dispatcher;

/**
 * Created by lgvalle on 02/08/15.
 */
public abstract class Store {

    protected Dispatcher dispatcher;

    protected Store(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    /**
     * received storeAction that sent from ActionsCreator and dispatchStoreAction from Dispatcher.
     *
     * @param storeAction  Eventbus event
     */
    public abstract void onEventAsync(StoreAction storeAction);


}
