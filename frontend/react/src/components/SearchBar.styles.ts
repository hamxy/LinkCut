import styled from "styled-components";

/**
 * Styles 
 */

export const Form = styled.form`
  display: flex;
  border-radius: 30px;
  box-sizing: border-box;
`;

export const Input = styled.input`
  flex-grow: 1;
  color: var(--font-color);
  border: 1px;
  background-color: #ffffff;
  padding: 1rem;
  border-radius: 30px 0 0 30px;
  font-size: 1rem;
  box-shadow: inset 0px 4px 4px 0px rgba(9, 11, 51, 0.2);
`;

export const Button = styled.button`
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
