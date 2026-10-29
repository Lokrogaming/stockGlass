package dev.lokro.stockglass.data.remote

import dev.lokro.stockglass.data.remote.dto.ErrorDto
import dev.lokro.stockglass.data.remote.dto.QuoteDto
import dev.lokro.stockglass.data.remote.dto.SymbolSearchResponseDto
import dev.lokro.stockglass.data.remote.dto.TimeSeriesResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TwelveDataApi {

    @GET("quote")
    suspend fun quote(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String,
    ): retrofit2.Response<QuoteDto>

    @GET("symbol_search")
    suspend fun symbolSearch(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String,
    ): retrofit2.Response<SymbolSearchResponseDto>

    @GET("time_series/{interval}")
    suspend fun timeSeries(
        @Path("interval") interval: String,
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String,
        @Query("outputsize") outputsize: Int? = null,
        @Query("format") format: String? = "JSON",
    ): retrofit2.Response<TimeSeriesResponseDto>
}
