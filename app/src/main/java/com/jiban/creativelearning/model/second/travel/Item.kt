package com.jiban.creativelearning.model.second.travel

data class Item(
    val galContentId: Int,
    val galContentTypeId: Int,
    val galCreatedtime: Long,
    val galModifiedtime: Long,
    val galPhotographer: String,
    val galPhotographyLocation: String,
    val galPhotographyMonth: Int,
    val galSearchKeyword: String,
    val galTitle: String,
    val galViewCount: Int,
    val galWebImageUrl: String
)