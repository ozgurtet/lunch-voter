<template>
  <div class="restaurant-wrapper">
    <div class="box">
      <article class="media">
        <div class="media-content">
          <div class="content">
            <p>
              <strong>{{restaurant.name}}</strong>
              <br>
              <small>{{restaurant.description}}</small>
              <br>
              <small>{{restaurant.address}}</small>
              <br>
            </p>
          </div>
          <nav class="level is-mobile">

            <div class="level-left">
                <a class="level-item" href="">Web Site: {{restaurant.websiteURL}}</a>
            </div>
            <div class="level-left">
                <button class="button is-danger is-small" @click="removeRestaurant">Remove</button>
            </div>
          </nav>
        </div>
      </article>
    </div>
  </div>
</template>

<script>
import LunchVoterService from '@/service/LunchVoterService'

export default {
  name: 'Restaurant-Card',
  props: ['restaurant', 'restaurantIndex'],
  data() {
    return {
      lunchVoterService: null,
      isRestaurantVoted: false,
    };
  },
  created() {
    this.lunchVoterService = new LunchVoterService();
  },
  methods: {
    async removeRestaurant() {
        const response = await this.lunchVoterService.deleteRestaurant(this.restaurant.restaurantId);
        this.$store.commit('setRestaurants', response);
    }
  }
}
</script>
