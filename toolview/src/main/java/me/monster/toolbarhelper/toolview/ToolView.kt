package me.monster.toolbarhelper.toolview

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import me.monster.toolbarhelper.toolview.nav.PopProvider
import me.monster.toolbarhelper.toolview.tools.*

/**
 * @description
 * @author: Created jiangjiwei in 2019-09-19 16:51
 */
class ToolView(context: Context, attributeSet: AttributeSet? = null) :
    BaseToolView(context, attributeSet),
    ToolViewActions {
//    var listener: ToolClickListener? = null

    private val vFakeStatus: View
    private val ivBack: ImageView
    private val tvTitle: TextView
    private val tvMenu: TextView
    private val ivMenuImg: ImageView
    private val clRoot: ConstraintLayout

//    private var navInterceptor: Boolean = false

    var initTitle = ""
    var initMenu = ""

    var menuImgVisible: Int = 0
        set(value) {
            ivMenuImg.visibility = value
            field = value
        }
    var menuTextVisible: Int = 0
        set(value) {
            tvMenu.visibility = value
            field = value
        }
    var navIconVisible: Int = 0
        set(value) {
            ivBack.visibility = value
            field = value
        }

    var navIcon: Int = notFound
        set(value) {
            if (value == notFound) {
                return
            }
            ivBack.setImageResource(value)
            field = value
        }

//    var popProvider: PopProvider? = null
//        set(value) {
//            navInterceptor = value != null
//            field = value
//        }

    var titleTextSize: Float = defaultTitleTextSize
        set(value) {
            if (value <= 0) {
                return
            }
            tvTitle.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
            field = value
        }
    var titleTextColor : Int = Color.WHITE
        set(@ColorInt value) {
            tvTitle.setTextColor(value)
            field = value
        }

    var menuTextSize: Float = defaultMenuTextSize
        set(value) {
            if (value <= 0) {
                return
            }
            tvMenu.setTextSize(TypedValue.COMPLEX_UNIT_PX, value)
            field = value
        }

    var menuTextColor: Int = Color.BLUE
        set(@ColorInt value) {
            tvMenu.setTextColor(value)
            field = value
        }

    init {
        View.inflate(context, R.layout.tool_view, this)
        clRoot = findViewById(R.id.cl_tool_root)
        vFakeStatus = findViewById(R.id.v_tool_status_fake)
        ivBack = findViewById(R.id.iv_tool_back)
        tvTitle = findViewById(R.id.tv_tool_title)
        tvMenu = findViewById(R.id.tv_tool_menu)
        ivMenuImg = findViewById(R.id.img_tool_menu)

        menuImgVisible = ivMenuImg.visibility
        menuTextVisible = ivMenuImg.visibility
        navIconVisible  = ivBack.visibility

        val typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.ToolView)
        val xmlVisible = typedArray.getInt(R.styleable.ToolView_navShow, 1)
        initTitle = typedArray.getString(R.styleable.ToolView_toolTitle) ?: ""
        titleTextSize = typedArray.getDimension(R.styleable.ToolView_titleTextSize, defaultTitleTextSize)
        titleTextColor = typedArray.getColor(R.styleable.ToolView_titleTextColor, Color.WHITE)
        initMenu = typedArray.getString(R.styleable.ToolView_menuText) ?: ""
        menuTextSize = typedArray.getDimension(R.styleable.ToolView_menuTextSize, defaultMenuTextSize)
        menuTextColor = typedArray.getColor(R.styleable.ToolView_menuTextColor, Color.BLACK)
        navIcon = typedArray.getResourceId(R.styleable.ToolView_navIcon, notFound)
        val menuIcon = typedArray.getResourceId(R.styleable.ToolView_menuImg, notFound)
        typedArray.recycle()

        configView(xmlVisible, navIcon, menuIcon)

        ivBack.setOnClickListener { navClick(ivBack) }
        tvTitle.setOnClickListener { listener?.onClick(title) }
        tvMenu.setOnClickListener { listener?.onClick(menu) }
        ivMenuImg.setOnClickListener { listener?.onClick(menuImg) }

        val fakeParams = vFakeStatus.layoutParams
        fakeParams.height = StatusBarUtils.getHeight(context)
        vFakeStatus.layoutParams = fakeParams
    }

    private fun configView(navVisibility: Int, navIcon: Int, menuIcon: Int) {
        if (navIcon == notFound) {
            ivBack.gone()
        } else {
            ivBack.visible()
            ivBack.setImageResource(navIcon)
        }
        ivBack.visibility = when (navVisibility) {
            1 -> nav_visible
            2 -> nav_invisible
            else -> nav_gone
        }
        tvTitle.text = initTitle
        setMenu(initMenu)
        setMenuImg(menuIcon)
    }

//    private fun navClick() {
//        if (navInterceptor) {
//            popProvider?.pop(ivBack)
//        } else {
//            listener?.onClick(navigation)
//        }
//    }

    override fun setTitle(title: String) {
        tvTitle.text = title
    }

    override fun setMenu(menu: String) {
        tvMenu.visible()
        ivMenuImg.gone()
        tvMenu.text = menu
    }

    override fun setMenuImg(@DrawableRes id: Int) {
        if (id == notFound) {
            ivMenuImg.gone()
            return
        }
        ivMenuImg.visible()
        tvMenu.gone()
        ivMenuImg.setImageResource(id)
    }

    override fun setBgColor(id: Int) {
        clRoot.setBackgroundColor(getColorMe(id))
    }

    override fun setBackground(background: Drawable?) {
        clRoot.background = background
    }

    override fun setBackgroundColor(color: Int) {
        clRoot.setBackgroundColor(color)
    }

    override fun setBackgroundResource(resid: Int) {
        clRoot.setBackgroundResource(resid)
    }

//    companion object {
//        const val notFound = -1
//        val defaultTitleTextSize = 20.sp()
//        val defaultMenuTextSize = 15.sp()
//
//        const val navigation = 1
//        const val title = 2
//        const val menu = 3
//        const val menuImg = 4
//
//        const val nav_gone = View.GONE
//        const val nav_visible = View.VISIBLE
//        const val nav_invisible = View.INVISIBLE
//    }
}
