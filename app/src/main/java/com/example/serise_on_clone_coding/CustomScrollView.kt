package com.example.serise_on_clone_coding

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewTreeObserver
import android.widget.ScrollView

class NewScrollView : ScrollView, ViewTreeObserver.OnGlobalLayoutListener {
    constructor(context: Context) : this(context, null, 0)
    constructor(context: Context, attr: AttributeSet?) : this(context, attr, 0)
    constructor(context: Context, attr: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attr,
        defStyleAttr
    ) {
        overScrollMode = OVER_SCROLL_IF_CONTENT_SCROLLS
        viewTreeObserver.addOnGlobalLayoutListener(this)
    }

    var header: View? = null
        set(value) {
            field = value
            field?.let {
                it.translationZ = 1f
                it.setOnClickListener { _ ->
                    //클릭 시, 헤더뷰가 최상단으로 오게 스크롤 이동
                    this.smoothScrollTo(scrollX, it.top)
                    callStickListener()
                }
            }
        }

    var stickListener: (View) -> Unit = {}
    var freeListener: (View) -> Unit = {}
    var scrollStartListener: () -> Unit = {}
    var initialPositionListener: () -> Unit = {}
    var isStartedScroll: () -> Unit = {}
    var isMovieListListener: () -> Unit = {}
    var isNotMovieListListener: () -> Unit = {}

    private var mIsHeaderSticky = false

    private var mHeaderInitPosition = 0f
    private var isScrolling = false
    private var isInitalPosition = true
    private var isMovieList = false
    override fun onGlobalLayout() {
        mHeaderInitPosition = header?.top?.toFloat() ?: 0f
    }

    override fun onScrollChanged(l: Int, t: Int, oldl: Int, oldt: Int) {
        super.onScrollChanged(l, t, oldl, oldt)

        if (!isScrolling) {
            isScrolling = true
            scrollStartListener()
        }

        val scrolly = t - 120
        Log.i("scrolly", scrolly.toString())

        if (scrolly > mHeaderInitPosition) {
            stickHeader(scrolly - mHeaderInitPosition)
        } else {
            freeHeader()
        }

        if (isInitalPosition) {
            isStartedScroll()
            isInitalPosition = false
        }

        if (scrolly <= -120) {
            initialPositionListener()
            isInitalPosition = true
        }

        // 영화 리스트에 접근했는지 체크하는 코드
        if(!isMovieList) {
            if (scrolly >= 1050) {
                isMovieList = true
                isMovieListListener()
            }
        }
        if (isMovieList) {
            if(scrolly <= 1050) {
                isMovieList = false
                isNotMovieListListener()
            }
        }
    }

    private fun stickHeader(position: Float) {
        header?.translationY = position
        callStickListener()
    }

    private fun callStickListener() {
        if (!mIsHeaderSticky) {
            stickListener(header ?: return)
            mIsHeaderSticky = true
        }
    }

    private fun freeHeader() {
        header?.translationY = 0f
        callFreeListener()
    }

    private fun callFreeListener() {
        if (mIsHeaderSticky) {
            freeListener(header ?: return)
            mIsHeaderSticky = false
        }
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        viewTreeObserver.removeOnGlobalLayoutListener(this)
    }

}