import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '@/views/Login'
import Events from '@/views/Events'
import Restaurants from '@/views/Restaurants'
import Vote from '@/views/Vote'
import App from '@/App'


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'App',
    component: App
  },
  {
    path: '/events',
    name: 'Events',
    component: Events
  },
  {
    path: '/restaurants',
    name: 'Restaurants',
    component: Restaurants
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/vote',
    name: 'Vote',
    component: Vote
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
