package com.example.mypagedrecyclerview.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.mypagedrecyclerview.api.MyApi
import com.example.mypagedrecyclerview.data.CampDataSource

class CampViewModel(
    private val api: MyApi
) : ViewModel() {
    val items = Pager(PagingConfig(pageSize = 10)) {
        CampDataSource(api)
    }.flow.cachedIn(viewModelScope)
}