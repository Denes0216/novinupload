import React, { useEffect, useState } from 'react';
import { getInvoices } from '../services/invoiceService';
import { Link } from 'react-router-dom';
import './InvoiceListPage.css';
import { useRecoilValue } from 'recoil';
import { userRolesState } from '../store/userStore';

function InvoiceList() {
    const [invoices, setInvoices] = useState([]);
    const roles = useRecoilValue(userRolesState);
    const isAdmin = roles.includes('ROLE_ADMIN');
    const isAccountant = roles.includes('ROLE_ACCOUNTANT');

    useEffect(() => {
        async function fetchData() {
            try {
                const data = await getInvoices();
                setInvoices(data);
            } catch (error) {
                console.error(error.message);
            }
        }
        fetchData();
    }, []);

    return (
        <div className='invoice-list-container'>
            <h2>Invoice List</h2>
            {isAdmin || isAccountant && <button onClick={() => window.location.href = '/invoice-creation'}>Create Invoice</button>}
            <table>
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Issue Date</th>
                        <th>Due Date</th>
                        <th>Item Name</th>
                        <th>Price</th>
                        <th>Comment</th>
                    </tr>
                </thead>
                <tbody>
                    {invoices.map(invoice => (
                        <tr key={invoice.id}>
                            <td>{invoice.username}</td>
                            <td>{invoice.issueDate}</td>
                            <td>{invoice.dueDate}</td>
                            <td>{invoice.itemName}</td>
                            <td>{invoice.price} $</td>
                            <td>{invoice.comment}</td>
                            <td>
                                <Link to={`/invoice/${invoice.id}`}>View Details</Link>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default InvoiceList;