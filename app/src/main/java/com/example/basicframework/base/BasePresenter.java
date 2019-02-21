package com.example.basicframework.base;
/**
 * @deprecated  主持view 和 model交互
 * 必须持有 view 的引用 和model 的 引用
 */
public abstract class BasePresenter<V> {
    //view 的引用
    protected V mView;

    public BasePresenter(V view) {
        onAttachView(view);
    }

    //绑定view
    public void onAttachView(V view){
        this.mView = view;
    }

    //销毁后解绑视图
    public void onDetachView(){
        mView = null;
    }
}
