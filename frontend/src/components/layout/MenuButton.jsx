import React, { useState, useEffect } from 'react';
import { Link, useLocation } from 'react-router-dom';
import { useRecoilState, useSetRecoilState } from 'recoil';
import { userIdState, userNameState, userRolesState, userTokenState } from '../../store/userStore';
import './MenuButton.css';

const MenuButton = () => {
    const [menuOpen, setMenuOpen] = useState(false);
    const [token, setToken] = useRecoilState(userTokenState);
    const [roles, setRoles] = useRecoilState(userRolesState);
    const [visible, setVisible] = useState(false)
    const setUserId = useSetRecoilState(userIdState);
    const setUserName = useSetRecoilState(userNameState);
    const isAdmin = roles.includes('ROLE_ADMIN');
    const isAccountant = roles.includes('ROLE_ACCOUNTANT');
    const location = useLocation();

    const handleMenuClick = () => {
        setMenuOpen(!menuOpen);
    };

    const handleLogout = () => {
        setToken('');
        setRoles([]);
        setUserId('');
        setUserName('');
        setVisible(false);
    }

    useEffect(() => {
        if (token === '' || token === undefined || token === null) {
            setVisible(false);
        } else {
            setVisible(true);
        }
        setMenuOpen(false);
    }, [location.pathname]);

    if(visible)
    return (
        <div className="menu-container">
            <button onClick={handleMenuClick}>Menu</button>
            {menuOpen && (
                <div className="dropdown">
                    {isAdmin && <Link to="/admin">Admin Page</Link>}
                    <Link to="/invoice-list">Invoices</Link>
                    {isAdmin || isAccountant && <Link to="/invoice-creation">Create Invoice</Link>}
                    <Link to="/login" onClick={handleLogout}>LogOut</Link>
                </div>
            )}
        </div>
    );
}

export default MenuButton;
