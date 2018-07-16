# PlanA Android快速开发基础架构平台
![PlanA.png](https://upload-images.jianshu.io/upload_images/4768944-0be75b48e143f9ac.png?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

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
	   compile 'com.github.Beseting:PlanA:v1.1.1'
	}

## 2.混淆

  请查看app/proguard-rules.pro 混淆文件

## 3.集成
   文档地址：https://www.jianshu.com/p/02e2ce2a7414
## 4.联系作者
   简书：https://www.jianshu.com/u/ba1cc3214081

   CSDN：https://blog.csdn.net/zjb12316