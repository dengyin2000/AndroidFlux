package com.iteye.dengyin2000.androidflux.library.dispatcher;

import com.iteye.dengyin2000.androidflux.library.action.Action;
import com.iteye.dengyin2000.androidflux.library.action.UIUpdateEvent;

import de.greenrobot.event.EventBus;

/**
 * Created by denny on 2016/1/7.
 */
public class Dispatcher {
    private EventBus bus;
    private static Dispatcher instance;

    public static Dispatcher get(EventBus bus) {
        if (instance == null) {
            instance = new Dispatcher(bus);
        }
        return instance;
    }

    Dispatcher(EventBus bus) {
        this.bus = bus;
    }

    public void register(final Object cls) {
        bus.register(cls);

    }

    public void unregister(final Object cls) {
        bus.unregister(cls);
    }

    public void dispatch(String type, Object... data) {
        if (isEmpty(type)) {
            throw new IllegalArgumentException("Type must not be empty");
        }

        if (data.length % 2 != 0) {
            throw new IllegalArgumentException("Data must be a valid list of key,value pairs");
        }

        Action.Builder actionBuilder = Action.type(type);
        int i = 0;
        while (i < data.length) {
            String key = (String) data[i++];
            Object value = data[i++];
            actionBuilder.bundle(key, value);
        }
        post(actionBuilder.build());
    }

    public void emitChange(UIUpdateEvent o) {
        post(o);
    }

    private boolean isEmpty(String type) {
        return type == null || type.isEmpty();
    }

    private void post(final Object event) {
        bus.post(event);
    }
}
