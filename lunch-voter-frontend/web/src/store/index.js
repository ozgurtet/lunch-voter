import Vue from 'vue'
import Vuex from 'vuex'
import VuexPersistence from 'vuex-persist'

Vue.use(Vuex)

const vuexLocal = new VuexPersistence({
  storage: window.sessionStorage
})

const getDefaultState = () => {
  return {
    user: null,
    restaurantList: [],
    eventList: [],
    isRestaurantModalOpened: false,
    isEventsModalOpened: false,
    votedEvent : {},
    isRestaurantVotable: false,
    votedRestaurantIndex: -1,
    startLoader: false,
    userVote: null,
    isRestaurantsRemovable: false,
    voterListMap: null
  }
}

export default new Vuex.Store({
  state: getDefaultState(),
  mutations: {
    resetStore (state) {
      Object.assign(state, getDefaultState())
    },
    setUser(state, value) {
      state.user = value;
    },
    setRestaurants(state, value) {
      state.restaurantList = value;
    },
    isRestaurantsModalOpen(state, value) {
      state.isRestaurantModalOpened = value;
    },
    isEventsModalOpened(state, value) {
      this.state.isEventsModalOpened = value;
    },
    setEventList(state, value) {
      state.eventList = value;
    },
    setVotedEvent(state, value) {
      state.votedEvent = value;
    },
    setRestaurantsVotable(state, value) {
      state.isRestaurantVotable = value;
    },
    setVotedRestaurantIndex(state, value) {
      state.votedRestaurantIndex = value;
    },
    resetSelectedRestaurantIndex(state) {
      state.votedRestaurantIndex = -1;
    },
    spinLoader(state, value) {
      state.startLoader = value;
    },
    setUserVote(state, value) {
      state.userVoted = value;
    },
    setIsRestaurantsRemovable(state, value) {
      state.isRestaurantsRemovable = value;
    },
    removeRestaurant(state, value) {
      state.restaurantList.splice(value, 1);
    },
    setVoterListMap(state, value) {
      state.voterListMap = value
    },
    setNewRestaurantToMap(state, value) {
      state.voterListMap.set(value.restaurantId, []);
    },
    addVoters(state, value) {
      state.voterListMap.get(value.restaurantId).push(value.userName);
    },
    removeVoter(state, value) {
      const voterList = state.voterListMap.get(value.restaurantId);
      if(voterList.indexOf(value.userName) === 0) {
        state.voterListMap.set(value.restaurantId, []);
      } else {
        state.voterListMap.set(value.restaurantId, voterList.splice(voterList.indexOf(value.userName), 1));
      }

    }
  },
  getters: {
    getRestaurantList(state) {
      return state.restaurantList;
    },
    getEventList(state) {
      return state.eventList;
    },
    getUser(state) {
      return state.user;
    },
    getVotedRestaurantIndex(state) {
      return state.votedRestaurantIndex;
    },
    getSpinLoader(state) {
      return state.startLoader;
    },
    getVotedEvent(state) {
      return state.votedEvent;
    },
    getUserVote(state) {
      return state.userVote;
    },
    getIsRestaurantsRemovable(state) {
      return state.isRestaurantsRemovable;
    },
    getVoterListMap(state) {
      return state.voterListMap;
    }
  },
  plugins: [vuexLocal.plugin]
})
