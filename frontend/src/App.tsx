import React, { useState, useEffect } from 'react'

function App() {
  type Item = {
    id: number,
    name: string
    quantity: number
    createdAt: string
    updatedAt: string
  };

  const [items, setItems] = useState<Item[]>([]);
  const [name, setName] = useState("");
  const [strQuantity, setStrQuantity] = useState<string>("");
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    itemLoading()
      .catch((e) => {
        console.error(e);
        setError(e ? e.message : "通信エラーが発生しました");
      })
  }, []);

  function onSubmit(e: React.FormEvent<HTMLFormElement>) {

    e.preventDefault();

    if (strQuantity?.trim() === "") {
      setError("数値を入力してください");
      return;
    }

    const numQuantity: number = Number(strQuantity);

    return fetch("/api/items", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ name, quantity: numQuantity })
    })
      .then((response) => {
        if (!response.ok) {
          return response.text().then((bodyText) => {
            throw new Error(`${bodyText || response.status}`);
          })
        }
      })
      .then(() => {
        itemLoading();
        setName("");
        setStrQuantity("");
      })
      .catch((e) => {
        console.error(e);
        setError(e ? e.message : "通信エラーが発生しました");
      })
  }

  function itemLoading() {
    return fetch("/api/items")
      .then((response) => {
        if (!response.ok) {
          return response.text().then((bodyText) => {
            throw new Error(`${bodyText || response.status}`);
          })
        }
        return response.json();
      })
      .then(setItems)
  }

  return (
    <div>
      {error && <p>{error}</p>}
      <form onSubmit={onSubmit}>
        <input
          placeholder='name'
          value={name} onChange={(e) => { setName(e.target.value) }}
        />
        <input
          placeholder='number'
          value={strQuantity}
          onChange={(e) => { setStrQuantity(e.target.value) }}
        />

        <button type='submit' disabled={!name}>追加</button>
      </form>
      <ul>
        {items.map((item) => {
          return (
            <li key={item.id}>
              {item.name}:
              {item.quantity}個
              {item.createdAt}
              {item.updatedAt}
            </li>
          );
        })}
      </ul>
    </div>
  );
}

export default App