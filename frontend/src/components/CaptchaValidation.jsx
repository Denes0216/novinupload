import React, { useState } from 'react';
import ReCAPTCHA from "react-google-recaptcha";
import { verifyCaptcha } from '../services/captchaService';
import toastWithText from '../services/toastService';

export default function CaptchaValidation({ onCaptchaVerify }) {
    const [isCaptchaVerified, setIsCaptchaVerified] = useState(false);

    const handleCaptchaVerify = async (response) => {
        try {
            await verifyCaptcha(response);
            setIsCaptchaVerified(true);
            toastWithText('Captcha validation successful');
            onCaptchaVerify();
        } catch (error) {
            toastWithText(error.message)
        }
    }

    return (
        <div className="captcha-container">
            <ReCAPTCHA
                sitekey="6Lc14PYoAAAAALtOy1ZoFJz7q3tC6TTXcwmI2K8r"
                onChange={handleCaptchaVerify}
            />
            {isCaptchaVerified && <p className="captcha-verified">Captcha verified!</p>}
        </div>
    );
}