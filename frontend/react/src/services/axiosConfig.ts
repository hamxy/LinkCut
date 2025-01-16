import axios from 'axios';

// Set up the base URL for all API requests
const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080/api/',
  timeout: 2000,  // Set a timeout limit for requests
  headers: {
    'Content-Type': 'application/json',
  },
});


// axiosInstance.interceptors.request.use(
//   (config) => {
//     // Modify request if necessary, e.g., adding authentication token
//     const token = localStorage.getItem('authToken');
//     if (token) {
//       config.headers['Authorization'] = `Bearer ${token}`;
//     }
//     return config;
//   },
//   (error) => {
//     return Promise.reject(error);
//   }
// );

// axiosInstance.interceptors.response.use(
//   (response) => response,
//   (error) => {
//     // Handle response errors globally
//     if (error.response && error.response.status === 401) {
//       // Redirect to login if unauthorized (optional)
//       console.error('Unauthorized access');
//     }
//     return Promise.reject(error);
//   }
// );

export default axiosInstance;