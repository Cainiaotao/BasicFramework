package com.example.basicframework.presenter.view

/**
 * 获取视图数据回调接口
 */
interface INewListVIew {

    fun onGetNewsListSuccess(news:ArrayList<String>, tipInfo: String)

    fun onError()
}