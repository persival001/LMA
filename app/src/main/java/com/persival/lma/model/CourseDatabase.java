package com.persival.lma.model;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Category.class, Course.class}, version = 1)
public abstract class CourseDatabase extends RoomDatabase {

    private static CourseDatabase INSTANCE;

    public static synchronized CourseDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    CourseDatabase.class, "course_database")
                .fallbackToDestructiveMigration()
                .addCallback(roomCallback)
                .build();
        }
        return INSTANCE;
    }

    public abstract CategoryDAO categoryDAO();

    public abstract CourseDAO courseDAO();

    // Callback
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            // Insert data when database is created

        }
    };
}
