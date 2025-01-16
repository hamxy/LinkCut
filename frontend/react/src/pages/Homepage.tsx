// src/pages/Homepage.tsx

import { useEffect, useState } from 'react';
import axios from 'axios';

type ApiResponse = {
  message: string;
};

const Homepage = () => {
  const [data, setData] = useState<ApiResponse | null>(null);
  const [loading, setLoading] = useState<boolean>(true);  // Track loading state
  const [error, setError] = useState<string | null>(null); // Track error state

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await axios.get<ApiResponse>('http://localhost:8080/api/'); // Make sure the URL is correct
        setData(response.data);
        console.log(response.data);
      } catch (error) {
        setError('Failed to fetch data');
      } finally {
        setLoading(false);  // Stop loading once request is finished
      }
    };

    fetchData();
  }, []);

  return (
    <div>
      <h1>Homepage</h1>
      {loading && <p>Loading...</p>}
      {error && <p>{error}</p>}
      {data && <p>{data.message}</p>}
    </div>
  );
};

export default Homepage;