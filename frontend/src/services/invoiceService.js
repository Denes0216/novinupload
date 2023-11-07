import apiService from './apiService';

const resource = 'invoices';

export async function getInvoices() {
    const response = await apiService.get(`${resource}`);
    return response.data;
}

export async function getInvoiceById(id) {
    const response = await apiService.get(`${resource}/${id}`);
    return response.data;
}

export async function createInvoice(invoice) {
    const response = await apiService.post(`${resource}`, invoice);
    return response.data;
}