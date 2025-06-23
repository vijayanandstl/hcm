import React, { useEffect } from 'react';
import {
  Box,
  Paper,
  Table,
  TableBody,
  TableCell,
  TableContainer,
  TableHead,
  TableRow,
  Typography,
  Button,
  Chip,
  IconButton,
  Tooltip,
} from '@mui/material';
import { useNavigate } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import { RootState } from '../store';
import { setVendors, deleteVendor } from '../store/slices/vendorSlice';
import { Vendor } from '../store/slices/vendorSlice';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import AddIcon from '@mui/icons-material/Add';

const VendorList: React.FC = () => {
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const { vendors, loading, error } = useSelector((state: RootState) => state.vendors);

  useEffect(() => {
    // TODO: Replace with actual API call
    const mockVendors: Vendor[] = [
      {
        id: '1',
        name: 'Tech Solutions Inc.',
        contactPerson: 'John Doe',
        email: 'john@techsolutions.com',
        phoneNumber: '+1 234-567-8901',
        address: '123 Tech Street, Silicon Valley',
        taxId: 'TAX123456',
        paymentTerms: 'Net 30',
        status: 'Active',
        contractStartDate: '2023-01-01',
        contractEndDate: '2024-01-01',
      },
      {
        id: '2',
        name: 'Global Services Ltd.',
        contactPerson: 'Jane Smith',
        email: 'jane@globalservices.com',
        phoneNumber: '+1 234-567-8902',
        address: '456 Global Ave, New York',
        taxId: 'TAX789012',
        paymentTerms: 'Net 45',
        status: 'Active',
        contractStartDate: '2023-02-01',
        contractEndDate: '2024-02-01',
      },
    ];
    dispatch(setVendors(mockVendors));
  }, [dispatch]);

  const handleDelete = (id: string) => {
    if (window.confirm('Are you sure you want to delete this vendor?')) {
      dispatch(deleteVendor(id));
    }
  };

  if (loading) return <Typography>Loading...</Typography>;
  if (error) return <Typography color="error">{error}</Typography>;

  return (
    <Box>
      <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 3 }}>
        <Typography variant="h5" component="h1">
          Vendor Management
        </Typography>
        <Button
          variant="contained"
          color="primary"
          startIcon={<AddIcon />}
          onClick={() => navigate('/vendors/new')}
        >
          Add Vendor
        </Button>
      </Box>

      <TableContainer component={Paper}>
        <Table>
          <TableHead>
            <TableRow>
              <TableCell>Name</TableCell>
              <TableCell>Contact Person</TableCell>
              <TableCell>Email</TableCell>
              <TableCell>Phone</TableCell>
              <TableCell>Status</TableCell>
              <TableCell>Contract Period</TableCell>
              <TableCell align="right">Actions</TableCell>
            </TableRow>
          </TableHead>
          <TableBody>
            {vendors.map((vendor) => (
              <TableRow key={vendor.id}>
                <TableCell>{vendor.name}</TableCell>
                <TableCell>{vendor.contactPerson}</TableCell>
                <TableCell>{vendor.email}</TableCell>
                <TableCell>{vendor.phoneNumber}</TableCell>
                <TableCell>
                  <Chip
                    label={vendor.status}
                    color={vendor.status === 'Active' ? 'success' : 'error'}
                    size="small"
                  />
                </TableCell>
                <TableCell>
                  {new Date(vendor.contractStartDate).toLocaleDateString()} -{' '}
                  {new Date(vendor.contractEndDate).toLocaleDateString()}
                </TableCell>
                <TableCell align="right">
                  <Tooltip title="Edit">
                    <IconButton
                      onClick={() => navigate(`/vendors/${vendor.id}`)}
                      color="primary"
                    >
                      <EditIcon />
                    </IconButton>
                  </Tooltip>
                  <Tooltip title="Delete">
                    <IconButton
                      onClick={() => handleDelete(vendor.id)}
                      color="error"
                    >
                      <DeleteIcon />
                    </IconButton>
                  </Tooltip>
                </TableCell>
              </TableRow>
            ))}
          </TableBody>
        </Table>
      </TableContainer>
    </Box>
  );
};

export default VendorList; 