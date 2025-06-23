import React from 'react';
import {
  Box,
  Card,
  CardContent,
  Typography,
  Paper,
  IconButton,
  useTheme,
  LinearProgress,
  Tooltip,
  Grid,
  Button,
  Menu,
  MenuItem,
  ListItemIcon,
  ListItemText,
  Divider,
  Chip,
  Avatar,
  CardHeader,
  CardActions,
  useMediaQuery,
} from '@mui/material';
import {
  BarChart,
  Bar,
  XAxis,
  YAxis,
  CartesianGrid,
  Tooltip as RechartsTooltip,
  Legend,
  ResponsiveContainer,
  PieChart,
  Pie,
  Cell,
  LineChart,
  Line,
  TooltipProps,
} from 'recharts';
import {
  People as PeopleIcon,
  Work as WorkIcon,
  Event as EventIcon,
  AttachMoney as MoneyIcon,
  MoreVert as MoreVertIcon,
  TrendingUp as TrendingUpIcon,
  Business as BusinessIcon,
  Assignment as AssignmentIcon,
  Payment as PaymentIcon,
  Assessment as AssessmentIcon,
  Settings as SettingsIcon,
  Add as AddIcon,
  List as ListIcon,
  ArrowForward as ArrowForwardIcon,
  PostAdd as PostAddIcon,
  Person as PersonIcon,
  WorkOutline as WorkOutlineIcon,
  Description as DescriptionIcon,
  Store as StoreIcon,
  Task as TaskIcon,
  AccountBalance as AccountBalanceIcon,
  InsertChart as InsertChartIcon,
  Tune as TuneIcon,
  Dashboard as DashboardIcon,
  Update as UpdateIcon,
} from '@mui/icons-material';
import { useNavigate } from 'react-router-dom';

interface Module {
  id: string;
  title: string;
  description: string;
  icon: React.ReactNode;
  path: string;
}

interface ChartData {
  name: string;
  value: number;
}

interface CustomTooltipProps extends TooltipProps<number, string> {
  payload?: Array<{
    name: string;
    value: number;
  }>;
}

const CustomTooltip: React.FC<CustomTooltipProps> = ({ payload }) => {
  if (payload && payload[0]) {
    return (
      <Box sx={{ p: 1, backgroundColor: 'white', borderRadius: 1, boxShadow: 1 }}>
        <Typography variant="body2">
          {payload[0].name}: {payload[0].value}
        </Typography>
      </Box>
    );
  }
  return null;
};

