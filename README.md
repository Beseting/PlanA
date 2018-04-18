# PlanA Android平台资源整合架构体系
[![](https://jitpack.io/v/Beseting/PlanA.svg)](https://jitpack.io/#Beseting/PlanA)

## 添加依赖（Gradle）
    1.添加jitPack到你项目的build.gradle

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
		}
	}

    2.添加依赖

    dependencies {
	   compile 'com.github.Beseting:PlanA:v1.0.18'
	}

## 混淆

  * 请查看app/proguard-rules.pro 混淆文件

## 1.项目开启dataBinding，在build.gradle中添加
    android{
        ...
        dataBinding {
            enabled = true
        }
    }
## 2.BaseActivity（继承）
  * 开启菊花加载模态窗 showDialog();

  * 关闭菊花加载模态窗 closeDialog();

  * TitleBar仅支持根布局为线性布局和相对布局，且相对布局时需要设置正文的上边距（50dp，TitleBar的高度）

  * 隐藏标题栏 hideTitleBar();

  * 隐藏返回icon hideEsc();

  * 设置标题 setTitle(Object title); title参数为String或int（String引用）

  * 标题栏显示更多icon showMore(OnTitleBarListener onTitleBarListener); 参数为点击回调接口，位于BaseActivity

  * 替换标题栏更多icon replaceMoreIcon(int iconId); 参数为新icon的id引用

  * 设置标题栏背景颜色 setTitleBarBg(color); 参数为int颜色值

  * 设置返回icon setEscIcon(color);

  * 设置标题颜色 setTitleTextColor(color);

  * 设置标题字体大小 setTitleTextSize(textSizeSP);

  * 获取返回icon getEscIcon();

  * 获取标题控件 getTitleView();

  * 获取更多icon getMoreIcon();

  * 获取标题栏 getRlTitleBar();

  * 获取ViewDataBinding getViewDataBinding(); 获取的实例为ViewDataBinding 需要强转

  * setIsShowStatusBar(isShow); 是否显示状态栏，true：恢复状态栏位置并默认背景色，false：隐藏状态栏并使布局顶上去

  * setStatusBarColor(color); 设置状态栏颜色

  * setStatusBarColor(color,alpha); 设置状态栏颜色，alpha为透明度

  * 6.0+权限请求，requestPermission(permissions,code) 所请求的权限别忘了在Manifest里注册

  * 权限请求回调，权限请求成功回调接口：permissionSucceed  权限请求失败回调接口：permissionFailing

## 3.路由管理：
  * 界面路由（不带参）：PRouter.getInstance().navigation(context,targetActivity)

  * 界面路由（链式带参）：PRouter.getInstance().withInt(key,value).withSting(key,value)....navigation(context,targetActivity)

  * 路由跳转：navigation(context,targetActivity) 或者 navigation(activity,targetActivity,isFinish) 后者多了个是否结束当前Activity的参数

  * 路由跳转：navigation(activity,targetActivity,requestCode) 即相当于startActivityForResult附带Activity结果回调

  * 路由参数获取：PRouter.getInt(key)  PRouter.getString(key)  PRouter.getLong(key)...

  * PS:调用路由单例PRouter.getInstance()的时候会初始化之前带过的参数，所以在PRouter.getInstance()后再调路由参数获取是拿不到参数的（因为这个时候的参数已经被单例初始化了）

## 4.Activity堆栈：
  * 继承BaseActivity后会在OnCreate函数自动调用Activity入栈方法，这里就不需要重复对Activity进行入栈了

  * Activity入栈：ActivityStack.getInstance().pushActivity(activity);

  * Activity弹栈：ActivityStack.getInstance().popActivity("activityName")  ActivityStack.getInstance().popActivity(targetActivity)

  * 清栈（销毁所有Activity）：ActivityStack.getInstance().clearAllActivity();

## 5.BaseFragment（继承）
  * 与继承BaseActivity类似，不包含TitleBar相关操作

