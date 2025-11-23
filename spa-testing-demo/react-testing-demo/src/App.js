import { Counter } from './components/Counter';
import { Greeter } from './components/Greeter';
import { AsyncMessageFetcher } from './components/AsyncMessageFetcher';
import './App.css';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <Greeter name="Studencie" />
        <hr />
        <Counter />
        <hr />
        <AsyncMessageFetcher />
      </header>
    </div>
  );
}
export default App;