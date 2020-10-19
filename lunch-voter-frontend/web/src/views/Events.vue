<template>
  <div class="events-wrapper">
      <div class="container">
        <p class="title is-3 pt-5">Events</p>
        <ul>
          <li v-for="event in storeEventList" :key="event.id" class="event-card-wrapper">
              <EventCard :event="event" />
          </li>
        </ul>
        <div class="container  mb-3">
          <button class="button is-success is-small" @click="openEvenModal">+ Add Event</button>
        </div>
      </div>
      <AddEventModal/>
  </div>
</template>
<script>
import LunchVoterService from '@/service/LunchVoterService'
import EventCard from '@/components/Event/EventCard'
import AddEventModal from '@/components/Modal/AddEventModal'

export default {
  name: 'events',
  components: {
    EventCard,
    AddEventModal
  },
  computed: {
    storeEventList() {
      return this.$store.getters.getEventList;
    }
  },
  data() {
    return {
      lunchVoterService: null
    };
  },
  created() {
    this.lunchVoterService = new LunchVoterService();
    this.getEventList();
  },
  watch: {
    storeEventList(newList) {
      this.checkEventExpired(newList);
    }
  },
  methods: {
    async getEventList() {
      const response = await this.lunchVoterService.getEventList();
      this.checkEventExpired(response);
      this.$store.commit('setEventList', response);
    },
    checkEventExpired(eventList) {

      for(let event of eventList) {
        const isExpired = this.isEventExpired(event.eventexpiredate);

        if(isExpired) {
          event.isExpired = true;
        } else {
          event.isExpired = false;
        }
      }

    },
    isEventExpired(eventExpireDate) {
      const now = this.$moment(new Date());
      const expireDate = this.$moment(eventExpireDate, 'DD-MM-YYYY HH:mm:ss');
      return (expireDate.diff(now) < 0) ? true : false;
    },
    openEvenModal() {
      this.$store.commit('isEventsModalOpened', true);
    }
  }
}
</script>
