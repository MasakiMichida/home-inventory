import { useState, useEffect } from 'react'

function App() {
  const [text, setText] = useState("loading...");

  useEffect(()=>{
    fetch("/api/health")
    .then((r) => r.text())
    .then(setText)
  },[]);

  return (
    <div>
      {text}
    </div>
  );
}

export default App
