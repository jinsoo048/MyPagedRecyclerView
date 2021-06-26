package com.example.mypagedrecyclerview.model.camp

data class Body(
    val items: Items,
    val numOfRows: Int,
    val pageNo: Int,
    val totalCount: Int
)