import React, { useState, useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { useNavigate, useParams } from 'react-router-dom';
import {
  Box,
  Button,
  TextField,
  MenuItem,
  Typography,
  Paper,
} from '@mui/material';
import { addVendor, updateVendor } from '../store/slices/vendorSlice';
import { RootState } from '../store/store';
import { Vendor } from '../store/slices/vendorSlice';

const VendorForm: React.FC = () => {
  const dispatch = useDispatch();
  const navigate = useNavigate();
  const { id } = useParams<{ id: string }>();
  const vendors = useSelector((state: RootState) => state.vendors.vendors);
  
  const [formData, setFormData] = useState<Partial<Vendor>>({
    name: '',
    contactPerson: '',
    email: '',
    phoneNumber: '',
    address: '',
    status: 'Active',
    taxId: '',
    paymentTerms: '',
    contractStartDate: '',
    contractEndDate: '',
  });

  useEffect(() => {
    if (id) {
      const vendor = vendors.find((v: Vendor) => v.id === id);
      if (vendor) {
        setFormData(vendor);
      }
    }
  }, [id, vendors]);

  const handleChange = (e: React.ChangeEvent<HTMLInputElement | HTMLTextAreaElement>) => {
    const { name, value } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleSubmit = (e: React.FormEvent) => {
    e.preventDefault();
    if (id) {
      dispatch(updateVendor({ ...formData, id }));
    } else {
      const newVendor: Vendor = {
        ...formData as Vendor,
        id: Date.now().toString(),
      };
      dispatch(addVendor(newVendor));
    }
    navigate('/vendors');
  };

  return (
    <Box component="form" onSubmit={handleSubmit} sx={{ maxWidth: 800, mx: 'auto', p: 3 }}>
      <Paper elevation={3} sx={{ p: 3 }}>
        <Typography variant="h5" gutterBottom>
          {id ? 'Edit Vendor' : 'Add New Vendor'}
        </Typography>
        
        <Box sx={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fit, minmax(300px, 1fr))', gap: 2 }}>
          <TextField
            name="name"
            label="Vendor Name"
            value={formData.name}
            onChange={handleChange}
            required
            fullWidth
          />
          
          <TextField
            name="contactPerson"
            label="Contact Person"
            value={formData.contactPerson}
            onChange={handleChange}
            required
            fullWidth
          />
          
          <TextField
            name="email"
            label="Email"
            type="email"
            value={formData.email}
            onChange={handleChange}
            required
            fullWidth
          />
          
          <TextField
            name="phoneNumber"
            label="Phone Number"
            value={formData.phoneNumber}
            onChange={handleChange}
            required
            fullWidth
          />
          
          <TextField
            name="address"
            label="Address"
            value={formData.address}
            onChange={handleChange}
            required
            fullWidth
            multiline
            rows={2}
          />
          
          <TextField
            name="status"
            label="Status"
            select
            value={formData.status}
            onChange={handleChange}
            required
            fullWidth
          >
            <MenuItem value="Active">Active</MenuItem>
            <MenuItem value="Inactive">Inactive</MenuItem>
          </TextField>
          
          <TextField
            name="taxId"
            label="Tax ID"
            value={formData.taxId}
            onChange={handleChange}
            required
            fullWidth
          />
          
          <TextField
            name="paymentTerms"
            label="Payment Terms"
            value={formData.paymentTerms}
            onChange={handleChange}
            required
            fullWidth
          />
          
          <TextField
            name="contractStartDate"
            label="Contract Start Date"
            type="date"
            value={formData.contractStartDate}
            onChange={handleChange}
            required
            fullWidth
            InputLabelProps={{ shrink: true }}
          />
          
          <TextField
            name="contractEndDate"
            label="Contract End Date"
            type="date"
            value={formData.contractEndDate}
            onChange={handleChange}
            required
            fullWidth
            InputLabelProps={{ shrink: true }}
          />
        </Box>
        
        <Box sx={{ mt: 3, display: 'flex', gap: 2, justifyContent: 'flex-end' }}>
          <Button
            variant="outlined"
            onClick={() => navigate('/vendors')}
          >
            Cancel
          </Button>
          <Button
            type="submit"
            variant="contained"
            color="primary"
          >
            {id ? 'Update' : 'Add'} Vendor
          </Button>
        </Box>
      </Paper>
    </Box>
  );
};

export default VendorForm; 