## 6.工具类
  * ImageHelper，图片助手工具

    * ImageHelper.CompressionImage(filePath)，压缩图片，返回Bitmap

  * LogUtils，用于Log日志打印

    * debug:LogUtils.d(tag,msg)&nbsp;&nbsp;&nbsp;LogUtils.d(msg)默认debug tag

    * info:LogUtils.i(tag,msg)&nbsp;&nbsp;&nbsp;LogUtils.i(msg)默认info tag

    * error:LogUtils.e(tag,msg)&nbsp;&nbsp;&nbsp;LogUtils.e(msg)默认error tag

  * MeasureUnitTranUtil，度量单位转换工具

    * MeasureUnitTranUtil.dip2px(context,dpValue) dp转px

    * MeasureUnitTranUtil.px2dip(context,pxValue) px转dp

  * ToastUtils 吐司工具

    * ToastUtils.showShort(context,stringId)&nbsp;&nbsp;&nbsp;ToastUtils.showShort(context,msgStr) 短时间显示吐司

    * ToastUtils.showLong(context,stringId)&nbsp;&nbsp;&nbsp;ToastUtils.showLong(context,msgStr) 长时间显示吐

    * ToastUtils.show(context,stringId,during)&nbsp;&nbsp;&nbsp;ToastUtils.show(context,msgStr,during) 自定义显示时长

  * AlertUtils v7style的AlertDialog

    * AlertUtils.showAlert(context, title, content, confirmOnClickListener) 参数分别为 上下文、标题、内容、确定点击回调

  * DateUtils 日期工具

    * DateUtils.getDateStr() 获取日期  2016-10-01 12:02:12

    * DateUtils.getTimeInMillis() 获取当前时间戳

    * DateUtils.getCurrentYear() 获取当前年份

    * DateUtils.getCurrentMonth() 获取当前月份

    * DateUtils.getCurrentDate() 获取当前日期号数

    * DateUtils.getCurrentDay() 获取当前星期几

  * SPUtils SharedPreferences存储工具类

    * SPUtils.setParam(context,key,value) 键值对方式存储数据

    * SPUtils.getParam(context,key,defaultValue) 获取key对应的数据

    * SPUtils.clear(context) 清除存储的数据

## 7.CrashReportHelper 崩溃日志助手类

    SDK APPID申请及崩溃日志：https://bugly.qq.com

    ndk配置：在主模块的build.gradle中配置

        ndk {
            // 设置支持的SO库架构
            abiFilters 'armeabi', 'x86', 'armeabi-v7a', 'x86_64', 'arm64-v8a'
        }

    //依赖
    dependencies {

        ...

        // 腾讯CrashReport
        implementation 'com.tencent.bugly:crashreport:2.6.6'
        implementation 'com.tencent.bugly:nativecrashreport:3.3.1'
    }

    // 初始化Bugly,请在Application类的onCreate方法调用
    String processName = AppUtils.getProcessName(android.os.Process.myPid());// 获取当前进程名
    CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(getApplicationContext());// 设置是否为上报进程
    strategy.setUploadProcess(processName == null || processName.equals(getApplicationContext().getPackageName()));
    CrashReport.initCrashReport(getApplicationContext(), appId, isDebug, strategy);

