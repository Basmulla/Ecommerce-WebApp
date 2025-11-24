import { useState } from "react";
import { login } from "../api/auth";
import Navbar from "../components/Navbar";

export default function Login() {
  const [form, setForm] = useState({ email: "", password: "" });

  async function handleSubmit(e) {
    e.preventDefault();

    try {
      const user = await login(form);
      alert("Login successful: " + user.name);
    } catch (err) {
      alert("Login failed");
    }
  }

  return (
    <div>
      <Navbar />
      <h2>Login</h2>

      <form onSubmit={handleSubmit}>
        <input
          placeholder="Email"
          onChange={(e) => setForm({ ...form, email: e.target.value })}
        /><br />

        <input
          type="password"
          placeholder="Password"
          onChange={(e) => setForm({ ...form, password: e.target.value })}
        /><br />

        <button>Login</button>
      </form>
    </div>
  );
}
