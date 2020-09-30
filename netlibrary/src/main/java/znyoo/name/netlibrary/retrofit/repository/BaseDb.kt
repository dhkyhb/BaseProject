package znyoo.name.netlibrary.retrofit.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import znyoo.name.netlibrary.retrofit.db.UserDao
import znyoo.name.netlibrary.retrofit.repository.db.User

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.netlibrary.retrofit.db
 * @ClassName:      BaseDb
 * @Author:         dhkyhb
 * @CreateDate:     2020/7/6 1:56 PM
 * @Description:     java类作用描述
 */
@Database(
    entities = [User::class],
    version = 1
)
abstract class BaseDb: RoomDatabase(){

    abstract fun userDao(): UserDao

}