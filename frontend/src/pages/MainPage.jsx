import React, { useState } from 'react';
import { useRecoilValue } from 'recoil';
import { userLastLoginState, userNameState, userRolesState } from '../store/userStore';
import { format } from 'date-fns';
import './MainPage.css';

export default function MainPage() {
    const name = useRecoilValue(userNameState);
    const roles = useRecoilValue(userRolesState);
    const lastLogin = useRecoilValue(userLastLoginState);
    const formattedDate = format(new Date(lastLogin), "MMMM dd, yyyy HH:mm:ss");
    const formattedRoles = roles.map(role => role.replace('ROLE_', '').toLowerCase()).join(', ');

    return (
        <div className='main'>
            <main>
                <div className="user-info">
                    <h1>Welcome, {name}!</h1>
                    <p>Roles: {formattedRoles}</p>
                    <p>Last Login: {formattedDate}</p>
                </div>
            </main>
        </div>
    );
}