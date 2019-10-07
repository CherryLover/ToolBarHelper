# ToolBarHelper README

## 概述（Introduction）

ToolBarHelper 是一个用于动态设置 Android ToolBar 的第三方库，使用了 ToolBarHelper 可以让你不需要在 xml 中声明，但运行后页面上有一个 ToolBar。实现原理是通过 Java 代码创建出一个 ToolBar 之后利用 ConstraintLayout 的特性，分别为其设置左、上、右的目标约束均为父容器的约束关系。所以这个库对使用项目有一个隐形要求：**页面根节点必须是 ConstraintLayout**。当前项目**不支持非 AndroidX** 使用。

本项目处于刚起步阶段，难免存在一些问题，还请在 [issues 页面](https://github.com/CherryLover/ToolBarHelper/issues)阐述。

##### 当前版本

```
[![](https://jitpack.io/v/CherryLover/ToolBarHelper.svg)](https://jitpack.io/#CherryLover/ToolBarHelper)
```

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

#### Java 代码或 Kotlin 代码中使用

#### XML 代码中使用

## TODO

- [ ] RiggerNavigation
- [ ] ToolView 的 xml 支持更多原生属性
  - [ ] background
  - [ ] backgroundRes
- [ ] 支持更多的 ToolBar

## 开源协议

