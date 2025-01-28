import styled from "styled-components";

const Form = styled.form`
  display: flex;
  border-radius: 30px;
  box-sizing: border-box;
`;

const Input = styled.input`
  flex-grow: 1;
  color: #090b33;
  border: 1px;
  background-color: #ffffff;
  padding: 1rem;
  border-radius: 30px 0 0 30px;
  font-size: 1rem;
  box-shadow: inset 0px 4px 4px 0px rgba(9, 11, 51, 0.2);
`;

const Button = styled.button`
  border: none;
  background-color: #ff7e3e;
  color: #ffffff;
  font-size: 1rem;
  cursor: pointer;
  padding: 1rem 1.5rem;
  padding-right: 2rem;
  border-radius: 0 30px 30px 0;
  border: 1px solid #ff7e3e;
  transition: background-color 0.3s ease;
`;

const SearchBar = () => {
  return (
    <Form>
      <Input
        type="text"
        placeholder="https://www.example.com/long-link"
        aria-label="Enter the URL you want to shorten"
      />
      <Button type="submit">Shorten</Button>
    </Form>
  );
};

export default SearchBar;
