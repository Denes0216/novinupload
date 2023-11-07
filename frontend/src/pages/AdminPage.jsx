import React, { useState, useEffect } from 'react';
import UserTable from '../components/UserTable';
import { getAllUsers } from '../services/userService';

function AdminPage() {
    const [users, setUsers] = useState([]);

    useEffect(() => {
        getAllUsers().then((data) => setUsers(data));
    }, []);

    return (
        <div>
            <h1>Admin Page</h1>
            <UserTable users={users} setUsers={setUsers} />
        </div>
    );
}

export default AdminPage;
