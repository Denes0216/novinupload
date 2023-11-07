import { atom } from 'recoil';

export const userTokenState = atom({
    key: 'UserToken',
    default: localStorage.getItem('token'),
    effects: [
        ({ onSet }) => {
            onSet((newToken) => {
                localStorage.setItem('token', newToken);
            });
        },
    ],
});

export const userIdState = atom({
    key: 'UserId',
    default: localStorage.getItem('user_id'),
    effects: [
        ({ onSet }) => {
            onSet((newId) => {
                localStorage.setItem('user_id', newId);
            });
        },
    ],
});

export const userNameState = atom({
    key: 'UserName',
    default: localStorage.getItem('user_name'),
    effects: [
        ({ onSet }) => {
            onSet((newName) => {
                localStorage.setItem('user_name', newName);
            });
        },
    ],
});

export const userLastLoginState = atom({
    key: 'LastLogin',
    default: localStorage.getItem('last_login'),
    effects: [
        ({ onSet }) => {
            onSet((newLastLogin) => {
                localStorage.setItem('last_login', newLastLogin);
            });
        },
    ],
});

export const userRolesState = atom({
    key: 'UserRoles',
    default: localStorage.getItem('UserRoles') ? JSON.parse(localStorage.getItem('UserRoles')) : [],
    effects: [
        ({ onSet }) => {
            onSet((userRoles) => localStorage.setItem('UserRoles', JSON.stringify(userRoles)));
        },
    ],
});