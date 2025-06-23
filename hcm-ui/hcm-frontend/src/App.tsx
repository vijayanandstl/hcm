import React from 'react';
import { BrowserRouter as Router, Routes, Route, Navigate } from 'react-router-dom';
import { ThemeProvider, CssBaseline } from '@mui/material';
import { Provider } from 'react-redux';
import { store } from './store';
import theme from './theme';
import Layout from './components/Layout';
import EmployeeList from './components/EmployeeList';
import EmployeeForm from './components/EmployeeForm';
import VendorList from './components/VendorList';
import VendorForm from './components/VendorForm';
import JobList from './components/JobList';
import JobForm from './components/JobForm';
import Dashboard from './components/Dashboard';

const App: React.FC = () => {
  return (
    <Provider store={store}>
      <ThemeProvider theme={theme}>
        <CssBaseline />
        <Router>
          <Layout>
            <Routes>
              <Route path="/" element={<Dashboard />} />
              <Route path="/employees" element={<EmployeeList />} />
              <Route path="/employees/new" element={<EmployeeForm />} />
              <Route path="/employees/:id" element={<EmployeeForm />} />
              <Route path="/jobs" element={<JobList />} />
              <Route path="/jobs/new" element={<JobForm />} />
              <Route path="/jobs/:id" element={<JobForm />} />
              <Route path="/vendors" element={<VendorList />} />
              <Route path="/vendors/new" element={<VendorForm />} />
              <Route path="/vendors/:id" element={<VendorForm />} />
              <Route path="*" element={<Navigate to="/" replace />} />
            </Routes>
          </Layout>
        </Router>
      </ThemeProvider>
    </Provider>
  );
};

export default App;
