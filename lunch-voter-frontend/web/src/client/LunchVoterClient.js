import Axios from 'axios'

export function getInstance() {
  const instance = Axios.create({
    timeout: 150000,
    baseURL: '/lunch-voter',
    headers: {
      'Accept'      : 'application/json',
      'Content-Type': 'application/json;charset=utf-8',
      'Access-Control-Allow-Origin': '*',
      'Content-Encoding': 'gzip',
      'X-Requested-With': 'XMLHttpRequest'
    }
  });

  instance.interceptors.response.use((response) => {
    console.log('Lunch Voter Response Interceptor.');
    return response;
  },(error) => {
    console.log(error);
    return Promise.reject(error);
  });

  instance.interceptors.request.use((request) => {
    console.log('Lunch Voter Request Interceptor.');
    return request;
  },(error) => {
    console.log(error);
    return Promise.reject(error);
  });

  return instance;
}
