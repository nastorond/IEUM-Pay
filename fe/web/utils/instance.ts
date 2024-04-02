'use client';
import axios, { AxiosInstance } from 'axios';
import { getCookie, setCookie } from './cookie';
const api = 'https://www.ieum-pay.site/';
// const accessToken = localStorage.getItem('access_token');

const axiosAuthApi = (): AxiosInstance => {
  const accessToken = getCookie('access_token');
  const instance = axios.create({
    baseURL: api,
    headers: { Authorization: 'Bearer ' + accessToken },
    timeout: 10000,
  });

  instance.interceptors.response.use(
    (response) => {
      return response;
    },
    async (error) => {
      const {
        config,
        response: { status },
      } = error;
      if (error.response?.status === 401) {
        const originRequest = config;
        axiosAuthApi()
          .put('/api/auth/token-renew')
          .then((response) => {
            const newAccessToken = response.data.data;
            console.log('리프레시');
            console.log(response);
            setCookie('access_token', newAccessToken, 1);
            localStorage['access_token'] = newAccessToken;
            originRequest.headers.Authorization = `Bearer ${newAccessToken}`;
            return axios(originRequest);
          })
          .catch((e) => {
            window.alert('로그인이 만료되었습니다. 다시 로그인 해주세요');
          });
      }
    },
  );

  return instance;
};

const axiosApi = (): AxiosInstance => {
  const instance = axios.create({
    baseURL: api,
    timeout: 1000,
  });
  return instance;
};

export { axiosApi, axiosAuthApi };
