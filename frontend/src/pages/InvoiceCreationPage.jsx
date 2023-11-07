import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { createInvoice } from '../services/invoiceService';
import InvoiceForm from '../components/InvoiceForm';
import toastWithText from '../services/toastService';

const InvoiceCreationPage = () => {
    const [formData, setFormData] = useState({
        buyer: '',
        issueDate: new Date(),
        dueDate: '',
        itemName: '',
        comment: '',
        price: 0,
    });

    const navigate = useNavigate()

    const [errors, setErrors] = useState({});

    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const validationErrors = {};

        if (!formData.buyer) {
            validationErrors.buyer = 'Buyer is mandatory';
        }

        if (!formData.dueDate) {
            validationErrors.dueDate = 'Due Date is mandatory';
        }

        if (!formData.itemName) {
            validationErrors.itemName = 'Item Name is mandatory';
        }

        if (!formData.comment) {
            validationErrors.comment = 'Comment is mandatory';
        }

        if (!formData.price) {
            validationErrors.price = 'Price is mandatory';
        }

        if (Object.keys(validationErrors).length === 0) {
            const response = createInvoice(formData);
            response.then(() => {
                toastWithText('Invoice created successfully');
                navigate("/invoice-list");
            })
            .catch(() => toastWithText('Something went wrong'));
        } else {
            setErrors(validationErrors);
        }
    };

    return (
        <div className="invoice-details">
            <h1>Create Invoice</h1>
            <InvoiceForm formData={formData} errors={errors} handleChange={handleChange} />
            <button type="submit" onClick={handleSubmit}>Save</button>
            <button onClick={() => navigate("/invoice-list")}>Go Back</button>
        </div>
    );
};

export default InvoiceCreationPage;