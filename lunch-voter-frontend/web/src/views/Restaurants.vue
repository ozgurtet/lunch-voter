<template>
  <div class="restaurant-view-wrapper">
      <div class="container">
        <p class="title is-3 pt-5">Restaurant List</p>
        <ul>
          <li v-for="(restaurant, index) in storeRestaurantList" :key="restaurant.restaurantId">
              <RestaurantCard
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
</template>

<script>
import LunchVoterService from '@/service/LunchVoterService'
import RestaurantCard from '@/components/Restaurant/RestaurantCard'
import AddRestaurantModal from '@/components/Modal/AddRestaurantModal'

export default {
  name: 'Restaurants',
  props: ['isVotable'],
  components: {
    RestaurantCard,
    AddRestaurantModal
  },
  data() {
    return {
      lunchVoterService: null,
      openAddResModal: false,
      selectedRestaurantIndex: -1
    };
  },
  computed: {
    storeRestaurantList() {
      return this.$store.getters.getRestaurantList;
    },
    votedRestaurantIndex() {
      return this.$store.getters.getVotedRestaurantIndex;
    },
    user() {
      return this.$store.getters.getUser;
    },
    selectedEvent() {
      return this.$store.getters.getVotedEvent;
    }
  },
  created() {
    this.lunchVoterService = new LunchVoterService();
    this.getRestaurantList();

  },
  methods: {
    async getRestaurantList() {
      const restaurantList = await this.lunchVoterService.getRestaurantList();
      this.$store.commit('setRestaurants', restaurantList);
    },
    openRestaurantModal() {
      this.$store.commit('isRestaurantsModalOpen', true);
    }
  }
}
</script>
