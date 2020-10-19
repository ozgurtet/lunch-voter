<template>
  <div class="event-modal">
    <div class="modal" :class="{'is-active': isOpen}">
      <div class="modal-background"></div>
      <div class="modal-card event-card-modal">
        <header class="modal-card-head">
          <p class="modal-card-title is-2">Add Event</p>
          <button class="delete" aria-label="close" @click="close"></button>
        </header>
        <section class="modal-card-body">
          <div class="columns">
            <div class="column">
              <div class="field">
                <label class="label">Event Title</label>
                <div class="control">
                  <input v-model="event.eventTitle" class="input is-small" type="text" placeholder="Event Title">
                </div>
                <!-- eslint-disable-next-line vue/valid-v-model -->
                <p v-show="validation.isEventTitleEmpty" class="help is-danger">Event Title cannot be empty</p>
              </div>
            </div>
            <div class="column">
              <div class="field">
                <label class="label">Event Detail</label>
                <div class="control">
                  <input v-model="event.eventDetail" class="input is-small" type="text" placeholder="Event Detail">
                </div>
                <!-- eslint-disable-next-line vue/valid-v-model -->
                <p v-show="validation.isEventDetailEmpty" class="help is-danger">Event Detail cannot be empty</p>
              </div>
            </div>
          </div>
          <div class="columns">
            <div class="column">
              <div class="field">
                <label class="label">Start Date</label>
                <div class="control">
                  <VueCtkDateTimePicker
                  :id="'start-date'"
                  v-model="event.eventstartdate"
                  :format="'DD-MM-YYYY HH:mm:ss'"
                  :min-date="minDate"
                  />
                </div>
                <!-- eslint-disable-next-line vue/valid-v-model -->
                <p v-show="validation.isEventStartDateEmpty" class="help is-danger">Start Date cannot be empty</p>
              </div>
            </div>
            <div class="column">
              <div class="field">
                <label class="label">Expire Date</label>
                <div class="control">
                  <VueCtkDateTimePicker
                   :id="'expire-date'"
                   v-model="event.eventexpiredate"
                   :format="'DD-MM-YYYY HH:mm:ss'"
                   :min-date="minDate"
                   />
                </div>
                <!-- eslint-disable-next-line vue/valid-v-model -->
                <p v-show="validation.isEventExpireDateEmpty" class="help is-danger">Expire Date cannot be empty</p>
              </div>
            </div>
          </div>
        </section>
        <footer class="modal-card-foot">
          <button class="button is-success is-small" @click="save">Save</button>
          <button class="button is-small" @click="close">Cancel</button>
        </footer>
      </div>
    </div>
  </div>

</template>

<script>
import Vue from 'vue';
import LunchVoterService from '@/service/LunchVoterService';
import VueCtkDateTimePicker from 'vue-ctk-date-time-picker';

Vue.component('VueCtkDateTimePicker', VueCtkDateTimePicker);

export default {
  name: 'Add-Event-Modal',
  data() {
    return {
      lunchVoterService: null,
      minDate: this.$moment(new Date(), 'DD-MM-YYY HH:mm:ss').subtract(1, 'd'),
      event: {
        eventTitle: '',
        eventDetail: '',
        eventStartDate: '',
        eventExpireDate: ''
      },
      validation: {
        isEventTitleEmpty: false,
        isEventDetailEmpty: false,
        isEventStartDateEmpty: false,
        isEventExpireDateEmpty: false
      }
    };
  },
  created() {
    this.lunchVoterService = new LunchVoterService();
  },
  computed: {
    isOpen() {
      return this.$store.state.isEventsModalOpened;
    }
  },
  watch: {
    isOpen() {
      this.reset();
    }
  },
  methods: {
    close() {
      this.$store.commit('isEventsModalOpened', false);
    },
    reset() {
      this.event = {
        eventTitle: '',
        eventDetail: '',
        eventstartdate: '',
        eventexpiredate: ''
      };

      this.validation = {
        isEventTitleEmpty: false,
        isEventDetailEmpty: false,
        isEventStartDateEmpty: false,
        isEventExpireDateEmpty: false
      }
    },
    async save() {
      const isFormValid = this.validate(this.event);

      if(isFormValid) {
        const eventList = await this.lunchVoterService.saveEvent(this.event);
        this.$store.commit('setEventList', eventList);
        this.close();
      }
    },
    validate(event) {
      if(!event.eventTitle) {
        this.validation.isEventTitleEmpty = true;
      } else {
        this.validation.isEventTitleEmpty = false;
      }

      if(!event.eventDetail) {
        this.validation.isEventDetailEmpty = true;
      } else {
        this.validation.isEventDetailEmpty = false;
      }

      if(!event.eventstartdate) {
        this.validation.isEventStartDateEmpty = true;
      } else {
        this.validation.isEventStartDateEmpty = false;
      }

      if(!event.eventexpiredate) {
        this.validation.isEventExpireDateEmpty = true;
      } else {
        this.validation.isEventExpireDateEmpty = false;
      }

      return !(this.validation.isEventTitleEmpty
            || this.validation.isEventDetailEmpty
            || this.validation.isEventStartDateEmpty
            || this.validation.isEventExpireDateEmpty);
      }
  }
}
</script>
