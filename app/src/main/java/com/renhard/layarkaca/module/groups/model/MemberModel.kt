package com.renhard.layarkaca.module.groups.model

import com.google.gson.annotations.SerializedName

data class MemberModel(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String
)

data class GroupModel(
    @field:SerializedName("members")
    val members: List<MemberModel>
)
