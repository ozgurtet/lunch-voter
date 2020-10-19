<template>
  <div class="restaurant-modal">
    <div class="modal" :class="{'is-active': isOpen}">
      <div class="modal-background"></div>
      <div class="modal-card">
        <header class="modal-card-head">
          <p class="modal-card-title is-2">Add Restaurant</p>
          <button class="delete" aria-label="close" @click="close"></button>
        </header>
        <section class="modal-card-body">
          <div class="columns">
            <div class="column">
              <div class="field">
                <label class="label">Restaurant Name</label>
                <div class="control">
                  <input v-model="restaurant.name" class="input is-small" type="text" placeholder="Name">
                </div>
                <!-- eslint-disable-next-line vue/valid-v-model -->
                <p v-show="validation.isNameEmpty" class="help is-danger">Resaurant name cannot be empty</p>
              </div>
            </div>
            <div class="column">
              <div class="field">
                <label class="label">Restaurant Web Site</label>
                <div class="control">
                  <input v-model="restaurant.websiteURL" class="input is-small" type="text" placeholder="URL">
                </div>
                <!-- eslint-disable-next-line vue/valid-v-model -->
                <p v-show="validation.iswebsiteURLEmpty" class="help is-danger">URL cannot be empty</p>
              </div>
            </div>
          </div>
          <div class="columns">
            <div class="column">
              <div class="field">
                <label class="label">Restaurant Detail</label>
                <div class="control">
                  <textarea v-model="restaurant.description" class="textarea" placeholder="Details"></textarea>
                </div>
                <!-- eslint-disable-next-line vue/valid-v-model -->
                <p v-show="validation.isDescriptionEmpty" class="help is-danger">Detail cannot be empty</p>
              </div>
            </div>
          </div>
          <div class="columns">
            <div class="column">
              <div class="field">
                <label class="label">Restaurant Address</label>
                <div class="control">
                  <textarea v-model="restaurant.address" class="textarea" placeholder="Address"></textarea>
                </div>
                <!-- eslint-disable-next-line vue/valid-v-model -->
                <p v-show="validation.isAddresEmpty" class="help is-danger">Address cannot be empty</p>
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
import LunchVoterService from '@/service/LunchVoterService'

export default {
  name: 'Add-Restaurant-Modal',
  data() {
    return {
      lunchVoterService: null,
      restaurant: {
        address: '',
        description: '',
        name: '',
        websiteURL: ''
      },
      validation: {
        isAddresEmpty: false,
        isDescriptionEmpty: false,
        isNameEmpty: false,
        iswebsiteURLEmpty: false
      }
    };
  },
  created() {
    this.lunchVoterService = new LunchVoterService();
  },
  computed: {
    isOpen() {
      return this.$store.state.isRestaurantModalOpened;
    }
  },
  watch: {
    isOpen() {
      this.reset();
    }
  },
  methods: {
    close() {
      this.$store.commit('isRestaurantsModalOpen', false);
    },
    reset() {
      this.restaurant = {
        address: '',
        description: '',
        name: '',
        websiteURL: ''
      };
      this.validation = {
        isAddresEmpty: false,
        isDescriptionEmpty: false,
        isNameEmpty: false,
        iswebsiteURLEmpty: false
      };
    },
    async save() {
      const isFormValid = this.validate(this.restaurant);

      if(isFormValid) {
        const restaurantList = await this.lunchVoterService.saveRestaurant(this.restaurant);
        this.$store.commit('setRestaurants', restaurantList);

        // We check route if it is vote have to map added restaurant
        if(this.$router.currentRoute.path === '/vote') {
          const size = restaurantList.length - 1;
          const newRestaurant = restaurantList[size];
          this.setVoterMapForNewRestaurant(newRestaurant);
        }

        this.close();
      }
    },
    setVoterMapForNewRestaurant(newRestaurant) {
      this.$store.commit('setNewRestaurantToMap', newRestaurant);
    },
    validate(restaurant) {

      this.validation.isAddresEmpty      = (!restaurant.address)     ? true : false;
      this.validation.isDescriptionEmpty = (!restaurant.description) ? true : false;
      this.validation.isNameEmpty        = (!restaurant.name)        ? true : false;
      this.validation.iswebsiteURLEmpty  = (!restaurant.websiteURL)  ? true : false;

      return !(this.validation.isAddresEmpty
            || this.validation.isDescriptionEmpty
            || this.validation.isNameEmpty
            || this.validation.iswebsiteURLEmpty);
    }
  }
}
</script>
