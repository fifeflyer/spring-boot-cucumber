import {Component} from "react";
import logo from './logo.svg';
import './App.css';

class App extends Component {
  state = {
    isLoading: true,
    customers: []
  };

  componentDidMount = async () => {
    const response = await fetch('../api/v1/customers');
    const body = await response.json();

    this.setState({
      customers: body,
      isLoading: false
    });
  };

  render = () => {
    const {customers, isLoading} = this.state;

    if (isLoading) {
      return <p>Loading...</p>;
    }

    return (
        <div className="App">
          <header className="App-header">
            <img src={logo} className="App-logo" alt="logo"/>
            <a
                className="App-link"
                href="https://reactjs.org"
                target="_blank"
                rel="noopener noreferrer"
            >
              Learn React
            </a>
            <h2>Customers</h2>
            {customers.map(customer =>
                <div key={customer.id}>
                  {customer.name}
                </div>
            )}
          </header>
        </div>
    );
  }
}

export default App;
