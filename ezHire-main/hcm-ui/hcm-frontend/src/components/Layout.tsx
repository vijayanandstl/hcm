import React from 'react';
import { AppBar, Toolbar, Typography, Button, Container, Box, Avatar } from '@mui/material';
import { Link as RouterLink, useLocation } from 'react-router-dom';
import DashboardIcon from '@mui/icons-material/Dashboard';
import PeopleIcon from '@mui/icons-material/People';
import PersonAddIcon from '@mui/icons-material/PersonAdd';
import BusinessIcon from '@mui/icons-material/Business';
import AddBusinessIcon from '@mui/icons-material/AddBusiness';
import WorkIcon from '@mui/icons-material/Work';
import AddIcon from '@mui/icons-material/Add';
import BusinessCenterIcon from '@mui/icons-material/BusinessCenter';

interface LayoutProps {
  children: React.ReactNode;
}

const Layout: React.FC<LayoutProps> = ({ children }) => {
  const location = useLocation();

  const isActive = (path: string) => {
    return location.pathname.startsWith(path);
  };

  return (
    <Box sx={{ display: 'flex', flexDirection: 'column', minHeight: '100vh' }}>
      <AppBar position="static">
        <Toolbar>
          <Box 
            component={RouterLink} 
            to="/"
            sx={{ 
              display: 'flex', 
              alignItems: 'center', 
              textDecoration: 'none', 
              color: 'inherit',
              flexGrow: 1,
              gap: 1
            }}
          >
            <Avatar 
              sx={{ 
                bgcolor: 'secondary.main',
                width: 40,
                height: 40,
                display: 'flex',
                alignItems: 'center',
                justifyContent: 'center'
              }}
            >
              <BusinessCenterIcon />
            </Avatar>
            <Typography variant="h6" sx={{ fontWeight: 600 }}>
              HCM System
            </Typography>
          </Box>
          <Box sx={{ display: 'flex', gap: 2 }}>
            <Button 
              color="inherit" 
              component={RouterLink} 
              to="/"
              startIcon={<DashboardIcon />}
              sx={{ 
                backgroundColor: isActive('/') && location.pathname === '/' ? 'rgba(255, 255, 255, 0.1)' : 'transparent',
                '&:hover': {
                  backgroundColor: 'rgba(255, 255, 255, 0.1)',
                }
              }}
            >
              Dashboard
            </Button>
            <Button 
              color="inherit" 
              component={RouterLink} 
              to="/employees"
              startIcon={<PeopleIcon />}
              sx={{ 
                backgroundColor: isActive('/employees') ? 'rgba(255, 255, 255, 0.1)' : 'transparent',
                '&:hover': {
                  backgroundColor: 'rgba(255, 255, 255, 0.1)',
                }
              }}
            >
              Employees
            </Button>
            <Button 
              color="inherit" 
              component={RouterLink} 
              to="/jobs"
              startIcon={<WorkIcon />}
              sx={{ 
                backgroundColor: isActive('/jobs') ? 'rgba(255, 255, 255, 0.1)' : 'transparent',
                '&:hover': {
                  backgroundColor: 'rgba(255, 255, 255, 0.1)',
                }
              }}
            >
              Jobs
            </Button>
            {/* <Button 
              color="inherit" 
              component={RouterLink} 
              to="/jobs/new"
              startIcon={<AddIcon />}
              sx={{ 
                backgroundColor: isActive('/jobs/new') ? 'rgba(255, 255, 255, 0.1)' : 'transparent',
                '&:hover': {
                  backgroundColor: 'rgba(255, 255, 255, 0.1)',
                }
              }}
            >
              Add Job
            </Button> */}
            <Button 
              color="inherit" 
              component={RouterLink} 
              to="/vendors"
              startIcon={<BusinessIcon />}
              sx={{ 
                backgroundColor: isActive('/vendors') ? 'rgba(255, 255, 255, 0.1)' : 'transparent',
                '&:hover': {
                  backgroundColor: 'rgba(255, 255, 255, 0.1)',
                }
              }}
            >
              Vendors
            </Button>
            {/* <Button 
              color="inherit" 
              component={RouterLink} 
              to="/vendors/new"
              startIcon={<AddBusinessIcon />}
              sx={{ 
                backgroundColor: isActive('/vendors/new') ? 'rgba(255, 255, 255, 0.1)' : 'transparent',
                '&:hover': {
                  backgroundColor: 'rgba(255, 255, 255, 0.1)',
                }
              }}
            >
              Add Vendor
            </Button> */}
          </Box>
        </Toolbar>
      </AppBar>
      <Container maxWidth="lg" sx={{ mt: 4, flex: 1 }}>
        {children}
      </Container>
      <Box 
        component="footer" 
        sx={{ 
          py: 3, 
          px: 2, 
          mt: 'auto', 
          backgroundColor: (theme) => theme.palette.grey[100]
        }}
      >
        <Container maxWidth="lg">
          <Typography variant="body2" color="text.secondary" align="center">
            © {new Date().getFullYear()} HCM System. All rights reserved.
          </Typography>
        </Container>
      </Box>
    </Box>
  );
};

export default Layout; 