package com.jiban.creativelearning.data.third

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jiban.creativelearning.api.third.DosungApi
import com.jiban.creativelearning.model.third.dosung.Row


class DosungDataSource(
    private val api: DosungApi
) : PagingSource<Int, Row>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Row> {
        return try {

            val nextPageNumber = params.key ?: 0
            val numOfRows = 10
            val startIndex = (nextPageNumber * 10) + 1
            val endIndex = (nextPageNumber * 10) + numOfRows
            val ServiceKey = "41547274756a6a7335326d6f475450"
            val _type = "json"
            val MobileOS = "ETC"
            val MobileApp = "AppTest"

            val response = api.getDosungData(ServiceKey, startIndex, endIndex)
            LoadResult.Page(
                data = response.walkDoseongInfo.row,
                prevKey = if (nextPageNumber > 0) nextPageNumber - 1 else null,
                nextKey = if (nextPageNumber < response.walkDoseongInfo.list_total_count / 10) nextPageNumber + 1 else null
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Row>): Int? {
        return state.anchorPosition
    }
}