package dev.lokro.stockglass.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import dev.lokro.stockglass.data.local.AppDatabase
import dev.lokro.stockglass.data.local.dao.WidgetConfigDao
import dev.lokro.stockglass.data.remote.TwelveDataApi
import dev.lokro.stockglass.data.remote.repository.TwelveDataMarketDataRepositoryImpl
import dev.lokro.stockglass.data.cache.ExponentialBackoff
import dev.lokro.stockglass.data.cache.RequestDeduplicator
import dev.lokro.stockglass.data.repository.MarketDataRepository
import dev.lokro.stockglass.data.repository.WidgetRepository
import dev.lokro.stockglass.data.repository.WidgetRepositoryImpl
import dev.lokro.stockglass.data.security.EncryptedStore
import dev.lokro.stockglass.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideEncryptedStore(@ApplicationContext context: Context): EncryptedStore {
        EncryptedStore.init(context)
        return EncryptedStore
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC)
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .connectTimeout(java.time.Duration.ofSeconds(15))
            .readTimeout(java.time.Duration.ofSeconds(15))
            .build()
    }

    @Provides
    @Singleton
    fun provideTwelveDataApi(client: OkHttpClient): TwelveDataApi {
        return Retrofit.Builder()
            .baseUrl("https://api.twelvedata.com/")
            .client(client)
            .addConverterFactory(kotlinx.serialization.json.Json.asConverterFactory(kotlinx.serialization.json.Json { ignoreUnknownKeys = true }, "application/json"))
            .build()
            .create(TwelveDataApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRequestDeduplicator(): RequestDeduplicator = RequestDeduplicator()

    @Provides
    @Singleton
    fun provideExponentialBackoff(): ExponentialBackoff = ExponentialBackoff

    @Provides
    @Singleton
    fun provideMarketDataRepository(
        context: Context,
        api: TwelveDataApi,
        database: AppDatabase,
        deduplicator: RequestDeduplicator,
    ): MarketDataRepository {
        return TwelveDataMarketDataRepositoryImpl(context, api, database, deduplicator)
    }

    @Provides
    @Singleton
    fun provideWidgetRepository(context: Context, database: AppDatabase): WidgetRepository {
        return WidgetRepositoryImpl(context, database)
    }

    // Use cases
    @Provides @Singleton fun provideGetQuote(repo: MarketDataRepository): GetQuote = GetQuoteImpl(repo)
    @Provides @Singleton fun provideSearchSymbols(repo: MarketDataRepository): SearchSymbols = SearchSymbolsImpl(repo)
    @Provides @Singleton fun provideGetHistoricalSeries(repo: MarketDataRepository): GetHistoricalSeries = GetHistoricalSeriesImpl(repo)
    @Provides @Singleton fun provideSaveWidgetConfig(repo: WidgetRepository): SaveWidgetConfig = SaveWidgetConfigImpl(repo)
    @Provides @Singleton fun provideDeleteWidgetConfig(repo: WidgetRepository): DeleteWidgetConfig = DeleteWidgetConfigImpl(repo)
    @Provides @Singleton fun provideGetWidgetConfig(repo: WidgetRepository): GetWidgetConfig = GetWidgetConfigImpl(repo)
    @Provides @Singleton fun provideWatchWidgetConfigs(repo: WidgetRepository): WatchWidgetConfigs = WatchWidgetConfigsImpl(repo)
    @Provides @Singleton fun provideUpdateWidgetSnapshot(repo: WidgetRepository): UpdateWidgetSnapshot = UpdateWidgetSnapshotImpl(repo)
    @Provides @Singleton fun provideGetApiKey(store: EncryptedStore): GetApiKey = GetApiKeyImpl(store)
    @Provides @Singleton fun provideStoreApiKey(store: EncryptedStore): StoreApiKey = StoreApiKeyImpl(store)
    @Provides @Singleton fun provideDeleteApiKey(store: EncryptedStore): DeleteApiKey = DeleteApiKeyImpl(store)
    @Provides @Singleton fun provideIsApiKeyValidated(store: EncryptedStore): IsApiKeyValidated = IsApiKeyValidatedImpl(store)
    @Provides @Singleton fun provideMarkApiKeyValidated(store: EncryptedStore): MarkApiKeyValidated = MarkApiKeyValidatedImpl(store)
}
