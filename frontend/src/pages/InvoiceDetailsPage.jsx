import React, { useEffect, useState } from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import { getInvoiceById } from '../services/invoiceService';
import InvoiceForm from '../components/InvoiceForm';
import './InvoiceDetailsPage.css';

function InvoiceDetailsPage() {
    const { id } = useParams();
    const [invoice, setInvoice] = useState(null);
    const navigate = useNavigate()

    useEffect(() => {
        async function fetchInvoiceDetails() {
            try {
                const data = await getInvoiceById(id);
                setInvoice(data);
            } catch (error) {
                console.error(error.message);
            }
        }
        fetchInvoiceDetails();
    }, [id]);

    return (
        <div className="invoice-details">
            <h2>Invoice Details</h2>
            <InvoiceForm formData={invoice || {}} errors={{}} handleChange={() => {}} />
            <button onClick={() => navigate("/invoice-list")}>Go Back</button>
        </div>
    );
}

export default InvoiceDetailsPage;
