# ToolBarHelper README

## 概述

ToolBarHelper 是一个用于动态设置 Android ToolBar 的第三方库，使用了 ToolBarHelper 可以让你不需要在 xml 中声明，但运行后页面上有一个 ToolBar。实现原理是通过 Java 代码创建出一个 ToolBar 之后利用 ConstraintLayout 的特性，分别为其设置左、上、右的目标约束均为父容器的约束关系。所以这个库对使用项目有一个隐形要求：**页面根节点必须是 ConstraintLayout**。当前项目**不支持非 AndroidX** 使用。

本项目处于刚起步阶段，难免存在一些问题，还请在 [issues 页面](https://github.com/CherryLover/ToolBarHelper/issues)阐述。

**当前版本**

[![](https://jitpack.io/v/CherryLover/ToolBarHelper.svg)](https://jitpack.io/#CherryLover/ToolBarHelper)

## 演示

![](https://github.com/CherryLover/ToolBarHelper/blob/master/screen/ToolBarHeler_Menu.jpg?raw=true)

更多演示请看：[GIF](https://github.com/CherryLover/ToolBarHelper/blob/master/screen/ToolBarHelper_actions.gif)

## 如何使用

**Step 1.** 在你的项目的根基目录的 build.gradle 中 repositories 的末尾配置：

```groovy
allprojects {
  repositories {
    ...
    maven { url 'https://jitpack.io' }
  }
}
```

**Step 2.** 添加依赖

```groovy
dependencies {
  implementation 'com.github.CherryLover:ToolBarHelper:lastVersion'
}
```

### 使用

#### Java

```java
ToolBarHelper toolBarHelper;

@Nullable
@Override
public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_list, container, false);
    ListView closeToolView = rootView.findViewById(R.id.lv_list_menu);
    toolBarHelper = new ToolBarHelper(rootView, closeToolView, "你的标题");

    return rootView;
}
```

以上是 ToolBarHelper 在 Fragment 中的使用，在 Activity 中使用，在 onCreate 中进行初始化即可。其中 closeToolView 为垂直方向上最接近 ToolBar 的 View。

#### API

##### ToolBarHelper

这个类是作为操作或响应 ToolView 事件的代理，也是整个框架的管理核心。

| 方法名                    | 参数类型          | 返回值类型    | 作用                                                     | 备注     |
| ------------------------- | ----------------- | ------------- | -------------------------------------------------------- | -------- |
| setPopProiver             | PopProvider       | void          | 设置返回键点击托管控制器                                 |          |
| setListener()             | ToolClickListener | void          | ToolView 上除 PlaceHolder 内元素外的所有 View 的点击回调 |          |
| setMenu                   | String            | void          | 设置右侧菜单文字                                         |          |
| setMenuImg                | Int `Res id`      | void          | 设置右侧菜单图标                                         |          |
| setTitle                  | String            | void          | 设置中间标题文字                                         |          |
| setBgColor                | Int `ColorRes`    | void          | 设置 ToolView 背景色                                     |          |
| setBackgroundColor        | Int `ColorInt`    | void          | 设置 ToolView 背景色                                     |          |
| setBackgroundRes          | Int `DrawableRes` | void          | 设置 ToolView 背景 Drawable 资源                         |          |
| setBackground             | Drawable          | void          | 设置 ToolView 背景 Drawable 对象                         |          |
| getHolderProviderRequires | 无参              | `Array<View>` | 获取创建 HolderProvider 实现类的必要参数                 | 尚未完善 |
| getDefaultHolderProvider  | 无参              | DefaultHolder | 获取默认的 HolderProvider 实例 DefaultHolder             | 尚未完善 |

##### ToolView

实际完成 View 绘制及事件产生的来源类。

##### PopProvider

返回按钮的点击事件消费类，但此类为抽象类，默认实现类有 NavPop，RiggerPop。

**NavPop**

当项目内使用的 Fragment 导航框架为 Google Jetpack 中的 Navigation 时使用。

**RiggerPop「尚未完善」**

当项目内使用的 Fragment 导航框架为 [FragmentRigger](https://github.com/JingYeoh/FragmentRigger) 时使用。

##### HolderProvider「尚未完善」

该类为 ToolView 中的特殊 View 提供管理，可设置 CheckBox 等特殊场景下放置在 ToolView 上的元素。默认实现为 DefaultHolder。

#### 建议

推荐将 ToolBarHelper 的初始化放在 BaseFragment 中。可参考 [BaseFragment.kt](https://github.com/CherryLover/ToolBarHelper/blob/master/app/src/main/java/me/monster/toolbarhelper/base/BaseFragment.kt)。

## 开源协议

