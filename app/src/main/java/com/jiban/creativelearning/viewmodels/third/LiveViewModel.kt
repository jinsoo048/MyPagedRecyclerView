package com.jiban.creativelearning.viewmodels.third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.jiban.creativelearning.api.third.LiveApi
import com.jiban.creativelearning.data.third.LiveDataSource

class LiveViewModel(
    private val api: LiveApi
) : ViewModel() {
    val rows = Pager(PagingConfig(pageSize = 10)) {
        LiveDataSource(api)
    }.flow.cachedIn(viewModelScope)
}