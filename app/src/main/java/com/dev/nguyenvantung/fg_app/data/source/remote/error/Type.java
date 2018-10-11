package com.dev.nguyenvantung.fg_app.data.source.remote.error;

import android.support.annotation.StringDef;

@StringDef({Type.NETWORK, Type.HTTP, Type.UNEXPECTED, Type.SERVER})
public @interface Type {

    String NETWORK = "NETWORK";

    String HTTP = "HTTP";

    String SERVER = "SERVER";

    String UNEXPECTED = "UNEXPECTED";
}
