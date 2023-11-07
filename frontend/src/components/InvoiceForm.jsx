import React from 'react';

function InvoiceForm({ formData, errors, handleChange }) {
    return (
        <form>
            <div>
                <label>Buyer:</label>
                <input type="text" name="buyer" value={formData.username} onChange={handleChange} />
                {errors.buyer && <div className="error">{errors.buyer}</div>}
            </div>
            <div>
                <label>Due Date:</label>
                <input type="date" name="dueDate" value={formData.dueDate} onChange={handleChange} />
                {errors.dueDate && <div className="error">{errors.dueDate}</div>}
            </div>
            <div>
                <label>Item Name:</label>
                <input type="text" name="itemName" value={formData.itemName} onChange={handleChange} />
                {errors.itemName && <div className="error">{errors.itemName}</div>}
            </div>
            <div>
                <label>Comment:</label>
                <input type="text" name="comment" value={formData.comment} onChange={handleChange} />
                {errors.comment && <div className="error">{errors.comment}</div>}
            </div>
            <div>
                <label>Price:</label>
                <input type="number" name="price" value={formData.price} onChange={handleChange} />
                {errors.price && <div className="error">{errors.price}</div>}
            </div>
        </form>
    );
}

export default InvoiceForm;
