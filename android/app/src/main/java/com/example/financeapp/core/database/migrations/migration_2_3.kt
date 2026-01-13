package com.example.financeapp.core.database.migrations

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL("""
            ALTER TABLE categories ADD COLUMN colorKey INTEGER NOT NULL DEFAULT 1
        """)

        db.execSQL("""
            UPDATE categories SET colorKey = (id % 6) + 1
        """)
    }
}