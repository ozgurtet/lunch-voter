<template>
  <div class="login-form">
    <h6 class="subtitle is-4 has-text-centered mt-3">Lunch Voter</h6>
    <div class="container">
      <div class="columns">
          <div class="column ml-2 mr-2">
            <p class="control has-icons-left">
             <input v-model="user.userName" class="input is-small" type="text" placeholder="User Name">
             <span class="icon is-small is-left">
               <i class="fa fa-user"></i>
             </span>
           </p>
           <!-- eslint-disable-next-line vue/valid-v-model -->
           <p v-show="validation.isUserNameEmpty" class="help is-danger">Username cannot be empty</p>
          </div>
      </div>
      <div class="columns">
        <div class="column ml-2 mr-2">
          <p class="control has-icons-left">
           <input v-model="user.password" class="input is-small" type="password" placeholder="Password">
           <span class="icon is-small is-left">
             <i class="fa fa-lock"></i>
           </span>
         </p>
          <!-- eslint-disable-next-line vue/valid-v-model -->
         <p v-show="validation.isPasswordEmpty" class="help is-danger">This email is invalid</p>
        </div>
      </div>
      <div class="columns">
        <div class="column has-text-centered">
          <button class="button is-small login-button" @click="login">Login</button>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import LuncVoterService from '@/service/LunchVoterService'

export default {
  name: 'Login-Form',
  data() {
    return {
      lunchVoterService : null,
      user: {
        userName: 'M_JORDAN',
        password: 'mj1234'
      },
      validation: {
        isUserNameEmpty: false,
        isPasswordEmpty: false
      }
    };
  },
  created() {
    this.lunchVoterService = new LuncVoterService();
  },
  methods: {
    async login() {
      const isFormValid = this.validate(this.user);

      if(isFormValid) {
        const result = await this.lunchVoterService.login(this.user);
        if(result.status !== 200) {
          const message = result.data;
          this.showToastmessage(message);
        } else {
          this.$store.commit('setUser', result.data[0]);
          this.$router.push('/events');
        }
      }
    },
    validate(user) {
      if(!user.userName) {
        this.validation.isUserNameEmpty = true;
      } else {
        this.validation.isUserNameEmpty = false;
      }

      if(!user.password) {
        this.validation.isPasswordEmpty = true;
      } else {
        this.validation.isPasswordEmpty = false;
      }

      return !(this.validation.isUserNameEmpty || this.validation.isPasswordEmpty);
    },
    showToastmessage(message) {
      this.$buefy.toast.open({
         duration: 5000,
         message: `${message}`,
         position: 'is-top',
         type: 'is-warning'
      })
    }
  }
}
</script>
