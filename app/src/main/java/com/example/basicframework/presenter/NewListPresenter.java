package com.example.basicframework.presenter;


import com.example.basicframework.base.BasePresenter;
import com.example.basicframework.presenter.view.INewListVIew;

public class NewListPresenter extends BasePresenter<INewListVIew> {

    public NewListPresenter(INewListVIew view) {
        super(view);
    }

    public void getNewsListData(String setting){

    }
}
