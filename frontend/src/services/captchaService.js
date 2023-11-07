import apiService from './apiService';

const resource = 'captcha';

export async function verifyCaptcha(recaptchaResponse) {
    const response = await apiService.post(`${resource}/verify-captcha?recaptchaResponse=${recaptchaResponse}`);
    return response.data;
}