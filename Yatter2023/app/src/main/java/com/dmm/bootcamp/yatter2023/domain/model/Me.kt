package com.dmm.bootcamp.yatter2023.domain.model

import java.net.URL

abstract class Me(
  id: AccountId,
  username: Username,
  displayUsername: String?,
  displayName: String?,
  note: String?,
  avatar: URL?,
  header: URL?,
  followingCount: Int,
  followerCount: Int,
) : Account(
  id,
  username,
  displayUsername,
  displayName,
  note,
  avatar,
  header,
  followingCount,
  followerCount,
) {

  abstract suspend fun follow(username: Username)

  abstract suspend fun unfollow(username: Username)
}