package com.group.so.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.group.so.data.dao.CategoryDao
import com.group.so.data.dao.CustomerDao
import com.group.so.data.dao.ItemDao
import com.group.so.data.dao.SalesUnitDao
import com.group.so.data.dao.ServiceOrderDao
import com.group.so.data.entities.db.CategoryDb
import com.group.so.data.entities.db.CategoryTypeConverter
import com.group.so.data.entities.db.CustomerDb
import com.group.so.data.entities.db.CustomerTypeConverter
import com.group.so.data.entities.db.ItemDb
import com.group.so.data.entities.db.ItemsDbConverters
import com.group.so.data.entities.db.SaleUnitTypeConverter
import com.group.so.data.entities.db.SalesUnitDb
import com.group.so.data.entities.db.ServiceOrderDb

@Database(
    entities = [CategoryDb::class, CustomerDb::class, ItemDb::class, SalesUnitDb::class, ServiceOrderDb::class],
    version = 6,
    exportSchema = false
)
@TypeConverters(
    CategoryTypeConverter::class,
    SaleUnitTypeConverter::class,
    CustomerTypeConverter::class,
    ItemsDbConverters::class
)
abstract class ServiceOrderDatabase : RoomDatabase() {
    abstract val dao: CategoryDao
    abstract val daoCustomer: CustomerDao
    abstract val daoItem: ItemDao
    abstract val daoSalesUnit: SalesUnitDao
    abstract val daoServiceOrder: ServiceOrderDao

    companion object {

        @Volatile
        private var INSTANCE: ServiceOrderDatabase? = null

        fun getInstance(context: Context): ServiceOrderDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ServiceOrderDatabase::class.java,
                        "service_order_cache_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}
