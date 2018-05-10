package com.yxld.yxchuangxin.ui.activity.main.presenter;

import android.support.annotation.NonNull;

import com.socks.library.KLog;
import com.yxld.yxchuangxin.data.api.HttpAPIWrapper;
import com.yxld.yxchuangxin.ui.activity.main.contract.TestContract;

import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * @author hu
 * @Package com.example.hu.myapplication.ui.activity.main
 * @Description: $description
 * @date 2017/05/18
 */
public class TestPresenter implements TestContract.TestPresenter{

    HttpAPIWrapper httpAPIWrapper;
    private final TestContract.View mView;
//    private CompositeSubscription mSubscriptions;

    @Inject
    public TestPresenter(@NonNull HttpAPIWrapper httpAPIWrapper, @NonNull TestContract.View view) {
        mView = view;
        this.httpAPIWrapper = httpAPIWrapper;
//        mSubscriptions = new CompositeSubscription();
    }
    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
//        mSubscriptions.unsubscribe();
    }

    @Override
    public void getText() {
        mView.setText("用Presenter改变了的Button");
    }

    @Override
    public void startObservable() {
        List<String> words = Arrays.asList(
                "the",
                "quick",
                "brown",
                "fox",
                "jumped",
                "over",
                "the",
                "lazy",
                "dog"
        );
        Observable.fromIterable(words)
                .flatMap(word -> Observable.fromArray(word.split("")))
                .distinct()
                .sorted()
                .zipWith(Observable.range(1, Integer.MAX_VALUE),
                        (string, count)->String.format("%2d. %s", count, string))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe((x) -> {KLog.i(x);});


//        Observable
//                .fromIterable(words)
//                .interval(1, TimeUnit.SECONDS)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<Long>() {
//                    @Override
//                    public void accept(@io.reactivex.annotations.NonNull Long aLong) throws Exception {
//                        mView.setButton(aLong + "");
//                    }
//                })


//        Observable.just("hahha", "hehe", "xixi")
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(s -> {
//                    mView.setButton(s + "");
//                    KLog.i(s);
//                });
    }
}