# ToolBar 在项目中的变迁

![](https://monster-image-backup.oss-cn-shanghai.aliyuncs.com/picgo/blog/toolbarhelpertoolBar-convert.jpg)

本文旨在分享自己在 ToolBar 使用上的偷懒，没有较多代码，只是分享一种思路。

> 这里指的 ToolBar 是泛指顶部的那个功能区域，不仅仅局限于 Android 中的 ActionBar、ToolBar 。

## ToolBar 的样式

ToolBar 应该算是在项目中使用较为广泛的一个 View 了，它主要用于展示当前页面的标题、导航按钮及可能存在的扩展功能。

下图就展示了一个具有一些基本属性的 ToolBar。

![](https://monster-image-backup.oss-cn-shanghai.aliyuncs.com/picgo/blog/toolbarhelperToolBar-Normal.png))

在 Android 中也不难实现，无非就是在布局文件声明出 ToolBar，然后在 Activity 使用它，为其设置一些内容，如果需要用到右侧的菜单，为其添加菜单即可。

不过呢，国内的应用大多没有按照这种样式去设计，我们公司也是如此。这里我们不去评判它该是怎么样的，只是来讨论如何快速做出与设计师要求相同的效果。

> QQ 的 ToolBar

![](https://monster-image-backup.oss-cn-shanghai.aliyuncs.com/picgo/blog/toolbarhelperToolbar-QQ.png)

> 小米短信的 ToolBar

![](https://monster-image-backup.oss-cn-shanghai.aliyuncs.com/picgo/blog/toolbarhelperToolBar-Mi-Message.png)

> 我司产品的 ToolBar

![](https://monster-image-backup.oss-cn-shanghai.aliyuncs.com/picgo/blog/toolbarhelperToolbar-osapling.png)

遇到这种情况，无非就是通过自定义 View，做出一个类似 ToolBar 的 View，然后放在页面的最靠近状态栏的地方。然后每个页面通过在布局文件中进行声明，再到相应的 Activity 或是 Fragment 中通过 findViewById 的形式找到再设置一些相关属性之类的。

难吗？不难，就是有点恶心，每个页面都得写一些重复代码，我是真的不想写啊。那就一起来偷个懒吧。

## 偷懒版本 V1

我不想在每个 Activity 或是 Fragment 上都写那些恶心的代码，能不能少些一点？

经过观察我发现，大部分页面的 ToolBar 都是中间是标题，左侧是返回按钮，右侧可能为文字、图片或是什么都不显示。区别最大的就是底部的内容，那么我把底部的内容全部划分给 Fragment、Toolbar 所属的区域属于承载 Fragment 的 Activity。(如下图所示)

![](https://monster-image-backup.oss-cn-shanghai.aliyuncs.com/picgo/blog/toolbarhelperToolBar-Activity-Fragment.png)

这样一来，开发者只需要在 Activity 中写，在 Activity 加载不同的 Fragment 时由 Fragment 去更新当前的标题。当用户点击了左侧或右侧的功能按键，通过查找当前 Activity 中存在的 Fragment 列表，找出当前显示的 Fragment，把点击事件传入即可。

这样一来，既能实现了 UI 效果也少些了部分代码，挺好的。到这还没完，来思考一下这种方案的优缺点。

优点是不需要在每个 Fragment 中声明并设置 ToolBar 了，在一定程度上减少了 ToolBar 声明与设置属性的次数。

不过呢这种方案的缺点也很明显，由于把 ToolBar 放在了 Activity，所以每次设置 ToolBar 的相关属性都必须经过 Activity，Activity 要保证其承载的每个 Fragment 显示不出问题，就必须做到对每个 Fragment 的情况做到兼容。比如，有个页面需要放置一张圆弧的背景图，此时这种方案做起来就会比较麻烦。

所以，整体来说，只是把粗略的把重复代码的解决掉，但是又会带来某些兼容的问题。也不算多好，所以才又有了新的偷懒版本。

## 偷懒版本 V2

自从 Android Studio 大力推行 ConstraintLayout 之后，我就一直在使用，发现了可以利用 ConstraintLayout 动态设置 View 的约束从而达到设置 ToolBar 的效果。

具体来说是这样的：

首先使用 Java 代码动态创建出 ToolBar 这个 View。紧接着为 ToolBar 设置约束条件，分别为：

- Start: Parent
- Top: Parent
- End: Parent

这样一来，就能让 ToolBar 位于整个页面的顶端位置。

不过这样有一个致命的痛，就是会覆盖原有属于顶部的 View（closeToolView），解决这个问题也很简单，利用 ConstraintLayout 为顶端 View （closeToolView）设置一个 top_toBottomOf 这个属性，这个属性值当然就是 ToolBar 了。

怎么样，这个方案是不是挺简单的，不过，稍微想一想就会发现，我们在布局文件里不就这么写的吗？有什么稀奇的。

这个方案没什么稀奇的，只是把原本属于开发者写在布局文件中的内容，放在了 Java 代码中而已。

不过稍稍变通一下，我们在页面的布局文件也就不需要写这么多的重复代码了，而且，可以创建出一个 ToolBarHelper 这个类，由这个类去完成上述的这些内容，如此一来，Activity 与 Fragment 的基类只需要去调用 ToolBarHelper 即可。

这样这个流程下来就算是完事了，不过你要是想用的话还是得清楚他的不足之处：

1. 根布局必须为 ConstraintLayout

   这个很好理解，如果不是 ConstraintLayout 这个布局的话，后续所有的约束条件都不能使用；

2. 顶部 View （closeToolView）的高度不能设置为 match_parent

   如果顶部 View（closeToolView）的高度为 match_parent 的话，那么即使设置了 top_toBottomOf 顶部 View（closeToolView）的相对位置也不会发生改变。

#### Talk is cheap show me the code.

关键的代码如下：

```kotlin
private fun addToolBar(root: View, closeToolView: View) {
    val layoutParams = ConstraintLayout.LayoutParams(root.layoutParams)
    layoutParams.width = ConstraintLayout.LayoutParams.MATCH_PARENT
    layoutParams.height = 48.toPix()
    toolView.layoutParams = layoutParams
    toolView.id = View.generateViewId()
    if (initTitle.isNotEmpty()) {
        toolView.setTitle(initTitle)
    }
    if (root is ConstraintLayout) {
        val relation = ConstraintSet()
        checkId(root)
        root.addView(toolView)
        relation.clone(root)
        relation.connect(toolView.id, ConstraintSet.START, root.id, ConstraintSet.START)
        relation.connect(toolView.id, ConstraintSet.END, root.id, ConstraintSet.END)
        relation.connect(toolView.id, ConstraintSet.TOP, root.id, ConstraintSet.TOP)
        relation.applyTo(root)

        val closeRelation = ConstraintSet()
        closeRelation.clone(root)
        closeRelation.connect(
            closeToolView.id,
            ConstraintSet.TOP,
            toolView.id,
            ConstraintSet.BOTTOM
        )
        closeRelation.applyTo(root)
    }
}
```

笔者做了一个简单的 Demo，并上传到了 [GitHub](https://github.com/CherryLover/ToolBarHelper)，这个 Demo 仅用于演示 ToolBarHelper 的使用，后续可能包含 ToolBar 的点击相应及多样化的设置，没有什么特别的功能，其中 Fragment 的导航库使用的是 AndroidX 的 Navigation。

有什么问题或是情况，可以在评论或是 issues 中留言。😉

封面图：Photo by [Amber Walker](https://unsplash.com/@artwalker?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText) on [Unsplash](https://unsplash.com/?utm_source=unsplash&utm_medium=referral&utm_content=creditCopyText)

推荐阅读：[来学一波 Navigation](https://jiangjiwei.site/post/lai-xue-yi-bo-navigation/)