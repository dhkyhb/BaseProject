package znyoo.name.netlibrary.retrofit.repository

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import znyoo.name.netlibrary.retrofit.repository.db.User

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.netlibrary.retrofit.repository
 * @ClassName:      UserDao
 * @Author:         dhkyhb
 * @CreateDate:     2020/6/30 10:37 AM
 * @Description:     java类作用描述
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

}