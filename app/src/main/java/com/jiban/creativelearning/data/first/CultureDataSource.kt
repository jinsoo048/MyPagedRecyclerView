package com.jiban.creativelearning.data.first

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jiban.creativelearning.api.first.CultureApi
import com.jiban.creativelearning.model.first.culture.Row


class CultureDataSource(
    private val api: CultureApi
) : PagingSource<Int, Row>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Row> {
        return try {
            val nextPageNumber = params.key ?:0
            val ServiceKey = "6c566673796a6a733839664d4a624d"
            val numOfRows = 10

            val response = api.getCultureData(ServiceKey,nextPageNumber+1,numOfRows)
            LoadResult.Page(
                data = response.ListPublicReservationCulture.row,
                prevKey = if (nextPageNumber > 0) nextPageNumber -1 else null,
                nextKey = if (nextPageNumber < response.ListPublicReservationCulture.list_total_count/10) nextPageNumber + 1 else null
            )
        }catch (e: Exception){
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, Row>): Int? {
        return state.anchorPosition
    }

}