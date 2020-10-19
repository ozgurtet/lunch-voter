export default {
  listUser : `/user/list`,
  login : `/user/login`,
  listEvents: `/event/list`,
  listRestaurant: `/restaurant/list`,
  deleteRestaurant: `/restaurant/delete`,
  saveRestaurant: `/restaurant/create`,
  saveEvent: `/event/create`,
  voteInsert: `/vote/insert`,
  deleteVote: `/vote/delete`,
  getVoteByUser: `/vote/listUserVote`,
  getVotesByEvent: `/vote/listAllVotesByEvent`,
  deleteUserVoteEvent: `/vote/deleteUserVote`,
  resetEventVotes: `/vote/deleteAllUserVotes`
}
