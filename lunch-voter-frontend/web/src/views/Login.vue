<template>
  <div class="login-wrapper">
     <section class="login-form-wrapper">
       <LoginForm/>
     </section>

     <section class="opaque-text">
       <ul class="user-list">
         <li v-for="user in users" :key="user.userId"> Username: {{user.userName}} Password: {{user.password}}</li>
       </ul>
     </section>

  </div>
</template>

<script>

  import LoginForm from '@/components/Login/LoginForm'
  import LuncVoterService from '@/service/LunchVoterService'

  export default {
    name: 'Login',
    components: {
      LoginForm
    },
    data() {
      return {
        users: []
      };
    },
    created() {
      this.lunchVoterService = new LuncVoterService();
      this.getUserList();
    },
    methods: {
      async getUserList() {
        this.users = await this.lunchVoterService.getUserList();
      }
    }
  }
</script>
