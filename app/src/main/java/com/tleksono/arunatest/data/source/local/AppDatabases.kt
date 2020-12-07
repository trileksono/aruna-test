package com.tleksono.arunatest.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by trileksono on 07/12/20
 */
@Database(
    entities = [PostEntity::class],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabases : RoomDatabase() {
    abstract fun postDao(): PostDao
}
