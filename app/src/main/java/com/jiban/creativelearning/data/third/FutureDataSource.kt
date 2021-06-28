package com.jiban.creativelearning.data.third

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jiban.creativelearning.api.third.FutureApi
import com.jiban.creativelearning.model.third.future.Row


class FutureDataSource(
    private val api: FutureApi
) : PagingSource<Int, Row>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Row> {
        return try {
            val nextPageNumber = params.key ?:0

            val ServiceKey = "4c6f5066626a6a7334386e7757434c"
            val numOfRows = 10
            val _type = "json"
            val MobileOS = "ETC"
            val MobileApp = "AppTest"


            val response = api.getFutureData(ServiceKey,nextPageNumber+1,numOfRows)
            LoadResult.Page(
                data = response.futureCourseInfo.row,
                prevKey = if (nextPageNumber > 0) nextPageNumber -1 else null,
                nextKey = if (nextPageNumber < response.futureCourseInfo.list_total_count/10) nextPageNumber + 1 else null
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Row>): Int? {
        TODO("Not yet implemented")
    }

}