import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import CaptchaValidation from '../components/CaptchaValidation';
import './LoginPage.css';
import { useRecoilValue, useSetRecoilState } from 'recoil';
import { userIdState, userLastLoginState, userNameState, userRolesState, userTokenState } from '../store/userStore';
import { loginUser } from '../services/authService';
import toastWithText from '../services/toastService';

export default function LoginPage() {
    const [formData, setFormData] = useState({ username: '', password: '' });
    const [error, setError] = useState(null);
    const [attempts, setAttempts] = useState(0);
    const setToken = useSetRecoilState(userTokenState);
    const setRoles = useSetRecoilState(userRolesState);
    const setUserId = useSetRecoilState(userIdState);
    const setLastLogin = useSetRecoilState(userLastLoginState);
    const setName = useSetRecoilState(userNameState);
    const token = useRecoilValue(userTokenState);
    const navigate = useNavigate();

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleLogin = async () => {
        try {
            const user = {
                username: formData.username,
                password: formData.password
            }
            const data = await loginUser(user);
                    setToken(data.token);
                    const roles = data.roles.map(role => role.name);
                    setRoles(roles);
                    setUserId(data.userId);
                    setLastLogin(data.loginDate)
                    setName(data.name);
                    toastWithText('Login successful')
                    navigate("/main-page")
        } catch (Error) {
            setError("E-mail or password is invalid");
            setAttempts(prev => prev + 1);
        }
    };

    const handleCaptchaVerify = () => {
        setAttempts(0);
        setError(null);
    }

    return (
        <div className='login-container'>
            <div className="login-content">
                <h2>Login</h2>
                <form>
                    <input
                        className='input-field'
                        type="text"
                        name="username"
                        value={formData.username}
                        onChange={handleInputChange}
                        placeholder="Username"
                        required
                    />
                    <input
                        className='input-field'
                        type="password"
                        name="password"
                        value={formData.password}
                        onChange={handleInputChange}
                        placeholder="Password"
                        required
                    />
                    <button
                        onClick={handleLogin}
                        type='button'
                        disabled={formData.username.length < 5 || formData.password.length < 8 || attempts >= 3}
                    >
                        Login
                    </button>
                    <button
                        onClick={() => navigate('/register')}
                        type='button'
                    >
                        Register
                    </button>
                </form>
                {error && <p>{error}</p>}
                {attempts >= 3 && <CaptchaValidation onCaptchaVerify={handleCaptchaVerify}/>}
            </div>
        </div>
    );
}
