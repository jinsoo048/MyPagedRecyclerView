package com.jiban.creativelearning.viewmodels.third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.jiban.creativelearning.api.third.FutureApi
import com.jiban.creativelearning.data.third.FutureDataSource

class FutureViewModel(
    private val api: FutureApi
) : ViewModel() {
    val rows = Pager(PagingConfig(pageSize = 10)) {
        FutureDataSource(api)
    }.flow.cachedIn(viewModelScope)
}