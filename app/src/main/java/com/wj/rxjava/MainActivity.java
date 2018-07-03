package com.wj.rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    click();

    rxjava();
  }

  private void rxjava() {
    Observable.create(new ObservableOnSubscribe<Integer>() {
      @Override
      public void subscribe(ObservableEmitter<Integer> e) throws Exception {
        //testOne(e);
        testTwo(e);
      }
    }).subscribe(new Observer<Integer>() {
      @Override
      public void onSubscribe(Disposable d) {
        Log.e("RxJava", "onSubscribe");
      }

      @Override
      public void onNext(Integer value) {
        Log.e("RxJava", "onNext" + ":" + value);
      }

      @Override
      public void onError(Throwable e) {
        Log.e("RxJava", "onError");
      }

      @Override
      public void onComplete() {
        Log.e("RxJava", "onComplete");
      }
    });
  }

  private void click() {
    Button btn = (Button) findViewById(R.id.main_btn);
    EditText et = (EditText) findViewById(R.id.main_et);
    //btn.setOnClickListener(new View.OnClickListener() {
    //  @Override
    //  public void onClick(View view) {
    //    Log.e("Button", "button click:"+view.getTag());
    //  }
    //});

    //可将参数类型省略
    btn.setOnClickListener((view) -> {
      Log.e("Lambda Button", "button click lambda:" + view.getTag());
    });
    //可保留参数类型
    et.setOnFocusChangeListener((View view, boolean b) -> {
      Log.e("Lambda EditText", "focus:" + b);
    });
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        Log.e("normal Thread", "00000000");
      }
    });
    thread.start();

    Thread thread1 = new Thread(() -> {
      Log.e("Lambda Thread", "111111111");
    });
    thread1.start();

    List<String> list = new ArrayList<>();
    list.add("a");
    list.add("b");
    list.add("c");
    list.add("d");
    list.add("e");
    for (String s : list) {
      Log.e("normal List", "输出：" + s);
    }
    list.forEach(s->{
      Log.e("lambda List", "输出：" + s);
    });
  }

  /**
   * 测试1
   */
  private void testOne(ObservableEmitter<Integer> e) {
    e.onNext(1);
    e.onNext(2);
    e.onNext(3);
    e.onComplete();
  }

  /**
   * 测试2
   */
  private void testTwo(ObservableEmitter<Integer> e) {
    Log.e("RxJava", "ObservableEmitter 1");
    e.onNext(1);
    Log.e("RxJava", "ObservableEmitter 2");
    e.onNext(2);
    Log.e("RxJava", "ObservableEmitter complete");
    e.onComplete();
    Log.e("RxJava", "ObservableEmitter 3");
    e.onNext(3);
  }
}