## 8.Retrofit 网络请求

  * 在Application的onCreate中初始化BASE_URL

        RetrofitClient.initBaseUrl(BASE_URL);

  * 请求参数

        RequestParams.getInstance().addParam(key,value).addParam(key,value)...;//链式添加参数

        RequestParams.paramMap //参数的map对象

        demo：添加参数为id=1，name=Kevin

        RequestParams.getInstance().addParam("id",1).addParam("name","Kevin");

        RequestParams.paramMap //这个对象返回的就是id=1，name=Kevin的map对象

  * IBaseBiz 业务接口基类

    * IBaseBiz 用到网络请求的地方就可以在Activity里实现，主要有以下两个实现方法

    * getActivity() 获取Activity对象

    * toActivity() 到UI执行的方法

  * Data / ResponseBody 请求成功返回类型

        Data:

        {
            status : 1,
            message : "请求成功",
            data : Object
        }

        ResponseBody : 将会直接返回解析好的请求字符串

  * ICommonHttpCallback 请求回调接口

    * onResponse(requestCode,data); 回调成功，不区分status全返回

    * onSuccess(requestCode,data); 回调正确状态的data数据 status:1

    * onException(requestCode,data); 回调异常状态data数据 status!=1

    * onError(requestCode,e); 回调错误方法，诸如404,500,timeout等

    * requestCode 为请求码，主要用于多个请求并行的时候，在请求成功回调接口里面区分请求对应的响应

    * data 请求返回的数据

    * e 请求失败的Throwable

  * GET请求

        RetrofitClient.getInstance().doGet(url,iBaseBiz,iCommonHttpCallback);//无参，无requestCode

        RetrofitClient.getInstance().doGet(url,params,iBaseBiz,iCommonHttpCallback);//有参，无requestCode

        RetrofitClient.getInstance().doGet(url,requestCode,iBaseBiz,iCommonHttpCallback);//无参，有requestCode

        RetrofitClient.getInstance().doGet(url,requestCode, params,iBaseBiz,iCommonHttpCallback);//有参，有requestCode

        commonHttpCallback附带的泛型有Data与ResponseBody，主要是看请求成功回调方法给数据类型

  * POST请求

        RetrofitClient.getInstance().doPost(url,iBaseBiz,iCommonHttpCallback);//无参，无requestCode

        RetrofitClient.getInstance().doPost(url,params,iBaseBiz,iCommonHttpCallback);//有参，无requestCode

        RetrofitClient.getInstance().doPost(url,requestCode,iBaseBiz,iCommonHttpCallback);//无参，有requestCode

        RetrofitClient.getInstance().doPost(url,requestCode,params,iBaseBiz,iCommonHttpCallback);//有参，有requestCode

        commonHttpCallback附带的泛型有Data与ResponseBody，主要是看请求成功回调方法给数据类型

  * 文件上传

        RetrofitClient.getInstance().uploadFile(url,iBaseBiz,iCommonHttpCallback,file);//无参，无requestCode，单个文件上传

        RetrofitClient.getInstance().uploadFile(url,params,iBaseBiz,iCommonHttpCallback,file);//有参，无requestCode，单个文件上传

        RetrofitClient.getInstance().uploadFile(url,requestCode,iBaseBiz,iCommonHttpCallback,file);//无参，有requestCode，单个文件上传

        RetrofitClient.getInstance().uploadFile(url,requestCode,params,iBaseBiz,iCommonHttpCallback,file);//有参，有requestCode，单个文件上传

        RetrofitClient.getInstance().uploadFiles(url,iBaseBiz,iCommonHttpCallback,fileMaps);//无参，无requestCode，多个文件上传

        RetrofitClient.getInstance().uploadFiles(url,params,iBaseBiz,iCommonHttpCallback,fileMaps);//有参，无requestCode，多个文件上传

        RetrofitClient.getInstance().uploadFiles(url,requestCode,iBaseBiz,iCommonHttpCallback,fileMaps);//无参，有requestCode，多个文件上传

        RetrofitClient.getInstance().uploadFiles(url,requestCode,params,iBaseBiz,iCommonHttpCallback,fileMaps);//有参，有requestCode，多个文件上传

        commonHttpCallback附带的泛型有Data与ResponseBody，主要是看请求成功回调方法给数据类型

  * 取消网络请求

        RetrofitClient.getInstance().cancelRequest(taskId);//所有RetrofitClient.getInstance().xxx()的网络请求返回的都是taskId

  * 文件下载

        FileDownloadHelper.download(context, url, path, fileDownloadCallback);//文件下载助手

        接口回调FileDownloadCallback，有以下两个方法

        onProgress(int progress, int total, double percent);//progress进度，total总大小，percent百分比

        completed(File file);//完成下载，返回下载完成的file对象

## 9.自定义控件

  * BounceScrollView：仿IOS下拉、上拉反弹效果，继承ScrollView的自定义控件

  * GridViewWithBorder：附带边框的GridView自定义控件

  * NoScrollGridView：固定高度（不滚动）的自定义GridView，适合滑动嵌套

  * NoScrollListView：固定高度（不滚动）的自定义ListView，适合滑动嵌套

  * CircleImageView：圆形图片

        app:border_width:边框宽度

        app:border_color:边框颜色

  * RatingBar：自定义打分控件

        starCount：总分

        starEmpty：默认显示的图

        starFill：显示满的图

        starHalf：显示一半的图

        starImageSize：图片尺寸

        starPadding：内边距

        starStep：显示数量，支持小数点

        stepSize：每次点击所增加的量是整个还是半个

        mClickable：是否可点击

        onRatingChangeListener：点击改变事件

## 10.图片浏览

  * 单张网络图片

        ImageBrowseIntent.showUrlImageBrowse(mContext,"...");

  * 多张网络图片

        ArrayList<String> imageList = new ArrayList<>();
        imageList.add("...");
        ...
        ImageBrowseIntent.showUrlImageBrowse(mContext,imageList,position);

  * 单张资源图片

        ImageBrowseIntent.showResIdImageBrowse(mContext,R.mipmap.xxx);

  * 多张资源图片

        ArrayList<Integer> imageResList = new ArrayList<>();
        imageResList.add(R.mipmap.xxx);
        ...
        ImageBrowseIntent.showResIdImageBrowse(mContext,imageResList,position);