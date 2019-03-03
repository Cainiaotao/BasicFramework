package com.example.basicframework.bean

class NewsBean {
    var user:UserInfo? = null //用户基本信息

    var textContent:String = "" //发布的文本内容

    var imgs:ArrayList<Int>?=ArrayList() //图片

    var voice:Boolean = false//语音是否有

    var video:Boolean = false//视频是否有

    var labels = ArrayList<String>()

    var isExpand:Boolean = false//是否展开

    constructor(user: UserInfo?, textContent: String, imgs: ArrayList<Int>?, voice: Boolean, video: Boolean) {
        this.user = user
        this.textContent = textContent
        this.imgs = imgs
        this.voice = voice
        this.video = video
    }
}