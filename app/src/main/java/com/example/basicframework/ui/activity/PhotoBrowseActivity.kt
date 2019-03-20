package com.example.basicframework.ui.activity

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v4.app.ActivityOptionsCompat
import android.support.v4.app.SharedElementCallback
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewCompat
import android.support.v4.view.ViewPager
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.basicframework.R
import com.example.basicframework.base.BaseActivity
import com.example.basicframework.ui.widget.BaseAnimCloseViewPager
import java.util.ArrayList

class PhotoBrowseActivity: BaseActivity() {

    private var firstDisplayImageIndex = 0
    private var newPageSelected = false
    private var mCurImage: ImageView? = null
    private var imageViewPager: BaseAnimCloseViewPager? = null
    private var pictureList: ArrayList<String>? = null

    private var adapter:PagerAdapter?=null

    private var canDrag:Boolean = false

    override fun beforeSetContentView() {
        super.beforeSetContentView()
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun setContentView(): Int = R.layout.activity_photo_browse


    fun startWithElement(context: Activity, urls: ArrayList<String>, firstIndex: Int, view: View?) {
        val intent = Intent(context, PhotoBrowseActivity::class.java)
        intent.putStringArrayListExtra("urls", urls)
        intent.putExtra("index", firstIndex)
        var compat: ActivityOptionsCompat? = null
        if (view == null) {
            compat = ActivityOptionsCompat.makeSceneTransitionAnimation(context)
        } else {
            compat = ActivityOptionsCompat.makeSceneTransitionAnimation(context, view,"tansition_view" )
        }
        ActivityCompat.startActivity(context, intent, compat.toBundle())
    }

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        pictureList = intent.getStringArrayListExtra("urls")
        if (pictureList!=null && pictureList!!.size>0){
            firstDisplayImageIndex = Math.min(intent.getIntExtra("index", firstDisplayImageIndex), pictureList!!.size)
        }
        imageViewPager = findViewById(R.id.viewpager)
        setViewPagerAdapter()
        setEnterSharedElementCallback(object : SharedElementCallback(){
            override fun onMapSharedElements(names: MutableList<String>?, sharedElements: MutableMap<String, View>?) {
                val layout = imageViewPager?.findViewWithTag(imageViewPager?.currentItem) as ViewGroup ?: return
                val sharedView = layout.findViewById<ImageView>(R.id.image_view)
                sharedElements?.clear()
                sharedElements?.put("tansition_view",sharedView)
            }
        })
    }

    private fun setViewPagerAdapter(){
        adapter = object : PagerAdapter() {
            override fun getCount(): Int {
                return if (pictureList == null) 0 else pictureList!!.size
            }

            override fun notifyDataSetChanged() {
                super.notifyDataSetChanged()
            }

            override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
                val layout = `object` as View
                container.removeView(layout)
            }

            override fun isViewFromObject(view: View, `object`: Any): Boolean {
                return view === `object`
            }

            override fun instantiateItem(container: ViewGroup, position: Int): Any {
                val layout: View
                layout = LayoutInflater.from(this@PhotoBrowseActivity).inflate(R.layout.layout_browse, null)
                //                layout.setOnClickListener(onClickListener);
                container.addView(layout)
                layout.tag = position

                if (position == firstDisplayImageIndex) {
                    onViewPagerSelected(position)
                }

                return layout

            }

            override fun getItemPosition(`object`: Any): Int {
                return PagerAdapter.POSITION_NONE
            }
        }

        imageViewPager?.adapter = adapter
        imageViewPager?.offscreenPageLimit = 1
        imageViewPager?.currentItem = firstDisplayImageIndex
        imageViewPager?.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
                if (positionOffset == 0f && newPageSelected) {
                    newPageSelected = false
                    onViewPagerSelected(position)
                }
            }

            override fun onPageSelected(position: Int) {
                newPageSelected = true
            }

            override fun onPageScrollStateChanged(state: Int) {

            }
        })
        imageViewPager?.setiAnimClose(object : BaseAnimCloseViewPager.IAnimClose {
            override fun canDrag(): Boolean {
                return canDrag
            }

            override fun onPictureClick() {
                finishAfterTransition()
            }

            override fun onPictureRelease(view: View?) {
                finishAfterTransition()
            }
        })
    }


    private fun onViewPagerSelected(position: Int) {
        updateCurrentImageView(position)
        setImageView(pictureList!!.get(position))
    }


    /**
     * 设置图片
     *
     * @param path
     */
    private fun setImageView(path: String) {
        if (mCurImage!!.getDrawable() != null)
        //判断是否已经加载了图片，避免闪动
            return
        if (TextUtils.isEmpty(path)) {
            mCurImage!!.setBackgroundColor(Color.GRAY)
            return
        }
        canDrag = false
        Glide.with(this).load(path).into(mCurImage!!)
    }

    // 初始化每个view的image
    protected fun updateCurrentImageView(position: Int) {
        val currentLayout = imageViewPager?.findViewWithTag<ViewGroup>(position)
        if (currentLayout == null) {
            ViewCompat.postOnAnimation(imageViewPager!!, Runnable { updateCurrentImageView(position) })
            return
        }
        mCurImage = currentLayout.findViewById(R.id.image_view)
        imageViewPager!!.setCurrentShowView(mCurImage)
    }

    override fun finishAfterTransition() {
        val intent = Intent()
        intent.putExtra("index", imageViewPager?.currentItem)
        setResult(Activity.RESULT_OK, intent)
        super.finishAfterTransition()
    }
}