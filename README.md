# AndroidFlux

标签（空格分隔）： android flux greenrobot eventbus

---

关于android上的flux实现请看[android-flux-todo-app][1], 这个例子中使用的是otto eventbus，如果遇到网络请求等异步线程的话，需要自己来做这块，他的建议是放在ActionsCreator来做异步线程操作，然后再传到主线程中执行Store操作，然后再到View。这样感觉有点别扭，感觉更好的方式应该是把Store操作放在异步线程中。

[greenrobot的Eventbus][2]有个非常好的特性，就是支持Event是在那个线程中执行，比如主线程或者后台线程等。所以这里可以很方便的指定Store的执行在后台线程。

    @Override
    public void onEventAsync(Action action) {
        long id;
        switch (action.getType()) {
            case TodoActions.TODO_CREATE:
                String text = ((String) action.getData().get(TodoActions.KEY_TEXT));
                create(text);
                try {
                    Thread.currentThread().sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                emitStoreChange();
                break;
        }
    }

Store操作完之后通知View更新时指定Event在主线程中执行。

    public void onEventMainThread(UIUpdateEvent event) {
        updateUI();
    }

![Android Flux][3]


  [1]: https://github.com/lgvalle/android-flux-todo-app
  [2]: https://github.com/greenrobot/EventBus/blob/master/HOWTO.md
  [3]: https://raw.githubusercontent.com/dengyin2000/dengyin2000.github.io/master/public/images/android-flux1.png