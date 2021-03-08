package com.patricioglenn.cafeto_admin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.patricioglenn.cafeto_admin.model.Category
import com.patricioglenn.cafeto_admin.model.Product

@Database(entities = [Product::class, Category::class], version = 2, exportSchema = false)
abstract class CafetoDatabase : RoomDatabase() {

    abstract val cafetoDao: CafetoDao

    companion object {

        @Volatile
        private var INSTANCE: CafetoDatabase? = null

        fun getInstance(context: Context): CafetoDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                            context.applicationContext,
                            CafetoDatabase::class.java,
                            "cafeto_database"
                    )
                            .fallbackToDestructiveMigration()
                            .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}