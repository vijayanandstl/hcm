import { configureStore } from '@reduxjs/toolkit';
import employeeReducer from './slices/employeeSlice';
import vendorReducer from './slices/vendorSlice';

export const store = configureStore({
  reducer: {
    employees: employeeReducer,
    vendors: vendorReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch; 