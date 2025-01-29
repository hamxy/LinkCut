// src/routes.tsx

import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import Homepage from "./pages/Homepage";
import CreatedLink from "./pages/CreatedLink";

const RoutesComponent = () => {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Homepage />} />
        <Route path="/link/:id" element={<CreatedLink />} />
      </Routes>
    </Router>
  );
};

export default RoutesComponent;
