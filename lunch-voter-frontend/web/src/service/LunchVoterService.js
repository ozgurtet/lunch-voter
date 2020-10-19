import * as LunchVoterClient from '@/client/LunchVoterClient'
import Endpoints from '@/endpoints'

export default class LunchVoterService {
  constructor() {
    this.lunchVoterServiceClient = LunchVoterClient.getInstance();
  }

  login(req) {
    return this.lunchVoterServiceClient.post(Endpoints.login, req)
    .then(response => {
      return response;
    })
    .catch(error => {
      return error.response;
    })
  }

  getUserList() {
    return this.lunchVoterServiceClient.get(Endpoints.listUser)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      console.log(error);
    })
  }

  getEventList() {
    return this.lunchVoterServiceClient.get(Endpoints.listEvents)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      console.log(error);
    })
  }

  getRestaurantList() {
    return this.lunchVoterServiceClient.get(Endpoints.listRestaurant)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      console.log(error);
    })
  }

  deleteRestaurant(restaurantId){
    return this.lunchVoterServiceClient.delete(`${Endpoints.deleteRestaurant}/${restaurantId}`)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      console.log(error);
    })
  }

  saveRestaurant(restaurant){
    return this.lunchVoterServiceClient.post(`${Endpoints.saveRestaurant}`, restaurant)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      console.log(error);
    })
  }

  saveEvent(event) {
    return this.lunchVoterServiceClient.post(`${Endpoints.saveEvent}`, event)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      console.log(error);
    })
  }

  insertVote(req) {
    return this.lunchVoterServiceClient.post(`${Endpoints.voteInsert}`, req)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      console.log(error);
    })
  }

  deleteVote(req) {
    return this.lunchVoterServiceClient.delete(`${Endpoints.deleteVote}`, req)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      console.log(error);
    })
  }

  getUserVote(req) {
    return this.lunchVoterServiceClient.post(`${Endpoints.getVoteByUser}`, req)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      console.log(error);
    })
  }

  getVotesByEvent(req) {
    return this.lunchVoterServiceClient.post(`${Endpoints.getVotesByEvent}`, req)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      console.log(error);
    })
  }

  deleteUserVoteEvent(req) {
    return this.lunchVoterServiceClient.delete(`${Endpoints.deleteUserVoteEvent}` + '/' + req.userId + '/' + req.eventId)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      console.log(error);
    })
  }

  resetEventVotes(req) {
    return this.lunchVoterServiceClient.delete(`${Endpoints.resetEventVotes}` + '/' + req.eventId)
    .then(response => {
      return response.data;
    })
    .catch(error => {
      console.log(error);
    })
  }
}
