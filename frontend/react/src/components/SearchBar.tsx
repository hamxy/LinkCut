import { useState } from "react";
import { Form, Input, Button } from "./SearchBar.styles";

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
