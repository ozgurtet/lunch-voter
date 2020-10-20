import { mount, createLocalVue} from '@vue/test-utils'
import Vuex from 'vuex'
import Store from '@/store/index'
import RestaurantVoteCard from '@/components/Restaurant/RestaurantVoteCard.vue'

const localVue = createLocalVue()
localVue.use(Vuex)

const store = new Vuex.Store({
  state: {
    user: {
      userId: 1
    },
    restaurantList: [],
    eventList: [],
    isRestaurantModalOpened: false,
    isEventsModalOpened: false,
    votedEvent : {},
    isRestaurantVotable: false,
    votedRestaurantIndex: -1,
    startLoader: false,
    userVote: null,
    isRestaurantsRemovable: false
  },
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
    }
  }
})

describe('RestaurantVoteCard.vue', () => {
  it('test vote function called', () => {
    const cmpName = 'Restaurant'

    const restaurantProp = {
      restauranId: 1,
      address: 'Res1 address',
      detail: 'Res1 detail',
      name: 'Res1 Name',
      websiteURL: "RESTARANT1 WEB URL"
    }

    const voterList = []
    const restaurantIndex = -1

    const wrapper = mount(RestaurantVoteCard,{
      store,
      localVue,
      propsData: {
        restaurant      : restaurantProp,
        voterList       : voterList,
        restaurantIndex :restaurantIndex
      }
    })



    const voteButton = wrapper.find('#vote')
    expect(voteButton.text()).toBe("Vote")
  })
})
