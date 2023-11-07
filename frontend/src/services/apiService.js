import axios from 'axios';
import { getRecoil } from 'recoil-nexus';
import { userTokenState } from '../store/userStore';

const axiosInstance = axios.create({
    baseURL: '/api',
});

const requestConfig = (config) => {
    const token = getRecoil(userTokenState);
    config.headers.Authorization = token ? `Bearer ${token}` : '';
    return config;
};

axiosInstance.interceptors.request.use(requestConfig);

axiosInstance.interceptors.response.use(
    (response) => response,
    (error) => {
        const message = error.response.data;
        throw new Error(message);
    },
);

export default axiosInstance;