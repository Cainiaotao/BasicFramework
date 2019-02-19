package com.example.basicframework.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

//import com.daigen.hyt.wedate.R;
//import com.tencent.mm.opensdk.modelmsg.SendAuth;
//import com.tencent.mm.opensdk.modelmsg.SendMessageToWX;
//import com.tencent.mm.opensdk.modelmsg.WXImageObject;
//import com.tencent.mm.opensdk.modelmsg.WXMediaMessage;
//import com.tencent.mm.opensdk.modelmsg.WXMiniProgramObject;
//import com.tencent.mm.opensdk.modelmsg.WXTextObject;
//import com.tencent.mm.opensdk.modelmsg.WXWebpageObject;
//import com.tencent.mm.opensdk.openapi.IWXAPI;
//import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.io.ByteArrayOutputStream;


public class WXShare {

//    public static final String APP_ID = "wx6d6cbcf4410bf8d2";
//    public static final String APP_SECRET = "0aa3d97672f7c176837fdc9f1d9c8659";
//    public static final String MIN_ID = "gh_ba23fb3396dd";
//    public static final String MIN_TITLE = "我约Lite";
//
//    private Context context;
//    private IWXAPI api;
//
//    public WXShare(Context context){
//        api = WXAPIFactory.createWXAPI(context,APP_ID,true);
//        api.registerApp(APP_ID);
//        this.context =context;
//    }
//
//    public IWXAPI getApi(){
//        return api;
//    }
//
//    public Boolean isWXAppInstall(){
//        if (api.isWXAppInstalled()){
//            return true;
//        }else {
//            return false;
//        }
//    }
//
//
//    public WXShare Login(){
//        SendAuth.Req req = new SendAuth.Req();
//        req.scope = "snsapi_userinfo";
//        req.state = "wechat_sdk_demo_test";
//        api.sendReq(req);
//        return this;
//    }
//
//
//
//    /**
//     * 分享文本
//     * @param text
//     * @return
//     */
//    public WXShare shareText(String text){
//        WXTextObject textObject = new WXTextObject();
//        textObject.text = text;
//
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = textObject;
//        msg.description = text;
//
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buildTransaction("text");
//        req.message = msg;
//        //分享到好友
//        req.scene = SendMessageToWX.Req.WXSceneSession;
//        boolean result = api.sendReq(req);
//        Log.e("WX text shared: " + result,"");
//        return this;
//    }
//
//
//    /**
//     * 分享链接
//     * @param flag
//     * @param url
//     * @param title
//     * @param descroption
//     * @return
//     */
//    public WXShare shareURL(int flag,String url,String title,String descroption){
//        WXWebpageObject webpageObject = new WXWebpageObject();
//        webpageObject.webpageUrl = url;
//
//        WXMediaMessage msg = new WXMediaMessage(webpageObject);
//        msg.title = title;
//        msg.description = descroption;
//        Bitmap thumb = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);
//        //msg.setThumbImage(thumb);
//        msg.thumbData = WxUtil.bmpToByteArray(thumb, true);
//
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buildTransaction("webpage");
//        req.message = msg;
//        req.scene = flag == 0 ? SendMessageToWX.Req.WXSceneSession :SendMessageToWX.Req.WXSceneTimeline;
//        api.sendReq(req);
//        return this;
//    }
//
//
//    /**
//     * 分享图片
//     * @param flag
//     * @param image
//     * @return
//     */
//    public WXShare sharePicture(int flag,int image,Bitmap mbitmap){
//        Bitmap bitmap;
//        if (image != 0){
//            bitmap = BitmapFactory.decodeResource(context.getResources(), image);
//        }else {
//            bitmap = mbitmap;
//        }
//
//        WXImageObject imageObject = new WXImageObject(bitmap);
//        WXMediaMessage msg = new WXMediaMessage();
//        msg.mediaObject = imageObject;
//
//        Bitmap thumb = Bitmap.createScaledBitmap(bitmap,120, 120, true);
//        bitmap.recycle();
//        msg.thumbData = WxUtil.bmpToByteArray(thumb, true);
//
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        req.transaction = buildTransaction("img");
//        req.message = msg;
//        req.scene = flag == 0 ? SendMessageToWX.Req.WXSceneSession :SendMessageToWX.Req.WXSceneTimeline;
//        api.sendReq(req);
//        return this;
//    }
//
//    /**
//     * 分享小程序
//     * @return
//     */
//    public WXShare shareWXMinProgram(String title,String description,String gid,String uid,Bitmap bitmap){
//        String MIN_PATH = "pages/actinfo/main?gid="+gid+"&uid="+uid;
//        WXMiniProgramObject wxMiniProgramObject = new WXMiniProgramObject();
//        wxMiniProgramObject.webpageUrl = "http://www.qq.com/";
//        wxMiniProgramObject.userName = MIN_ID;
//        wxMiniProgramObject.path = MIN_PATH;
//
//        WXMediaMessage msg = new WXMediaMessage(wxMiniProgramObject);
//        msg.title = title;
//        msg.description = description;
//        msg.mediaObject = wxMiniProgramObject;
//
//        msg.thumbData = WxUtil.bmpToByteArray(bitmap, true);
//        SendMessageToWX.Req req = new SendMessageToWX.Req();
//        //req.transaction = buildTransaction("");
//        req.message = msg;
//        req.scene = SendMessageToWX.Req.WXSceneSession;
//        api.sendReq(req);
//        return this;
//
//    }

    private String buildTransaction(final String type) {
        return (type == null) ? String.valueOf(System.currentTimeMillis()) : type + System.currentTimeMillis();
    }

    private Bitmap compressQuality(Bitmap bm) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.JPEG, 50, bos);
        byte[] bytes = bos.toByteArray();
        bm.recycle();
        return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
    }

}
