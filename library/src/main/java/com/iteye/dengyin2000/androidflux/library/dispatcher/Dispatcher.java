package com.iteye.dengyin2000.androidflux.library.dispatcher;

import com.iteye.dengyin2000.androidflux.library.action.StoreAction;
import com.iteye.dengyin2000.androidflux.library.action.UIUpdateAction;

import de.greenrobot.event.EventBus;

/**
 * Created by denny on 2016/1/7.
 */
public class Dispatcher {
    private EventBus bus;

    public Dispatcher(EventBus bus) {
        this.bus = bus;
    }

    public void register(final Object cls) {
        bus.register(cls);

    }

    public void unregister(final Object cls) {
        bus.unregister(cls);
    }

    /**
     * dispatch the action of store type, Store will receive this action.
     *
     * @param type  action name
     * @param data  data information
     */
    public void dispatchStoreAction(String type, Object... data) {
        if (isEmpty(type)) {
            throw new IllegalArgumentException("Type must not be empty");
        }

        if (data.length % 2 != 0) {
            throw new IllegalArgumentException("Data must be a valid list of key,value pairs");
        }

        StoreAction.Builder actionBuilder = StoreAction.type(type);
        int i = 0;
        while (i < data.length) {
            String key = (String) data[i++];
            Object value = data[i++];
            actionBuilder.bundle(key, value);
        }
        post(actionBuilder.build());
    }

    /**
     *  dispatch the action of ui update action, View will receive this action, View will do ui
     *  update.
     *
     * @param type action name
     * @param data data information
     */
    public void dispatchUIAction(String type, Object... data) {
        if (isEmpty(type)) {
            throw new IllegalArgumentException("Type must not be empty");
        }

        if (data.length % 2 != 0) {
            throw new IllegalArgumentException("Data must be a valid list of key,value pairs");
        }

        UIUpdateAction.Builder actionBuilder = UIUpdateAction.type(type);
        int i = 0;
        while (i < data.length) {
            String key = (String) data[i++];
            Object value = data[i++];
            actionBuilder.bundle(key, value);
        }
        post(actionBuilder.build());
    }

    private boolean isEmpty(String type) {
        return type == null || type.isEmpty();
    }

    private void post(final Object event) {
        bus.post(event);
    }
}
