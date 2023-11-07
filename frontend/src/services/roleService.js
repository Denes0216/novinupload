import apiService from './apiService';

const resource = 'roles';

export async function getAllRoles() {
    const response = await apiService.get(`${resource}`);
    return response.data;
}