import React, { useState } from 'react';
export function AsyncMessageFetcher() {
  const [message, setMessage] = useState('');
  const [error, setError] = useState('');
  const fetchMessage = () => {
    setMessage('');
    setError('');
    fetch('https://jsonplaceholder.typicode.com/todos/1')
      .then(res => res.json())
      .then(data => setMessage(data.title))
      .catch(() => setError('Nie udało się pobrać wiadomości'));
  };
  return (
    <div>
      <button onClick={fetchMessage}>Pobierz wiadomość</button>
      {message && <p data-testid="message-display">{message}</p>}
      {error && <p role="alert">{error}</p>}
    </div>
  );
}