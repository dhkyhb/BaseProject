package znyoo.name.netlibrary.retrofit.repository.db

import androidx.room.Entity
import com.google.gson.annotations.SerializedName

/**
 * @ProjectName:    BaseProject
 * @Package:        znyoo.name.netlibrary.retrofit.repository.db
 * @ClassName:      User
 * @Author:         dhkyhb
 * @CreateDate:     2020/6/30 10:39 AM
 * @Description:    User类
 */
@Entity
data class User (
    @field:SerializedName("login")
    val login: String
)