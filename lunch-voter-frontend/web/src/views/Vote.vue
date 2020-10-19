<template>
  <div class="voter-view-wrapper">
    <div class="restaurant-view-wrapper">
        <div class="container">
          <p class="title is-3 pt-5">Restaurant List</p>
          <ul>
            <li v-for="(restaurant, index) in storeRestaurantList" :key="restaurant.restaurantId">
                <RestaurantVoteCard
                 :restaurant="restaurant"
                 :restaurantIndex = "index"
                />
            </li>
          </ul>
          <div class="container mb-3">
            <button class="button is-success is-small" @click="openRestaurantModal">+ Add Restaurant</button>
          </div>
        </div>

        <AddRestaurantModal/>
    </div>
  </div>
</template>

<script>
import RestaurantVoteCard from '@/components/Restaurant/RestaurantVoteCard'
import AddRestaurantModal from '@/components/Modal/AddRestaurantModal'
import LunchVoterService from '@/service/LunchVoterService'

export default {
  name: 'Restaurants',
  data() {
    return {
      lunchVoterService: null,
      restaurantVoterMap: null
    }
  },
  components: {
    RestaurantVoteCard,
    AddRestaurantModal
  },
  computed: {
    storeRestaurantList() {
      return this.$store.getters.getRestaurantList;
    },
    votedRestaurantIndex() {
      return this.$store.getters.getVotedRestaurantIndex;
    },
    selectedEvent() {
      return this.$store.getters.getVotedEvent;
    },
    user() {
      return this.$store.getters.getUser;
    },
    getVoterLisMap() {
      return this.$store.getters.getVoterListMap;
    }
  },
  created() {
    this.lunchVoterService = new LunchVoterService();
    this.getRestaurantList();
    this.getUserVote();
  },
  methods: {
    async getRestaurantList() {
      const restaurantList = await this.lunchVoterService.getRestaurantList();
      this.$store.commit('setRestaurants', restaurantList);
      
      this.createRestaurantMap();
      this.getVotesByEvent();
    },
    openRestaurantModal() {
      this.$store.commit('isRestaurantsModalOpen', true);
    },
    async getUserVote() {
      const userVote = await this.lunchVoterService.getUserVote({ eventId: this.selectedEvent.id, userId: this.user.userId });
      this.setSelectedEventIndex(userVote);
    },
    async getVotesByEvent() {
      const allVotesGivenEvent = await this.lunchVoterService.getVotesByEvent({ eventId: this.selectedEvent.id });
      this.addVotersToRestaurantMap(allVotesGivenEvent);
    },
    addVotersToRestaurantMap(allEventVotes) {

      for(let eventNote of allEventVotes) {
        const restaurantId = eventNote.restaurantId;
        const userName = eventNote.userName;
        this.addVoters(restaurantId, userName);
      }

    },
    addVoters(resId, userName) {
      this.$store.commit('addVoters', {restaurantId: resId, userName: userName});
    },
    createRestaurantMap() {
      const restaurantMap = new Map();

      for(let restaurant of this.storeRestaurantList) {
        const retaurantId = restaurant.restaurantId;
        const emptyVoterList = [];
        restaurantMap.set(retaurantId, emptyVoterList);
      }

      this.$store.commit('setVoterListMap', restaurantMap);
    },
    getVotersOfRestaurant(restaurantIndex) {
      return this.restaurantMap.get(restaurantIndex);
    },
    setSelectedEventIndex(userVotes) {
        // Means event have any votes
        let votedRestaurantIndex = -1;

        if(this.eventHasAnyVote(userVotes)) {
          votedRestaurantIndex = this.detectRestaurantVoted(userVotes);
        }

        this.$store.commit('setVotedRestaurantIndex', votedRestaurantIndex);
    },
    eventHasAnyVote(votes) {
      return (votes.length > 0) ? true:false;
    },
    detectRestaurantVoted(votes) {
      let restaurantIndex = -1;

      this.storeRestaurantList.forEach((restaurant, index) => {
          if(restaurant.restaurantId === votes[0].restaurantId){
              restaurantIndex = index;
          }
      });

      return restaurantIndex;
    }
  }
}
</script>
