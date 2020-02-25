# DelayLoadSample
这是一篇Android DelayLoad 的博文中所带的例子.

文章地址：  
[Android应用启动优化:一种DelayLoad的实现和原理(上篇)](https://www.androidperformance.com/2015/11/18/Android-app-lunch-optimize-delay-load/)

[Android应用启动优化:一种DelayLoad的实现和原理(下篇)](https://www.androidperformance.com/2015/12/29/Android%E5%BA%94%E7%94%A8%E5%90%AF%E5%8A%A8%E4%BC%98%E5%8C%96-%E4%B8%80%E7%A7%8DDelayLoad%E7%9A%84%E5%AE%9E%E7%8E%B0%E5%92%8C%E5%8E%9F%E7%90%86-%E4%B8%8B%E7%AF%87/)

欢迎提出更好的思路大家一起讨论。

## 第一种写法:直接Post
```java
myHandler.post(mLoadingRunnable);
```

## 第二种写法:直接PostDelay 300ms.
```java
myHandler.postDelayed(mLoadingRunnable, DELAY_TIME);
```

## 第三种写法: 优化的 PostDelay ，两次进行 Delay Load
```java
getWindow().getDecorView().post(new Runnable() {
    @Override
    public void run() {
        myHandler.post(mLoadingRunnable);
    }
});
```

## 第四种写法（推荐）：使用 IdleHandler 进行 Delay Load
```java
// 利用 IdleHandler
MessageQueue.IdleHandler idleHandler = new MessageQueue.IdleHandler() {
    @Override
    public boolean queueIdle() {
        updateText();
        return false;
    }
};
Looper.myQueue().addIdleHandler(idleHandler);
```
