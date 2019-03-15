package com.example.basicframework.ui.custom

interface VideoStateListener {

     fun onStateNormal()

     fun onPreparing()

     fun onStartClick()

    fun onStart()

     fun onPlaying()

    fun onPause()

     fun onProgressChanged(progress: Int)

     fun onComplete()

     fun onTouch()

     fun onStartDismissControlViewTimer()

     fun onError()

     fun onThumb()
}