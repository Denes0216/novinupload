import apiService from './apiService';

const resource = 'auth';

export async function loginUser(user) {
    const response = await apiService.post(`${resource}/login`, user);
    return response.data;
}

export async function registerUser(user) {
    const response = await apiService.post(`${resource}/register`, user);
    return response.data;
}