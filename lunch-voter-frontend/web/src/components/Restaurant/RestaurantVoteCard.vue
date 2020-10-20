<template>
  <div class="restaurant-vote-wrapper">
    <div class="box" :class="{'disabled': disabled}">
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

          <div v-if="isEventContinue" class="level-left">
            <div class="level-left">
                <button id="undo-vote" v-if="isRestaurantVoted" class="button is-danger is-small" @click="undoVote">
                   <span class="icon is-small">
                     <i class="fa fa-undo" aria-hidden="true"></i>
                   </span>
                </button>
                <button id="vote" v-else class="button is-success is-small" @click="vote">Vote</button>
            </div>

          </div>
          </nav>

          <nav class="level is-mobile">

            <div class="level-left">
                <span>Voters:</span>
                <span v-for="voter in voterList" :key="voter.voteId" class="tag is-info voter">{{ voter }}</span>
            </div>

            <span>This restaurant voted by <strong> {{voterList.length}}</strong> user.</span>

          </nav>
        </div>
      </article>
    </div>
  </div>
</template>

<script>
import LunchVoterService from '@/service/LunchVoterService'

export default {
  name: 'Restaurant-Vote-Card',
  props: ['restaurant', 'restaurantIndex', 'voterList'],
  data() {
    return {
      lunchVoterService: null,
      isRestaurantVoted: false,
    };
  },
  created() {
    this.lunchVoterService = new LunchVoterService();
  },
  mounted() {
    this.setRestaurantVoted();
  },
  computed: {
    isRestaurantsVotable() {
      return this.$store.state.isRestaurantVotable;
    },
    selectedEvent() {
      return this.$store.getters.getVotedEvent;
    },
    user() {
      return this.$store.getters.getUser;
    },
    isRestaurantRemovable() {
      return this.$store.getters.getIsRestaurantsRemovable;
    },
    votedRestaurantIndex() {
      return this.$store.getters.getVotedRestaurantIndex;
    },
    disabled() {
      return (this.votedRestaurantIndex !== -1 && this.votedRestaurantIndex !== this.restaurantIndex)
    },
    isEventContinue() {
      return !this.$store.getters.getVotedEvent.isExpired;
    }
  },
  watch: {
    votedRestaurantIndex() {
      this.setRestaurantVoted();
    }
  },
  methods: {
    setRestaurantVoted() {
      if(this.isUserVoteExitOnevent() && this.checkVotedRestaurant()){
        this.isRestaurantVoted = true;
      }
    },
    checkVotedRestaurant() {
      return (this.votedRestaurantIndex === this.restaurantIndex) ? true : false;
    },
    isUserVoteExitOnevent() {
      return (this.votedRestaurantIndex !== -1) ? true : false;
    },
    async removeRestaurant() {
        const response = await this.lunchVoterService.deleteRestaurant(this.restaurant.restaurantId);
        this.$store.commit('setRestaurants', response);
    },
    async vote() {
      this.$store.commit('spinLoader', true);

      const insertVoteRequest = {
        eventId: this.selectedEvent.id,
        restaurantId: this.restaurant.restaurantId,
        userId: this.user.userId
      };

      await this.lunchVoterService.insertVote(insertVoteRequest);

      this.addVoter();
      this.setVotedRestaurantIndex();
      this.$store.commit('spinLoader', false);
    },
    setVotedRestaurantIndex() {
      this.$store.commit('setVotedRestaurantIndex', this.restaurantIndex);
    },
    addVoter() {
      this.voterList.push(this.user.userName);
    },
    async undoVote() {
      this.$store.commit('spinLoader', true);

      const req = {
        eventId: this.selectedEvent.id,
        userId: this.user.userId
      };

      await this.lunchVoterService.deleteUserVoteEvent(req);

      this.setRestaurantUnvoted();
      this.removeUserFromVoterList();
      this.uncheckVotedRestaurant();
      this.$store.commit('spinLoader', false);
    },
    setRestaurantUnvoted() {
      this.isRestaurantVoted = false;
    },
    removeUserFromVoterList() {
      this.voterList.splice(this.voterList.indexOf(this.user.userName), 1);
    },
    uncheckVotedRestaurant() {
      this.$store.commit('setVotedRestaurantIndex', -1);
    }
  }
}
</script>
