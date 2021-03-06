package znyoo.name.netlibrary.retrofit;


import znyoo.name.base.exception.AppException;

/**
 * @author wangdh
 * @time 2019/5/15 16:26
 * @describe
 */
public interface OnlineListener<T> {
    void succeed(T t, OnlineContext context);

    void fail(String errorCode, AppException exception);
}
