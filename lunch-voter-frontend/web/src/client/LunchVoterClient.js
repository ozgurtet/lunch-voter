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
    return response;
  },(error) => {
    return Promise.reject(error);
  });

  instance.interceptors.request.use((request) => {
    return request;
  },(error) => {
    return Promise.reject(error);
  });

  return instance;
}
