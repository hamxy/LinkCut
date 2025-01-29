import { useState } from "react";
import styled from "styled-components";

// Styled components for the form, input, and button
const Form = styled.form`
  display: flex; // Flexbox layout to arrange input and button horizontally
  border-radius: 30px;
  box-sizing: border-box;
`;

const Input = styled.input`
  flex-grow: 1;
  color: var(--font-color);
  border: 1px;
  background-color: #ffffff;
  padding: 1rem;
  border-radius: 30px 0 0 30px;
  font-size: 1rem;
  box-shadow: inset 0px 4px 4px 0px rgba(9, 11, 51, 0.2); // Inner shadow effect
`;

const Button = styled.button`
  border: none;
  background-color: var(--secondary-color);
  color: #ffffff;
  font-size: 1rem;
  cursor: pointer;
  padding: 1rem 1.5rem;
  padding-right: 2rem;
  border-radius: 0 30px 30px 0;
  transition: background-color 0.3s ease;
`;

/**
 * Interface for SearchBarProps
 * onSubmit: Function passed as prop to handle form submission
 */
interface SearchBarProps {
  onSubmit: (value: string) => void; // Function to handle the submitted value (link)
}

const SearchBar = ({ onSubmit }: SearchBarProps) => {
  // State to store the value entered by the user
  const [link, setLink] = useState("");

  // Handle form submission
  const handleSubmit = (event: React.FormEvent<HTMLFormElement>) => {
    event.preventDefault(); // Prevent default form submission behavior (page reload)
    onSubmit(link); // Pass the current link to the parent component via the onSubmit prop
  };

  return (
    <Form onSubmit={handleSubmit}>
      <Input
        type="text"
        value={link}
        onChange={(e) => setLink(e.target.value)} // Update the state when user types in the input field
        placeholder="https://www.example.com/long-link"
        aria-label="Enter the URL you want to shorten" // Accessibility label for screen readers
      />
      <Button type="submit">Shorten</Button>
    </Form>
  );
};

export default SearchBar;
