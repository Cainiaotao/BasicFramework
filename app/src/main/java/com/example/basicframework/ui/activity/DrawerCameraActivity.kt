package com.example.basicframework.ui.activity

import android.os.Bundle
import android.view.Gravity
import com.example.basicframework.R
import com.example.basicframework.base.BaseActivity
import kotlinx.android.synthetic.main.activity_camera_main.*
import kotlinx.android.synthetic.main.activity_drawer_camera.*
import android.provider.MediaStore
import android.annotation.SuppressLint
import android.content.Context
import android.database.Cursor
import android.graphics.Bitmap
import android.net.Uri
import android.os.Handler
import android.support.v7.widget.GridLayoutManager
import android.widget.ImageView
import ch.ielse.view.imagewatcher.ImageWatcher
import com.bumptech.glide.Glide
import com.example.basicframework.base.PictureMedia
import com.example.basicframework.base.VideoMedia
import com.example.basicframework.ui.adapter.recycler.PictureListAdapter
import com.example.basicframework.utils.GlideSimpleTarget
import com.example.basicframework.utils.GlideUtils
import com.example.basicframework.utils.UIUtils
import io.reactivex.Observable
import io.reactivex.ObservableOnSubscribe
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers



class DrawerCameraActivity:BaseActivity(), ImageWatcher.OnPictureLongPressListener,ImageWatcher.Loader {



    private var pictureList = ArrayList<PictureMedia>()
    private var pictureAdapter: PictureListAdapter?=null

    override fun setContentView(): Int = R.layout.activity_drawer_camera

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        imageWatcher.setTranslucentStatus(UIUtils.calcStatusBarHeight(this))
        imageWatcher.setErrorImageRes(R.mipmap.error_picture)
        imageWatcher.setLoader(this)

    }

    override fun initData() {
        super.initData()
        pictureAdapter = PictureListAdapter(this,pictureList)
        pictureAdapter?.listener = object :PictureListAdapter.OnItemClickListener{
            override fun onItemClick(view:ImageView,position: Int, imageUrl: String) {
                imageWatcher.show(view, arrayListOf(view), arrayListOf(imageUrl))
            }
        }
        recyclerView.layoutManager = GridLayoutManager (this,4,GridLayoutManager.VERTICAL,false)
        recyclerView.adapter = pictureAdapter
        loadData()
    }

    override fun initEvent() {
        super.initEvent()
        tv_menu.setOnClickListener {drawerLay.openDrawer(Gravity.LEFT) }
        imageWatcher.setOnPictureLongPressListener(this)
    }

    //获取本地图片
    @SuppressLint("Recycle")
    private fun getLocalAllPicture():ArrayList<PictureMedia>{
        val images = ArrayList<PictureMedia>()
        val mImageUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection  = arrayOf(MediaStore.Images.Media._ID
            ,MediaStore.Images.Media.DATA
            ,MediaStore.Images.Media.SIZE
            ,MediaStore.Images.Media.DISPLAY_NAME
            ,MediaStore.Images.ImageColumns.MIME_TYPE)
        val selection = MediaStore.Images.Media.MIME_TYPE +"=? or "+ MediaStore.Images.Media.MIME_TYPE +"=? or "+ MediaStore.Images.Media.MIME_TYPE +"=?"
        val selectionArgs = arrayOf("image/jpeg", "image/png", "image/jpg")
        val cursor: Cursor = contentResolver.query(mImageUri,projection,selection,selectionArgs,MediaStore.Images.Media.DATE_MODIFIED + " desc ")
            ?: return images
        while (cursor.moveToNext()){
            val imageId = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media._ID))
            val filePath = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
            val fileName = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DISPLAY_NAME))
            val fileSize = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.SIZE))
            val fileType = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.MIME_TYPE))
            images.add(PictureMedia(imageId,filePath,fileSize,fileName,fileType))
        }
        cursor.close()
        return images
    }

    @SuppressLint("CheckResult")
    private fun loadData(){
        val observable= Observable.create(ObservableOnSubscribe<ArrayList<PictureMedia>>{
            it.onNext(getLocalAllPicture())
            it.onComplete()
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        val observer =object :Observer<ArrayList<PictureMedia>>{
            override fun onComplete() {

            }

            override fun onSubscribe(d: Disposable) {

            }

            override fun onNext(t: ArrayList<PictureMedia>) {
                pictureList.clear()
                pictureList.addAll(t)
                pictureAdapter?.notifyDataSetChanged()
            }

            override fun onError(e: Throwable) {

            }

        }
        observable.subscribe(observer)
    }

    //获取本地视频
    private fun getLocalVideos():ArrayList<VideoMedia>{
        val videos = ArrayList<VideoMedia>()
        val videoURl:Uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.ImageColumns.DATA,
            MediaStore.Video.Media._ID,
            MediaStore.Video.Media.DISPLAY_NAME,
            MediaStore.Video.Media.RESOLUTION,
            MediaStore.Video.Media.SIZE,
            MediaStore.Video.Media.DURATION,
            MediaStore.Video.Media.DATE_MODIFIED)
        val selection = MediaStore.Video.Media.MIME_TYPE + "=?"
        val selectionArgs = arrayOf("video/mp4")
        val cursor: Cursor = contentResolver.query(videoURl,
            projection,selection,selectionArgs,null)
            ?: return videos
        while (cursor.moveToNext()){
            val id = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID))
            val filePath = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA))
            val fileName = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME))
            val fileSize = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Images.Media.SIZE))
            val resolution = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.RESOLUTION))
            val duration = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DURATION))
            val date = cursor.getLong(cursor.getColumnIndexOrThrow(MediaStore.Video.Media.DATE_MODIFIED))
            videos.add(VideoMedia(id,filePath,fileSize,duration,resolution,fileName,date))
        }
        cursor.close()
        return videos
    }

    override fun onPictureLongPress(v: ImageView?, url: String?, pos: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun load(context: Context?, url: String?, lc: ImageWatcher.LoadCallback?) {
        Glide.with(context!!).asBitmap().load(url).into(GlideSimpleTarget(lc))
    }

    override fun onBackPressed() {
        if (!imageWatcher.handleBackPressed()){
            super.onBackPressed()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}

