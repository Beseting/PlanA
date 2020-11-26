# PlanA Android快速开发基础架构平台

![PlanA.jpg](https://upload-images.jianshu.io/upload_images/4768944-c63c7a20e42ef71b.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

PlanA是一款集成BaseActivity，BaseFragment，BasePermission，PRouter路由管理，多种开发所需工具类，Retrofit2+Okhttp+RxJava的网络请求终极封装（一行代码实现网络请求），文件上传下载及进度回调，多种开发所需的自定义控件等，PlanA框架正在火热持续维护中，请多多支持！

[![](https://jitpack.io/v/Beseting/PlanA.svg)](https://jitpack.io/#Beseting/PlanA)

## 1.项目依赖（Gradle）
    1.添加jitPack到你项目的build.gradle

    allprojects {
        repositories {
            ...
            maven { url 'https://jitpack.io' }
		}
	}

    2.添加依赖

    dependencies {
	   implementation 'com.github.Beseting:PlanA:v1.2.6'
	}

## 2.混淆

  请查看app/proguard-rules.pro 混淆文件

## 3.集成
   文档地址：https://www.jianshu.com/p/02e2ce2a7414
## 4.联系作者
   简书：https://www.jianshu.com/u/ba1cc3214081
   CSDN：https://blog.csdn.net/zjb12316