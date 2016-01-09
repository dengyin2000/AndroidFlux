package com.iteye.dengyin2000.androidflux.demo.actionscreator;

import com.iteye.dengyin2000.androidflux.demo.TodoActions;
import com.iteye.dengyin2000.androidflux.demo.model.Todo;
import com.iteye.dengyin2000.androidflux.library.dispatcher.Dispatcher;

/**
 * Created by denny on 2016/1/7.
 */
public class ActionsCreator {

    private static ActionsCreator instance;
    final Dispatcher dispatcher;

    ActionsCreator(Dispatcher dispatcher) {
        this.dispatcher = dispatcher;
    }

    public static ActionsCreator get(Dispatcher dispatcher) {
        if (instance == null) {
            instance = new ActionsCreator(dispatcher);
        }
        return instance;
    }


    public void create(String text) {
        dispatcher.dispatchStoreAction(
                TodoActions.TODO_CREATE,
                TodoActions.KEY_TEXT, text
        );

    }

    public void destroy(long id) {
        dispatcher.dispatchStoreAction(
                TodoActions.TODO_DESTROY,
                TodoActions.KEY_ID, id
        );
    }

    public void undoDestroy() {
        dispatcher.dispatchStoreAction(
                TodoActions.TODO_UNDO_DESTROY
        );
    }

    public void toggleComplete(Todo todo) {
        long id = todo.getId();
        String actionType = todo.isComplete() ? TodoActions.TODO_UNDO_COMPLETE : TodoActions.TODO_COMPLETE;

        dispatcher.dispatchStoreAction(
                actionType,
                TodoActions.KEY_ID, id
        );
    }

    public void toggleCompleteAll() {
        dispatcher.dispatchStoreAction(TodoActions.TODO_TOGGLE_COMPLETE_ALL);
    }

    public void destroyCompleted() {
        dispatcher.dispatchStoreAction(TodoActions.TODO_DESTROY_COMPLETED);
    }
}