const Dashboard: React.FC = () => {
  const theme = useTheme();
  const navigate = useNavigate();
  const isMobile = useMediaQuery(theme.breakpoints.down('sm'));
  const [employeeAnchorEl, setEmployeeAnchorEl] = React.useState<null | HTMLElement>(null);
  const [vendorAnchorEl, setVendorAnchorEl] = React.useState<null | HTMLElement>(null);

  const handleEmployeeClick = (event: React.MouseEvent<HTMLElement>) => {
    setEmployeeAnchorEl(event.currentTarget);
  };

  const handleVendorClick = (event: React.MouseEvent<HTMLElement>) => {
    setVendorAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setEmployeeAnchorEl(null);
    setVendorAnchorEl(null);
  };

  const handleNavigation = (path: string) => {
    navigate(path);
    handleClose();
  };

  const modules: Module[] = [
    {
      id: 'employees',
      title: 'Employees',
      description: 'Manage employee information, attendance, and performance',
      icon: <PersonIcon fontSize="large" />,
      path: '/employees',
    },
    {
      id: 'jobs',
      title: 'Jobs',
      description: 'Create and manage job postings',
      icon: <WorkOutlineIcon fontSize="large" />,
      path: '/jobs',
    },
    {
      id: 'job-postings',
      title: 'Job Postings',
      description: 'View and manage active job postings',
      icon: <DescriptionIcon fontSize="large" />,
      path: '/jobs',
    },
    {
      id: 'vendors',
      title: 'Vendors',
      description: 'Manage vendor relationships and contracts',
      icon: <StoreIcon fontSize="large" />,
      path: '/vendors',
    },
    {
      id: 'tasks',
      title: 'Tasks',
      description: 'Assign and track employee tasks and projects',
      icon: <TaskIcon fontSize="large" />,
      path: '/tasks',
    },
    {
      id: 'payroll',
      title: 'Payroll',
      description: 'Process payroll and manage employee compensation',
      icon: <AccountBalanceIcon fontSize="large" />,
      path: '/payroll',
    },
    {
      id: 'reports',
      title: 'Reports',
      description: 'Generate and view various HR reports',
      icon: <InsertChartIcon fontSize="large" />,
      path: '/reports',
    },
    {
      id: 'settings',
      title: 'Settings',
      description: 'Configure system settings and preferences',
      icon: <TuneIcon fontSize="large" />,
      path: '/settings',
    },
  ];

  const departmentData: ChartData[] = [
    { name: 'Engineering', value: 30 },
    { name: 'Marketing', value: 20 },
    { name: 'Sales', value: 25 },
    { name: 'HR', value: 15 },
    { name: 'Finance', value: 10 },
  ];

  const leaveData: ChartData[] = [
    { name: 'Approved', value: 45 },
    { name: 'Pending', value: 15 },
    { name: 'Rejected', value: 5 },
  ];

  const revenueData: ChartData[] = [
    { name: 'Jan', value: 4000 },
    { name: 'Feb', value: 3000 },
    { name: 'Mar', value: 5000 },
    { name: 'Apr', value: 4500 },
    { name: 'May', value: 6000 },
    { name: 'Jun', value: 5500 },
  ];

  const COLORS = ['#0088FE', '#00C49F', '#FFBB28', '#FF8042', '#8884D8'];

  return (
    <Box sx={{ p: { xs: 2, md: 3 }, maxWidth: '1400px', margin: '0 auto' }}>
      <Paper 
        elevation={0} 
        sx={{ 
          p: 2, 
          mb: 3, 
          borderRadius: 2,
          background: `linear-gradient(135deg, ${theme.palette.primary.main} 0%, ${theme.palette.primary.dark} 100%)`,
          color: 'white',
        }}
      >
        <Box sx={{ 
          display: 'flex', 
          flexDirection: { xs: 'column', md: 'row' }, 
          alignItems: { xs: 'flex-start', md: 'center' },
          gap: 1
        }}>
          <Box sx={{ flex: 1 }}>
            <Box sx={{ display: 'flex', alignItems: 'center', mb: { xs: 1, md: 0 } }}>
              <DashboardIcon sx={{ fontSize: 32, mr: 1.5 }} />
              <Box>
                <Typography variant="h5" sx={{ fontWeight: 600, mb: 0.5 }}>
                  Dashboard
                </Typography>
                <Typography variant="body2" sx={{ opacity: 0.8 }}>
                  Welcome to your HR management dashboard
                </Typography>
              </Box>
            </Box>
          </Box>
          <Box sx={{ 
            display: 'flex', 
            justifyContent: { xs: 'flex-start', md: 'flex-end' }, 
            alignItems: 'center' 
          }}>
            <Chip 
              icon={<UpdateIcon />} 
              label={`Last updated: ${new Date().toLocaleDateString()}`} 
              size="small"
              sx={{ 
                bgcolor: 'rgba(255, 255, 255, 0.2)', 
                color: 'white',
                '& .MuiChip-icon': { color: 'white' }
              }} 
            />
          </Box>
        </Box>
      </Paper>

      <Box
        sx={{
          display: 'grid',
          gridTemplateColumns: {
            xs: '1fr',
            sm: 'repeat(2, 1fr)',
            md: 'repeat(3, 1fr)',
            lg: 'repeat(4, 1fr)',
          },
          gap: 2,
          mb: 4,
        }}
      >
        {modules.map((module) => (
          <Card 
            key={module.id} 
            sx={{ 
              height: '100%',
              display: 'flex',
              flexDirection: 'column',
              transition: 'all 0.3s ease',
              '&:hover': {
                transform: 'translateY(-4px)',
                boxShadow: theme.shadows[8],
              },
            }}
          >
            <CardContent sx={{ p: 2, display: 'flex', flexDirection: 'column', flexGrow: 1 }}>
              <Box sx={{ display: 'flex', alignItems: 'center', mb: 1 }}>
                <Box 
                  sx={{ 
                    mr: 1.5,
                    p: 0.75,
                    borderRadius: 1,
                    color: theme.palette.primary.main,
                  }}
                >
                  {module.icon}
                </Box>
                <Typography variant="h6" component="h2" sx={{ fontWeight: 600, fontSize: '1rem' }}>
                  {module.title}
                </Typography>
              </Box>
              <Typography variant="body2" color="text.secondary" sx={{ mb: 2, fontSize: '0.8rem', flexGrow: 1 }}>
                {module.description}
              </Typography>
              {module.id === 'employees' ? (
                <>
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={handleEmployeeClick}
                    fullWidth
                    endIcon={<ArrowForwardIcon />}
                    sx={{ 
                      textTransform: 'none',
                      py: 1,
                      borderRadius: 2,
                      fontSize: '0.8rem',
                      mt: 'auto',
                    }}
                  >
                    Manage Employees
                  </Button>
                  <Menu
                    anchorEl={employeeAnchorEl}
                    open={Boolean(employeeAnchorEl)}
                    onClose={handleClose}
                    PaperProps={{
                      sx: {
                        mt: 1,
                        minWidth: 200,
                      },
                    }}
                  >
                    <MenuItem 
                      onClick={() => handleNavigation('/employees')}
                      sx={{ py: 1 }}
                    >
                      <ListItemIcon>
                        <ListIcon fontSize="small" />
                      </ListItemIcon>
                      <ListItemText>View Employee List</ListItemText>
                    </MenuItem>
                    <MenuItem 
                      onClick={() => handleNavigation('/employees/new')}
                      sx={{ py: 1 }}
                    >
                      <ListItemIcon>
                        <AddIcon fontSize="small" />
                      </ListItemIcon>
                      <ListItemText>Add New Employee</ListItemText>
                    </MenuItem>
                  </Menu>
                </>
              ) : module.id === 'vendors' ? (
                <>
                  <Button
                    variant="contained"
                    color="primary"
                    onClick={handleVendorClick}
                    fullWidth
                    endIcon={<ArrowForwardIcon />}
                    sx={{ 
                      textTransform: 'none',
                      py: 1,
                      borderRadius: 2,
                      fontSize: '0.8rem',
                      mt: 'auto',
                    }}
                  >
                    Manage Vendors
                  </Button>
                  <Menu
                    anchorEl={vendorAnchorEl}
                    open={Boolean(vendorAnchorEl)}
                    onClose={handleClose}
                    PaperProps={{
                      sx: {
                        mt: 1,
                        minWidth: 200,
                      },
                    }}
                  >
                    <MenuItem 
                      onClick={() => handleNavigation('/vendors')}
                      sx={{ py: 1 }}
                    >
                      <ListItemIcon>
                        <ListIcon fontSize="small" />
                      </ListItemIcon>
                      <ListItemText>View Vendor List</ListItemText>
                    </MenuItem>
                    <MenuItem 
                      onClick={() => handleNavigation('/vendors/new')}
                      sx={{ py: 1 }}
                    >
                      <ListItemIcon>
                        <AddIcon fontSize="small" />
                      </ListItemIcon>
                      <ListItemText>Add New Vendor</ListItemText>
                    </MenuItem>
                  </Menu>
                </>
              ) : (
                <Button
                  variant="contained"
                  color="primary"
                  onClick={() => navigate(module.path)}
                  fullWidth
                  endIcon={<ArrowForwardIcon />}
                  sx={{ 
                    textTransform: 'none',
                    py: 1,
                    borderRadius: 2,
                    fontSize: '0.8rem',
                    mt: 'auto',
                  }}
                >
                  Open {module.title}
                </Button>
              )}
            </CardContent>
          </Card>
        ))}
      </Box>

      {/* Charts */}
      <Box sx={{ 
        display: 'flex', 
        flexDirection: { xs: 'column', md: 'row' }, 
        gap: 3, 
        mb: 4 
      }}>
        <Box sx={{ flex: 1 }}>
          <Paper 
            sx={{ 
              p: 3,
              height: 400,
              borderRadius: 2,
              boxShadow: theme.shadows[2],
              overflow: 'hidden',
            }}
          >
            <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 2 }}>
              <Typography variant="h6" sx={{ fontWeight: 600 }}>
                Department Distribution
              </Typography>
              <Chip 
                label="Employee Data" 
                size="small" 
                sx={{ 
                  bgcolor: theme.palette.primary.light,
                  color: theme.palette.primary.main,
                }} 
              />
            </Box>
            <Divider sx={{ mb: 2 }} />
            <Box sx={{ height: 'calc(100% - 80px)' }}>
              <ResponsiveContainer width="100%" height="100%">
                <PieChart>
                  <Pie
                    data={departmentData}
                    cx="50%"
                    cy="50%"
                    labelLine={false}
                    label={({ name, percent }) => `${name} ${(percent * 100).toFixed(0)}%`}
                    outerRadius={80}
                    fill="#8884d8"
                    dataKey="value"
                  >
                    {departmentData.map((entry, index) => (
                      <Cell key={`cell-${index}`} fill={COLORS[index % COLORS.length]} />
                    ))}
                  </Pie>
                  <RechartsTooltip />
                </PieChart>
              </ResponsiveContainer>
            </Box>
          </Paper>
        </Box>

        <Box sx={{ flex: 1 }}>
          <Paper 
            sx={{ 
              p: 3,
              height: 400,
              borderRadius: 2,
              boxShadow: theme.shadows[2],
              overflow: 'hidden',
            }}
          >
            <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 2 }}>
              <Typography variant="h6" sx={{ fontWeight: 600 }}>
                Leave Status
              </Typography>
              <Chip 
                label="Attendance Data" 
                size="small" 
                sx={{ 
                  bgcolor: theme.palette.secondary.light,
                  color: theme.palette.secondary.main,
                }} 
              />
            </Box>
            <Divider sx={{ mb: 2 }} />
            <Box sx={{ height: 'calc(100% - 80px)' }}>
              <ResponsiveContainer width="100%" height="100%">
                <BarChart data={leaveData}>
                  <CartesianGrid strokeDasharray="3 3" />
                  <XAxis dataKey="name" />
                  <YAxis />
                  <RechartsTooltip />
                  <Legend />
                  <Bar dataKey="value" fill={theme.palette.primary.main} />
                </BarChart>
              </ResponsiveContainer>
            </Box>
          </Paper>
        </Box>
      </Box>

      {/* Revenue Chart */}
      <Paper 
        sx={{ 
          p: 3, 
          height: '400px', 
          borderRadius: 2,
          boxShadow: theme.shadows[2],
          overflow: 'hidden',
        }}
      >
        <Box sx={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', mb: 2 }}>
          <Typography variant="h6" sx={{ fontWeight: 600 }}>
            Revenue Trend
          </Typography>
          <Chip 
            label="Financial Data" 
            size="small" 
            sx={{ 
              bgcolor: theme.palette.success.light,
              color: theme.palette.success.main,
            }} 
          />
        </Box>
        <Divider sx={{ mb: 2 }} />
        <Box sx={{ height: 'calc(100% - 80px)' }}>
          <ResponsiveContainer width="100%" height="100%">
            <LineChart data={revenueData}>
              <CartesianGrid strokeDasharray="3 3" stroke={theme.palette.divider} />
              <XAxis 
                dataKey="name" 
                tick={{ fill: theme.palette.text.primary }}
              />
              <YAxis 
                tick={{ fill: theme.palette.text.primary }}
              />
              <RechartsTooltip 
                contentStyle={{ 
                  backgroundColor: theme.palette.background.paper,
                  border: `1px solid ${theme.palette.divider}`,
                  borderRadius: 4,
                }}
              />
              <Legend />
              <Line 
                type="monotone" 
                dataKey="value" 
                stroke={theme.palette.primary.main}
                strokeWidth={2}
                dot={{ r: 4 }}
                activeDot={{ r: 6 }}
              />
            </LineChart>
          </ResponsiveContainer>
        </Box>
      </Paper>
    </Box>
  );
};

export default Dashboard; 