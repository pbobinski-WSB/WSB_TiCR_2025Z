import React, { useState } from 'react';
export function Counter() {
  const [count, setCount] = useState(0);
  return (
    <div>
      <p>Wartość licznika: {count}</p>
      <button onClick={() => setCount(count + 1)}>Zwiększ</button>
    </div>
  );
}