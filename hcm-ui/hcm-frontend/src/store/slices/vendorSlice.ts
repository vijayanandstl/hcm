import { createSlice, PayloadAction } from '@reduxjs/toolkit';

export interface Vendor {
  id: string;
  name: string;
  contactPerson: string;
  email: string;
  phoneNumber: string;
  address: string;
  status: 'Active' | 'Inactive';
  taxId: string;
  paymentTerms: string;
  contractStartDate: string;
  contractEndDate: string;
}

interface VendorState {
  vendors: Vendor[];
  loading: boolean;
  error: string | null;
}

const initialState: VendorState = {
  vendors: [],
  loading: false,
  error: null,
};

const vendorSlice = createSlice({
  name: 'vendors',
  initialState,
  reducers: {
    setVendors: (state, action: PayloadAction<Vendor[]>) => {
      state.vendors = action.payload;
      state.loading = false;
      state.error = null;
    },
    addVendor: (state, action: PayloadAction<Vendor>) => {
      state.vendors.push(action.payload);
    },
    updateVendor: (state, action: PayloadAction<Partial<Vendor> & { id: string }>) => {
      const index = state.vendors.findIndex(v => v.id === action.payload.id);
      if (index !== -1) {
        state.vendors[index] = { ...state.vendors[index], ...action.payload };
      }
    },
    deleteVendor: (state, action: PayloadAction<string>) => {
      state.vendors = state.vendors.filter(v => v.id !== action.payload);
    },
    setLoading: (state, action: PayloadAction<boolean>) => {
      state.loading = action.payload;
    },
    setError: (state, action: PayloadAction<string>) => {
      state.error = action.payload;
      state.loading = false;
    },
  },
});

export const {
  setVendors,
  addVendor,
  updateVendor,
  deleteVendor,
  setLoading,
  setError,
} = vendorSlice.actions;

export default vendorSlice.reducer; 