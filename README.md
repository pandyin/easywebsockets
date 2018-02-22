DOWNLOAD
========

project build.gradle:
```
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

module build.gradle:
```
dependencies {
  compile 'com.github.pandyin:easywebsockets:0.9.1'
}
```


USAGE
========

create client:
```
EasyWebsockets websockets = EasyWebsockets.builder()
                .writeTimeout(10000)
                .readTimeOut(10000)
                .connectTimeout(10000)
                .pingInterval(10000)
                .timeUnit(TimeUnit.MILLISECONDS)
                .build(URL);
```

headers:
```
websockets.headers("k1", "v1",
                "k2", "v2",
                "k3", "v3",
                ...
                );
```

subscribe to in-coming messages:
```
websockets.message().subscribe(new Action1<String>() {
            @Override
            public void call(String message) {

            }
        });
```

subscribe to connection states:
```
websockets.connectionState().subscribe(new Action1<EasyWebsocketsConnectionState>() {
            @Override
            public void call(EasyWebsocketsConnectionState connectionState) {

            }
        });
```

open, close, immediatelyClose and send:
```
websockets.open();
...

websockets.close()
...

websockets.immediatelyClose()
...

websockets.send(message)
...
```
