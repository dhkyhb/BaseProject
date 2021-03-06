# BaseProject

> 项目基础框架
> 
> MVVM+协程+Rxjava+Dagger2+Room
> 
> 新项目先拉取这个、基础库都已经导入.

## 适配
- ~~BaseActivity中的getReesource()方法已经植入适配方法、使用的时候只需要资源文件中有PT(一英寸的1/72)代替DP(dpi)、用法根据设计图宽高来适配软件~~
```
override fun getResources(): Resources {
//        宽度适配
        return AdaptScreenUtils.adaptWidth(super.getResources(), 720)
        //按高度适配
//        return AdaptScreenUtils.adaptHeight(super.getResources(), 720)
        //如果某个页面不需要适配、直接返回这个
//        return AdaptScreenUtils.closeAdapt(super.getResources())
    }
```

- AndroidManifest.xml中根据想适配的方向来适配、value值为设计图的宽高。
```
<!-- Base项目适配标准,横向适配 -->
<meta-data
android:name="design_width_in_dp"
android:value="375"/>
<!-- 如果需要纵向适配 -->
<!-- <meta-data -->
<!-- android:name="design_height_in_dp" -->
<!-- android:value="640"/> -->

```

## Core
- ~~BaseActivity/BaseFragment基础类、子类BaseVmActivity中持有BaseViewModel对象、BaseViewmodel中持有SavedStateHandle对象(用于由于资源缺失导致的activity销毁后的数据恢复)~~
- BaseVm---viewmodel---repository，BaseVm持有livedate数据对象、通过observe监听repository网络请求后的数值变化、然后通过databinding来单方面**setvalue**显示界面、如果页面数据过大、可以添加presenter来操作数据(目前Base没有、后续考虑添加)
- Application通过ActivityLifecycleCallback在每个Activity/Fragment注册Dagger2、通过Dagger2生成Viewmodel/Dao、减少代码量以及耦合关系
