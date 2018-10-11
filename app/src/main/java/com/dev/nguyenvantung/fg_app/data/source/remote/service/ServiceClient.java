package com.dev.nguyenvantung.fg_app.data.source.remote.service;

import android.content.Context;
import android.util.Log;

import com.dev.nguyenvantung.fg_app.BuildConfig;
import com.dev.nguyenvantung.fg_app.data.source.remote.adapter.BooleanAdapter;
import com.dev.nguyenvantung.fg_app.data.source.remote.adapter.IntegerAdapter;
import com.dev.nguyenvantung.fg_app.data.source.remote.adapter.SingleToArrayTypeAdapter;
import com.dev.nguyenvantung.fg_app.data.source.remote.middleware.RxErrorHandlingCallAdapterFactory;
import com.dev.nguyenvantung.fg_app.utils.AppConstants;
import com.dev.nguyenvantung.fg_app.utils.AppPref;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.annotations.NonNull;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceClient {
    private static final int CONNECTION_TIMEOUT = 60;

    public static <T> T createService(Context context, String endPoint, Class<T> serviceClass) {
        return createService(context, endPoint, serviceClass, getGsonConfig(), null);
    }

    public static <T> T createService(Context context, String endPoint, Class<T> serviceClass, Gson gson) {
        return createService(context, endPoint, serviceClass, gson, null);
    }

    static <T> T createService(final Context context, String endPoint, Class<T> serviceClass,
                               @NonNull Gson gson, Interceptor interceptor) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        httpClientBuilder.cache(
                new Cache(context.getApplicationContext().getCacheDir(), cacheSize));
        if (interceptor != null) {
            httpClientBuilder.addInterceptor(interceptor)
                    .addNetworkInterceptor(new Interceptor() {
                        @Override
                        public Response intercept(Chain chain) throws IOException {
                            Request request;

                            Request original = chain.request();
                            Request.Builder requestBuilder = original.newBuilder();
//                            requestBuilder.addHeader(AppConstants.ACCEPT, AppConstants.VALUE_ACCEPT)
//                                    .addHeader(AppConstants.CONTENT_TYPE, AppConstants.VALUE_CONTENT_TYPE);
                            //authorizationValue = AppPref.getString("authorizationValue");
                            if (!AppPref.getInstance(context).getApiToken().equals("")) {
                                Log.d("--Authorization-- ", AppPref.getInstance(context).getApiToken());
                                // Request customization: add request headers
                                requestBuilder.addHeader(AppConstants.AUTHORIZATION, AppConstants.BEARER + AppPref.getInstance(context).getApiToken());
                            }
                            request = requestBuilder.build();
                            return chain.proceed(request);
                        }
                    });
        }
        httpClientBuilder.readTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        httpClientBuilder.connectTimeout(CONNECTION_TIMEOUT, TimeUnit.SECONDS);
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(endPoint)
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson));

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            httpClientBuilder.addInterceptor(logging);
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        }

        Retrofit retrofit = builder.client(httpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        return retrofit.create(serviceClass);
    }

    static Gson getGsonConfig() {
        BooleanAdapter booleanAdapter = new BooleanAdapter();
        IntegerAdapter integerAdapter = new IntegerAdapter();
        return new GsonBuilder().registerTypeAdapter(Boolean.class, booleanAdapter)
                .registerTypeAdapter(boolean.class, booleanAdapter)
                .registerTypeAdapter(Integer.class, integerAdapter)
                .registerTypeAdapter(int.class, integerAdapter)
                .registerTypeAdapterFactory(SingleToArrayTypeAdapter.FACTORY)
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithoutExposeAnnotation()
                .create();
    }
}
