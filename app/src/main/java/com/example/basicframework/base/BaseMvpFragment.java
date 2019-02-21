package com.example.basicframework.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * @deprecated mvp view 层得到 对应的 presenter
 */
abstract class BaseMvpFragment<T extends BasePresenter> extends LazyLoadFragment {
    private T mPresenter;


}
