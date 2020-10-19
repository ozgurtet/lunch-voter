<template>
  <div id="app">
    <section class="header-wrapper">
      <Header/>
    </section>

    <section class="dynamic-content-wrapper">
      <router-view/>
    </section>

    <b-loading :is-full-page="true" v-model="spinLoader" :can-cancel="false"></b-loading>
  </div>
</template>

<script>
import Header from '@/components/Blocks/Header'

export default {
  name: 'Home',
  components: {
    Header
  },
  computed: {
    user() {
      return this.$store.getters.getUser;
    },
    spinLoader() {
      return this.$store.getters.getSpinLoader;
    }
  },
  created() {
    if(!this.user) {
      this.$router.push('/login');
    }
  }
}
</script>

<style lang="scss">
@import './styles/common.scss';

#app {
  background-color: rgba(var(--b3f,250,250,250),1);
}
</style>
