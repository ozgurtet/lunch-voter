<template>
  <div class="event-card">
    <div class="card event-card-wrapper" @click="selectEvent">
      <div class="card-content">
        <div class="media">
          <div class="media-content">
            <p class="title is-4">{{event.eventTitle}}</p>
          </div>
        </div>
        <div class="content">
          {{event.eventDetail}}

          <br>
          <br>

          <p class="title is-6">Start Date:</p>
          <p>{{event.eventstartdate}}</p>
          <p  class="title is-6">Expire Date:</p>

          <span v-if="event.isExpired" class="title is-6" :class="{ 'red-text': event.isExpired}">
            <p>{{ event.eventexpiredate }}  (Expired)</p>
          </span>

          <span v-else>
            <p>{{ event.eventexpiredate }}</p>
          </span>

          <nav class="level mt-4">
            <div v-show="showVoteButton" class="level-left">
                <button class="button is-success is-small" @click.stop="selectEvent">Give Vote</button>
            </div>
            <div v-show="showResetButton" class="level-left">
                <button class="button is-danger is-small" @click.stop="resetVotes">Reset Votes</button>
            </div>
            <div v-show="showResultButton" class="level-left">
                <button class="button is-danger is-small">Show Result</button>
            </div>
          </nav>

        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LunchVoterService from '@/service/LunchVoterService'

export default {
  name: 'Event-Card',
  props: ['event'],
  data() {
    return {
      lunchVoterService: null
    }
  },
  created() {
    this.lunchVoterService = new LunchVoterService();
  },
  computed: {
    isAdmin() {
      return (this.$store.getters.getUser.userType === "ADMIN") ? true : false;
    },
    showVoteButton() {
      return (!this.event.isExpired);
    },
    showResetButton() {
      return (this.isAdmin && !this.event.isExpired);
    },
    showResultButton() {
      return this.event.isExpired;
    }
  },
  methods: {
    async selectEvent() {
      this.$store.commit('setVotedEvent', this.event);
      this.$store.commit('resetSelectedRestaurantIndex');
      this.$router.push('/vote');
    },
    async resetVotes() {
      await this.lunchVoterService.resetEventVotes({eventId: this.event.id});
    }
  }
}
</script>
