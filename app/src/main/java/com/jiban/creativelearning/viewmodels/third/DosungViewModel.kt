package com.jiban.creativelearning.viewmodels.third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.jiban.creativelearning.api.third.DosungApi
import com.jiban.creativelearning.data.third.DosungDataSource

class DosungViewModel(
    private val api: DosungApi
) : ViewModel() {
    val rows = Pager(PagingConfig(pageSize = 10)) {
        DosungDataSource(api)
    }.flow.cachedIn(viewModelScope)
}