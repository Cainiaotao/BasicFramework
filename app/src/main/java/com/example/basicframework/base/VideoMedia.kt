package com.example.basicframework.base

class VideoMedia {
    var id:String = ""
    var path:String = ""
    var size:String = ""
    var duration:Long = 0L
    var resolution:String = ""
    var name:String = ""
    var date:Long = 0L

    constructor(
        id: String,
        path: String,
        size: String,
        duration: Long,
        resolution: String,
        name: String,
        date: Long
    ) {
        this.id = id
        this.path = path
        this.size = size
        this.duration = duration
        this.resolution = resolution
        this.name = name
        this.date = date
    }
}