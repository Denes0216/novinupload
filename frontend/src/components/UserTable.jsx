import React from 'react';
import RoleSelector from './RoleSelector';
import { deleteUserById } from '../services/userService';
import toastWithText from '../services/toastService';

function UserTable({ users, setUsers }) {

    const handleDelete = (id) => {
        const confirmed = window.confirm('Are you sure you want to delete this user?');
        if (confirmed) {
            const response = deleteUserById(id);
            response.then(() => {
                const updatedUsers = users.filter(user => user.id !== id);
                setUsers(updatedUsers);
                toastWithText('User deleted successfully');
            })
                .catch((err) => console.error(err.message));
        }
    }

    return (
        <table>
            <thead>
                <tr>
                    <th>Name</th>
                    <th>Roles</th>
                    <th>Update</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                {users.map((user) => (
                    <tr key={user.id}>
                        <td>{user.name}</td>
                        <>
                            <td>
                                {user.roles.map(role => role.name.replace('ROLE_', '')).join(', ')}
                            </td>
                            <RoleSelector userId={user.id} roles={user.roles.map(role => role.name)} />
                        </>
                        <td>
                            <button onClick={() => handleDelete(user.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
            </tbody>
        </table>
    );
}

export default UserTable;
