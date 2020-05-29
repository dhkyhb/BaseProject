package znyoo.name.netlibrary.retrofit;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;

import io.reactivex.Observable;
import znyoo.name.base.base.BaseActivity;
import znyoo.name.base.exception.AppException;


/**
 * @author wangdh
 * @time 2019/5/15 16:13
 * @describe 1.本类 主要是对 返回数据进行校验后传递给回调函数、
 */
public class XH_RXOnline extends StandardRXOnline {

    public static XH_RXOnline getInstance() {
        return InstanceHolder.getInstance();
    }

    public <T> void connectFuc(Observable<? extends HttpResponse<T>> obs, OnlineListener<HttpResponse<T>> listener) {

        OnlineObserver<HttpResponse<T>> disposableObserver = new OnlineObserver<HttpResponse<T>>() {
            @Override
            protected void onStart() {
                super.onStart();
                LogUtils.e("onStart：" + Thread.currentThread().getName());
                ((BaseActivity)XH_RXOnline.this.onlineContext.context).showLoading();
            }

            @Override
            public void onNext(HttpResponse<T> bean) {
                LogUtils.e("onNext：" + Thread.currentThread().getName());
                try {
                    if (this.listener != null) {
                        check(bean);
                        listener.succeed(bean, this.context);
                    }
                } catch (AppException e) {
                    LogUtils.e("onNext" + e.getMessage());
                }
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                ((BaseActivity)XH_RXOnline.this.onlineContext.context).hideLoading();
                LogUtils.e("onError" + e.getMessage());
                LogUtils.e("onError：" + Thread.currentThread().getName());
                if (this.listener != null) {
                    AppException appException = new AppException(e);
                    listener.fail(appException.getErrorCode(), appException);
                }
            }

            @Override
            public void onComplete() {
                ((BaseActivity)XH_RXOnline.this.onlineContext.context).hideLoading();
                LogUtils.e("onComplete：" + Thread.currentThread().getName());
            }

        };
        disposableObserver.setContext(null);
        disposableObserver.setListener(listener);

        this.connect(obs, disposableObserver);
    }

    /**
     * 校验
     *
     * @param bean
     * @throws AppException
     */
    public void check(HttpResponse bean) throws AppException {
        if (bean == null) {
            throw new AppException("1", "数据不完整");
        }

        if (!"000000".equals(bean.getCode())) {
            String reason = bean.getMessage();
            throw new AppException(bean.getCode(), reason);
        }
        //......
    }

    //    内部类单例 线程安全(虽然用不上)
    public static class InstanceHolder {
        private static XH_RXOnline instance = new XH_RXOnline();

        private InstanceHolder() {
            System.out.println("InstanceHolder has loaded");
        }

        public static XH_RXOnline getInstance() {
            instance.setOnlineConfig(null);
            return instance;
        }
    }

}
