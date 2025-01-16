// src/routes.tsx

import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Homepage from './pages/Homepage';

const RoutesComponent = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Homepage />} />
        {/* Other routes */}
      </Routes>
    </Router>
  );
};

export default RoutesComponent;