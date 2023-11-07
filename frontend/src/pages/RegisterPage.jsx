import React, { useState } from 'react';
import { useNavigate } from 'react-router';
import { registerUser } from '../services/authService';
import { useEffect } from 'react';
import { getAllRoles } from '../services/roleService';
import './registerPage.css';
import toastWithText from '../services/toastService';

export default function RegisterPage() {
    const [formData, setFormData] = useState({ name: '', username: '', password: '', role: 'ROLE_USER' });
    const [error, setError] = useState(null);
    const [validationErrors, setValidationErrors] = useState({})
    const [roles, setRoles] = useState([]);
    const navigate = useNavigate();

    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleRegister = async () => {
        const validationErrors = {};

        if (!formData.name) {
            validationErrors.name = 'Name is required';
        }

        if (formData.username.length < 5) {
            validationErrors.username = 'Username must be at least 5 characters';
        }

        if (formData.password.length < 8) {
            validationErrors.password = 'Password must be at least 8 characters';
        }

        setValidationErrors(validationErrors);

        if (Object.keys(validationErrors).length === 0) {
            try {
                await registerUser(formData);
                toastWithText('Registration successful')
                navigate('/login');
            } catch (error) {
                setError(error.message);
            }
        }
    };

    useEffect(() => {
        const response = getAllRoles();
        response.then((data) => {
            setRoles(data.filter(role => role.name !== 'ROLE_ADMIN'));
        })
        .catch(() => toastWithText('Could not fetch roles'))
    }, []);

    return (
        <div className='register-container'>
            <div className="register-content">
                <h2>Register</h2>
                <form>
                    <input
                        className='input-field'
                        type="text"
                        name="name"
                        value={formData.name}
                        onChange={handleInputChange}
                        placeholder="Name"
                        required
                    />
                    {validationErrors.name && <p className="error-message">{validationErrors.name}</p>}
                    <input
                        className='input-field'
                        type="text"
                        name="username"
                        value={formData.username}
                        onChange={handleInputChange}
                        placeholder="Username"
                        required
                    />
                    {validationErrors.username && <p className="error-message">{validationErrors.username}</p>}
                    <input
                        className='input-field'
                        type="password"
                        name="password"
                        value={formData.password}
                        onChange={handleInputChange}
                        placeholder="Password"
                        required
                    />
                    {validationErrors.password && <p className="error-message">{validationErrors.password}</p>}

                    <select
                        name="role"
                        id="role"
                        value={formData.role}
                        onChange={handleInputChange}
                    >
                        {roles.map((role) => (
                            <option key={role.name} value={role.name}>
                                {role.name.replace('ROLE_', '').toLowerCase()}
                            </option>
                        ))}
                    </select>

                    <button
                        onClick={handleRegister}
                        type='button'
                        disabled={formData.name.length === 0 || formData.username.length < 5 || formData.password.length < 8}
                    >
                        Register
                    </button>
                    <p>Already have an account? <span onClick={() => navigate('/login')}>Login</span></p>
                </form>
                {error && <p>{error}</p>}
            </div>
        </div>
    );
}
