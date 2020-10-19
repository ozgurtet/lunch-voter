<template>
  <div class="header">
      <div class="logo-wrapper">
          <img src="@/assets/logo_TxN.png" height="50" width="150" alt="">
      </div>
      <div v-if="user" class="nav-wrapper">
        <div class="nav-wrapper">
          <nav class="nav is-title is-4">
            <span>
              <router-link class="router-link" :to="'/restaurants'" >Restaurants</router-link>
              <router-link class="router-link" :to="'/events'" >Events</router-link>
            </span>

            <span class="logout">
              <p class="user-name">{{this.user.userName}}</p>
              <button class="button is-danger is-small" @click="logout">Logout</button>
            </span>

          </nav>
        </div>

      </div>
  </div>
</template>

<script>

export default {
  name: 'Header',
  data() {
    return {
      message: 'Header Mesage'
    }
  },
  computed: {
    user() {
      return this.$store.getters.getUser;
    }
  },
  methods: {
    logout() {
      this.$store.commit('resetStore');
      sessionStorage.removeItem('vuex');
      this.$router.push('/login');
    }
  }
}

</script>
