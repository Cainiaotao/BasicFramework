package com.example.basicframework.bean

import com.example.basicframework.bean.enums.MeItemType
import com.example.basicframework.bean.enums.MeViewType

class MeViewContent {
    var type: MeViewType = MeViewType.One
    var icon:Int = 0
    var title:String = ""
    var itemType:MeItemType = MeItemType.Other

    constructor(type: MeViewType, icon: Int, title: String, itemType: MeItemType) {
        this.type = type
        this.icon = icon
        this.title = title
        this.itemType = itemType
    }
}


