import React, { useState } from 'react';
import { updateUserRoles } from '../services/userService';
import { useEffect } from 'react';
import { getAllRoles } from '../services/roleService';
import toastWithText from '../services/toastService';

function RoleSelector({ userId, roles }) {
    const [selectedRoles, setSelectedRoles] = useState(roles);
    const [fetchedRoles, setFetchedRoles] = useState(["ROLE_ADMIN", "ROLE_USER", "ROLE_ACCOUNTANT"])

    const handleRoleChange = (event) => {
        const role = event.target.value;
        if (event.target.checked) {
            setSelectedRoles([...selectedRoles, role]);
        } else {
            setSelectedRoles(selectedRoles.filter((selectedRole) => selectedRole !== role));
        }
    };

    useEffect(() => {
        const response = getAllRoles();
        response.then((data)=> setFetchedRoles(data.map(role => role.name)));
    }, []);

    const handleRoleUpdate = () => {
        updateUserRoles(userId, selectedRoles)
            .then((updatedRoles) => {
                const rolesArray = updatedRoles.roles.map(role => role.name);
                if (Array.isArray(rolesArray)) {
                    setSelectedRoles(rolesArray);
                    toastWithText('Roles updated successfully');
                } else {
                    toastWithText('Error updating roles: Invalid response');
                }
            })
            .catch(() => {
                toastWithText('Error updating roles');
            });
    };

    const isUpdateDisabled = selectedRoles.length === 0;

    return (
        <td>
            <div>
                {fetchedRoles.map((role) => (
                    <label key={role}>
                        <input
                            type="checkbox"
                            value={role}
                            checked={selectedRoles.includes(role)}
                            onChange={handleRoleChange}
                        />
                        {role.replace('ROLE_', '')}
                    </label>
                ))}
            </div>
            <button onClick={handleRoleUpdate} disabled={isUpdateDisabled}>
                Update Roles
            </button>
        </td>
    );
}

export default RoleSelector;
