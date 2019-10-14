# ToolBarHelper README

## 概述（Introduction）

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

#### 建议

推荐将 ToolBarHelper 的初始化放在 BaseFragment 中。可参考 [BaseFragment.kt](https://github.com/CherryLover/ToolBarHelper/blob/master/app/src/main/java/me/monster/toolbarhelper/base/BaseFragment.kt)。

## TODO

- [ ] RiggerNavigation
- [ ] ToolView 的 xml 支持更多原生属性
- [ ] 支持更多的 ToolBar

## 开源协议

