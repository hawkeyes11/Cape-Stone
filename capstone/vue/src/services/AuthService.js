import axios from 'axios';

// const http = axios.create({
//  baseURL: "http://localhost:8080/"

// });

export default {

  login(user) {
    return axios.post('/login', user)
  },

  register(user) {
    return axios.post('/register', user)
  }
  // getUser() {

  // }

  // getToken() {

  // }

  // saveToken(token) {  -- stores user token locally upon successful/authenticated login

  // }

  // trashToken () {  -- dumps Token upon logout

  // }

  // saveRestaurant () { -- posts favorited restaurant to current users profile??

  // }

}
