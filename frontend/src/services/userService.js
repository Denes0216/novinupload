import apiService from './apiService';

const resource = 'users';

export async function getUserById(id) {
    const response = await apiService.get(`${resource}/${id}`);
    return response.data;
}

export async function getAllUsers() {
    const response = await apiService.get(`${resource}`);
    return response.data;
}

export async function updateUserRoles(id, selectedRoles) {
    const response = await apiService.patch(`${resource}/${id}/roles`, selectedRoles);
    return response.data;
}

export async function deleteUserById(id) {
    const response = await apiService.delete(`${resource}/${id}`);
    return response.data;
}