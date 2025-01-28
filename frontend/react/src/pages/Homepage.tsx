import SearchBar from "../components/SearchBar";
import styled from "styled-components";

const Main = styled.main`
  max-width: 768px;
  margin: auto;
  text-align: center;
`;
const Description = styled.p`
  font-size: 1.2rem;
  margin-bottom: 2rem;
`;
const Label = styled.p`
  font-size: 1.2rem;
  font-weight: 500;
  margin: 3rem auto 0.7rem 1rem;
  text-align: left;
`;
const SearchBarStyled = styled(SearchBar)`
  margin-top: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
`;

const Homepage = () => {
  return (
    <Main>
      <h1>Simplify Your Links</h1>
      <Description>
        Easily shorten and manage your URLs while tracking their performance in
        real-time. Powered by Google Safe Browsing API, we ensure your links are
        protected from malware and phishing risks.
      </Description>
      <Label>Paste your link:</Label>
      <SearchBarStyled />
    </Main>
  );
};

export default Homepage;
